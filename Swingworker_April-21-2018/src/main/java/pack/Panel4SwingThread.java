package pack;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

import org.apache.log4j.Logger;

/**
 * GUI panel for displaying {@link SwingThread} thread.
 */
public class Panel4SwingThread extends JPanel {
    /**
     * Constructor.
     * @param control {@link Control} instance
     */
    protected Panel4SwingThread(final Control control) {

        logger = logger.getLogger(Panel4SwingThread.class);
        logger.debug(LOG_TAG + "<<constructor>> Panel4SwingThread()");

        this.control = control;

        GridBagLayout gridBagLayout = new GridBagLayout();
        setLayout(gridBagLayout);

        GridBagConstraints constraint = new GridBagConstraints();
        constraint.insets = new Insets(5, 5, 5, 5);

        JLabel jlResult = new JLabel("Result");
        constraint.gridx = 0;
        constraint.gridy = 0;
        constraint.gridwidth = 1;
        constraint.gridheight = 1;
        constraint.anchor = GridBagConstraints.WEST;
        gridBagLayout.setConstraints(jlResult, constraint);
        add(jlResult);

        JLabel jlThread = new JLabel("Thread");
        constraint.gridx = 1;
        constraint.gridy = 0;
        constraint.gridwidth = 2;
        constraint.gridheight = 1;
        constraint.anchor = GridBagConstraints.CENTER;
        gridBagLayout.setConstraints(jlThread, constraint);
        add(jlThread);

        JLabel jlProgress = new JLabel("Progress");
        constraint.gridx = 3;
        constraint.gridy = 0;
        constraint.gridwidth = 1;
        constraint.gridheight = 1;
        constraint.anchor = GridBagConstraints.WEST;
        gridBagLayout.setConstraints(jlProgress, constraint);
        add(jlProgress);

        jtfResult = new JTextField(20);
        jtfResult.setEditable(false);
        jtfResult.setToolTipText("Output result");
        constraint.gridx = 0;
        constraint.gridy = 1;
        constraint.gridwidth = 1;
        constraint.gridheight = 1;
        constraint.anchor = GridBagConstraints.WEST;
        gridBagLayout.setConstraints(jtfResult, constraint);
        add(jtfResult);

        jbStart = new JButton("Start");
        jbStart.setEnabled(true);
        jbStart.setToolTipText("Start the background thread");
        jbStart.addActionListener(e -> {
            logger.debug("action_command: " + "Start");
            control.startSwingThread();
            jbStart.setEnabled(false);
            jbCancel.setEnabled(true);

            jtfResult.setText("");
            strResult = "";

            // button Cancel acts 'clicked', whenever the user hits the Enter/Return key
            control.getFrame().getRootPane()
                .setDefaultButton(Panel4SwingThread.jbCancel);
        });
        constraint.gridx = 1;
        constraint.gridy = 1;
        constraint.gridwidth = 1;
        constraint.gridheight = 1;
        constraint.anchor = GridBagConstraints.WEST;
        gridBagLayout.setConstraints(jbStart, constraint);
        add(jbStart);

        jbCancel = new JButton("Cancel");
        jbCancel.setEnabled(false);
        jbCancel.setToolTipText("Cancel the background thread");
        jbCancel.addActionListener(e -> {
            logger.debug("action_command: " + "Cancel");
            control.cancelSwingThread();
            jbStart.setEnabled(true);
            jbCancel.setEnabled(false);

            // button Start acts 'clicked', whenever the user hits the Enter/Return key
            control.getFrame().getRootPane()
                .setDefaultButton(Panel4SwingThread.jbStart);
        });
        constraint.gridx = 2;
        constraint.gridy = 1;
        constraint.gridwidth = 1;
        constraint.gridheight = 1;
        constraint.anchor = GridBagConstraints.WEST;
        gridBagLayout.setConstraints(jbCancel, constraint);
        add(jbCancel);

        jpbProgress = new JProgressBar();
        jpbProgress.setValue(0);
        jpbProgress.setStringPainted(true);
        jpbProgress.setToolTipText("Thread progress in percentage");
        constraint.gridx = 3;
        constraint.gridy = 1;
        constraint.gridwidth = 1;
        constraint.gridheight = 1;
        constraint.anchor = GridBagConstraints.WEST;
        gridBagLayout.setConstraints(jpbProgress, constraint);
        add(jpbProgress);

        JLabel jlState = new JLabel("State");
        constraint.gridx = 1;
        constraint.gridy = 2;
        constraint.gridwidth = 1;
        constraint.gridheight = 1;
        constraint.anchor = GridBagConstraints.EAST;
        gridBagLayout.setConstraints(jlState, constraint);
        add(jlState);

        jtfState = new JTextField(5);
        jtfState.setEditable(false);
        jtfState.setToolTipText("State of the background thread");
        constraint.gridx = 2;
        constraint.gridy = 2;
        constraint.gridwidth = 1;
        constraint.gridheight = 1;
        constraint.anchor = GridBagConstraints.WEST;
        gridBagLayout.setConstraints(jtfState, constraint);
        add(jtfState);
    }

    /**
     * Sets an intermediate result from the {@link SwingThread} into a text field.
     * @param string containing an intermediate result from the {@link SwingThread}
     */
    //************************************************************************
    //*                 setResultSwingThread
    //************************************************************************
    protected void setResultSwingThread(final String string) {

        logger.debug(LOG_TAG + ".setResultSwingThread()");

        strResult += string;
        jtfResult.setText(strResult);

    }

    /**
     * Sets the progress made by the {@link SwingThread} into a text field.
     * @param dProgress made by the {@link SwingThread} in percentage
     */
    //************************************************************************
    //*                 setProgressSwingThread
    //************************************************************************
    protected void setProgressSwingThread(final double dProgress) {

        logger.debug(LOG_TAG + ".setProgressSwingThread()");

        jpbProgress.setValue((int)dProgress);

    }

    /**
     * Sets the state the {@link SwingThread} is in into a text field.
     * @param state the {@link SwingThread} is in
     */
    //************************************************************************
    //*                 setStateSwingThread
    //************************************************************************
    protected void setStateSwingThread(final String state) {

        logger.debug(LOG_TAG + ".setStateSwingThread()");

        jtfState.setText(state);

        if (state.equals("DONE")) {
            jbStart.setEnabled(true);
            jbCancel.setEnabled(false);

            // button Start acts 'clicked', whenever the user hits the Enter/Return key
            control.getFrame().getRootPane()
                .setDefaultButton(Panel4SwingThread.jbStart);

            if (strResult.length() == 26)
                jpbProgress.setValue(100);
        }

    }

    //************************************************************************
    //*                 declare
    //************************************************************************
    private static final String LOG_TAG = "*** " + Panel4SwingThread.class.getSimpleName();

    private static Logger logger = null;

    private static Control control = null;

    protected static JButton jbStart = null;
    protected static JButton jbCancel = null;

    private static JTextField jtfResult = null;
    private static JTextField jtfState = null;

    private static JProgressBar jpbProgress = null;

    private static String strResult = "";
}
