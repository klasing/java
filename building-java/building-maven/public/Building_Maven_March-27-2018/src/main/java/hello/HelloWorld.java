package hello;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import org.apache.log4j.Logger;
import org.joda.time.LocalTime;

public class HelloWorld {
    public static void main(String[] args) {

        logger = logger.getLogger(HelloWorld.class);
        logger.debug(LOG_TAG + ".main()");

        greeter = new Greeter();

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
        JFrame frame = new JFrame("Hello Gradle World");
        // set initial size
        frame.setPreferredSize(new Dimension(MINIMUM_FRAME_WIDTH,
            MINIMUM_FRAME_HEIGHT));
        // set the default close operation
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // try to get an icon on the frame, from within a jar
        // from an instance: getClass()
        // from a static context: HelloWorld.class
        // for Gradle: place icon in src/main/resources
        ImageIcon img = new ImageIcon(HelloWorld.class.getClassLoader()
            .getResource("Icon-Small48.png"));
        frame.setIconImage(img.getImage());

        frame.setLayout(new BorderLayout());

        JPanel jPanelNorth = new JPanel();
        frame.add(jPanelNorth, BorderLayout.NORTH);
        JLabel jlLocalTime = new JLabel("Local time");
        jPanelNorth.add(jlLocalTime);
        JTextField jtfLocalTime = new JTextField(12);
        jtfLocalTime.setEditable(false);

        LocalTime currentTime = new LocalTime();
        jtfLocalTime.setText(currentTime.toString());

        jPanelNorth.add(jtfLocalTime);

        JPanel jPanelCenter = new JPanel();
        frame.add(jPanelCenter, BorderLayout.CENTER);
        JLabel jLabel = new JLabel("Message");
        jPanelCenter.add(jLabel);
        jTextField = new JTextField(greeter.sayHello(), 12);
        jTextField.setEditable(false);
        jPanelCenter.add(jTextField);

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
    protected static final int MINIMUM_FRAME_HEIGHT = 100;

    private static Logger logger = null;
    private static Greeter greeter = null;
    private static JTextField jTextField = null;
}