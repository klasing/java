package pack;

import java.awt.Dimension;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    protected View(final Control control) {

        logger = logger.getLogger(View.class);
        logger.debug(LOG_TAG + "<<constructor>> View()");

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
        frame = new JFrame("SingWorker");

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
            .getResource("ic_swingworker.png"));
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

        // add tabbedpane
        panel4Tab = new Panel4Tab(control);
        frame.add(panel4Tab);

        frame.validate();
        // display the window
        frame.pack();
        frame.setVisible(true);

        // button Start acts 'clicked', whenever the user hits the Enter/Return key
        frame.getRootPane().setDefaultButton(Panel4SwingThread.jbStart);

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
    protected static final int MINIMUM_FRAME_WIDTH = 800;
    protected static final int MINIMUM_FRAME_HEIGHT = 600;

    private static Logger logger = null;

    private static Control control = null;

    private static JFrame frame = null;
    private static MenuBar menuBar = null;
    protected static Panel4Tab panel4Tab = null;

}
