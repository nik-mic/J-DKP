import api.DKP;
import formats.DataFormat;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


public class APITest {
    @Test
    public void callsTransformOfDataFormat(){
        DataFormat m = Mockito.mock(DataFormat.class);
        DKP.INSTANCE.transform(m);
        Mockito.verify(m, Mockito.times(1)).transform();
    }
}
