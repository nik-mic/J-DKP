import entities.LiteralScanner;
import entities.ObjectScanner;
import formats.Parsetree;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import subjects.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class ObjectScannerTest {

    ObjectScanner scanner = new LiteralScanner();

    private static Stream<Arguments> scanningTest() {
        return Stream.of(
                Arguments.of(new SimpleSubject(), new Parsetree(Arrays.asList("Samy", "18")).getTree()),
                Arguments.of(new MediumSubject(), new Parsetree(Arrays.asList(Arrays.asList("Samy", "18"), "Nele", "19")).getTree()),
                Arguments.of(new HighSubject(), new Parsetree(Arrays.asList("Marie", Arrays.asList(Arrays.asList("Samy", "18"), "Nele", "19"), "21")).getTree()),
                Arguments.of(new ParallelSubject(), new Parsetree(Arrays.asList(Arrays.asList("Samy", "18"), Arrays.asList("Samy", "18"))).getTree())
        );
    };

    @ParameterizedTest
    @MethodSource
    public void scanningTest(Subject s, List<Object> pt){
        Assertions.assertEquals(pt, scanner.scan(s));
    }

    @Test
    public void throwsAccessException(){
        // TODO
        // Assertions.assertThrows(IllegalAccessException.class, () -> scanner.scan(Arrays.asList(1, 2, 3)));
    }
}
