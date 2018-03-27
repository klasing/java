package hello;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import org.apache.log4j.Logger;

public class HelloWorld {
    public static void main(String[] args) {

        logger = Logger.getLogger(HelloWorld.class);
        logger.debug(LOG_TAG + ".main()");

        // schedule a job for the event dispatching thread:
        // creating and showing this application's GUI
        // using lambda expression
        SwingUtilities.invokeLater(() -> createAndShowGui());

    }
    //************************************************************************
    //*                 createAndShowGui
    //************************************************************************
    private static void createAndShowGui() {
        logger.debug(LOG_TAG + ".createAndShowGui()");

        // create and set up the window
        JFrame frame = new JFrame("Hello Ant World");
        // set initial size
        frame.setPreferredSize(new Dimension(MINIMUM_FRAME_WIDTH,
            MINIMUM_FRAME_HEIGHT));
        // set the default close operation
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // try to get an icon on the frame, from within a jar
        // from an instance: getClass()
        // from a static context: HelloWorld.class
        ImageIcon img = new ImageIcon(HelloWorld.class.getClassLoader()
            .getResource("hello/resource/Icon-Small48.png"));
        frame.setIconImage(img.getImage());

        JPanel jPanel = new JPanel();
        frame.add(jPanel);
        JLabel jLabel = new JLabel("Message");
        jPanel.add(jLabel);
        jTextField = new JTextField("Hello Ant World", 10);
        jTextField.setEditable(false);
        jPanel.add(jTextField);

        frame.validate();
        // display the window
        frame.pack();
        frame.setVisible(true);

    }
    //************************************************************************
    //*                 declare
    //************************************************************************
    private static final String LOG_TAG = "*** " + HelloWorld.class.getSimpleName();

    protected static final int MINIMUM_FRAME_WIDTH = 350;
    protected static final int MINIMUM_FRAME_HEIGHT = 80;

    private static Logger logger = null;
    private static JTextField jTextField = null;

}