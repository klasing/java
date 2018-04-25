package pack;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import org.apache.log4j.Logger;

/**
 * Defines action command for Stop button in {@link Panel4JavaThread}.
 */
public class StopAction extends AbstractAction {
    /**
     * Constructor
     * @param control {@link Control} instance
     * @param instanceNumber number for the {@link JavaThread} instance
     * @param panel4JavaThread {@link Panel4JavaThread} instance
     */
    public StopAction(final Control control, final int instanceNumber,
        final Panel4JavaThread panel4JavaThread) {
        super();
        putValue(ACTION_COMMAND_KEY, "Stop");

        logger = logger.getLogger(StopAction.class);
        logger.debug(LOG_TAG + "<<constructor>> StopAction()");

        this.control = control;
        this.instanceNumber = instanceNumber;
        this.panel4JavaThread = panel4JavaThread;
    }

    /**
     * Action performed on Stop button.
     * @param e ActionEvent, triggered by ActionListener
     */
    //************************************************************************
    //*                 actionPerformed
    //************************************************************************
    @Override
    public void actionPerformed(ActionEvent e) {
        logger.info("ActionCommand: " + e.getActionCommand() +
            "-" + instanceNumber);

        panel4JavaThread.jbRun.setEnabled(true);
        panel4JavaThread.jbInterrupt.setEnabled(false);
        panel4JavaThread.jbResume.setEnabled(false);
        panel4JavaThread.jbStop.setEnabled(false);

        // send message to control
        control.stopJavaThread(instanceNumber);
    }

    //************************************************************************
    //*                 declare
    //************************************************************************
    private static final String LOG_TAG = "*** " + StopAction.class.getSimpleName();

    private static Logger logger = null;

    private static Control control = null;

    private int instanceNumber = 0;
    private Panel4JavaThread panel4JavaThread = null;
}