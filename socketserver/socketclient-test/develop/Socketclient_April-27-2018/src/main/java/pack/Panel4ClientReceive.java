package pack;


import javax.swing.BorderFactory;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.apache.log4j.Logger;

public class Panel4ClientReceive extends JPanel {
    public Panel4ClientReceive(final Control control) {

        logger = logger.getLogger(Panel4ClientReceive.class);
        logger.debug(LOG_TAG + ".Panel4ClientReceive()");

        this.control = control;

        // create text area for messages to receive
        // empty textarea, 10 rows, 32 columns
        jtaReceive = new JTextArea("", 10, 32);
        jtaReceive.setEditable(false);

        // create scrollpane for text area
        JScrollPane jScrollPane = new JScrollPane(jtaReceive,
            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
            JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        add(jScrollPane);

        // add border
        TitledBorder titledBorder = BorderFactory.createTitledBorder("Receive");
        setBorder(titledBorder);
    }

    //************************************************************************
    //*                 setReceivedMessage
    //************************************************************************
    protected void setReceivedMessage(final String message) {

        logger.debug(LOG_TAG + ".setReceivedMessage()");

        jtaReceive.append("> " + message + "\n");

        // stop socket thread
        if (message.equals("@goodbye") || message.equals("@server_down"))
            control.stopSocketThread();
    }

    //************************************************************************
    //*                 declare
    //************************************************************************
    private static final String LOG_TAG = "*** " + Panel4ClientReceive.class.getSimpleName();

    private static Logger logger = null;

    private static Control control = null;

    private static JTextArea jtaReceive = null;

}