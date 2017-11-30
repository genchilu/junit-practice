package url.genchi.practice.db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

/**
 * Created by genchi.lu on 2017/5/26.
 */
public class TodoListDao {
  private JDBCSource jdbcsource;
  final private String findAllStr = "SELECT * FROM TODOLIST";
  final private String findOneByIdStr = "SELECT * FROM TODOLIST WHERE ID=?";
  final private String saveUserStr = "INSERT INTO TODOLIST (ID, DESCRIPTION) VALUES (?, ?)";
  final private String updateUserStr = "UPDATE TODOLIST SET DESCRIPTION = ?";
  final private String deleteUserStr = "DELETE FROM TODOLIST WHERE ID =?";

  public TodoListDao(JDBCSource jdbcSource){
    this.jdbcsource = jdbcSource;
  }
  public void saveTodoList(TodoList tdl) throws SQLException {
    Connection conn = jdbcsource.getConn();
    PreparedStatement ps = conn.prepareStatement(saveUserStr);
    ps.setString(1, tdl.getId().toString());
    ps.setString(2, tdl.getDescription());
    ps.execute();
    conn.commit();
  }
  public void updateTodolist(TodoList tdl) throws SQLException {
    Connection conn = jdbcsource.getConn();
    PreparedStatement ps = conn.prepareStatement(updateUserStr);
    ps.setString(1,tdl.getDescription());
    ps.execute();
    conn.commit();
  }
  public TodoList findOneById(String id) throws SQLException {
    Connection conn = jdbcsource.getConn();
    PreparedStatement ps = conn.prepareStatement(findOneByIdStr);
    ps.setString(1, id);
    ResultSet rs = ps.executeQuery();
    if(rs.next() == false ) {
      return null;
    }
    UUID uuid = UUID.fromString(rs.getString("ID"));
    String desc = rs.getString("DESCRIPTION");
    TodoList tol = new TodoList(uuid, desc);
    return tol;
  }
  public List<TodoList> findAll() throws SQLException {
    Connection conn = jdbcsource.getConn();
    PreparedStatement ps = conn.prepareStatement(findAllStr);
    ResultSet rs = ps.executeQuery();
    LinkedList<TodoList> tolList = new LinkedList<TodoList>();
    while(rs.next()) {
      UUID uuid = UUID.fromString(rs.getString("ID"));
      String desc = rs.getString("DESCRIPTION");
      TodoList tol = new TodoList(uuid, desc);
      tolList.add(tol);
    }
    return tolList;
  }
  public void deleteTodoList(TodoList tdl) throws SQLException {
    Connection conn = jdbcsource.getConn();
    PreparedStatement ps = conn.prepareStatement(deleteUserStr);
    ps.setString(1, tdl.getId().toString());
    ps.execute();
    conn.commit();
    tdl = null;
  }
}
