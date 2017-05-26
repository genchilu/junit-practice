package url.genchi.practice.db;

import static org.junit.Assert.assertTrue;
import java.io.InputStream;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
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
  public void testSaveTodoList() throws SQLException {
    TodoList tdlOri = new TodoList("test save");
    this.tdo.saveTodoList(tdlOri);
    TodoList tdlFromDao = tdo.findOneById(tdlOri.getId().toString());
    assertTrue(tdlOri.equals(tdlFromDao));
  }

  @Test
  public void testUpdateTodoList() throws SQLException {
    this.testTdl1.setDescription("after update");
    this.tdo.updateTodolist(testTdl1);
    TodoList tdlFromDao = tdo.findOneById(testTdl1.getId().toString());
    assertTrue(this.testTdl1.equals(testTdl1));
  }


  @Override
  protected void tearDown() throws Exception {
    Statement stmt = jdbcSource.getConn().createStatement();
    stmt.execute("DROP TABLE TODOLIST");
    stmt.close();
    jdbcSource.getConn().commit();
  }

}
