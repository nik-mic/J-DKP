import entities.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import subjects.*;

import static org.mockito.Mockito.*;

public class UnparseTest {

   /* @Test
    public void callsScanTest(){
        ObjectScanner u = mock(ObjectScanner.class);
        DkpUnparser un = new DkpUnparser();
        un.unparse(new SimpleSubject());
        verify(u, times(1)).scan(Mockito.any());
    } */

    @Test
    public void complexUnparseTest(){
        Unparser un = new DkpUnparser();
    }

    @Test
    public void unparseArrayTest(){
        Unparser un = new DkpUnparser();
        Subject s = new ArraySubject();
    }
}
