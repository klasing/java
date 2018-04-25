package pack;

import java.awt.Color;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

import org.apache.log4j.Logger;

/**
 * Panel for showing a {@link ConsumerThread}.
 */
public class Panel4Consumer extends JPanel implements ActionListener {
    /**
     * Constructor.
     * @param control {@link Control}
     */
    protected Panel4Consumer(final Control control) {

        logger = logger.getLogger(Panel4Consumer.class);
        logger.debug(LOG_TAG + "<<constructor>> Panel4Consumer()");

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
        jtfName.setToolTipText("Name for the consumer thread");
        constraint.gridx = 1;
        constraint.gridy = 0;
        constraint.gridwidth = 2;
        constraint.gridheight = 1;
        constraint.anchor = GridBagConstraints.WEST;
        gridBagLayout.setConstraints(jtfName, constraint);
        add(jtfName);

        JLabel jlDelay = new JLabel("Delay (ms)");
        constraint.gridx = 0;
        constraint.gridy = 1;
        constraint.gridwidth = 1;
        constraint.gridheight = 1;
        constraint.anchor = GridBagConstraints.WEST;
        gridBagLayout.setConstraints(jlDelay, constraint);
        add(jlDelay);

        jtfDelay = new JTextField(
            "" + control.JAVA_THREAD_INITIAL_DELAY, 6);
        jtfDelay.setToolTipText("Thread delay in millisecond");
        constraint.gridx = 1;
        constraint.gridy = 1;
        constraint.gridwidth = 1;
        constraint.gridheight = 1;
        constraint.anchor = GridBagConstraints.WEST;
        gridBagLayout.setConstraints(jtfDelay, constraint);
        add(jtfDelay);

        jbDelay = new JButton("Commit");
        jbDelay.addActionListener(this);
        jbDelay.setToolTipText("Commit the delay to the consumer thread");
        constraint.gridx = 2;
        constraint.gridy = 1;
        constraint.gridwidth = 1;
        constraint.gridheight = 1;
        constraint.anchor = GridBagConstraints.WEST;
        gridBagLayout.setConstraints(jbDelay, constraint);
        add(jbDelay);

        jbRun = new JButton("Run");
        jbRun.addActionListener(this);
        jbRun.setToolTipText("Run the consumer thread");
        constraint.gridx = 0;
        constraint.gridy = 2;
        constraint.gridwidth = 1;
        constraint.gridheight = 1;
        constraint.anchor = GridBagConstraints.WEST;
        gridBagLayout.setConstraints(jbRun, constraint);
        add(jbRun);

        jbInterrupt = new JButton("Interrupt");
        jbInterrupt.addActionListener(this);
        jbInterrupt.setEnabled(false);
        jbInterrupt.setToolTipText("Interrupt the consumer thread");
        constraint.gridx = 1;
        constraint.gridy = 2;
        constraint.gridwidth = 1;
        constraint.gridheight = 1;
        constraint.anchor = GridBagConstraints.WEST;
        gridBagLayout.setConstraints(jbInterrupt, constraint);
        add(jbInterrupt);

        jbResume = new JButton("Resume");
        jbResume.addActionListener(this);
        jbResume.setEnabled(false);
        jbResume.setToolTipText("Resume the consumer thread");
        constraint.gridx = 2;
        constraint.gridy = 2;
        constraint.gridwidth = 1;
        constraint.gridheight = 1;
        constraint.anchor = GridBagConstraints.WEST;
        gridBagLayout.setConstraints(jbResume, constraint);
        add(jbResume);

        jbStop = new JButton("Stop");
        jbStop.addActionListener(this);
        jbStop.setEnabled(false);
        jbStop.setToolTipText("Stop the consumer thread");
        constraint.gridx = 3;
        constraint.gridy = 2;
        constraint.gridwidth = 1;
        constraint.gridheight = 1;
        constraint.anchor = GridBagConstraints.WEST;
        gridBagLayout.setConstraints(jbStop, constraint);
        add(jbStop);

        JLabel jlState = new JLabel("State");
        constraint.gridx = 0;
        constraint.gridy = 3;
        constraint.gridwidth = 1;
        constraint.gridheight = 1;
        constraint.anchor = GridBagConstraints.WEST;
        gridBagLayout.setConstraints(jlState, constraint);
        add(jlState);

        jtfState = new JTextField(12);
        jtfState.setEditable(false);
        jtfState.setToolTipText("State of the consumer thread");
        constraint.gridx = 1;
        constraint.gridy = 3;
        constraint.gridwidth = 2;
        constraint.gridheight = 1;
        constraint.anchor = GridBagConstraints.WEST;
        gridBagLayout.setConstraints(jtfState, constraint);
        add(jtfState);

        titledBorder = BorderFactory.createTitledBorder("title");
        setBorder(titledBorder);
    }

    /**
     * Reacts on button clicks.
     * Sends a message to the {@link Control} instance, depending on which
     * item triggered the event.
     * @param e ActionEvent, triggered by an ActionListener
     */
    //************************************************************************
    //*                 actionPerformed
    //************************************************************************
    @Override
    public void actionPerformed(ActionEvent e) {
        logger.debug(LOG_TAG + ".actionPerformed()");

        String action_command = e.getActionCommand();
        logger.info("action_command: " + action_command);

        switch (action_command) {
            case "Commit":

                // send message to control
                control.setDelayConsumerThread(
                    Integer.parseInt(jtfDelay.getText()));

                break;
            case "Run":
                jbRun.setEnabled(false);
                jbInterrupt.setEnabled(true);
                jbResume.setEnabled(false);
                jbStop.setEnabled(true);

                // send message to control
                control.runConsumerThread();

                break;
            case "Interrupt":
                jbRun.setEnabled(false);
                jbInterrupt.setEnabled(false);
                jbResume.setEnabled(true);
                jbStop.setEnabled(false);

                // send message to control
                control.interruptConsumerThread(true);

                break;
            case "Resume":
                jbRun.setEnabled(false);
                jbInterrupt.setEnabled(true);
                jbResume.setEnabled(false);
                jbStop.setEnabled(true);

                // send message to control
                control.interruptConsumerThread(false);

                break;
            case "Stop":
                jbRun.setEnabled(true);
                jbInterrupt.setEnabled(false);
                jbResume.setEnabled(false);
                jbStop.setEnabled(false);

                // send message to control
                control.stopConsumerThread();

                break;
        } // eof switch

    }

    //************************************************************************
    //*                 declare
    //************************************************************************
    private static final String LOG_TAG = "*** " + Panel4Consumer.class.getSimpleName();

    private static Logger logger = null;

    private static Control control = null;

    protected TitledBorder titledBorder = null;

    protected JTextField jtfName = null;
    protected JTextField jtfDelay = null;
    protected volatile JTextField jtfState = null;

    protected JButton jbDelay = null;
    protected JButton jbRun = null;
    protected volatile JButton jbInterrupt = null;
    protected volatile JButton jbResume = null;
    protected JButton jbStop = null;

}
