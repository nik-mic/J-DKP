import formats.Stump;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StumpTest {

    Stump s = new Stump("9abcd911119");

    @Test
    public void getIdentifier(){
        Assertions.assertEquals("9", s.getIdentifier());
    }
    @Test
    public void getKeyword(){
        Assertions.assertEquals("abcd", s.getSuperkey());
    }
    @Test
    public void getHeader(){
        Assertions.assertEquals("1111", s.getHeader());
    }
}
