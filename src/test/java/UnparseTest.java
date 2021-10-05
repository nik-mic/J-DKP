import entities.*;
import formats.DataFormat;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import subjects.*;

import static org.mockito.Mockito.*;

public class UnparseTest {

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
