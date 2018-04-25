package pack;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import org.apache.log4j.Logger;

/**
 * Defines action command for Run button in {@link Panel4JavaThread}.
 */
public class RunAction extends AbstractAction {
    /**
     * Constructor
     * @param control {@link Control} instance
     * @param instanceNumber number for the {@link JavaThread} instance
     * @param panel4JavaThread {@link Panel4JavaThread} instance
     */
    public RunAction(final Control control, final int instanceNumber,
        final Panel4JavaThread panel4JavaThread) {
        super();
        putValue(ACTION_COMMAND_KEY, "Run");

        logger = logger.getLogger(RunAction.class);
        logger.debug(LOG_TAG + "<<constructor>> RunAction()");

        this.control = control;
        this.instanceNumber = instanceNumber;
        this.panel4JavaThread = panel4JavaThread;
    }

    /**
     * Action performed on Run button.
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

        panel4JavaThread.result = "";
        panel4JavaThread.jpbProgress.setValue(0);
        panel4JavaThread.jtfDelay.setText("" + control.JAVA_THREAD_INITIAL_DELAY);

        // send message to control
        control.runJavaThread(instanceNumber);
    }

    //************************************************************************
    //*                 declare
    //************************************************************************
    private static final String LOG_TAG = "*** " + RunAction.class.getSimpleName();

    private static Logger logger = null;

    private static Control control = null;

    private int instanceNumber = 0;
    private Panel4JavaThread panel4JavaThread = null;
}