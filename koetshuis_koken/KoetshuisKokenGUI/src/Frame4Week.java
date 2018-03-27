package src;
//****************************************************************************
//*                     import
//****************************************************************************
import java.awt.Color;
import java.awt.Dimension;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.text.SimpleDateFormat;
import java.text.ParseException;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.swing.JFrame;

//****************************************************************************
//*                     Frame4Week
//****************************************************************************
public class Frame4Week {
    /**
     * Get resources for literals.
     */
    //************************************************************************
    //*                 getBundle
    //************************************************************************
    protected static void getBundle(String[] args) {
        System.out.println("Frame4Week.getBundle()");

        String strLanguage, strCountry;
        if (args.length == 2) {
            strLanguage = args[0];
            strCountry = args[1];
        } else {
            strLanguage = "en";
            strCountry = "US";
        }
        currentLocale = new Locale(strLanguage, strCountry);
        try {
            messages = ResourceBundle.getBundle("src/resource/MessagesBundle",
                currentLocale);
        } catch(MissingResourceException e) {
            e.printStackTrace();
        } finally {
            ;
        }
    }
    /**
     * Get date for current day and day of the week.
     */
    //************************************************************************
    //*                 getDate
    //************************************************************************
    protected static void getDate() {
        System.out.println("Frame4Week.getDate()");

        currentDate = new Date();
        strCurrentDate = sdf.format(currentDate);
        try {
            Calendar cal = Calendar.getInstance();
            cal.setTime(sdf.parse(strCurrentDate));
            iCurrentDow = cal.get(Calendar.DAY_OF_WEEK);
        } catch(ParseException e) {
            e.printStackTrace();
        } finally {
            ;
        }
    }
    //************************************************************************
    //*                 setArrayDow
    //************************************************************************
    protected static void setArrayDow() {
        System.out.println("Frame4Week.setArrayDow()");

        aDayOfWeek[0] = new DayOfWeek(messages.getString("sunday"),    new Color(0xFF0000));
        aDayOfWeek[1] = new DayOfWeek(messages.getString("monday"),    new Color(0xFF7F00));
        aDayOfWeek[2] = new DayOfWeek(messages.getString("tuesday"),   new Color(0x7FFF00));
        aDayOfWeek[3] = new DayOfWeek(messages.getString("wednesday"), new Color(0x00FF00));
        aDayOfWeek[4] = new DayOfWeek(messages.getString("thursday"),  new Color(0x00FF7F));
        aDayOfWeek[5] = new DayOfWeek(messages.getString("friday"),    new Color(0x007FFF));
        aDayOfWeek[6] = new DayOfWeek(messages.getString("saturday"),  new Color(0x0000FF));

        int iDow = iCurrentDow - 1;
        String strDate = strCurrentDate;
        for (int i = 0; i < NOF_DAY; i++) {
            aDayOfWeek[iDow].iIdx = iDow;
            aDayOfWeek[iDow].strDate = strDate;
            iDow = (iDow + 1) % 7;
            try {
                Calendar cal = Calendar.getInstance();
                cal.setTime(sdf.parse(strDate));
                cal.add(Calendar.DATE, 1);
                strDate = sdf.format(cal.getTime());
            } catch(ParseException e) {
                e.printStackTrace();
            } finally {
                ;
            }
        }
    }
    /**
     * Remove panel for week and switch to a panel for dinner on this frame
     */
    //************************************************************************
    //*                 setPanel4Dinner
    //************************************************************************
    protected static void setPanel4Dinner(int iIdx) {
        System.out.println("Frame4Week.setPanel4Dinner()");

        frame.getContentPane().removeAll();
        panel4Dinner = new Panel4Dinner(iIdx);

        panel4Dinner.mainPane.setBackground(aDayOfWeek[iIdx].color);
        panel4Dinner.jlHeader.setText(aDayOfWeek[iIdx].strNameOfDay + " " +
            aDayOfWeek[iIdx].strDate);

        if (panel4Dinner.bCanProceed) {
            // set the componets that are created in the createPanel4Dinner method
            panel4Dinner.jbOK.setBackground(aDayOfWeek[iIdx].color);
            panel4Dinner.jbSave.setBackground(aDayOfWeek[iIdx].color);
            panel4Dinner.jbCancel.setBackground(aDayOfWeek[iIdx].color);

            panel4Dinner.jlCook.setText(Frame4Week.messages.getString("cook"));
            panel4Dinner.jlEating.setText(Frame4Week.messages.getString("eating"));
            panel4Dinner.jbOK.setText(Frame4Week.messages.getString("button_ok"));
            panel4Dinner.jbSave.setText(Frame4Week.messages.getString("button_save"));
            panel4Dinner.jbCancel.setText(Frame4Week.messages.getString("button_cancel"));
        } else {
            // set the componets that are created in the createLockMessage method
            panel4Dinner.jbOK.setBackground(aDayOfWeek[iIdx].color);

            panel4Dinner.jlLockMessage1.setText(Frame4Week.messages.getString("lock_msg_1"));
            panel4Dinner.jlLockMessage2.setText(Frame4Week.messages.getString("lock_msg_2"));
            panel4Dinner.jbOK.setText(Frame4Week.messages.getString("button_ok"));
        }

        frame.getContentPane().add(panel4Dinner.mainPane);
        frame.validate();
        frame.setVisible(true);

    }
    //************************************************************************
    //*                 releasePanel4Dinner
    //************************************************************************
    protected static void releasePanel4Dinner(String strActionCommand, int iIdx) {
        System.out.println("Frame4Week.releasePanel4Dinner()");

        frame.getContentPane().removeAll();
        panel4Week = new Panel4Week();

        frame.getContentPane().add(panel4Week.mainPane);
        frame.validate();
        frame.setVisible(true);

        if (strActionCommand.equals(Frame4Week.messages.getString("button_save")))
            Panel4Dinner.getGuiContent();
        Panel4Dinner.lockAndWrite(false);
    }
    /**
     * Create the GUI and show it. For thread safety, this method should be
     * invoked from the event-dispatching thread.
     */
    //************************************************************************
    //*                 createAndShowGUI
    //************************************************************************
    public static void createAndShowGUI() {
        System.out.println("Frame4Week.createAndShowGUI()");

        // create and set up the window
        frame = new JFrame();
        frame.setTitle(messages.getString("title"));
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // add a window listener to handle unlocking when closing the app
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onExit();
            }
        });
        frame.setSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        frame.setResizable(false);

        // create and set up the content pane
        panel4Week = new Panel4Week();
        // content panes must be opaque
        panel4Week.mainPane.setOpaque(true);
        frame.getContentPane().add(panel4Week.mainPane);

        // display the window
        frame.pack();
        frame.setVisible(true);
    }
    //************************************************************************
    //*                 onExit
    //************************************************************************
    private static void onExit() {
        System.out.println("Frame4Week.onExit()");

        if (Panel4Dinner.getLock()) Panel4Dinner.lockAndWrite(false);
        //TODO remove out of date revisions
        //GoogleDrive.getRevisions();
        System.exit(0);
    }
    //************************************************************************
    //*                 declare
    //************************************************************************
    protected static final String JSON_FILE_PATH = "C:\\Users\\Klasing\\Documents";
    protected static final String TEST_JSON_FILE = "dd-MM-yy.json";

    protected static final int NOF_DAY = 7;
    protected static final int FRAME_WIDTH = 300;
    protected static final int FRAME_HEIGHT = 600;

    protected static Locale currentLocale = null;
    protected static ResourceBundle messages = null;

    //TODO adjust date format according to locale
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");
    private static Date currentDate = null;
    protected static String strCurrentDate;
    protected static int iCurrentDow;
    protected static DayOfWeek[] aDayOfWeek = new DayOfWeek[NOF_DAY];

    private static JFrame frame;
    private static Panel4Week panel4Week;
    private static Panel4Dinner panel4Dinner = null;

}
