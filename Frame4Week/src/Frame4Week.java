package src;
//****************************************************************************
//*                     import
//****************************************************************************
import java.awt.Color;
import java.awt.Dimension;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
//****************************************************************************
//*                     Frame4Week
//****************************************************************************
public class Frame4Week {
    /**
     * get resources for literals;
     */
    //************************************************************************
    //*                     getBundle
    //************************************************************************
    private static void getBundle(String[] args) {
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
            messages = ResourceBundle.getBundle("src/resource/MessagesBundle", currentLocale);
        } catch(MissingResourceException e) {
            //e.printStackTrace();
            ;
        } finally {
            ;
        }
    }
    //************************************************************************
    //*                     getDate
    //************************************************************************
    private static void getDate() {
        currentDate = new Date();
        strCurrentDate = sdf.format(currentDate);
        try {
            Calendar cal = Calendar.getInstance();
            cal.setTime(sdf.parse(strCurrentDate));
            iCurrentDow = cal.get(Calendar.DAY_OF_WEEK);
        } catch(ParseException e) {
            //e.printStackTrace();
            ;
        } finally {
            ;
        }
    }
    //************************************************************************
    //*                     setArrayDow
    //************************************************************************
    private static void setArrayDow() {
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
                //e.printStackTrace();
                ;
            } finally {
                ;
            }
        }
    }
    //************************************************************************
    //*                     showArrayDow
    //************************************************************************
    private static void showArrayDow() {
        int iDow = iCurrentDow - 1;
        for (int i = 0; i < NOF_DAY; i++) {
            System.out.println(
                aDayOfWeek[iDow].iIdx + " " +
                aDayOfWeek[iDow].strNameOfDay + " " +
                aDayOfWeek[iDow].strDate + " " +
                aDayOfWeek[iDow].color);
            iDow = (iDow + 1) % 7;
        }
    }
    /**
     * Remove panel for week and switch to a panel for dinner on the frame
     */
    //************************************************************************
    //*                     setPanel4Dinner
    //************************************************************************
    protected static void setPanel4Dinner(int iIdx) {
        //System.out.println("setPanel4Dinner()");
        frame.getContentPane().removeAll();
        panel4Dinner = new Panel4Dinner();
        panel4Dinner.mainPane.setBackground(aDayOfWeek[iIdx].color);
        panel4Dinner.jlHeader.setText(aDayOfWeek[iIdx].strNameOfDay + " " +
            aDayOfWeek[iIdx].strDate);
        panel4Dinner.jbOK.setBackground(aDayOfWeek[iIdx].color);
        panel4Dinner.jbSave.setBackground(aDayOfWeek[iIdx].color);
        panel4Dinner.jbCancel.setBackground(aDayOfWeek[iIdx].color);

        panel4Dinner.jlCook.setText(Frame4Week.messages.getString("cook"));
        panel4Dinner.jlEating.setText(Frame4Week.messages.getString("eating"));
        panel4Dinner.jbOK.setText(Frame4Week.messages.getString("button_ok"));
        panel4Dinner.jbSave.setText(Frame4Week.messages.getString("button_save"));
        panel4Dinner.jbCancel.setText(Frame4Week.messages.getString("button_cancel"));

        frame.getContentPane().add(panel4Dinner.mainPane);
        frame.validate();
        frame.setVisible(true);
    }
    /**
     * Remove panel for dinner and switch to a panel for week on the frame
     */
    //************************************************************************
    //*                     releasePanel4Dinner
    //************************************************************************
    protected static void releasePanel4Dinner() {
        //System.out.println("releasePanel4Dinner()");
        frame.getContentPane().removeAll();

        panel4Week = new Panel4Week();
        frame.getContentPane().add(panel4Week.mainPane);
        frame.validate();
        frame.setVisible(true);
    }
    /**
     * Create the GUI anbd show it. For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    //************************************************************************
    //*                 createAndShowGUI
    //************************************************************************
    private static void createAndShowGUI() {
        // create and set up the window
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
    //*                 main
    //************************************************************************
    public static void main(String[] args) {
        getBundle(args);
        getDate();
        setArrayDow();
        showArrayDow();
        // schedule a job for the event-dispatching thread:
        // creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
    //************************************************************************
    //*                     declare
    //************************************************************************
    protected static final int NOF_DAY = 7;
    protected static final int FRAME_WIDTH = 300;
    protected static final int FRAME_HEIGHT = 600;

    protected static Locale currentLocale = null;
    protected static ResourceBundle messages = null;

    // TODO adjust date format according to locale
    protected static SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");
    protected static Date currentDate = null;
    protected static String strCurrentDate;
    protected static int iCurrentDow;
    protected static DayOfWeek[] aDayOfWeek = new DayOfWeek[NOF_DAY];

    private static JFrame frame;
    private static Panel4Week panel4Week;
    private static Panel4Dinner panel4Dinner;
}
/*
package src;
//****************************************************************************
//*                     import
//****************************************************************************
import java.awt.Color;
import java.awt.Dimension;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import src.DayOfWeek;
import src.Panel4Week;
import src.Panel4Dinner;
//****************************************************************************
//*                     Frame4Week
//****************************************************************************
public class Frame4Week extends JFrame {
    //************************************************************************
    //*                     setPanel4Dinner
    //************************************************************************
    protected static void setPanel4Dinner(int iIdx) {
        //System.out.println("setPanel4Dinner()");
        frame4Week.getContentPane().removeAll();

        panel4Dinner = new Panel4Dinner(iIdx);
        //panel4Dinner.setBackground(aDayOfWeek[iIdx].color);
        //panel4Dinner.header.setText(aDayOfWeek[iIdx].strNameOfDay + " " +
        //    aDayOfWeek[iIdx].strDate);
        //panel4Dinner.header.setForeground(Color.WHITE);
        frame4Week.getContentPane().add(panel4Dinner);
        frame4Week.validate();
        frame4Week.setVisible(true);
    }
    //************************************************************************
    //*                     releasePanel4Dinner
    //************************************************************************
    protected static void releasePanel4Dinner() {
        //System.out.println("releasePanel4Dinner()");
        frame4Week.getContentPane().removeAll();

        panel4Week = new Panel4Week();
        frame4Week.getContentPane().add(panel4Week);
        frame4Week.validate();
        frame4Week.setVisible(true);
    }
    //************************************************************************
    //*                     getBundle
    //************************************************************************
    private static void getBundle(String[] args) {
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
            messages = ResourceBundle.getBundle("src/resource/MessagesBundle", currentLocale);
        } catch(MissingResourceException e) {
            //e.printStackTrace();
            ;
        } finally {
            ;
        }
    }
    //************************************************************************
    //*                     getDate
    //************************************************************************
    private static void getDate() {
        currentDate = new Date();
        strCurrentDate = sdf.format(currentDate);
        try {
            Calendar cal = Calendar.getInstance();
            cal.setTime(sdf.parse(strCurrentDate));
            iCurrentDow = cal.get(Calendar.DAY_OF_WEEK);
        } catch(ParseException e) {
            //e.printStackTrace();
            ;
        } finally {
            ;
        }
    }
    //************************************************************************
    //*                     setArrayDow
    //************************************************************************
    private static void setArrayDow() {
        aDayOfWeek[0] = new DayOfWeek(new Color(0xFF0000), messages.getString("sunday"));
        aDayOfWeek[1] = new DayOfWeek(new Color(0xFF7F00), messages.getString("monday"));
        aDayOfWeek[2] = new DayOfWeek(new Color(0x7FFF00), messages.getString("tuesday"));
        aDayOfWeek[3] = new DayOfWeek(new Color(0x00FF00), messages.getString("wednesday"));
        aDayOfWeek[4] = new DayOfWeek(new Color(0x00FF7F), messages.getString("thursday"));
        aDayOfWeek[5] = new DayOfWeek(new Color(0x007FFF), messages.getString("friday"));
        aDayOfWeek[6] = new DayOfWeek(new Color(0x0000FF), messages.getString("saturday"));
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
                //e.printStackTrace();
                ;
            } finally {
                ;
            }
        }
    }
    //************************************************************************
    //*                     showArrayDow
    //************************************************************************
    private static void showArrayDow() {
        int iDow = iCurrentDow - 1;
        for (int i = 0; i < NOF_DAY; i++) {
            System.out.println(
                aDayOfWeek[iDow].iIdx + " " +
                aDayOfWeek[iDow].strNameOfDay + " " +
                aDayOfWeek[iDow].strDate + " " +
                aDayOfWeek[iDow].color);
            iDow = (iDow + 1) % 7;
        }
    }
    //************************************************************************
    //*                     constructor
    //************************************************************************
    public Frame4Week() {
        this.setTitle(messages.getString("title"));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        this.setResizable(false);

        panel4Week = new Panel4Week();
        this.add(panel4Week);
        panel4Week.setVisible(true);

        // don't use pack for this app.
        //this.pack();
        this.setVisible(true);
    }
    //************************************************************************
    //*                     main
    //************************************************************************
    public static void main(String[] args) {
        getBundle(args);
        getDate();
        setArrayDow();
        showArrayDow();
        frame4Week = new Frame4Week();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                frame4Week.setVisible(true);
            }
        });
    }
    //************************************************************************
    //*                     declare
    //************************************************************************
    protected static final int NOF_DAY = 7;
    protected static final int FRAME_WIDTH = 350;
    protected static final int FRAME_HEIGHT = 600;

    protected static Locale currentLocale = null;
    protected static ResourceBundle messages = null;

    // TODO adjust date format according to locale
    protected static SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");
    protected static Date currentDate = null;
    protected static String strCurrentDate;
    protected static int iCurrentDow;
    protected static DayOfWeek[] aDayOfWeek = new DayOfWeek[NOF_DAY];

    private static Frame4Week frame4Week;
    private static Panel4Week panel4Week = null;
    private static Panel4Dinner panel4Dinner = null;
}
*/
/*
package src;
//****************************************************************************
//*                     import
//****************************************************************************
import java.awt.Color;
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
public class Frame4Week extends JFrame {
    //************************************************************************
    //*                     getBundle
    //************************************************************************
    private static void getBundle(String[] args) {
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
            messages = ResourceBundle.getBundle("src/resource/MessagesBundle", currentLocale);
        } catch(MissingResourceException e) {
            //e.printStackTrace();
            ;
        } finally {
            ;
        }
    }
    //************************************************************************
    //*                     getNameOfDay
    //************************************************************************
    private static void getNameOfDay() {
        aNameOfDay[0] = messages.getString("monday");    aColorOfDay[1] = new Color(0xFF0000);
        aNameOfDay[1] = messages.getString("tuesday");   aColorOfDay[2] = new Color(0xFF7F00);
        aNameOfDay[2] = messages.getString("wednesday"); aColorOfDay[3] = new Color(0x7FFF00);
        aNameOfDay[3] = messages.getString("thursday");  aColorOfDay[4] = new Color(0x00FF00);
        aNameOfDay[4] = messages.getString("friday");    aColorOfDay[5] = new Color(0x00FF7F);
        aNameOfDay[5] = messages.getString("saturday");  aColorOfDay[6] = new Color(0x007FFF);
        aNameOfDay[5] = messages.getString("sunday");    aColorOfDay[0] = new Color(0x0000FF);
    }
    //************************************************************************
    //*                     showNameOfDay
    //************************************************************************
    private static void showNameOfDay() {
        for (int i = 0; i < NOF_DAY; i++) {
            System.out.println(i + ": " + aNameOfDay[i]);
        }
    }
    //************************************************************************
    //*                     getDow
    //************************************************************************
    private static void getDow() {
        // get the string for the current day date
// TODO adjust date format according to locale !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");
        strCurrentDate = sdf.format(currentDate);
        try {
            Calendar cal = Calendar.getInstance();
            cal.setTime(sdf.parse(strCurrentDate));
            iDow = cal.get(Calendar.DAY_OF_WEEK);
        } catch(ParseException e) {
            //e.printStackTrace();
            ;
        } finally {
            ;
        }
        System.out.println(strCurrentDate + " " + iDow + " " + aNameOfDay[iDow - 1]);
    }
    //************************************************************************
    //*                     setArrayDayOfWeek
    //************************************************************************
    private static void setArrayDayOfWeek() {
        int dow = iDow - 1;
        String strDate = strCurrentDate;
        for (int i = 0; i < NOF_DAY; i++) {
            aDayOfWeek[i] = new DayOfWeek(aColorOfDay[dow], dow,
                aNameOfDay[dow], strDate);
            dow = (dow + 1) % 7;
// TODO roll-over srtDate to next date
        }
    }
    //************************************************************************
    //*                     showArrayDayOfWeek
    //************************************************************************
    private static void showArrayDayOfWeek() {
        for (int i = 0; i < NOF_DAY; i++) {
            System.out.println(i + ": " + aDayOfWeek[i].iDow + " " +
                aDayOfWeek[i].strNameOfDay  + " " +
                aDayOfWeek[i].strDate + " " +
                aDayOfWeek[i].color);
        }
    }
    //************************************************************************
    //*                     main
    //************************************************************************
    public static void main(String[] args) {
        getBundle(args);
        getNameOfDay();
        showNameOfDay();
        getDow();
        setArrayDayOfWeek();
        showArrayDayOfWeek();
    }
    //************************************************************************
    //*                     declare
    //************************************************************************
    protected static final int NOF_DAY = 7;
    protected static Locale currentLocale = null;
    protected static ResourceBundle messages = null;
    protected static String[] aNameOfDay = new String[NOF_DAY];
    protected static Color[] aColorOfDay = new Color[NOF_DAY];
    protected static Date currentDate = new Date();
    protected static String strCurrentDate;
    protected static int iDow;
    protected static DayOfWeek[] aDayOfWeek = new DayOfWeek[NOF_DAY];
}
*/
/*
package src;
//****************************************************************************
//*                     import
//****************************************************************************
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import src.Panel4Week;
//****************************************************************************
//*                     Frame4Week
//****************************************************************************
public class Frame4Week extends JFrame {
    protected static final int FRAME_WIDTH = 350;
    protected static final int FRAME_HEIGHT = 600;
    private static Locale currentLocale;
    private static ResourceBundle messages = null;
    private static Frame4Week frame4Week;
    private static Panel4Week panel4Week;

    public Frame4Week() {
        this.setTitle("Frame4Week");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        this.setResizable(false);
        panel4Week = new Panel4Week(FRAME_WIDTH, FRAME_HEIGHT, messages);
        this.add(panel4Week);
        // don't use pack for this app.
        //this.pack();
        this.setVisible(true);
    }
    //************************************************************************
    //*                     setLocale
    //************************************************************************
    private static void getBundle(String[] args) {
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
            messages = ResourceBundle.getBundle("src/resource/MessagesBundle", currentLocale);
        } catch(MissingResourceException e) {
            //e.printStackTrace();
            ;
        } finally {
            ;
        }
    }
    //************************************************************************
    //*                     main
    //************************************************************************
    public static void main(String[] args) {
        getBundle(args);
        frame4Week = new Frame4Week();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                frame4Week.setVisible(true);
            }
        });
    }
}
*/