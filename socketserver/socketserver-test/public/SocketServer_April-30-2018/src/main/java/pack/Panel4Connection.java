package pack;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.border.TitledBorder;

import javax.swing.JPanel;

import org.apache.log4j.Logger;
import javax.swing.JScrollPane;

public class Panel4Connection extends JPanel {
    public Panel4Connection(final Control control) {
        super(new BorderLayout());

        logger = logger.getLogger(Panel4Connection.class);
        logger.debug(LOG_TAG + "<<constructor>> Panel4Connection()");

        this.control = control;

        // create Table4Connection instance
        dataTable4Connection = new DataTable4Connection(
            dtm4Connection);

        // place table4Connection into jSrollPane
        JScrollPane jScrollPane = new JScrollPane(dataTable4Connection,
            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
            JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        add(jScrollPane, BorderLayout.CENTER);

        // add border
        TitledBorder titledBorder = BorderFactory
            .createTitledBorder("Server connection");
        setBorder(titledBorder);
    }

    //************************************************************************
    //*                 newConnection
    //************************************************************************
    protected void newConnection(final int client_id, final String time_stamp,
        final String email_address, final String remote_address) {

        logger.debug(LOG_TAG + ".newConnection()");

        DataObject4Connection dataObject4Connection = new DataObject4Connection()
            .setObject("" + client_id, 0)
            .setObject(time_stamp, 1)
            .setObject(email_address, 2)
            .setObject(remote_address, 3);

        dtm4Connection.addData(dataObject4Connection);
    }

    //************************************************************************
    //*                 removeConnection
    //************************************************************************
    protected void removeConnection(final int client_id) {

        logger.debug(LOG_TAG + ".removeConnection()");

        dtm4Connection.removeDataOnClientId(client_id);
    }

    //************************************************************************
    //*                 declare
    //************************************************************************
    private static final String LOG_TAG = "*** " + Panel4Connection.class.getSimpleName();

    private static Logger logger = null;

    private static Control control = null;

    private static DataObject4Connection dataObject4Connection = null;
    protected static Dtm4Connection dtm4Connection = new Dtm4Connection();
    private static DataTable4Connection dataTable4Connection = null;

}