package pack;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.log4j.Logger;

public class Panel4Control extends JPanel implements ActionListener {
    public Panel4Control(final Control control) {

        logger = logger.getLogger(Panel4Control.class);
        logger.debug(LOG_TAG + ".Panel4Control()");

        this.control = control;

        // add connect button
        jbConnect = new JButton("Connect");
        jbConnect.setToolTipText("Connect with the server");
        jbConnect.addActionListener(this);
        add(jbConnect);

        // add disconnect button
        jbDisconnect = new JButton("Disconnect");
        jbDisconnect.setToolTipText("Disconnect from the server");
        jbDisconnect.setEnabled(false);
        jbDisconnect.addActionListener(this);
        add(jbDisconnect);

        // add label status
        JLabel jLabel = new JLabel("Status");
        add(jLabel);

        // add textfield status
        jtfStatus = new JTextField("DISCONNECTED");
        jtfStatus.setToolTipText("Connection status");
        jtfStatus.setEditable(false);
        add(jtfStatus);

        // add border
        TitledBorder titledBorder = BorderFactory.createTitledBorder("Connection control");
        setBorder(titledBorder);
    }

    //************************************************************************
    //*                 actionPerformed
    //************************************************************************
    @Override
    public void actionPerformed(ActionEvent e) {

        logger.debug(LOG_TAG + ".actionPerformed()");

        String action_command = e.getActionCommand();
        logger.info("action_command: " + action_command);

        switch (action_command) {
            case "Connect":

                // get an email address from the user
                Object selectedValue = JOptionPane.showInputDialog(control.getFrame(),
                    "Email", "Connect", JOptionPane.QUESTION_MESSAGE,
                    null, null, "user@support.com");

                if (selectedValue == null)
                    // user cancelled the input dialog
                    return;

                logger.info("selectedValue: " + selectedValue.toString());

                // send message to control
                if (!control.connect(selectedValue.toString())) {
                    // return with an error message when connecting failed
                    JOptionPane.showMessageDialog(control.getFrame(),
                        "Sorry, can not establish a connection.", "Connect",
                        JOptionPane.ERROR_MESSAGE);

                    return;
                }

                // send message to control
                control.sendMessage(selectedValue.toString());

                // connection made
                jbConnect.setEnabled(false);
                jbDisconnect.setEnabled(true);

                jtfStatus.setText("CONNECTED");

                // enable send state
                control.enableSendState(true);

                break;
            case "Disconnect":

                // send message to control
                control.sendMessage("@disconnect");

                // connection broken
                jbConnect.setEnabled(true);
                jbDisconnect.setEnabled(false);

                jtfStatus.setText("DISCONNECTED");

                // disable send state
                control.enableSendState(false);

                break;
        } // eof switch

    }

    //************************************************************************
    //*                 disconnect
    //************************************************************************
    protected void disconnect() {

        logger.debug(LOG_TAG + ".disconnect()");

        // connection broken
        jbConnect.setEnabled(true);
        jbDisconnect.setEnabled(false);

        jtfStatus.setText("DISCONNECTED");

        // disable send state
        control.enableSendState(false);
    }

    //************************************************************************
    //*                 declare
    //************************************************************************
    private static final String LOG_TAG = "*** " + Panel4Control.class.getSimpleName();

    private static Logger logger = null;

    private static Control control = null;

    protected static JButton jbConnect = null;
    protected static JButton jbDisconnect = null;

    private static JTextField jtfStatus = null;

}