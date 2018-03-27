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
        System.out.println("<<constructor>> Panel4Day()");

        // create a JPanel
        dayPane = new JPanel();
        dayPane.setOpaque(true);
        dayPane.setBackground(Color.RED);
        dayPane.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));

        // set a borderlayout
        dayPane.setLayout(new BorderLayout());
        day = new JLabel("BLA", JLabel.CENTER);
        // set larger text size for day
        day.setFont(day.getFont().deriveFont(20.0f));
        day.setForeground(Color.WHITE);
        dayPane.add(day, BorderLayout.CENTER);

        date = new JLabel("dd-MM-yy", JLabel.CENTER);
        date.setForeground(Color.WHITE);
        dayPane.add(date, BorderLayout.SOUTH);

        // add mouse listener
        dayPane.addMouseListener(this);
    }
    /** Override the mouse events */
    //************************************************************************
    //*                 mousePressed
    //************************************************************************
    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("Panel4Day.mousePressed()");
        // mousepressed responsiveness by darkening the color
        Color darkColor = dayPane.getBackground().darker();
        dayPane.setBackground(darkColor);
    }
    //************************************************************************
    //*                 mouseReleased
    //************************************************************************
    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("Panel4Day.mouseReleased()");
        // mousereleased responsiveness by brightening the color
        Color brightColor = dayPane.getBackground().brighter();
        dayPane.setBackground(brightColor);
        // set a panel for dinner according to the present day
        Frame4Week.setPanel4Dinner(iIdx);
    }
    //************************************************************************
    //*                 mouseEntered
    //************************************************************************
    @Override
    public void mouseEntered(MouseEvent e) {
        System.out.println("Panel4Day.mouseEntered()");
    }
    //************************************************************************
    //*                 mouseExited
    //************************************************************************
    @Override
    public void mouseExited(MouseEvent e) {
        System.out.println("Panel4Day.mouseExited()");
    }
    //************************************************************************
    //*                 mouseClicked
    //************************************************************************
    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("Panel4Day.mouseClicked()");
    }
    //************************************************************************
    //*                 declare
    //************************************************************************
    private static final int PANEL_WIDTH = 60;
    private static final int PANEL_HEIGHT = 60;

    protected int iIdx;
    protected JPanel dayPane;
    protected JLabel day, date;
}