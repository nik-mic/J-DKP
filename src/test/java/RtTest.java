import entities.DkpParser;
import entities.DkpUnparser;
import entities.LiteralScanner;
import formats.Parsetree;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import subjects.*;

import java.util.stream.Stream;

public class RtTest {

    LiteralScanner l = new LiteralScanner();
    DkpUnparser un = new DkpUnparser();
    DkpParser pa = new DkpParser();

    private static Stream<Subject> RTTest() {
        return Stream.of(new SimpleSubject(), new MediumSubject(), new ParallelSubject(), new HighSubject(), new KeyFinderSubject(), new ExtremeSubject());
    }

    private static Stream<Subject> RTTransformTest() {
        return Stream.of(new SimpleSubject(), new MediumSubject(), new ParallelSubject(), new HighSubject(), new KeyFinderSubject(), new ExtremeSubject());
    }

    @ParameterizedTest
    @MethodSource
    public void RTTest(Subject sub){
        System.out.println("In : " + l.scan(sub));
        System.out.println(un.unparse(sub).getUbeContent());
        System.out.println("Out: " + pa.parse(un.unparse(sub).getUbeContent()).getTree());
        Assertions.assertEquals(l.scan(sub), pa.parse(un.unparse(sub).getUbeContent()).getTree());
    }

    @ParameterizedTest
    @MethodSource
    public void RTTransformTest(Subject sub){
        Assertions.assertEquals(new Parsetree(sub).transform().getContent(), un.unparse(sub).getContent());
    }
}
