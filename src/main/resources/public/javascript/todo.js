// Why do we use the `var getAllUsers = function()` syntax
// for the first definition, and the named function syntax
// for the second definition?

/**
 * Function to get all the todos!
 */
function getAllTodos() {
  console.log("Getting all the todos.");

  var HttpThingy = new HttpClient();
  HttpThingy.get("/api/todos", function (returned_json) {
    document.getElementById('jsonDump').innerHTML = returned_json;
  });
}

function getAllTodosByMax() {
  console.log("Getting all the todos by maximum.");

  var HttpThingy = new HttpClient();
  HttpThingy.get("/api/todos?limit=" + document.getElementById("limit").value, function (returned_json) {
    document.getElementById('jsonDump').innerHTML = returned_json;
  });
}


function getTodosByString() {
  console.log("Retrieving todos by string.");

  var HttpThingy = new HttpClient();
  HttpThingy.get("/api/todos?string=" + document.getElementById("string").value, function (returned_json) {
    document.getElementById('jsonDump').innerHTML = returned_json;
  });
}

function getTodosByComplete() {
  console.log("Getting all the todos by completion status.");

  var HttpThingy = new HttpClient();
  HttpThingy.get("/api/todos?status=" + "complete", function (returned_json) {
    document.getElementById('jsonDump').innerHTML = returned_json;
  });
}

function getTodosByIncomplete() {
  console.log("Getting all the todos by completion status.");

  var HttpThingy = new HttpClient();
  HttpThingy.get("/api/todos?status=" + "incomplete", function (returned_json) {
    document.getElementById('jsonDump').innerHTML = returned_json;
  });
}
/**
 * Wrapper to make generating http requests easier. Should maybe be moved
 * somewhere else in the future!.
 *
 * Based on: http://stackoverflow.com/a/22076667
 * Now with more comments!
 */
function HttpClient() {
  // We'll take a URL string, and a callback function.
  this.get = function (aUrl, aCallback) {
    var anHttpRequest = new XMLHttpRequest();

    // Set a callback to be called when the ready state of our request changes.
    anHttpRequest.onreadystatechange = function () {

      /**
       * Only call our 'aCallback' function if the ready state is 'DONE' and
       * the request status is 200 ('OK')
       *
       * See https://httpstatuses.com/ for HTTP status codes
       * See https://developer.mozilla.org/en-US/docs/Web/API/XMLHttpRequest/readyState
       *  for XMLHttpRequest ready state documentation.
       *
       */
      if (anHttpRequest.readyState === 4 && anHttpRequest.status === 200)
        aCallback(anHttpRequest.responseText);
    };

    anHttpRequest.open("GET", aUrl, true);
    anHttpRequest.send(null);
  }
}
