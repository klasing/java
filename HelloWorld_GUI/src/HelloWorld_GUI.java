package src;
//****************************************************************************
//*                     import
//****************************************************************************
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import src.TextfieldPanel;
//****************************************************************************
//*                     HelloWorld_GUI
//****************************************************************************
public class HelloWorld_GUI extends JFrame {
    private static HelloWorld_GUI helloWorld_GUI = new HelloWorld_GUI();
    private static TextfieldPanel textfieldPanel;
    public HelloWorld_GUI() {
        this.setTitle("HelloWorld_GUI");
        this.setDefaultCloseOperation(3);
        textfieldPanel = new TextfieldPanel();
        this.add(textfieldPanel);
        this.pack();
    }
    //************************************************************************
    //*                     main
    //************************************************************************
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                helloWorld_GUI.setVisible(true);
            }
        });
    }
}