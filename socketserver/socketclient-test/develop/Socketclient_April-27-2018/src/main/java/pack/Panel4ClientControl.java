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

public class Panel4ClientControl extends JPanel implements ActionListener {
    public Panel4ClientControl(final Control control) {

        logger = logger.getLogger(Panel4ClientControl.class);
        logger.debug(LOG_TAG + ".Panel4ClientControl()");

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

                // send connect message to control
                if (!control.connect(selectedValue.toString())) {
                    // return with an error message when connecting failed
                    JOptionPane.showMessageDialog(control.getFrame(),
                        "Sorry, can not establish a connection.", "Connect",
                        JOptionPane.ERROR_MESSAGE);

                    return;
                }

                // send the email address to control
                control.sendUserName(selectedValue.toString());

                // connection made
                jbConnect.setEnabled(false);
                jbDisconnect.setEnabled(true);

                jtfStatus.setText("CONNECTED");

                // enable send panel
                control.enableSendPanel(true);

                break;
            case "Disconnect":

                jbConnect.setEnabled(true);
                jbDisconnect.setEnabled(false);

                jtfStatus.setText("DISCONNECTED");

                // disable send panel
                control.enableSendPanel(false);

                // send disconnect message to control
                control.disconnect();

                break;
        } // eof switch

    }

    //************************************************************************
    //*                 declare
    //************************************************************************
    private static final String LOG_TAG = "*** " + Panel4ClientControl.class.getSimpleName();

    private static Logger logger = null;

    private static Control control = null;

    protected static JButton jbConnect = null;
    protected static JButton jbDisconnect = null;

    private static JTextField jtfStatus = null;

}