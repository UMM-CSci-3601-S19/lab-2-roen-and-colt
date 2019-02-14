package umm3601.todos;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import spark.Request;
import spark.Response;
import umm3601.user.Database;

import static umm3601.Util.buildFailJsonResponse;
import static umm3601.Util.buildSuccessJsonResponse;

public class TodoController {

    private final Gson gson;
    private Database database;

    /**
     * Construct a controller for users.
     * <p>
     * This loads the "database" of user info from a JSON file and
     * stores that internally so that (subsets of) users can be returned
     * in response to requests.
     *
     * @param database the database containing user data
     */
    public TodoController(Database database) {
      gson = new Gson();
      this.database = database;
    }


    /**
     * Get a JSON response with a list of all the users in the "database".
     *
     * @param req the HTTP request
     * @param res the HTTP response
     * @return a success JSON object containing all the users
     */


    public JsonObject getTodos(Request req, Response res) {
      res.type("application/json");
      Todo[] todos = database.listTodos();
      return buildSuccessJsonResponse("todos", gson.toJsonTree(todos));
    }
}
