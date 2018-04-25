package pack;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import org.apache.log4j.Logger;

/**
 * Defines action command for Resume button in {@link Panel4JavaThread}.
 */
public class ResumeAction extends AbstractAction {
    /**
     * Constructor
     * @param control {@link Control} instance
     * @param instanceNumber number for the {@link JavaThread} instance
     * @param panel4JavaThread {@link Panel4JavaThread} instance
     */
    public ResumeAction(final Control control, final int instanceNumber,
        final Panel4JavaThread panel4JavaThread) {
        super();
        putValue(ACTION_COMMAND_KEY, "Resume");

        logger = logger.getLogger(ResumeAction.class);
        logger.debug(LOG_TAG + "<<constructor>> ResumeAction()");

        this.control = control;
        this.instanceNumber = instanceNumber;
        this.panel4JavaThread = panel4JavaThread;
    }

    /**
     * Action performed on Resume button.
     * @param e ActionEvent, triggered by ActionListener
     */
    //************************************************************************
    //*                 actionPerformed
    //************************************************************************
    @Override
    public void actionPerformed(ActionEvent e) {
        logger.info("ActionCommand: " + e.getActionCommand() +
            "-" + instanceNumber);

        panel4JavaThread.jbRun.setEnabled(false);
        panel4JavaThread.jbInterrupt.setEnabled(true);
        panel4JavaThread.jbResume.setEnabled(false);
        panel4JavaThread.jbStop.setEnabled(true);

        // send message to control
        control.interruptJavaThread(instanceNumber, false);
    }

    //************************************************************************
    //*                 declare
    //************************************************************************
    private static final String LOG_TAG = "*** " + ResumeAction.class.getSimpleName();

    private static Logger logger = null;

    private static Control control = null;

    private int instanceNumber = 0;
    private Panel4JavaThread panel4JavaThread = null;
}