<<<<<<< HEAD
package src;
//****************************************************************************
//*                     import
//****************************************************************************
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
//****************************************************************************
//*                     Panel4Dinner
//****************************************************************************
public class Panel4Dinner {
    public Panel4Dinner() {
        // create a JPanel
        mainPane = new JPanel();
        mainPane.setOpaque(true);
        mainPane.setBackground(Color.RED);
        // set a gridbaglayout
        mainPane.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);

        // create a header
        jlHeader = new JLabel("BLA dd-mm-yy");
        jlHeader.setForeground(Color.WHITE);
        jlHeader.setFont(jlHeader.getFont().deriveFont(18.0f));
        constraints.anchor = GridBagConstraints.LINE_START;
        constraints.gridx = 0;
        constraints.gridy = 0;
        mainPane.add(jlHeader, constraints);
        // create label for cook
        jlCook = new JLabel("Cook");
        jlCook.setForeground(Color.WHITE);
        constraints.gridx = 0;
        constraints.gridy = 1;
        mainPane.add(jlCook, constraints);
        // create text field for cook
        jtfCook = new JTextField("Bla", 15);
        constraints.gridx = 0;
        constraints.gridy = 2;
        mainPane.add(jtfCook, constraints);
        // create label for eating
        jlEating = new JLabel("Eating");
        jlEating.setForeground(Color.WHITE);
        constraints.gridx = 0;
        constraints.gridy = 3;
        mainPane.add(jlEating, constraints);
        // create text area for eating
        jtaEating = new JTextArea(5, 15);
        // bring some content into the text area
        jtaEating.append("A_Bla\nB_Bla\nC_Bla\nD_Bla\nE_Bla");
        // create scroll pane for eating, containing text area
        jspEating = new JScrollPane(jtaEating,
            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        constraints.gridx = 0;
        constraints.gridy = 4;
        mainPane.add(jspEating, constraints);
        // create OK button
        jbOK = new JButton("BLA");
        jbOK.setForeground(Color.WHITE);
        constraints.gridx = 0;
        constraints.gridy = 5;
        mainPane.add(jbOK, constraints);
        // add actionlistener to button
        jbOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //System.out.println("actionPerformed");
                Frame4Week.releasePanel4Dinner();
            }
        });
        // create Save button
        jbSave = new JButton("BLA");
        jbSave.setForeground(Color.WHITE);
        constraints.gridx = 0;
        constraints.gridy = 6;
        mainPane.add(jbSave, constraints);
        // add actionlistener to button
        jbSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //System.out.println("actionPerformed");
                ;
                Frame4Week.releasePanel4Dinner();
            }
        });
        // create Cancel button
        jbCancel = new JButton("BLA");
        jbCancel.setForeground(Color.WHITE);
        constraints.gridx = 0;
        constraints.gridy = 7;
        mainPane.add(jbCancel, constraints);
        // add actionlistener to button
        jbCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //System.out.println("actionPerformed");
                ;
                Frame4Week.releasePanel4Dinner();
            }
        });
    }
    //************************************************************************
    //*                     declare
    //************************************************************************
    protected JPanel mainPane;
    protected JLabel jlHeader, jlCook, jlEating;
    protected JTextField jtfCook;
    protected JTextArea jtaEating;
    protected JScrollPane jspEating;
    protected JButton jbOK, jbSave, jbCancel;
}
/*
        constraints.insets = new Insets(10, 10, 10, 10);

        // create a header
        jlHeader = new JLabel("BLA dd-mm-yy");
        jlHeader.setForeground(Color.WHITE);
        jlHeader.setFont(jlHeader.getFont().deriveFont(20.0f));
        constraints.anchor = GridBagConstraints.LINE_START;
        constraints.gridwidth = 2;
        constraints.gridx = 0;
        constraints.gridy = 0;
        mainPane.add(jlHeader, constraints);

        // create label for cook
        jlCook = new JLabel("Cook");
        jlCook.setForeground(Color.WHITE);
        jlCook.setFont(jlCook.getFont().deriveFont(16.0f));
        // add label cook
        constraints.anchor = GridBagConstraints.LINE_START;
        constraints.gridwidth = 1;
        constraints.gridx = 0;
        constraints.gridy = 1;
        mainPane.add(jlCook, constraints);

        jtfCook = new JTextField("Bla");
        // add textfield cook
        constraints.anchor = GridBagConstraints.LINE_START;
        constraints.weightx = 1.0;
        constraints.gridx = 1;
        constraints.gridy = 1;
        mainPane.add(jtfCook, constraints);

        // create label for eating
        jlEating = new JLabel("Eating");
        jlEating.setForeground(Color.WHITE);
        jlEating.setFont(jlEating.getFont().deriveFont(16.0f));
        // add label eating
        constraints.anchor = GridBagConstraints.PAGE_START;
        constraints.gridwidth = 1;
        constraints.gridx = 0;
        constraints.gridy = 2;
        mainPane.add(jlEating, constraints);

        jtaEating = new JTextArea();
        // create a scroll pane for the textarea
        jspEating = new JScrollPane(jtaEating, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        // add scrollpane eating
        constraints.anchor = GridBagConstraints.PAGE_START;
        constraints.gridwidth = 2;
        constraints.gridx = 1;
        constraints.gridy = 2;
        mainPane.add(jspEating, constraints);
        // bring some content into the text area
        jtaEating.append("A_Bla\nB_Bla\nC_Bla\nD_Bla\nE_Bla");

        // create a OK button
        jbOK = new JButton("BLA");
        jbOK.setForeground(Color.WHITE);
        jbOK.setFont(jbOK.getFont().deriveFont(14.0f));
        jbOK.setBackground(Color.RED);
        // add OK button
        constraints.anchor = GridBagConstraints.LINE_END;
        constraints.gridwidth = 1;
        constraints.gridx = 0;
        constraints.gridy = 3;
        mainPane.add(jbOK, constraints);
        // add actionlistener to button
        jbOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //System.out.println("actionPerformed");
                Frame4Week.releasePanel4Dinner();
            }
        });

        // create a Save button
        jbSave = new JButton("BLA");
        jbSave.setForeground(Color.WHITE);
        jbSave.setFont(jbSave.getFont().deriveFont(14.0f));
        jbSave.setBackground(Color.RED);
        // add Save button
        constraints.anchor = GridBagConstraints.LINE_END;
        constraints.gridwidth = 1;
        constraints.gridx = 1;
        constraints.gridy = 3;
        mainPane.add(jbSave, constraints);
        // add actionlistener to button
        jbSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //System.out.println("actionPerformed");
                ;
            }
        });

        // create a Cancel button
        jbCancel = new JButton("BLA");
        jbCancel.setForeground(Color.WHITE);
        jbCancel.setFont(jbCancel.getFont().deriveFont(14.0f));
        jbCancel.setBackground(Color.RED);
        // add Cancel button
        constraints.anchor = GridBagConstraints.LINE_END;
        constraints.gridwidth = 1;
        constraints.gridx = 2;
        constraints.gridy = 3;
        mainPane.add(jbCancel, constraints);
        // add actionlistener to button
        jbCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //System.out.println("actionPerformed");
                ;
            }
        });

package src;
//****************************************************************************
//*                     import
//****************************************************************************
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
//****************************************************************************
//*                     Panel4Dinner
//****************************************************************************
public class Panel4Dinner extends JPanel {
    public Panel4Dinner(int iIdx) {
        this.setPreferredSize(new Dimension(Frame4Week.FRAME_WIDTH, Frame4Week.FRAME_HEIGHT));
        this.setLayout(new BorderLayout());
        this.setBackground(Frame4Week.aDayOfWeek[iIdx].color);
        header.setForeground(Color.WHITE);
        header.setFont(header.getFont().deriveFont(20.0f));
        header.setText(Frame4Week.aDayOfWeek[iIdx].strNameOfDay + " " +
            Frame4Week.aDayOfWeek[iIdx].strDate);
        this.add(header, BorderLayout.NORTH);
        release.setBackground(Frame4Week.aDayOfWeek[iIdx].color);
        release.setForeground(Color.WHITE);
        release.setFont(release.getFont().deriveFont(14.0f));
        this.add(release, BorderLayout.SOUTH);
        release.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //System.out.println("actionPerformed");
                Frame4Week.releasePanel4Dinner();
            }
        });
    }
    //************************************************************************
    //*                     declare
    //************************************************************************
    protected JLabel header = new JLabel("", JLabel.CENTER);
    protected JButton release = new JButton(" OK ");
}
=======
package src;
//****************************************************************************
//*                     import
//****************************************************************************
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
//****************************************************************************
//*                     Panel4Dinner
//****************************************************************************
public class Panel4Dinner {
    public Panel4Dinner() {
        // create a JPanel
        mainPane = new JPanel();
        mainPane.setOpaque(true);
        mainPane.setBackground(Color.RED);
        // set a gridbaglayout
        mainPane.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);

        // create a header
        jlHeader = new JLabel("BLA dd-mm-yy");
        jlHeader.setForeground(Color.WHITE);
        jlHeader.setFont(jlHeader.getFont().deriveFont(18.0f));
        constraints.anchor = GridBagConstraints.LINE_START;
        constraints.gridx = 0;
        constraints.gridy = 0;
        mainPane.add(jlHeader, constraints);
        // create label for cook
        jlCook = new JLabel("Cook");
        jlCook.setForeground(Color.WHITE);
        constraints.gridx = 0;
        constraints.gridy = 1;
        mainPane.add(jlCook, constraints);
        // create text field for cook
        jtfCook = new JTextField("Bla", 15);
        constraints.gridx = 0;
        constraints.gridy = 2;
        mainPane.add(jtfCook, constraints);
        // create label for eating
        jlEating = new JLabel("Eating");
        jlEating.setForeground(Color.WHITE);
        constraints.gridx = 0;
        constraints.gridy = 3;
        mainPane.add(jlEating, constraints);
        // create text area for eating
        jtaEating = new JTextArea(5, 15);
        // bring some content into the text area
        jtaEating.append("A_Bla\nB_Bla\nC_Bla\nD_Bla\nE_Bla");
        // create scroll pane for eating, containing text area
        jspEating = new JScrollPane(jtaEating,
            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        constraints.gridx = 0;
        constraints.gridy = 4;
        mainPane.add(jspEating, constraints);
        // create OK button
        jbOK = new JButton("BLA");
        jbOK.setForeground(Color.WHITE);
        constraints.gridx = 0;
        constraints.gridy = 5;
        mainPane.add(jbOK, constraints);
        // add actionlistener to button
        jbOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //System.out.println("actionPerformed");
                Frame4Week.releasePanel4Dinner();
            }
        });
        // create Save button
        jbSave = new JButton("BLA");
        jbSave.setForeground(Color.WHITE);
        constraints.gridx = 0;
        constraints.gridy = 6;
        mainPane.add(jbSave, constraints);
        // add actionlistener to button
        jbSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //System.out.println("actionPerformed");
                ;
                Frame4Week.releasePanel4Dinner();
            }
        });
        // create Cancel button
        jbCancel = new JButton("BLA");
        jbCancel.setForeground(Color.WHITE);
        constraints.gridx = 0;
        constraints.gridy = 7;
        mainPane.add(jbCancel, constraints);
        // add actionlistener to button
        jbCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //System.out.println("actionPerformed");
                ;
                Frame4Week.releasePanel4Dinner();
            }
        });
    }
    //************************************************************************
    //*                     declare
    //************************************************************************
    protected JPanel mainPane;
    protected JLabel jlHeader, jlCook, jlEating;
    protected JTextField jtfCook;
    protected JTextArea jtaEating;
    protected JScrollPane jspEating;
    protected JButton jbOK, jbSave, jbCancel;
}
/*
        constraints.insets = new Insets(10, 10, 10, 10);

        // create a header
        jlHeader = new JLabel("BLA dd-mm-yy");
        jlHeader.setForeground(Color.WHITE);
        jlHeader.setFont(jlHeader.getFont().deriveFont(20.0f));
        constraints.anchor = GridBagConstraints.LINE_START;
        constraints.gridwidth = 2;
        constraints.gridx = 0;
        constraints.gridy = 0;
        mainPane.add(jlHeader, constraints);

        // create label for cook
        jlCook = new JLabel("Cook");
        jlCook.setForeground(Color.WHITE);
        jlCook.setFont(jlCook.getFont().deriveFont(16.0f));
        // add label cook
        constraints.anchor = GridBagConstraints.LINE_START;
        constraints.gridwidth = 1;
        constraints.gridx = 0;
        constraints.gridy = 1;
        mainPane.add(jlCook, constraints);

        jtfCook = new JTextField("Bla");
        // add textfield cook
        constraints.anchor = GridBagConstraints.LINE_START;
        constraints.weightx = 1.0;
        constraints.gridx = 1;
        constraints.gridy = 1;
        mainPane.add(jtfCook, constraints);

        // create label for eating
        jlEating = new JLabel("Eating");
        jlEating.setForeground(Color.WHITE);
        jlEating.setFont(jlEating.getFont().deriveFont(16.0f));
        // add label eating
        constraints.anchor = GridBagConstraints.PAGE_START;
        constraints.gridwidth = 1;
        constraints.gridx = 0;
        constraints.gridy = 2;
        mainPane.add(jlEating, constraints);

        jtaEating = new JTextArea();
        // create a scroll pane for the textarea
        jspEating = new JScrollPane(jtaEating, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        // add scrollpane eating
        constraints.anchor = GridBagConstraints.PAGE_START;
        constraints.gridwidth = 2;
        constraints.gridx = 1;
        constraints.gridy = 2;
        mainPane.add(jspEating, constraints);
        // bring some content into the text area
        jtaEating.append("A_Bla\nB_Bla\nC_Bla\nD_Bla\nE_Bla");

        // create a OK button
        jbOK = new JButton("BLA");
        jbOK.setForeground(Color.WHITE);
        jbOK.setFont(jbOK.getFont().deriveFont(14.0f));
        jbOK.setBackground(Color.RED);
        // add OK button
        constraints.anchor = GridBagConstraints.LINE_END;
        constraints.gridwidth = 1;
        constraints.gridx = 0;
        constraints.gridy = 3;
        mainPane.add(jbOK, constraints);
        // add actionlistener to button
        jbOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //System.out.println("actionPerformed");
                Frame4Week.releasePanel4Dinner();
            }
        });

        // create a Save button
        jbSave = new JButton("BLA");
        jbSave.setForeground(Color.WHITE);
        jbSave.setFont(jbSave.getFont().deriveFont(14.0f));
        jbSave.setBackground(Color.RED);
        // add Save button
        constraints.anchor = GridBagConstraints.LINE_END;
        constraints.gridwidth = 1;
        constraints.gridx = 1;
        constraints.gridy = 3;
        mainPane.add(jbSave, constraints);
        // add actionlistener to button
        jbSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //System.out.println("actionPerformed");
                ;
            }
        });

        // create a Cancel button
        jbCancel = new JButton("BLA");
        jbCancel.setForeground(Color.WHITE);
        jbCancel.setFont(jbCancel.getFont().deriveFont(14.0f));
        jbCancel.setBackground(Color.RED);
        // add Cancel button
        constraints.anchor = GridBagConstraints.LINE_END;
        constraints.gridwidth = 1;
        constraints.gridx = 2;
        constraints.gridy = 3;
        mainPane.add(jbCancel, constraints);
        // add actionlistener to button
        jbCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //System.out.println("actionPerformed");
                ;
            }
        });

package src;
//****************************************************************************
//*                     import
//****************************************************************************
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
//****************************************************************************
//*                     Panel4Dinner
//****************************************************************************
public class Panel4Dinner extends JPanel {
    public Panel4Dinner(int iIdx) {
        this.setPreferredSize(new Dimension(Frame4Week.FRAME_WIDTH, Frame4Week.FRAME_HEIGHT));
        this.setLayout(new BorderLayout());
        this.setBackground(Frame4Week.aDayOfWeek[iIdx].color);
        header.setForeground(Color.WHITE);
        header.setFont(header.getFont().deriveFont(20.0f));
        header.setText(Frame4Week.aDayOfWeek[iIdx].strNameOfDay + " " +
            Frame4Week.aDayOfWeek[iIdx].strDate);
        this.add(header, BorderLayout.NORTH);
        release.setBackground(Frame4Week.aDayOfWeek[iIdx].color);
        release.setForeground(Color.WHITE);
        release.setFont(release.getFont().deriveFont(14.0f));
        this.add(release, BorderLayout.SOUTH);
        release.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //System.out.println("actionPerformed");
                Frame4Week.releasePanel4Dinner();
            }
        });
    }
    //************************************************************************
    //*                     declare
    //************************************************************************
    protected JLabel header = new JLabel("", JLabel.CENTER);
    protected JButton release = new JButton(" OK ");
}
>>>>>>> 5839fb13e8eecee60acee4bb332b91b2b147bac9
*/