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
  public void filterUsersByMax() throws IOException {
    Database db = new Database("src/main/data/users.json", "src/main/data/todos.json");
    Map<String, String[]> queryParams = new HashMap<>();

    queryParams.put("limit", new String[]{"3"});
    Todo[] limit3Todos = db.listTodos(queryParams);
    assertEquals(3, limit3Todos.length);
  }

  @Test
  public void filterUsersByStatus() throws IOException {
    Database db = new Database("src/main/data/users.json", "src/main/data/todos.json");
    Map<String, String[]> queryParams = new HashMap<>();
    queryParams.put("status", new String[]{"complete"});
    Todo[] completetodos = db.listTodos(queryParams);
    Todo filteredUsers = completetodos[21];
    assertEquals(true, filteredUsers.getStatus());

  }

  @Test
  public void filterUsersByGrocery() throws IOException {
    Database db = new Database("src/main/data/users.json", "src/main/data/todos.json");
    Map<String, String[]> queryParams = new HashMap<>();
    queryParams.put("category", new String[]{"groceries"});
    Todo[] completetodos = db.listTodos(queryParams);
    Todo filteredUsers = completetodos[3];
    assertEquals("groceries", filteredUsers.category);

  }

  //Multiple params
  @Test
  public void filterUsersByMultiple() throws IOException {
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
  public void filterUsersByBlanche() throws IOException {
    Database db = new Database("src/main/data/users.json", "src/main/data/todos.json");
    Map<String, String[]> queryParams = new HashMap<>();
    queryParams.put("owner", new String[]{"Blanche"});
    Todo[] completetodos = db.listTodos(queryParams);
    Todo filteredUsers = completetodos[3];
    assertEquals("Blanche", filteredUsers.owner);
  }

  @Test
  public void filterUsersByString() throws IOException {
    Database db = new Database("src/main/data/users.json", "src/main/data/todos.json");
    Map<String, String[]> queryParams = new HashMap<>();
    queryParams.put("string", new String[]{"Ipsum"});
    Todo[] completetodos = db.listTodos(queryParams);
    Todo filteredUsers = completetodos[3];
    assertEquals(true, filteredUsers.body.contains("Ipsum"));
  }
}


