package exception;

public class KeywordInjectionException extends Exception {
    public KeywordInjectionException(){
        super("Data could not be created in predicted length");
    }
}
