import entities.LiteralScanner;
import formats.Parsetree;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import subjects.HighSubject;
import subjects.MediumSubject;
import subjects.SimpleSubject;
import subjects.Subject;

import java.lang.reflect.Method;
import java.util.List;
import java.util.stream.Stream;

public class TreeOperationsTest {

    private static Stream<Arguments> flatLineTest() {
        return Stream.of(
          Arguments.of(new SimpleSubject(), "Samy18"),
          Arguments.of(new MediumSubject(), "Samy18Nele19"),
          Arguments.of(new HighSubject(), "MarieSamy18Nele1921")
        );
    }

    private static Stream<Arguments> allLevelflatLineTest() {
        return Stream.of(
                Arguments.of(new SimpleSubject(),"[Samy18]"),
                Arguments.of(new MediumSubject(),"[Samy18Nele19, Samy18]"),
                Arguments.of(new HighSubject(),"[MarieSamy18Nele1921, Samy18Nele19, Samy18]")
        );
    }

    @SneakyThrows
    @ParameterizedTest
    @MethodSource
    public void flatLineTest(Subject sub, String text) {
        Parsetree pt = new Parsetree(sub);
        Method method = Parsetree.class.getDeclaredMethod("getFlatline", List.class, String.class);
        method.setAccessible(true);
        Assertions.assertEquals(text, method.invoke(pt, new LiteralScanner().scan(sub), ""));
    }

    @SneakyThrows
    @ParameterizedTest
    @MethodSource
    public void allLevelflatLineTest(Subject sub, String lines) {
        Parsetree pt = new Parsetree(sub);
        Method method = Parsetree.class.getMethod("getAllLevel");
        Assertions.assertEquals(lines, method.invoke(pt).toString());
    }
}
