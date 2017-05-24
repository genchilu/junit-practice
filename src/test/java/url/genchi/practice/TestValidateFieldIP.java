package url.genchi.practice;


import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import java.util.Collection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

/**
 * Created by genchi.lu on 2017/5/23.
 */
@RunWith(Parameterized.class)
public class TestValidateFieldIP {
  @Parameters
  public static Collection<Object[]> data() {
    return Arrays.asList(new Object[][] {
        {"192.168.010.010", false},
        {"127.0.0.1", false},
        {"172.16.1.1", false},
        {"0.0.0.0", false},
        {"255.255.255.254", false},
        {"169.0.0.1", false},
        {"224.0.0.1", false},
        {"192.168.99.100", false},
        {"256.155.155.1", false},
        {"25.0.0.255", true},
        {"10.254.0.1", true},
        {"117.113.11.11", true},
        {"010A010A010A010", false},
        {"010.010.010.010", false}
    });
  }
  @Parameter(0)
  public String ip;
  @Parameter(1)
  public boolean valid;
  @Test
  public  void Test() {
    assertEquals(valid, ValidateField.validateIP(ip));
  }
}
