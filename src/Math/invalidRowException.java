package Math;
import java.lang.Exception;

public class invalidRowException extends Exception {
    invalidRowException (String message){
        super(message);
        // Exception goes here
    }

    public invalidRowException() {

    }
}
