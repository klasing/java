package pack;

import java.awt.Color;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

import org.apache.log4j.Logger;

/**
 * Panel for showing a {@link JavaThread}.
 */
public class Panel4JavaThread extends JPanel {
    /**
     * Constructor.
     * @param control {@link Control} instance
     * @param instanceNumber number for the {@link JavaThread} instance
     */
    protected Panel4JavaThread(final Control control,
        final int instanceNumber) {

        logger = logger.getLogger(Panel4JavaThread.class);
        logger.debug(LOG_TAG + "<<constructor>> Panel4JavaThread()");

        this.control = control;

        GridBagLayout gridBagLayout = new GridBagLayout();
        setLayout(gridBagLayout);

        GridBagConstraints constraint = new GridBagConstraints();
        constraint.insets = new Insets(5, 5, 5, 5);

        JLabel jlName = new JLabel("Name");
        constraint.gridx = 0;
        constraint.gridy = 0;
        constraint.gridwidth = 1;
        constraint.gridheight = 1;
        constraint.anchor = GridBagConstraints.WEST;
        gridBagLayout.setConstraints(jlName, constraint);
        add(jlName);

        jtfName = new JTextField(6);
        jtfName.setEditable(false);
        jtfName.setToolTipText("Name for the background thread");
        constraint.gridx = 1;
        constraint.gridy = 0;
        constraint.gridwidth = 2;
        constraint.gridheight = 1;
        constraint.anchor = GridBagConstraints.WEST;
        gridBagLayout.setConstraints(jtfName, constraint);
        add(jtfName);

        JLabel jlResult = new JLabel("Result");
        constraint.gridx = 0;
        constraint.gridy = 1;
        constraint.gridwidth = 1;
        constraint.gridheight = 1;
        constraint.anchor = GridBagConstraints.WEST;
        gridBagLayout.setConstraints(jlResult, constraint);
        add(jlResult);

        jtfResult = new JTextField(20);
        jtfResult.setEditable(false);
        jtfResult.setToolTipText("Output result");
        constraint.gridx = 1;
        constraint.gridy = 1;
        constraint.gridwidth = 3;
        constraint.gridheight = 1;
        constraint.anchor = GridBagConstraints.WEST;
        gridBagLayout.setConstraints(jtfResult, constraint);
        add(jtfResult);

        JLabel jlProgress = new JLabel("Progress");
        constraint.gridx = 0;
        constraint.gridy = 2;
        constraint.gridwidth = 1;
        constraint.gridheight = 1;
        constraint.anchor = GridBagConstraints.WEST;
        gridBagLayout.setConstraints(jlProgress, constraint);
        add(jlProgress);

        jpbProgress = new JProgressBar();
        jpbProgress.setValue(0);
        jpbProgress.setStringPainted(true);
        jpbProgress.setToolTipText("Thread progress in percentage");
        constraint.gridx = 1;
        constraint.gridy = 2;
        constraint.gridwidth = 2;
        constraint.gridheight = 1;
        constraint.anchor = GridBagConstraints.WEST;
        gridBagLayout.setConstraints(jpbProgress, constraint);
        add(jpbProgress);

        JLabel jlDelay = new JLabel("Delay (ms)");
        constraint.gridx = 0;
        constraint.gridy = 3;
        constraint.gridwidth = 1;
        constraint.gridheight = 1;
        constraint.anchor = GridBagConstraints.WEST;
        gridBagLayout.setConstraints(jlDelay, constraint);
        add(jlDelay);

        jtfDelay = new JTextField(
            "" + control.JAVA_THREAD_INITIAL_DELAY, 6);
        jtfDelay.setToolTipText("Thread delay in millisecond");
        constraint.gridx = 1;
        constraint.gridy = 3;
        constraint.gridwidth = 1;
        constraint.gridheight = 1;
        constraint.anchor = GridBagConstraints.WEST;
        gridBagLayout.setConstraints(jtfDelay, constraint);
        add(jtfDelay);

        CommitAction commitAction = new CommitAction(
            control, instanceNumber, this);
        jbDelay = new JButton(commitAction);
        jbDelay.setText("Commit");
        jbDelay.setToolTipText("Commit the delay to the background thread");
        constraint.gridx = 2;
        constraint.gridy = 3;
        constraint.gridwidth = 1;
        constraint.gridheight = 1;
        constraint.anchor = GridBagConstraints.WEST;
        gridBagLayout.setConstraints(jbDelay, constraint);
        add(jbDelay);

        RunAction runAction = new RunAction(
            control, instanceNumber, this);
        jbRun = new JButton(runAction);
        jbRun.setText("Run");
        jbRun.setToolTipText("Run the background thread");
        constraint.gridx = 0;
        constraint.gridy = 4;
        constraint.gridwidth = 1;
        constraint.gridheight = 1;
        constraint.anchor = GridBagConstraints.WEST;
        gridBagLayout.setConstraints(jbRun, constraint);
        add(jbRun);

        InterruptAction interruptAction = new InterruptAction(
            control, instanceNumber, this);
        jbInterrupt = new JButton(interruptAction);
        jbInterrupt.setText("Interrupt");
        jbInterrupt.setEnabled(false);
        jbInterrupt.setToolTipText("Interrupt the background thread");
        constraint.gridx = 1;
        constraint.gridy = 4;
        constraint.gridwidth = 1;
        constraint.gridheight = 1;
        constraint.anchor = GridBagConstraints.WEST;
        gridBagLayout.setConstraints(jbInterrupt, constraint);
        add(jbInterrupt);

        ResumeAction resumeAction = new ResumeAction(
            control, instanceNumber, this);
        jbResume = new JButton(resumeAction);
        jbResume.setText("Resume");
        jbResume.setEnabled(false);
        jbResume.setToolTipText("Resume the background thread");
        constraint.gridx = 2;
        constraint.gridy = 4;
        constraint.gridwidth = 1;
        constraint.gridheight = 1;
        constraint.anchor = GridBagConstraints.WEST;
        gridBagLayout.setConstraints(jbResume, constraint);
        add(jbResume);

        StopAction stopAction = new StopAction(
            control, instanceNumber, this);
        jbStop = new JButton(stopAction);
        jbStop.setText("Stop");
        jbStop.setEnabled(false);
        jbStop.setToolTipText("Stop the background thread");
        constraint.gridx = 3;
        constraint.gridy = 4;
        constraint.gridwidth = 1;
        constraint.gridheight = 1;
        constraint.anchor = GridBagConstraints.WEST;
        gridBagLayout.setConstraints(jbStop, constraint);
        add(jbStop);

        JLabel jlState = new JLabel("State");
        constraint.gridx = 0;
        constraint.gridy = 5;
        constraint.gridwidth = 1;
        constraint.gridheight = 1;
        constraint.anchor = GridBagConstraints.WEST;
        gridBagLayout.setConstraints(jlState, constraint);
        add(jlState);

        jtfState = new JTextField(12);
        jtfState.setEditable(false);
        jtfState.setToolTipText("State of the background thread");
        constraint.gridx = 1;
        constraint.gridy = 5;
        constraint.gridwidth = 2;
        constraint.gridheight = 1;
        constraint.anchor = GridBagConstraints.WEST;
        gridBagLayout.setConstraints(jtfState, constraint);
        add(jtfState);

        titledBorder = BorderFactory.createTitledBorder("title");
        setBorder(titledBorder);
    }

    /**
     * Sets an intermediate result from a {@link JavaThread}.
     * @param uniChar an intermediate result, produced by a {@link JavaThread}
     */
    //************************************************************************
    //*                 setResultJavaThread
    //************************************************************************
    protected void setResultJavaThread(final char uniChar) {

        logger.debug(LOG_TAG + ".setResultJavaThread()");

        result += uniChar;
        jtfResult.setText(result);

    }

    //************************************************************************
    //*                 declare
    //************************************************************************
    private static final String LOG_TAG = "*** " + Panel4JavaThread.class.getSimpleName();

    private static Logger logger = null;

    private static Control control = null;

    protected TitledBorder titledBorder = null;

    protected JTextField jtfName = null;
    protected JTextField jtfResult = null;
    protected JTextField jtfDelay = null;
    protected JTextField jtfState = null;

    protected JProgressBar jpbProgress = null;

    protected JButton jbDelay = null;
    protected JButton jbRun = null;
    protected JButton jbInterrupt = null;
    protected JButton jbResume = null;
    protected JButton jbStop = null;

    protected String result = "";

}
