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
        System.out.println("<<constructor>> Panel4Week()");

        // create a JPanel
        mainPane = new JPanel();
        mainPane.setOpaque(true);
        mainPane.setBackground(Color.BLACK);

        // set a gridbaglayout
        mainPane.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, Frame4Week.FRAME_WIDTH - 90);

        // add day panels to this panel
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
    //*                 declare
    //************************************************************************
    protected static JPanel mainPane;
}