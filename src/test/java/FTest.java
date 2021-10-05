import api.DKP;
import entities.LiteralScanner;
import formats.DataFormat;
import formats.Parsetree;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import subjects.HighSubject;
import subjects.MediumSubject;
import subjects.SimpleSubject;
import subjects.Subject;

import java.util.stream.Stream;

public class FTest {
    private static Stream<Subject> transformTest() {
        return Stream.of(new SimpleSubject(), new MediumSubject(), new HighSubject());
    }

    @ParameterizedTest
    @MethodSource
    public void transformTest(Subject subject){
        DataFormat pt = new Parsetree(subject);
        Assertions.assertEquals(pt.transform().getContent(), pt.transform().transform().transform().getContent());
    }
}
