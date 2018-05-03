package pack;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.log4j.Logger;

/**
 * Class with the GUI, that controls the {@link Server}.
 */
public class Panel4Control extends JPanel implements ActionListener {

    /**
     * Constructor
     * @param control {@link Control} instance
     */
    public Panel4Control(final Control control) {

        logger = logger.getLogger(Panel4Control.class);
        logger.debug(LOG_TAG + "<<constructor>> Panel4Control()");

        this.control = control;

        // add start button
        jbStart = new JButton("Start");
        jbStart.addActionListener(this);
        add(jbStart);

        // add stop button
        jbStop = new JButton("Stop");
        jbStop.setEnabled(false);
        jbStop.addActionListener(this);
        add(jbStop);

        // add label status
        JLabel jLabel = new JLabel("Status");
        add(jLabel);

        // add textfield status
        jtfStatus = new JTextField("DOWN");
        jtfStatus.setEditable(false);
        add(jtfStatus);

        // add border
        TitledBorder titledBorder = BorderFactory.createTitledBorder("Server control");
        setBorder(titledBorder);
    }

    /**
     * Reacts on button clicks.
     * Sends a message to the {@link Control} instance, depending on which
     * item triggered the event.
     * @param e ActionEvent, triggered by an actionlistener
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
            case "Start":

                jbStart.setEnabled(false);
                jbStop.setEnabled(true);

                jtfStatus.setText("UP");

                // button "Stop" acts 'clicked', whenever the user hits the Enter/Return key
                control.getFrame().getRootPane().setDefaultButton(jbStop);

                // send message to control
                control.start();

                break;
            case "Stop":

                jbStart.setEnabled(true);
                jbStop.setEnabled(false);

                jtfStatus.setText("DOWN");

                // button "Start" acts 'clicked', whenever the user hits the Enter/Return key
                control.getFrame().getRootPane().setDefaultButton(jbStart);

                // send message to control
                control.stop();

                break;
        } // eof switch

    }

    //************************************************************************
    //*                 declare
    //************************************************************************
    private static final String LOG_TAG = "*** " + Panel4Control.class.getSimpleName();

    private static Logger logger = null;

    private static Control control = null;

    protected static JButton jbStart = null;
    private static JButton jbStop = null;

    private static JTextField jtfStatus = null;

}