package url.genchi.practice.mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;
import url.genchi.practice.junit.ValidateField;

/**
 * Created by genchi.lu on 2017/5/24.
 */
@RunWith(JUnitParamsRunner.class)
public class ValidateFieldTest {
  @Test
  @Parameters
  public void testIsValidIP(String testIp) throws Exception {
    boolean actualResult = ValidateField.validateIP(testIp);
    assertTrue(actualResult);
  }
  private Object[] parametersForTestIsValidIP() {
    return new Object[] {
      new Object[]{"25.0.0.255"},
      new Object[]{"10.254.0.1"},
      new Object[]{"117.113.11.11"},
    };
  }

  @Test
  @Parameters
  public void testIsInvalidIP(String testIp) throws Exception {
    boolean actualResult = ValidateField.validateIP(testIp);
    assertFalse(actualResult);
  }
  private Object[] parametersForTestIsInvalidIP() {
    return new Object[] {
        new Object[]{"192.168.010.010"},
        new Object[]{"127.0.0.1"},
        new Object[]{"0.0.0.0"},
        new Object[]{"255.255.255.254"},
        new Object[]{"169.0.0.1"},
        new Object[]{"224.0.0.1"},
        new Object[]{"192.168.99.100"},
        new Object[]{"256.155.155.1"},
        new Object[]{null},
    };
  }

  @Test
  @Parameters
  public void testIsValidMail(String testMail) throws Exception {
    boolean actualResult = ValidateField.validateMail(testMail);
    assertTrue(actualResult);
  }
  private Object[] parametersForTestIsValidMail() {
    return new Object[] {
        new Object[]{"genchi.lu@104.com.tw"},
        new Object[]{"genchi.lu@gmail.com"},
    };
  }
  @Test
  @Parameters
  public void testIsInvalidMail(String testMail) throws Exception {
    boolean actualResult = ValidateField.validateMail(testMail);
    assertFalse(actualResult);
  }
  private Object[] parametersForTestIsInvalidMail() {
    return new Object[] {
        new Object[]{"genchi-.lu@104.com.tw"},
        new Object[]{"genchi.-lu@104.com.tw"},
        new Object[]{"genchi.-lu@104.com.99"},
        new Object[]{"genchi.lu@104.abc.99.com"},
        new Object[]{"genchi.lu@104_.99.com"},
        new Object[]{"Genchi.lu@104_.99.com"},
        new Object[]{"Genchi.l u@104_.99.com"},
        new Object[]{null},
    };
  }
  @Test
  @Parameters
  public void testIsValidIdNumber(String testIdNumber) throws Exception {
    boolean actualResult = ValidateField.validateIdNumber(testIdNumber);
    assertTrue(actualResult);
  }
  private Object[] parametersForTestIsValidIdNumber() {
    return new Object[] {
        new Object[]{"S123069316"},
        new Object[]{"M123456789"},
        new Object[]{"A234567820"},
    };
  }
  @Test
  @Parameters
  public void testIsInvalidIdNumber(String testIdNumber) throws Exception {
    boolean actualResult = ValidateField.validateIdNumber(testIdNumber);
    assertFalse(actualResult);
  }
  private Object[] parametersForTestIsInvalidIdNumber() {
    return new Object[] {
        new Object[]{"S12306931612"},
        new Object[]{"S323069316"},
        new Object[]{"Q123456789"},
        new Object[]{"A368829966"},
        new Object[]{null},
    };
  }
}
