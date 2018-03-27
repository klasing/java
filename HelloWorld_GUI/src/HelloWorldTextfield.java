<<<<<<< HEAD
package src;
//****************************************************************************
//*                     import
//****************************************************************************
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
//****************************************************************************
//*                     HelloWorldTextfield
//****************************************************************************
public class HelloWorldTextfield extends JTextField {
    protected static final String strMessage = "Hello World";

    public HelloWorldTextfield() {
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        this.setBackground(Color.BLACK);
        this.setForeground(Color.WHITE);
        this.setText(strMessage);
    }
=======
package src;
//****************************************************************************
//*                     import
//****************************************************************************
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
//****************************************************************************
//*                     HelloWorldTextfield
//****************************************************************************
public class HelloWorldTextfield extends JTextField {
    protected static final String strMessage = "Hello World";

    public HelloWorldTextfield() {
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        this.setBackground(Color.BLACK);
        this.setForeground(Color.WHITE);
        this.setText(strMessage);
    }
>>>>>>> 5839fb13e8eecee60acee4bb332b91b2b147bac9
}