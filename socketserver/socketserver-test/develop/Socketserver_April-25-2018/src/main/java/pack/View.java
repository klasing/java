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

public class View extends ComponentAdapter {
    public View(final Control control) {

        logger = logger.getLogger(View.class);
        logger.debug(LOG_TAG + "<<constructor>> View()");

        this.control = control;

        // schedule a job for the event dispatching thread:
        // creating and showing this application's GUI
        // using lambda expression
        SwingUtilities.invokeLater(() -> createAndShowGui());

    }

    //************************************************************************
    //*                 createAndShowGui
    //************************************************************************
    private void createAndShowGui() {

        logger.debug(LOG_TAG + ".createAndShowGui()");

        // create and set up the window
        frame = new JFrame("Server socket");

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
            .getResource("ic_socketserver.png"));
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

        // add panel for socket control
        frame.add(new Panel4SocketControl(control), BorderLayout.NORTH);
        // add panel for server connection
        panel4ServerConnection = new Panel4ServerConnection(control);
        frame.add(panel4ServerConnection, BorderLayout.CENTER);

        frame.validate();
        // display the window
        frame.pack();
        frame.setVisible(true);

        // button Start acts 'clicked', whenever the user hits the Enter/Return key
        frame.getRootPane().setDefaultButton(Panel4SocketControl.jbStart);
    }

    //************************************************************************
    //*                 componentShown
    //************************************************************************
    public void componentShown(ComponentEvent e) {

        logger.debug(LOG_TAG + ".componentShown()");

        control.frameIsVisible();
    }

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
    protected static Panel4ServerConnection panel4ServerConnection = null;
}