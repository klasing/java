package src;
//****************************************************************************
//*                     import
//****************************************************************************
import java.lang.Exception;
//****************************************************************************
//*                     UnknownOptionException
//****************************************************************************
public class UnknownOptionException extends Exception {
    public UnknownOptionException() {}

    public UnknownOptionException(String strMessage) {
        // must be the first statement in the constructor...
        super(strMessage);

        System.out.println("<<consructor>> UnknownOptionException()");
    }
}