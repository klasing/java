<<<<<<< HEAD
package src;
//****************************************************************************
//*                     import
//****************************************************************************
import java.awt.Color;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
//****************************************************************************
//*                     Panel4Week
//****************************************************************************
public class Panel4Week {
    public Panel4Week() {
        // create a JPanel
        mainPane = new JPanel();
        mainPane.setOpaque(true);
        mainPane.setBackground(Color.BLACK);
        // set a gridbaglayout
        mainPane.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, Frame4Week.FRAME_WIDTH - 90);
        // add day panels to panel for week
        int iDow = Frame4Week.iCurrentDow - 1;
        for (int i = 0; i < Frame4Week.NOF_DAY; i++) {
            // create Panel4Day object
            Panel4Day panel4Day = new Panel4Day();
            panel4Day.iIdx = iDow;
            panel4Day.dayPane.setBackground(Frame4Week.aDayOfWeek[iDow].color);
            panel4Day.day.setText(Frame4Week.aDayOfWeek[iDow].strNameOfDay);
            panel4Day.date.setText(Frame4Week.aDayOfWeek[iDow].strDate);
            constraints.gridx = 0;
            constraints.gridy = i;
            mainPane.add(panel4Day.dayPane, constraints);
            iDow = (iDow + 1) % 7;
        }
    }
    //************************************************************************
    //*                     declare
    //************************************************************************
    JPanel mainPane;
}
/*
package src;
//****************************************************************************
//*                     import
//****************************************************************************
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
//****************************************************************************
//*                     Panel4Week
//****************************************************************************
public class Panel4Week extends JPanel {
    public Panel4Week() {
        this.setPreferredSize(new Dimension(Frame4Week.FRAME_WIDTH, Frame4Week.FRAME_HEIGHT));
        this.setBackground(Color.BLACK);

        this.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, Frame4Week.FRAME_WIDTH - 80);

        int iDow = Frame4Week.iCurrentDow - 1;
        for (int i = 0; i < Frame4Week.NOF_DAY; i++) {
            Panel4Day panel4Day = new Panel4Day();
            panel4Day.iIdx = iDow;
            panel4Day.setBackground(Frame4Week.aDayOfWeek[iDow].color);
            panel4Day.day.setText(Frame4Week.aDayOfWeek[iDow].strNameOfDay);
            panel4Day.date.setText(Frame4Week.aDayOfWeek[iDow].strDate);
            constraints.gridx = 0;
            constraints.gridy = i;
            this.add(panel4Day, constraints);
            iDow = (iDow + 1) % 7;
        }
    }
    //************************************************************************
    //*                     declare
    //************************************************************************
}
*/
/*
package src;
//****************************************************************************
//*                     import
//****************************************************************************
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ResourceBundle;
import javax.swing.JPanel;
import src.Panel4Day;
//****************************************************************************
//*                     Panel4Week
//****************************************************************************
public class Panel4Week extends JPanel {
    public Panel4Week(final int SIZE_X_PARENT, final int SIZE_Y_PARENT,
        ResourceBundle messages) {

        this.setPreferredSize(new Dimension(SIZE_X_PARENT, SIZE_Y_PARENT));
        this.setBackground(Color.BLACK);
        this.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        // monday
        Panel4Day panel4Monday = new Panel4Day(messages.getString("monday"));
        panel4Monday.setBackground(Color.RED);
        constraints.insets = new Insets(10, 10, 10, SIZE_X_PARENT - 80);
        constraints.gridx = 0;
        constraints.gridy = 0;
        this.add(panel4Monday, constraints);
        // tuesday
        Panel4Day panel4Tuesday = new Panel4Day(messages.getString("tuesday"));
        panel4Tuesday.setBackground(Color.ORANGE);
        constraints.gridx = 0;
        constraints.gridy = 1;
        this.add(panel4Tuesday, constraints);
        // wednesday
        Panel4Day panel4Wednesday = new Panel4Day(messages.getString("wednesday"));
        panel4Wednesday.setBackground(new Color(0x7FFF00));
        constraints.gridx = 0;
        constraints.gridy = 2;
        this.add(panel4Wednesday, constraints);
        // thursday
        Panel4Day panel4Thursday = new Panel4Day(messages.getString("thursday"));
        panel4Thursday.setBackground(Color.GREEN);
        constraints.gridx = 0;
        constraints.gridy = 3;
        this.add(panel4Thursday, constraints);
        // friday
        Panel4Day panel4Friday = new Panel4Day(messages.getString("friday"));
        panel4Friday.setBackground(new Color(0x00FF7F));
        constraints.gridx = 0;
        constraints.gridy = 4;
        this.add(panel4Friday, constraints);
        // saturday
        Panel4Day panel4Saturday = new Panel4Day(messages.getString("saturday"));
        panel4Saturday.setBackground(new Color(0x007FFF));
        constraints.gridx = 0;
        constraints.gridy = 5;
        this.add(panel4Saturday, constraints);
        // sunday
        Panel4Day panel4Sunday = new Panel4Day(messages.getString("sunday"));
        panel4Sunday.setBackground(Color.BLUE);
        constraints.gridx = 0;
        constraints.gridy = 6;
        this.add(panel4Sunday, constraints);
    }
}
=======
package src;
//****************************************************************************
//*                     import
//****************************************************************************
import java.awt.Color;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
//****************************************************************************
//*                     Panel4Week
//****************************************************************************
public class Panel4Week {
    public Panel4Week() {
        // create a JPanel
        mainPane = new JPanel();
        mainPane.setOpaque(true);
        mainPane.setBackground(Color.BLACK);
        // set a gridbaglayout
        mainPane.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, Frame4Week.FRAME_WIDTH - 90);
        // add day panels to panel for week
        int iDow = Frame4Week.iCurrentDow - 1;
        for (int i = 0; i < Frame4Week.NOF_DAY; i++) {
            // create Panel4Day object
            Panel4Day panel4Day = new Panel4Day();
            panel4Day.iIdx = iDow;
            panel4Day.dayPane.setBackground(Frame4Week.aDayOfWeek[iDow].color);
            panel4Day.day.setText(Frame4Week.aDayOfWeek[iDow].strNameOfDay);
            panel4Day.date.setText(Frame4Week.aDayOfWeek[iDow].strDate);
            constraints.gridx = 0;
            constraints.gridy = i;
            mainPane.add(panel4Day.dayPane, constraints);
            iDow = (iDow + 1) % 7;
        }
    }
    //************************************************************************
    //*                     declare
    //************************************************************************
    JPanel mainPane;
}
/*
package src;
//****************************************************************************
//*                     import
//****************************************************************************
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
//****************************************************************************
//*                     Panel4Week
//****************************************************************************
public class Panel4Week extends JPanel {
    public Panel4Week() {
        this.setPreferredSize(new Dimension(Frame4Week.FRAME_WIDTH, Frame4Week.FRAME_HEIGHT));
        this.setBackground(Color.BLACK);

        this.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, Frame4Week.FRAME_WIDTH - 80);

        int iDow = Frame4Week.iCurrentDow - 1;
        for (int i = 0; i < Frame4Week.NOF_DAY; i++) {
            Panel4Day panel4Day = new Panel4Day();
            panel4Day.iIdx = iDow;
            panel4Day.setBackground(Frame4Week.aDayOfWeek[iDow].color);
            panel4Day.day.setText(Frame4Week.aDayOfWeek[iDow].strNameOfDay);
            panel4Day.date.setText(Frame4Week.aDayOfWeek[iDow].strDate);
            constraints.gridx = 0;
            constraints.gridy = i;
            this.add(panel4Day, constraints);
            iDow = (iDow + 1) % 7;
        }
    }
    //************************************************************************
    //*                     declare
    //************************************************************************
}
*/
/*
package src;
//****************************************************************************
//*                     import
//****************************************************************************
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ResourceBundle;
import javax.swing.JPanel;
import src.Panel4Day;
//****************************************************************************
//*                     Panel4Week
//****************************************************************************
public class Panel4Week extends JPanel {
    public Panel4Week(final int SIZE_X_PARENT, final int SIZE_Y_PARENT,
        ResourceBundle messages) {

        this.setPreferredSize(new Dimension(SIZE_X_PARENT, SIZE_Y_PARENT));
        this.setBackground(Color.BLACK);
        this.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        // monday
        Panel4Day panel4Monday = new Panel4Day(messages.getString("monday"));
        panel4Monday.setBackground(Color.RED);
        constraints.insets = new Insets(10, 10, 10, SIZE_X_PARENT - 80);
        constraints.gridx = 0;
        constraints.gridy = 0;
        this.add(panel4Monday, constraints);
        // tuesday
        Panel4Day panel4Tuesday = new Panel4Day(messages.getString("tuesday"));
        panel4Tuesday.setBackground(Color.ORANGE);
        constraints.gridx = 0;
        constraints.gridy = 1;
        this.add(panel4Tuesday, constraints);
        // wednesday
        Panel4Day panel4Wednesday = new Panel4Day(messages.getString("wednesday"));
        panel4Wednesday.setBackground(new Color(0x7FFF00));
        constraints.gridx = 0;
        constraints.gridy = 2;
        this.add(panel4Wednesday, constraints);
        // thursday
        Panel4Day panel4Thursday = new Panel4Day(messages.getString("thursday"));
        panel4Thursday.setBackground(Color.GREEN);
        constraints.gridx = 0;
        constraints.gridy = 3;
        this.add(panel4Thursday, constraints);
        // friday
        Panel4Day panel4Friday = new Panel4Day(messages.getString("friday"));
        panel4Friday.setBackground(new Color(0x00FF7F));
        constraints.gridx = 0;
        constraints.gridy = 4;
        this.add(panel4Friday, constraints);
        // saturday
        Panel4Day panel4Saturday = new Panel4Day(messages.getString("saturday"));
        panel4Saturday.setBackground(new Color(0x007FFF));
        constraints.gridx = 0;
        constraints.gridy = 5;
        this.add(panel4Saturday, constraints);
        // sunday
        Panel4Day panel4Sunday = new Panel4Day(messages.getString("sunday"));
        panel4Sunday.setBackground(Color.BLUE);
        constraints.gridx = 0;
        constraints.gridy = 6;
        this.add(panel4Sunday, constraints);
    }
}
>>>>>>> 5839fb13e8eecee60acee4bb332b91b2b147bac9
*/