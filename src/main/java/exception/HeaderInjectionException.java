package exception;

public class HeaderInjectionException extends Exception{
    public HeaderInjectionException(){
        super("Header did not reach predicted length");
    }
}
