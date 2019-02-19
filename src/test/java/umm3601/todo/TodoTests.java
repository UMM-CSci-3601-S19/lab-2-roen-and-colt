package umm3601.todo;

import org.junit.Test;
import umm3601.todos.Todo;
import umm3601.user.Database;
import umm3601.user.User;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


import static junit.framework.TestCase.assertEquals;

/**
 * Tests umm3601.user.Database filterUsersByAge
 * and listUsers with _age_ query parameters
 */
public class TodoTests {

  @Test
  public void filterTodosByMax() throws IOException {
    Database db = new Database("src/main/data/users.json", "src/main/data/todos.json");
    Map<String, String[]> queryParams = new HashMap<>();

    queryParams.put("limit", new String[]{"3"});
    Todo[] limit3Todos = db.listTodos(queryParams);
    assertEquals(3, limit3Todos.length);
  }

  @Test
  public void filterTodosByStatus() throws IOException {
    Database db = new Database("src/main/data/users.json", "src/main/data/todos.json");
    Map<String, String[]> queryParams = new HashMap<>();
    queryParams.put("status", new String[]{"complete"});
    Todo[] completetodos = db.listTodos(queryParams);
    Todo filteredUsers = completetodos[21];
    assertEquals(true, filteredUsers.getStatus());

  }

  @Test
  public void filterTodosByGrocery() throws IOException {
    Database db = new Database("src/main/data/users.json", "src/main/data/todos.json");
    Map<String, String[]> queryParams = new HashMap<>();
    queryParams.put("category", new String[]{"groceries"});
    Todo[] completetodos = db.listTodos(queryParams);
    Todo filteredUsers = completetodos[3];
    assertEquals("groceries", filteredUsers.category);

  }

  //Multiple params
  @Test
  public void filterTodosByMultiple() throws IOException {
    Database db = new Database("src/main/data/users.json", "src/main/data/todos.json");
    Map<String, String[]> queryParams = new HashMap<>();
    queryParams.put("owner", new String[]{"Blanche"});
    queryParams.put("status", new String[]{"complete"});
    Todo[] completetodos = db.listTodos(queryParams);
    Todo filteredUsers = completetodos[3];
    assertEquals("Blanche", filteredUsers.owner);
    assertEquals(true, filteredUsers.getStatus());
  }

  @Test
  public void filterTodosByBlanche() throws IOException {
    Database db = new Database("src/main/data/users.json", "src/main/data/todos.json");
    Map<String, String[]> queryParams = new HashMap<>();
    queryParams.put("owner", new String[]{"Blanche"});
    Todo[] completetodos = db.listTodos(queryParams);
    Todo filteredUsers = completetodos[3];
    assertEquals("Blanche", filteredUsers.owner);
  }

  @Test
  public void filterTodosByString() throws IOException {
    Database db = new Database("src/main/data/users.json", "src/main/data/todos.json");
    Map<String, String[]> queryParams = new HashMap<>();
    queryParams.put("string", new String[]{"Ipsum"});
    Todo[] completetodos = db.listTodos(queryParams);
    Todo filteredUsers = completetodos[3];
    assertEquals(true, filteredUsers.body.contains("Ipsum"));
  }

  @Test
  public void filterOwnerTodosByAlphabetical() throws IOException {
    Database db = new Database("src/main/data/users.json", "src/main/data/todos.json");
    Map<String, String[]> queryParams = new HashMap<>();
    queryParams.put("alphabetical", new String[]{"owner"});
    Todo[] completetodos = db.listTodos(queryParams);
    Todo filteredUsers = completetodos[0];
    assertEquals(true, filteredUsers.owner.contains("Blanche")); //Checks if Blanche is the first in the filtered list
    //Currently a stub of what it should be, as the implementation for "alphabetical owner sorting" is not in yet.
  }

}


