package pack;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import org.apache.log4j.Logger;

/**
 * Defines action command for Commit button in {@link Panel4JavaThread}.
 */
public class CommitAction extends AbstractAction {
    /**
     * Constructor
     * @param control {@link Control} instance
     * @param instanceNumber number for the {@link JavaThread} instance
     * @param panel4JavaThread {@link Panel4JavaThread} instance
     */
    public CommitAction(final Control control, final int instanceNumber,
        final Panel4JavaThread panel4JavaThread) {
        super();
        putValue(ACTION_COMMAND_KEY, "Commit");

        logger = logger.getLogger(CommitAction.class);
        logger.debug(LOG_TAG + "<<constructor>> CommitAction()");

        this.control = control;
        this.instanceNumber = instanceNumber;
        this.panel4JavaThread = panel4JavaThread;
    }

    /**
     * Action performed on Commit button.
     * @param e ActionEvent, triggered by ActionListener
     */
    //************************************************************************
    //*                 actionPerformed
    //************************************************************************
    @Override
    public void actionPerformed(ActionEvent e) {
        logger.info("ActionCommand: " + e.getActionCommand() +
            "-" + instanceNumber);

        // send message to control
        control.setDelayJavaThread(instanceNumber,
            Integer.parseInt(panel4JavaThread.jtfDelay.getText()));
    }

    //************************************************************************
    //*                 declare
    //************************************************************************
    private static final String LOG_TAG = "*** " + CommitAction.class.getSimpleName();

    private static Logger logger = null;

    private static Control control = null;

    private int instanceNumber = 0;
    private Panel4JavaThread panel4JavaThread = null;
}