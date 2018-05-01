package pack;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import javax.swing.BorderFactory;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.log4j.Logger;

public class Panel4Send extends JPanel implements ActionListener,
    DocumentListener {

    public Panel4Send(final Control control) {

        logger = logger.getLogger(Panel4Send.class);
        logger.debug(LOG_TAG + ".Panel4Send()");

        this.control = control;

        // add label message
        JLabel jLabel = new JLabel("Message");
        add(jLabel);

        // add textfield message
        jtfMessage = new JTextField(20);
        jtfMessage.setToolTipText("Enter a message to send");
        jtfMessage.setEditable(false);
        jtfMessage.getDocument().addDocumentListener(this);
        add(jtfMessage);

        // add connect button
        jbSend = new JButton("Send");
        jbSend.setToolTipText("Send the message");
        // send button is disabled, as long as no text is entered in the
        // message-textfield
        jbSend.setEnabled(false);
        jbSend.addActionListener(this);
        add(jbSend);

        // add border
        TitledBorder titledBorder = BorderFactory.createTitledBorder("Send");
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
            case "Send":

                control.sendMessage(jtfMessage.getText());

                break;
        } // eof switch

    }

    //************************************************************************
    //*                 documentListener methods
    //************************************************************************
    public void insertUpdate(DocumentEvent e) {
        //logger.debug(LOG_TAG + ".insertUpdate()");

        jbSend.setEnabled(true);
    }
    public void removeUpdate(DocumentEvent e) {
        //logger.debug(LOG_TAG + ".removeUpdate()");

        if (jtfMessage.getText().length() == 0)
            jbSend.setEnabled(false);
    }
    public void changedUpdate(DocumentEvent e) {
        //logger.debug(LOG_TAG + ".changedUpdate()");

        // attribute(s) of jtfMessage has (have) been changed
        // do nothing
    }

    //************************************************************************
    //*                 enableSendState
    //************************************************************************
    protected void enableSendState(final boolean enable) {

        logger.debug(LOG_TAG + ".enableSendState()");

        if (enable) {
            jtfMessage.setEditable(true);
            // button "Send" acts 'clicked', whenever the user hits the Enter/Return key
            control.getFrame().getRootPane().setDefaultButton(jbSend);
        } else {
            jtfMessage.setEditable(false);
            // disable "Send" button
            jbSend.setEnabled(false);
        }
    }

    //************************************************************************
    //*                 declare
    //************************************************************************
    private static final String LOG_TAG = "*** " + Panel4Send.class.getSimpleName();

    private static Logger logger = null;

    private static Control control = null;

    protected static JTextField jtfMessage = null;

    private static JButton jbSend = null;

}