package src;
//****************************************************************************
//*                     import
//****************************************************************************
import javax.swing.SwingUtilities;
//****************************************************************************
//*                     Frame4WeekJson
//****************************************************************************
public class Frame4WeekJson {
    //************************************************************************
    //*                 main
    //************************************************************************
    public static void main(String[] args) throws Exception {
        System.out.println("Frame4WeekJson.main()");

        Frame4Week.getBundle(args);
        Frame4Week.getDate();
        Frame4Week.setArrayDow();
        GoogleDrive.initialize();
        // schedule a job for the event dispatching thread:
        // creating and showing this application's GUI
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Frame4Week.createAndShowGUI();
            }
        });

        // don't terminate here, let the GUI exit the program
        //System.exit(0);
    }
    //************************************************************************
    //*                 declare
    //************************************************************************
}