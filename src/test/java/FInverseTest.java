import algorithm.FInverse;
import formats.DataFormat;
import formats.Parsetree;
import formats.Urbildelement;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import subjects.HighSubject;
import subjects.MediumSubject;
import subjects.SimpleSubject;

import java.util.stream.Stream;

public class FInverseTest {

    private static Stream<DataFormat> transformTest() {
        return Stream.of(new Parsetree(new SimpleSubject()).transform(), new Parsetree(new MediumSubject()).transform(), new Parsetree(new HighSubject()).transform());
    }

    @ParameterizedTest
    @MethodSource
    public void transformTest(DataFormat ube){
        System.out.println(ube.transform());
    }
}
