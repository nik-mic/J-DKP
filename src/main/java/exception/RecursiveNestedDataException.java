package exception;

public class RecursiveNestedDataException extends Exception {
    public RecursiveNestedDataException(){
        super("Object contains recursive nested fields");
    }
}
