package url.genchi.practice.junit;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

import org.junit.Test;
import url.genchi.practice.mockito.JvmChecker;
import url.genchi.practice.mockito.JvmRuntimeMetricDao;

import static org.junit.Assert.assertTrue;
/**
 * Created by genchi.lu on 2017/5/25.
 */
public class JvmCheckerTest  {

  @Test
  public void isThreadAlmostExceed_MemExceed_ShouldTrue() throws Exception {
    JvmRuntimeMetricDao jvmRuntimeMetricDaoMock = mock(JvmRuntimeMetricDao.class);
    when(jvmRuntimeMetricDaoMock.getMaxmem()).thenReturn(1024l);
    when(jvmRuntimeMetricDaoMock.getFreemem()).thenReturn(64l);
    when(jvmRuntimeMetricDaoMock.getTotalmem()).thenReturn(768l);
    boolean testMemExceedResult = JvmChecker.isMemoryAlmostExceed(jvmRuntimeMetricDaoMock);
    verify(jvmRuntimeMetricDaoMock).getMaxmem();
    verify(jvmRuntimeMetricDaoMock).getFreemem();
    verify(jvmRuntimeMetricDaoMock).getTotalmem();
    verify(jvmRuntimeMetricDaoMock, never()).getThreadCount();
    assertTrue(testMemExceedResult);
  }
  @Test
  public void isThreadAlmostExceed_MemNotExceed_ShouldFalse() throws Exception {
    JvmRuntimeMetricDao jvmRuntimeMetricDaoMock = mock(JvmRuntimeMetricDao.class);
    when(jvmRuntimeMetricDaoMock.getMaxmem()).thenReturn(1024l);
    when(jvmRuntimeMetricDaoMock.getFreemem()).thenReturn(512l);
    when(jvmRuntimeMetricDaoMock.getTotalmem()).thenReturn(768l);
    boolean testMemExceedResult = JvmChecker.isMemoryAlmostExceed(jvmRuntimeMetricDaoMock);
    verify(jvmRuntimeMetricDaoMock).getMaxmem();
    verify(jvmRuntimeMetricDaoMock).getFreemem();
    verify(jvmRuntimeMetricDaoMock).getTotalmem();
    verify(jvmRuntimeMetricDaoMock, never()).getThreadCount();
    assertFalse(testMemExceedResult);
  }
  @Test
  public void isThreadAlmostExceed_ThreadExceed_ShouldTrue() throws Exception {
    JvmRuntimeMetricDao jvmRuntimeMetricDaoMock = mock(JvmRuntimeMetricDao.class);
    when(jvmRuntimeMetricDaoMock.getThreadCount()).thenReturn(900);
    boolean testThreadExceedResult = JvmChecker.isThreadAlmostExceed(jvmRuntimeMetricDaoMock);
    verify(jvmRuntimeMetricDaoMock, never()).getMaxmem();
    verify(jvmRuntimeMetricDaoMock, never()).getFreemem();
    verify(jvmRuntimeMetricDaoMock, never()).getTotalmem();
    verify(jvmRuntimeMetricDaoMock).getThreadCount();
    assertTrue(testThreadExceedResult);
  }
  @Test
  public void isThreadAlmostExceed_ThreadExceed_ShouldFalse() throws Exception {
    JvmRuntimeMetricDao jvmRuntimeMetricDaoMock = mock(JvmRuntimeMetricDao.class);
    when(jvmRuntimeMetricDaoMock.getThreadCount()).thenReturn(200);
    boolean testThreadExceedResult = JvmChecker.isThreadAlmostExceed(jvmRuntimeMetricDaoMock);
    verify(jvmRuntimeMetricDaoMock, never()).getMaxmem();
    verify(jvmRuntimeMetricDaoMock, never()).getFreemem();
    verify(jvmRuntimeMetricDaoMock, never()).getTotalmem();
    verify(jvmRuntimeMetricDaoMock).getThreadCount();
    assertFalse(testThreadExceedResult);
  }
}
