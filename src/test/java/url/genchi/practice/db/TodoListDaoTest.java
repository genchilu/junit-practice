package url.genchi.practice.db;

import static org.junit.Assert.assertTrue;
import java.io.InputStream;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.UUID;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * Created by genchi.lu on 2017/5/26.
 */

public class TodoListDaoTest extends TestCase{
  private TodoListDao tdo;
  private JDBCSource jdbcSource;
  private TodoList testTdl1 = new TodoList("test 1");
  private TodoList testTdl2 = new TodoList("test 2");

  @Override
  protected void setUp() throws Exception {
    Properties prop = new Properties();
    InputStream in = TodoListDao.class.getClassLoader().getResourceAsStream("db.properties");
    prop.load(in);
    in.close();
    String driver = prop.getProperty("driver").toString();
    String jdbcurl = prop.get("jdbcurl").toString();
    String user = prop.get("user").toString();
    String pwd = prop.get("pwd").toString();
    this.jdbcSource = new JDBCSource(driver, jdbcurl, user, pwd);
    Statement stmt = jdbcSource.getConn().createStatement();
    stmt.execute("CREATE TABLE TODOLIST (\n"
        + "     ID UUID PRIMARY KEY,\n"
        + "     DESCRIPTION  char(50),\n"
        + ") ENGINE=InnoDB DEFAULT CHARSET=utf8;");
    stmt.close();
    jdbcSource.getConn().commit();
    this.tdo = new TodoListDao(this.jdbcSource);
    this.tdo.saveTodoList(this.testTdl1);
    this.tdo.saveTodoList(this.testTdl2);
  }

  @Test
  public void testSaveTodoList() throws Exception {
    TodoList tdlOri = new TodoList("test save");
    this.tdo.saveTodoList(tdlOri);
    TodoList tdlFromDao = tdo.findOneById(tdlOri.getId().toString());
    assertTrue(tdlOri.equals(tdlFromDao));
  }

  @Test
  public void testUpdateTodoList() throws Exception {
    this.testTdl1.setDescription("after update");
    this.tdo.updateTodolist(testTdl1);
    TodoList tdlFromDao = tdo.findOneById(testTdl1.getId().toString());
    assertTrue(this.testTdl1.equals(testTdl1));
  }

  @Test
  public void testFindOneById_ExistId() throws Exception {
    TodoList tdlFromDao = this.tdo.findOneById(testTdl1.getId().toString());
    assertTrue(this.testTdl1.equals(tdlFromDao));
  }

  @Test
  public void testFindOneById_NonExistId_ShouldNull() throws Exception {
    TodoList tdlFromDao = this.tdo.findOneById(UUID.randomUUID().toString());
    assertNull(tdlFromDao);
  }

  @Test
  public void testFindAll() throws Exception {
    List<TodoList> tdlListFromDao = this.tdo.findAll();
    List<TodoList> testTdlList = new LinkedList<>();
    testTdlList.add(testTdl1);
    testTdlList.add(testTdl2);
    assertTrue(isListEqual(testTdlList, tdlListFromDao));
  }

  static private boolean isListEqual(List<TodoList> list1, List<TodoList> list2){
    if(list1.size() != list2.size()) {
      return false;
    }
    for(int i = 0;i<list1.size();i++) {
      boolean isIdEqual = list1.get(i).getId().toString().equals(list2.get(i).getId().toString());
      boolean isDescEqual = list1.get(i).getDescription().equals(list2.get(i).getDescription());
      if(!isDescEqual || !isDescEqual) {
        return false;
      }
    }
    return true;
  }
  @Test
  public void testdeleteTodoList_Delete_ExistId_And_Find_Should_Null() throws Exception {
    String id = testTdl1.getId().toString();
    this.tdo.deleteTodoList(testTdl1);
    TodoList tdlFromDao = this.tdo.findOneById(id);
    assertNull(tdlFromDao);
  }
  
  @Override
  protected void tearDown() throws Exception {
    Statement stmt = jdbcSource.getConn().createStatement();
    stmt.execute("DROP TABLE TODOLIST");
    stmt.close();
    jdbcSource.getConn().commit();
  }
}
