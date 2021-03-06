package pack;

import java.awt.BorderLayout;
import java.awt.Dimension;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.apache.log4j.Logger;

/**
 * The application's view logic.
 */
public class View extends ComponentAdapter {

    /**
     * Constructor.
     * @param control {@link Control} instance
     */
    public View(final Control control) {

        logger = logger.getLogger(View.class);
        logger.debug(LOG_TAG + ".View()");

        this.control = control;

        // schedule a job for the event dispatching thread:
        // creating and showing this application's GUI
        // using lambda expression
        SwingUtilities.invokeLater(() -> createAndShowGui());

    }

    /**
     * Builds and shows the application's GUI.
     */
    //************************************************************************
    //*                 createAndShowGui
    //************************************************************************
    private void createAndShowGui() {

        logger.debug(LOG_TAG + ".createAndShowGui()");

        // create and set up the window
        frame = new JFrame("Client");

        // set initial size
        frame.setPreferredSize(new Dimension(MINIMUM_FRAME_WIDTH,
            MINIMUM_FRAME_HEIGHT));

        // set default close operation
        // not used, look at addWindowListener
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // try to get an icon on the frame, from within a jar
        // from an instance: getClass()
        // from a static context: HelloWorld.class
        // for Gradle: place icon in src/main/resources
        ImageIcon img = new ImageIcon(View.class.getClassLoader()
            .getResource("ic_socketclient.png"));
        frame.setIconImage(img.getImage());

        // add menubar, uses .exit() method in control
        menuBar = new MenuBar(control);
        frame.setJMenuBar(menuBar);

        // add listener
        frame.addComponentListener(this);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                logger.debug(LOG_TAG + ".windowClosing()");

                control.exit(0);
            }
        });

        // set layout for frame
        frame.setLayout(new BorderLayout());

        // add panel for client control
        panel4Control = new Panel4Control(control);
        frame.add(panel4Control, BorderLayout.NORTH);

        // add panel for receive
        panel4Receive = new Panel4Receive(control);
        frame.add(panel4Receive, BorderLayout.CENTER);

        // add panel for send
        panel4Send = new Panel4Send(control);
        frame.add(panel4Send, BorderLayout.SOUTH);

        frame.validate();
        // display the window
        frame.pack();
        frame.setVisible(true);

        // set focus to textfield message
        Panel4Send.jtfMessage.requestFocusInWindow();
        // button "Connect" acts 'clicked', whenever the user hits the Enter/Return key
        frame.getRootPane().setDefaultButton(Panel4Control.jbConnect);
    }

    /**
     * Send message to {@link Control} instance, when the GUI frame is build and visible.
     * @param e ComponentEvent, triggered by a ComponentListener
     */
    //************************************************************************
    //*                 componentShown
    //************************************************************************
    public void componentShown(ComponentEvent e) {

        logger.debug(LOG_TAG + ".componentShown()");

        control.frameIsVisible();
    }

    /**
     * Returns the frame in which the application is visible.
     * @return frame the frame in which the application is visible
     */
    //************************************************************************
    //*                 getFrame
    //************************************************************************
    protected JFrame getFrame() {
        logger.debug(LOG_TAG + ".getFrame()");

        return frame;
    }

    //************************************************************************
    //*                 declare
    //************************************************************************
    private static final String LOG_TAG = "*** " + View.class.getSimpleName();
    protected static final int MINIMUM_FRAME_WIDTH = 400;
    protected static final int MINIMUM_FRAME_HEIGHT = 400;

    private static Logger logger = null;

    private static Control control = null;

    private static JFrame frame = null;
    private static MenuBar menuBar = null;

    protected static Panel4Control panel4Control = null;
    protected static Panel4Receive panel4Receive = null;
    protected static Panel4Send panel4Send = null;
}