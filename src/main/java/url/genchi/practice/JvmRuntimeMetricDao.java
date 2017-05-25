package url.genchi.practice;

import java.io.IOException;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import javax.management.MBeanServer;

/**
 * Created by genchi.lu on 2017/5/25.
 */
public class JvmRuntimeMetricDao {
  private int threadCount = 0;
  private long freemem = 0;
  private long totalmem = 0;
  private long maxmem = 0;
  public JvmRuntimeMetricDao(){
    this.threadCount = java.lang.Thread.activeCount();
    Runtime runtime = Runtime.getRuntime();
    this.freemem = runtime.availableProcessors();
    this.totalmem = runtime.totalMemory();
    this.maxmem = runtime.maxMemory();
  }

  public int getThreadCount() {
    return threadCount;
  }

  public long getFreemem() {
    return freemem;
  }

  public long getTotalmem() {
    return totalmem;
  }

  public long getMaxmem() {
    return maxmem;
  }

  public static void main(String[] args) throws IOException {
    int tmp = java.lang.Thread.activeCount();
    System.out.println(tmp);
  }
}
