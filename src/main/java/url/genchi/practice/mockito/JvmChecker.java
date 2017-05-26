package url.genchi.practice.mockito;

/**
 * Created by genchi.lu on 2017/5/25.
 */
public class JvmChecker {
  private static int maxThreadCount = 1000;

  public static boolean isThreadAlmostExceed(JvmRuntimeMetricDao jvmRuntimeMetricDao) {
    //return true if number of threads is exceed 80% of maxTrreadCount
    return (jvmRuntimeMetricDao.getThreadCount() > (int)maxThreadCount*0.8);
  }

  public static boolean isMemoryAlmostExceed(JvmRuntimeMetricDao jvmRuntimeMetricDao) {
    long usedmem = jvmRuntimeMetricDao.getTotalmem() - jvmRuntimeMetricDao.getFreemem();
    //return true if mem usage is exceed 60%
    return (usedmem > jvmRuntimeMetricDao.getMaxmem()*0.6);
  }
}
