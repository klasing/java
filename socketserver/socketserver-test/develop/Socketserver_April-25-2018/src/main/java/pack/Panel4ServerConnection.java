package pack;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.border.TitledBorder;

import javax.swing.JPanel;

import org.apache.log4j.Logger;
import javax.swing.JScrollPane;

public class Panel4ServerConnection extends JPanel {
    public Panel4ServerConnection(final Control control) {
        super(new BorderLayout());

        logger = logger.getLogger(Panel4ServerConnection.class);
        logger.debug(LOG_TAG + "<<constructor>> Panel4ServerConnection()");

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
    protected void newConnection(final int thread_id, final String time_stamp) {

        logger.debug(LOG_TAG + ".newConnection()");

        dtm4Connection.setValueAt("" + thread_id, 0, 0);
        dtm4Connection.setValueAt(time_stamp, 0, 1);

    }

    //************************************************************************
    //*                 setUserName
    //************************************************************************
    protected void setUserName(final String user_name) {

        logger.debug(LOG_TAG + ".setUserName()");

        // user name message comes first,
        // so create DataObject4Connection instance here
        dataObject4Connection = new DataObject4Connection().setObject(user_name, 2);
        dtm4Connection.addData(dataObject4Connection);
    }

    //************************************************************************
    //*                 removeData
    //************************************************************************
    protected void removeData(final int idx) {

        logger.debug(LOG_TAG + ".removeData()");

        dtm4Connection.removeData(idx);
    }

    //************************************************************************
    //*                 declare
    //************************************************************************
    private static final String LOG_TAG = "*** " + Panel4ServerConnection.class.getSimpleName();

    private static Logger logger = null;

    private static Control control = null;

    private static Dtm4Connection dtm4Connection = new Dtm4Connection();
    private static DataTable4Connection dataTable4Connection = null;
    private static DataObject4Connection dataObject4Connection = null;

}