package umm3601.user;


import com.google.gson.Gson;
import umm3601.todos.Todo;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

/**
 * A fake "database" of user info
 * <p>
 * Since we don't want to complicate this lab with a real database,
 * we're going to instead just read a bunch of user data from a
 * specified JSON file, and then provide various database-like
 * methods that allow the `UserController` to "query" the "database".
 */
public class Database {

  private User[] allUsers;
  private Todo[] allTodos;

  public Database(String userDataFile, String todoDataFile) throws IOException {
    Gson gson = new Gson();
    FileReader reader = new FileReader(userDataFile);
    FileReader todoreader = new FileReader(todoDataFile);

    allUsers = gson.fromJson(reader, User[].class);
    allTodos = gson.fromJson(todoreader, Todo[].class);

  }

  /**
   * Get the single user specified by the given ID. Return
   * `null` if there is no user with that ID.
   *
   * @param id the ID of the desired user
   * @return the user with the given ID, or null if there is no user
   * with that ID
   */
  public User getUser(String id) {
    return Arrays.stream(allUsers).filter(x -> x._id.equals(id)).findFirst().orElse(null);
  }

  /**
   * Get an array of all the users satisfying the queries in the params.
   *
   * @param queryParams map of required key-value pairs for the query
   * @return an array of all the users matching the given criteria
   */
  public User[] listUsers(Map<String, String[]> queryParams) {
    User[] filteredUsers = allUsers;

    // Filter age if defined
    if (queryParams.containsKey("age")) {
      int targetAge = Integer.parseInt(queryParams.get("age")[0]);
      filteredUsers = filterUsersByAge(filteredUsers, targetAge);
    }
    // Process other query parameters here...
    return filteredUsers;
  }

  public Todo[] listTodos(Map<String, String[]> queryParams) {
    Todo[] ListTodo = allTodos;
    Boolean status;
    if (queryParams.containsKey("substring")) {
      String[] substring = queryParams.get("substring");
      ListTodo = filterTodosBySubstring(ListTodo, substring[0]);
      System.out.println(substring[0]);
    }
    if (queryParams.containsKey("limit")) {
      int targetLimit = Integer.parseInt(queryParams.get("limit")[0]);
      ListTodo = filterTodosByMax(ListTodo, targetLimit);
    }
    if (queryParams.containsKey("status")){
      String[] statusString = queryParams.get("status");
      if(statusString[0].equals("complete")){
        status = true;
      }
      else {
        status = false;
      }
      ListTodo = filterTodosByStatus(ListTodo, status);
    }
    return ListTodo;
  }

  /**
   * Get an array of all the users having the target age.
   *
   * @param users     the list of users to filter by age
   * @param targetAge the target age to look for
   * @return an array of all the users from the given list that have
   * the target age
   */
  public User[] filterUsersByAge(User[] users, int targetAge) {
    return Arrays.stream(users).filter(x -> x.age == targetAge).toArray(User[]::new);
  }

  public Todo[] filterTodosByMax(Todo[] todos, int targetLimit) {
    Todo[] filteredTodos = new Todo[targetLimit];
    for(int i=0;i<targetLimit;i++){
      filteredTodos[i]=todos[i];
    }
  return filteredTodos;
  }

  public Todo[] filterTodosByStatus(Todo[] todos, Boolean status) {
    return Arrays.stream(todos).filter(x -> x.getStatus() == status).toArray(Todo[]::new);
  }
  public Todo[] filterTodosBySubstring(Todo[] todos, String substring) {
    return Arrays.stream(todos).filter
      (x -> x.getBody().toLowerCase().contains(substring.toLowerCase()) == true).toArray(Todo[]::new);
  }

}
