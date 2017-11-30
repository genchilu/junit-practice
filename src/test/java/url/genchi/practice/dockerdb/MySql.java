package url.genchi.practice.dockerdb;

import com.spotify.docker.client.DefaultDockerClient;
import com.spotify.docker.client.DockerClient;
import com.spotify.docker.client.DockerClient.LogsParam;
import com.spotify.docker.client.LogMessage;
import com.spotify.docker.client.LogStream;
import com.spotify.docker.client.exceptions.DockerCertificateException;
import com.spotify.docker.client.exceptions.DockerException;
import com.spotify.docker.client.messages.ContainerConfig;
import com.spotify.docker.client.messages.ContainerCreation;
import com.spotify.docker.client.messages.HostConfig;
import java.nio.ByteBuffer;
import org.junit.After;
import org.junit.Before;

public class MySql {
  private DockerClient docker;
  private String containerid;

  @Before
  public void setup() throws DockerCertificateException, DockerException, InterruptedException {
    // Setup test db
    docker = DefaultDockerClient.fromEnv().build();

    docker.pull("mysql:5.5");

    final HostConfig hostConfig = HostConfig.builder().networkMode("host").build();

    final ContainerConfig containerConfig = ContainerConfig.builder()
        .attachStdout(true)
        .hostConfig(hostConfig)
        .image("mysql:5.5")
        //.exposedPorts(ports)
        .env("MYSQL_ROOT_PASSWORD=root", "MYSQL_DATABASE=test", "MYSQL_USER=test", "MYSQL_PASSWORD=test")
        .hostname("mysql4junit")
        .cmd("mysqld", "--character-set-server=utf8", "--collation-server=utf8_unicode_ci", "--init-connect='SET NAMES UTF8;'", "--innodb-flush-log-at-trx-commit=0")
        .build();

    final ContainerCreation creation = docker.createContainer(containerConfig);
    containerid = creation.id();
    docker.startContainer(containerid);
    //Thread.sleep(10000);
    LogStream logs = docker.logs(containerid, LogsParam.follow(), LogsParam.stdout(), LogsParam.stderr());
    String log;
    do {
      LogMessage logMessage = logs.next();
      ByteBuffer buffer = logMessage.content();
      byte[] bytes = new byte[buffer.remaining()];
      buffer.get(bytes);
      log = new String(bytes);
      System.out.println(log);
    } while (!log.contains("Version: '5.5.58'  socket: '/tmp/mysql.sock'  port: 3306  MySQL Community Server (GPL)"));
  }

  @After
  public void teardown() throws DockerException, InterruptedException {
    docker.killContainer(containerid);
    docker.removeContainer(containerid);
    docker.close();
  }
}
