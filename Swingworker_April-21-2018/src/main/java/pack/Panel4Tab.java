package pack;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import javax.swing.JTabbedPane;

import org.apache.log4j.Logger;

/**
 * Displays the tab pane on the frame.
 */
public class Panel4Tab extends JTabbedPane implements ChangeListener {
    /**
     * Constructor.
     * @param control {@link Control} instance
     */
    protected Panel4Tab(final Control control) {

        logger = logger.getLogger(Panel4Tab.class);
        logger.debug(LOG_TAG + "<<constructor>> Panel4Tab()");

        this.control = control;

        panel4SwingThread = new Panel4SwingThread(control);
        addTab("SwingThread", null, panel4SwingThread, "Show panel for Swing thread");

        panel4TwoJavaThread = new Panel4TwoJavaThread(control);
        addTab("JavaThread", null, panel4TwoJavaThread, "Show panel for two Java threads");

        panel4ProdCons = new Panel4ProdCons(control);
        addTab("Prod/Cons", null, panel4ProdCons, "Show panel for Producer/Consumer thread");

        // add listener for tab changes
        addChangeListener(this);
    }

    /**
     * The state changes on the tab pane, indicating the selected tab.
     * @param e ChangeEvent, triggered by a ChangeListener
     */
    //************************************************************************
    //*                 stateChanged
    //************************************************************************
    public void stateChanged(ChangeEvent e) {

        logger.debug(LOG_TAG + ".stateChanged()");

        int selectedIndex = ((JTabbedPane)e.getSource()).getSelectedIndex();
        logger.info("state_changed to: " + selectedIndex);

        switch(selectedIndex) {
            case 0:
                // panel4SwingThread
                // button Start acts 'clicked',
                // whenever the user hits the Enter/Return key
                control.getFrame().getRootPane()
                    .setDefaultButton(Panel4SwingThread.jbStart);
                break;
            case 1:
                // panel4TwoJavaThread
                break;
            case 2:
                // panel4ProdCons
                break;
        } // eof switch
    }

    //************************************************************************
    //*                 declare
    //************************************************************************
    private static final String LOG_TAG = "*** " + Panel4Tab.class.getSimpleName();

    private static Logger logger = null;

    private static Control control = null;

    protected static Panel4SwingThread panel4SwingThread = null;
    protected static Panel4TwoJavaThread panel4TwoJavaThread = null;
    protected static Panel4ProdCons panel4ProdCons = null;
}
