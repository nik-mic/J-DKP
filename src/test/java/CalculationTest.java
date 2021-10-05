import formats.Parsetree;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import subjects.*;
import java.lang.reflect.Method;
import java.util.stream.Stream;

public class CalculationTest {
    private static Stream<Arguments> CalcNestingTest() {
        return Stream.of(
                Arguments.of(new SimpleSubject(), 1),
                Arguments.of(new MediumSubject(), 2),
                Arguments.of(new HighSubject(), 3),
                Arguments.of(new ExtremeSubject(), 5)
        );
    }

    private static Stream<Subject> StumpTest() {
        return Stream.of(new SimpleSubject(),
                new MediumSubject(),
                new HighSubject(),
                new ParallelSubject(),
                new ExtremeSubject()
        );
    }

    @SneakyThrows
    @ParameterizedTest
    @MethodSource
    public void CalcNestingTest(Subject s, int length){
        Parsetree pt = new Parsetree(s);
        Method method = Parsetree.class.getDeclaredMethod("getMaxNesting");
        method.setAccessible(true);
        Assertions.assertEquals(length, method.invoke(pt));
    }

    @ParameterizedTest
    @MethodSource
    public void StumpTest(Subject s){
        Parsetree pt = new Parsetree(s);
        Assertions.assertEquals(pt.getStump().getContent().length(), pt.getStumpLength());
    }
}
