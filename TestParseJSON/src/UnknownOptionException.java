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
        super(strMessage);
    }
}