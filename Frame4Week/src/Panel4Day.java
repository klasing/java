<<<<<<< HEAD
package src;
//****************************************************************************
//*                     import
//****************************************************************************
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;
//****************************************************************************
//*                     Panel4Day
//****************************************************************************
public class Panel4Day implements MouseListener {
    public Panel4Day() {
        // create a JPanel
        dayPane = new JPanel();
        dayPane.setOpaque(true);
        dayPane.setBackground(Color.RED);
        dayPane.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));

        // set a borderlayout
        dayPane.setLayout(new BorderLayout());
        day = new JLabel("BLA", JLabel.CENTER);
        // set larger text size
        day.setFont(day.getFont().deriveFont(20.0f));
        day.setForeground(Color.WHITE);
        dayPane.add(day, BorderLayout.CENTER);

        date = new JLabel("dd-mm-yy", JLabel.CENTER);
        date.setForeground(Color.WHITE);
        dayPane.add(date, BorderLayout.SOUTH);

        // add mouse listener
        dayPane.addMouseListener(this);
    }
    //************************************************************************
    //*                     mousePressed
    //************************************************************************
    @Override
    public void mousePressed(MouseEvent e) {
        //System.out.println("mousePressed");
        // mousepressed responsivenes
        Color darkColor = dayPane.getBackground().darker();
        dayPane.setBackground(darkColor);
        ;
    }
    //************************************************************************
    //*                     mouseReleased
    //************************************************************************
    @Override
    public void mouseReleased(MouseEvent e) {
        //System.out.println("mouseReleased");
        // mousereleased responsivenes
        Color darkColor = dayPane.getBackground().brighter();
        dayPane.setBackground(darkColor);
        Frame4Week.setPanel4Dinner(this.iIdx);
    }
    //************************************************************************
    //*                     mouseEntered
    //************************************************************************
    @Override
    public void mouseEntered(MouseEvent e) {
        //System.out.println("mouseEntered");
        ;
    }
    //************************************************************************
    //*                     mouseExited
    //************************************************************************
    @Override
    public void mouseExited(MouseEvent e) {
        //System.out.println("mouseExited");
        ;
    }
    //************************************************************************
    //*                     mouseClicked
    //************************************************************************
    @Override
    public void mouseClicked(MouseEvent e) {
        //System.out.println("mouseClicked");
        ;
    }
    //************************************************************************
    //*                     declare
    //************************************************************************
    protected static final int PANEL_WIDTH = 60;
    protected static final int PANEL_HEIGHT = 60;

    protected int iIdx;
    protected JPanel dayPane;
    protected JLabel day, date;
}
/*
package src;
//****************************************************************************
//*                     import
//****************************************************************************
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;
//****************************************************************************
//*                     Panel4Day
//****************************************************************************
public class Panel4Day extends JPanel implements MouseListener {
    public Panel4Day() {
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        this.setLayout(new BorderLayout());
        day = new JLabel("", JLabel.CENTER);
        // set larger text size
        day.setFont(day.getFont().deriveFont(20.0f));
        day.setForeground(Color.WHITE);
        this.add(day, BorderLayout.CENTER);

        date = new JLabel("", JLabel.CENTER);
        date.setForeground(Color.WHITE);
        this.add(date, BorderLayout.SOUTH);

        // add mouse listener
        addMouseListener(this);
    }
    @Override
    public void mousePressed(MouseEvent e) {
        //System.out.println("mousePressed");
        // mousepressed response
        Color darkColor = this.getBackground().darker();
        this.setBackground(darkColor);
        Frame4Week.setPanel4Dinner(this.iIdx);
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        //System.out.println("mouseReleased");
        // mousereleased response
        Color darkColor = this.getBackground().brighter();
        this.setBackground(darkColor);
    }
    @Override
    public void mouseEntered(MouseEvent e) {
        //System.out.println("mouseEntered");
        ;
    }
    @Override
    public void mouseExited(MouseEvent e) {
        //System.out.println("mouseExited");
        ;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        //System.out.println("mouseClicked");
        ;
    }
    //************************************************************************
    //*                     declare
    //************************************************************************
    private static final int PANEL_WIDTH = 60;
    private static final int PANEL_HEIGHT = 60;
    protected int iIdx;
    protected JLabel day, date;
}
*/
/*
package src;
//****************************************************************************
//*                     import
//****************************************************************************
import java.awt.BorderLayout;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date; // OR MAYBE USE import java.time.LocalDateTime;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JPanel;
//****************************************************************************
//*                     Panel4Day
//****************************************************************************
public class Panel4Day extends JPanel {
    protected static final int PANEL_WIDTH = 60;
    protected static final int PANEL_HEIGHT = 60;
    private JLabel day, date;

    public Panel4Day(String strDow) {
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        this.setLayout(new BorderLayout());
        day = new JLabel(strDow, JLabel.CENTER);
        // set larger text size
        day.setFont(day.getFont().deriveFont(20.0f));
        day.setForeground(Color.WHITE);
        this.add(day, BorderLayout.CENTER);
        // get date for the current date
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");
        String strNow = sdf.format(now);
        // set the current date in the desired format on the panel
        date = new JLabel(strNow, JLabel.CENTER);
        date.setForeground(Color.WHITE);
        this.add(date, BorderLayout.SOUTH);

        // establish the next day from the current day and determine the day of week
        try {
            Calendar cal = Calendar.getInstance();
            cal.setTime(sdf.parse(strNow));
            cal.add(Calendar.DATE, 1);
            String tomorrow = sdf.format(cal.getTime());
            int dow = cal.get(Calendar.DAY_OF_WEEK);
            System.out.println(tomorrow + " " + dow);
        } catch(ParseException e) {
            //e.printStackTrace();
            ;
        }

    }
}
=======
package src;
//****************************************************************************
//*                     import
//****************************************************************************
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;
//****************************************************************************
//*                     Panel4Day
//****************************************************************************
public class Panel4Day implements MouseListener {
    public Panel4Day() {
        // create a JPanel
        dayPane = new JPanel();
        dayPane.setOpaque(true);
        dayPane.setBackground(Color.RED);
        dayPane.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));

        // set a borderlayout
        dayPane.setLayout(new BorderLayout());
        day = new JLabel("BLA", JLabel.CENTER);
        // set larger text size
        day.setFont(day.getFont().deriveFont(20.0f));
        day.setForeground(Color.WHITE);
        dayPane.add(day, BorderLayout.CENTER);

        date = new JLabel("dd-mm-yy", JLabel.CENTER);
        date.setForeground(Color.WHITE);
        dayPane.add(date, BorderLayout.SOUTH);

        // add mouse listener
        dayPane.addMouseListener(this);
    }
    //************************************************************************
    //*                     mousePressed
    //************************************************************************
    @Override
    public void mousePressed(MouseEvent e) {
        //System.out.println("mousePressed");
        // mousepressed responsivenes
        Color darkColor = dayPane.getBackground().darker();
        dayPane.setBackground(darkColor);
        ;
    }
    //************************************************************************
    //*                     mouseReleased
    //************************************************************************
    @Override
    public void mouseReleased(MouseEvent e) {
        //System.out.println("mouseReleased");
        // mousereleased responsivenes
        Color darkColor = dayPane.getBackground().brighter();
        dayPane.setBackground(darkColor);
        Frame4Week.setPanel4Dinner(this.iIdx);
    }
    //************************************************************************
    //*                     mouseEntered
    //************************************************************************
    @Override
    public void mouseEntered(MouseEvent e) {
        //System.out.println("mouseEntered");
        ;
    }
    //************************************************************************
    //*                     mouseExited
    //************************************************************************
    @Override
    public void mouseExited(MouseEvent e) {
        //System.out.println("mouseExited");
        ;
    }
    //************************************************************************
    //*                     mouseClicked
    //************************************************************************
    @Override
    public void mouseClicked(MouseEvent e) {
        //System.out.println("mouseClicked");
        ;
    }
    //************************************************************************
    //*                     declare
    //************************************************************************
    protected static final int PANEL_WIDTH = 60;
    protected static final int PANEL_HEIGHT = 60;

    protected int iIdx;
    protected JPanel dayPane;
    protected JLabel day, date;
}
/*
package src;
//****************************************************************************
//*                     import
//****************************************************************************
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;
//****************************************************************************
//*                     Panel4Day
//****************************************************************************
public class Panel4Day extends JPanel implements MouseListener {
    public Panel4Day() {
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        this.setLayout(new BorderLayout());
        day = new JLabel("", JLabel.CENTER);
        // set larger text size
        day.setFont(day.getFont().deriveFont(20.0f));
        day.setForeground(Color.WHITE);
        this.add(day, BorderLayout.CENTER);

        date = new JLabel("", JLabel.CENTER);
        date.setForeground(Color.WHITE);
        this.add(date, BorderLayout.SOUTH);

        // add mouse listener
        addMouseListener(this);
    }
    @Override
    public void mousePressed(MouseEvent e) {
        //System.out.println("mousePressed");
        // mousepressed response
        Color darkColor = this.getBackground().darker();
        this.setBackground(darkColor);
        Frame4Week.setPanel4Dinner(this.iIdx);
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        //System.out.println("mouseReleased");
        // mousereleased response
        Color darkColor = this.getBackground().brighter();
        this.setBackground(darkColor);
    }
    @Override
    public void mouseEntered(MouseEvent e) {
        //System.out.println("mouseEntered");
        ;
    }
    @Override
    public void mouseExited(MouseEvent e) {
        //System.out.println("mouseExited");
        ;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        //System.out.println("mouseClicked");
        ;
    }
    //************************************************************************
    //*                     declare
    //************************************************************************
    private static final int PANEL_WIDTH = 60;
    private static final int PANEL_HEIGHT = 60;
    protected int iIdx;
    protected JLabel day, date;
}
*/
/*
package src;
//****************************************************************************
//*                     import
//****************************************************************************
import java.awt.BorderLayout;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date; // OR MAYBE USE import java.time.LocalDateTime;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JPanel;
//****************************************************************************
//*                     Panel4Day
//****************************************************************************
public class Panel4Day extends JPanel {
    protected static final int PANEL_WIDTH = 60;
    protected static final int PANEL_HEIGHT = 60;
    private JLabel day, date;

    public Panel4Day(String strDow) {
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        this.setLayout(new BorderLayout());
        day = new JLabel(strDow, JLabel.CENTER);
        // set larger text size
        day.setFont(day.getFont().deriveFont(20.0f));
        day.setForeground(Color.WHITE);
        this.add(day, BorderLayout.CENTER);
        // get date for the current date
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");
        String strNow = sdf.format(now);
        // set the current date in the desired format on the panel
        date = new JLabel(strNow, JLabel.CENTER);
        date.setForeground(Color.WHITE);
        this.add(date, BorderLayout.SOUTH);

        // establish the next day from the current day and determine the day of week
        try {
            Calendar cal = Calendar.getInstance();
            cal.setTime(sdf.parse(strNow));
            cal.add(Calendar.DATE, 1);
            String tomorrow = sdf.format(cal.getTime());
            int dow = cal.get(Calendar.DAY_OF_WEEK);
            System.out.println(tomorrow + " " + dow);
        } catch(ParseException e) {
            //e.printStackTrace();
            ;
        }

    }
}
>>>>>>> 5839fb13e8eecee60acee4bb332b91b2b147bac9
*/