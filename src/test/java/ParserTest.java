import entities.DkpParser;
import formats.Parsetree;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;

public class ParserTest {

    private static Stream<Arguments> parseLevelTest() {
        return Stream.of(
                Arguments.of("91919", new Parsetree(Collections.emptyList())),
                Arguments.of("9191915016199", new Parsetree(Arrays.asList("50", "6", "99"))),
                Arguments.of("91239111912351231241218", new Parsetree(Arrays.asList("5", Arrays.asList("4", Arrays.asList("8"))))),
                Arguments.of("91239111912351231241218123781238712341231288", new Parsetree(Arrays.asList("5", Arrays.asList("4", Arrays.asList("8")), "78", "87", "4", Arrays.asList("88"))))
        );
    }

    @ParameterizedTest
    @MethodSource
    public void parseLevelTest(String ube, Parsetree pt) {
        DkpParser parser = new DkpParser();
        Assertions.assertEquals(pt.getTree(), parser.parse(ube).getTree());

    }
}
