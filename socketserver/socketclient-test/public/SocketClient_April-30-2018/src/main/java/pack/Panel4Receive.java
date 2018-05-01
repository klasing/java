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

public class Panel4Receive extends JPanel {
    public Panel4Receive(final Control control) {

        logger = logger.getLogger(Panel4Receive.class);
        logger.debug(LOG_TAG + ".Panel4Receive()");

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
    //*                 receiveMessage
    //************************************************************************
    protected void receiveMessage(final String message) {

        logger.debug(LOG_TAG + ".receiveMessage()");

        jtaReceive.append(message + "\n");

    }

    //************************************************************************
    //*                 declare
    //************************************************************************
    private static final String LOG_TAG = "*** " + Panel4Receive.class.getSimpleName();

    private static Logger logger = null;

    private static Control control = null;

    private static JTextArea jtaReceive = null;

}