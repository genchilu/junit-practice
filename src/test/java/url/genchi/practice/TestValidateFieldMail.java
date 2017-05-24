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
public class TestValidateFieldMail {
  @Parameters
  public static Collection<Object[]> data() {
    return Arrays.asList(new Object[][] {
        {"genchi.lu@104.com.tw", true},
        {"genchi.lu@gmail.com", true},
        {"genchi-.lu@104.com.tw", false},
        {"genchi.-lu@104.com.tw", false},
        {"genchi.-lu@104.com.99", false},
        {"genchi.lu@104.abc.99.com", false},
        {"genchi.lu@104_.99.com", false},
        {"Genchi.lu@104_.99.com", false}
    });
  }
  @Parameter(0)
  public String mail;
  @Parameter(1)
  public boolean valid;
  @Test
  public  void Test() {
    assertEquals(valid, ValidateField.validateMail(mail));
  }
}
