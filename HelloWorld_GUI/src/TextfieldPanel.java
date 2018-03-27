<<<<<<< HEAD
package src;
//****************************************************************************
//*                     import
//****************************************************************************
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import src.HelloWorldTextfield;
//****************************************************************************
//*                     TextfieldPanel
//****************************************************************************
public class TextfieldPanel extends JPanel {
    protected static final int PANEL_WIDTH = 200;
    protected static final int PANEL_HEIGHT = 50;

    public TextfieldPanel() {
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        this.setBackground(Color.BLACK);
        this.add(new HelloWorldTextfield());
    }
=======
package src;
//****************************************************************************
//*                     import
//****************************************************************************
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import src.HelloWorldTextfield;
//****************************************************************************
//*                     TextfieldPanel
//****************************************************************************
public class TextfieldPanel extends JPanel {
    protected static final int PANEL_WIDTH = 200;
    protected static final int PANEL_HEIGHT = 50;

    public TextfieldPanel() {
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        this.setBackground(Color.BLACK);
        this.add(new HelloWorldTextfield());
    }
>>>>>>> 5839fb13e8eecee60acee4bb332b91b2b147bac9
}