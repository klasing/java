package src;
//****************************************************************************
//*                     import
//****************************************************************************
import java.awt.Color;
//****************************************************************************
//*                     DayOfWeek
//****************************************************************************
public class DayOfWeek {
    public DayOfWeek(String _strNameOfDay, Color _color) {
        System.out.println("<<constructor>> DayOfWeek()");

        strNameOfDay = _strNameOfDay;
        color = _color;
    }
    //************************************************************************
    //*                 declare
    //************************************************************************
    protected int iIdx;
    protected Color color = null;
    protected String strNameOfDay;
    protected String strDate;
}