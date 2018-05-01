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

public class Panel4SocketControl extends JPanel implements ActionListener {
    public Panel4SocketControl(final Control control) {

        logger = logger.getLogger(Panel4SocketControl.class);
        logger.debug(LOG_TAG + "<<constructor>> Panel4SocketControl()");

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

                // send message to control
                control.startServerSocketThread();

                // button Start acts 'clicked', whenever the user hits the Enter/Return key
                control.getFrame().getRootPane().setDefaultButton(jbStop);

                break;
            case "Stop":

                jbStart.setEnabled(true);
                jbStop.setEnabled(false);

                jtfStatus.setText("DOWN");

                // send message to control
                control.stopServerSocketThread();

                // button Start acts 'clicked', whenever the user hits the Enter/Return key
                control.getFrame().getRootPane().setDefaultButton(jbStart);

                break;
        } // eof switch

    }

    //************************************************************************
    //*                 declare
    //************************************************************************
    private static final String LOG_TAG = "*** " + Panel4SocketControl.class.getSimpleName();

    private static Logger logger = null;

    private static Control control = null;

    protected static JButton jbStart = null;
    private static JButton jbStop = null;

    private static JTextField jtfStatus = null;

}