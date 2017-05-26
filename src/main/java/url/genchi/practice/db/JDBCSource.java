package url.genchi.practice.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collection;

/**
 * Created by genchi.lu on 2017/5/26.
 */
public class JDBCSource {
  private String driver;
  private String jdbcStr;
  private String user;
  private String pwd;
  private Connection conn;

  public JDBCSource(String driver, String jdbcStr, String user, String pwd)
      throws ClassNotFoundException {
    this.driver = driver;
    this.jdbcStr = jdbcStr;
    this.user = user;
    this.pwd = pwd;
    Class.forName(driver);
  }
  public Connection getConn() throws SQLException {
    if(conn == null || conn.isClosed()){
      conn = DriverManager.getConnection(jdbcStr, user, pwd);
    }
    return conn;
  }
  public void destory() throws SQLException {
    conn.close();
  }

}
