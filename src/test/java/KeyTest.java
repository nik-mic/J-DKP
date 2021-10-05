import entities.LiteralScanner;
import formats.Parsetree;
import algorithm.StumpGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import subjects.HighSubject;
import subjects.KeyFinderSubject;

public class KeyTest {

    @Test
    public void generateKeyTest(){
        Parsetree pt = new Parsetree(new LiteralScanner().scan(new HighSubject()));
        StumpGenerator kg = new StumpGenerator(pt);
        Assertions.assertEquals("bbc", kg.getStump().getSuperkey());
    }
    @Test
    public void generateKeySubjectTest(){
        Parsetree pt = new Parsetree(new LiteralScanner().scan(new KeyFinderSubject()));
        StumpGenerator kg = new StumpGenerator(pt);
        Assertions.assertEquals("nbbb", kg.getStump().getSuperkey());
    }
}
