import entities.LiteralScanner;
import entities.ObjectScanner;
import formats.Parsetree;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import subjects.HighSubject;
import subjects.MediumSubject;
import subjects.SimpleSubject;
import subjects.Subject;

import java.util.Arrays;

public class ObjectScannerTest {

    ObjectScanner scanner = new LiteralScanner();
    Parsetree onePt = new Parsetree(Arrays.asList("Samy", "18"));

    @Test
    public void zeroDimUnparsing(){
        Subject one = new SimpleSubject();
        Assertions.assertEquals(onePt.getTree(), new Parsetree(scanner.scan(one)).getTree());
    }

    @Test
    public void oneDimUnparsing(){
        Subject two = new MediumSubject();
        Parsetree pt = new Parsetree(Arrays.asList(Arrays.asList("Samy", "18"), "Nele", "19"));
        Assertions.assertEquals(pt.getTree(), new Parsetree(scanner.scan(two)).getTree());
    }

    @Test
    public void twoDimUnparsing(){
        Subject two = new HighSubject();
        Parsetree pt = new Parsetree(Arrays.asList("Marie", Arrays.asList(Arrays.asList("Samy", "18"), "Nele", "19"), "21"));
        Assertions.assertEquals(pt.getTree(), new Parsetree(scanner.scan(two)).getTree());
    }
}
