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
 * Created by genchi.lu on 2017/5/24.
 */
@RunWith(Parameterized.class)
public class TestValiddateFieldIDNumber {
  @Parameters
  public static Collection<Object[]> data() {
    return Arrays.asList(new Object[][] {
        {"S123069316", true},
        {"S12306931612", false},
        {"S323069316", false},
        {"Q123456789", false},
        {"M123456789", true},
        {"A234567820", true},
        {"A368829966", false},
        {null, false}
    });
  }
  @Parameter(0)
  public String idNumber;
  @Parameter(1)
  public boolean valid;
  @Test
  public  void Test() {
    assertEquals(valid, ValidateField.vaildIDnumber(idNumber));
  }
}
