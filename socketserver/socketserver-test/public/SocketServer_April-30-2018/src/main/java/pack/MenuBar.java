package pack;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import org.apache.log4j.Logger;

/**
 * The application's menu.
 */
public class MenuBar extends JMenuBar implements ActionListener {

    /**
     * Constructor.
     * @param control {@link Control} instance
     */
    protected MenuBar(final Control control) {

        logger = logger.getLogger(MenuBar.class);
        logger.debug(LOG_TAG + "<<constructor>> MenuBar()");

        this.control = control;

        // item on menubar
        JMenu file_menu = new JMenu("File");
        add(file_menu);

        // sub item
        jmiExit = new JMenuItem("Exit");
        jmiExit.addActionListener(this);
        file_menu.add(jmiExit);

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
    public void actionPerformed(ActionEvent e) {

        logger.debug(LOG_TAG + ".actionPerformed()");

        if (e.getActionCommand().equals("Exit")) {

            logger.info("action_command: " + "Exit");

            control.exit(0);
        }
    }

    //************************************************************************
    //*                 declare
    //************************************************************************
    private static final String LOG_TAG = "*** " + MenuBar.class.getSimpleName();

    private static Logger logger = null;

    private static Control control = null;

    JMenuItem jmiExit = null;
}
