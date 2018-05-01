package pack;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import java.net.ServerSocket;
import java.net.Socket;

import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingWorker;

import org.apache.log4j.Logger;
import org.joda.time.LocalTime;

/**
 * Holds a background thread that waits for a client connection to be
 * established.
 */
public class Server extends SwingWorker<List<String>, String>
    implements PropertyChangeListener {

    /**
     * Constructor.
     * @param model {@link Model} instance
     */
    public Server(final Model model) {

        logger = logger.getLogger(Server.class);
        logger.debug(LOG_TAG + "<<constructor>> Server()");

        this.model = model;

        addPropertyChangeListener(this);

    }

    /**
     * The job that runs in the background.
     * @return list contains the moment a connection is established for a client
     * @throws Exception for whatever reason
     */
    //************************************************************************
    //*                 doInBackground
    //************************************************************************
    @Override
    public List<String> doInBackground() throws Exception {

        logger.debug(LOG_TAG + ".doInBackground()");

        // create a ServerSocket instance to listen for incoming connection
        // requests
        serverSocket = new ServerSocket(portNumber);

        // list for publishing intermediate  result
        List<String> list = new ArrayList<String>();
        while (bContinue) {

            // now wait for a new connection to arrive
            // when a connection arrives, create a new thread to facilitate
            // the new connection
            // the method accept() blocks until a connection has arrived
            Socket socket = serverSocket.accept();

            // set moment new connection is made
            localTime = new LocalTime();
            list.add("" + localTime);
            publish("" + localTime);

            // set id for new client
            clientId++;

            // create a new Client instance, to communicate with a client
            Client client =
                new Client(this, socket, clientId);
            listClientBean.add(new ClientBean(client, clientId));

            client.bContinue = true;
            client.execute();

        }

        return list;

    }

    /**
     * Does nothing.
     * @param chunks contains a moment when a connection was made
     */
    //************************************************************************
    //*                 process
    //************************************************************************
    @Override
    protected void process(List<String> chunks) {

        logger.debug(LOG_TAG + ".process()");

    }

    /**
     * Called when the {@link Server} is stopped, and the background job has
     * finished its task.
     */
    //************************************************************************
    //*                 done
    //************************************************************************
    @Override
    protected void done() {

        logger.debug(LOG_TAG + ".done()");

    }

    /**
     * State changes of the background thread, indicating the state the
     * background thread is in.
     * @param e ProperChangeEvent, triggered by a PropertyChangeListener
     */
    //************************************************************************
    //*                 propertyChange
    //************************************************************************
    public void propertyChange(PropertyChangeEvent e) {

        logger.debug(LOG_TAG + ".propertyChangeListener()");
        String propertyName = e.getPropertyName();

        switch(propertyName) {
            case "progress":
                logger.info("server progress: " + e.getNewValue());

                break;
            case "state":
                StateValue stateValue = ((StateValue)e.getNewValue());
                logger.info("server state: " + stateValue.toString());

                switch(stateValue) {
                    case STARTED:

                        break;
                    case PENDING:

                        break;
                    case DONE:

                        break;
                } // eof switch
        } // eof switch

    }

    /**
     * Stop the server background thread, and closes all client background
     * threads for client communication.
     */
    //************************************************************************
    //*                 stop
    //************************************************************************
    protected synchronized void stop() {

        logger.debug(LOG_TAG + ".stop()");

        // close all client connections
        for (ClientBean clientBean : listClientBean) {
            clientBean.getClient().sendMessage("@server_down");
            clientBean.getClient().bContinue = false;
        }

        try {
            // stop the server
            bContinue = false;
            // close server socket
            serverSocket.close();
            serverSocket = null;
        } catch(Exception e) {
            logger.debug(LOG_TAG + e.getClass() + ": " + e.getMessage());
        }

    }

    //************************************************************************
    //*                 newConnection
    //************************************************************************
    protected void newConnection(final String email_address,
        final String remote_address) {

        logger.debug(LOG_TAG + ".newConnection()");

        // send message to model
        model.newConnection(clientId, "" + localTime, email_address,
            remote_address);

    }

    //************************************************************************
    //*                 removeConnection
    //************************************************************************
    protected void removeConnection(final int client_id) {

        logger.debug(LOG_TAG + ".removeConnection()");

        int i = 0;
        while (client_id != listClientBean.get(i++).getClientId());

        listClientBean.remove(i - 1);

        model.removeConnection(client_id);
    }

    //************************************************************************
    //*                 distributeMessage
    //************************************************************************
    protected void distributeMessage(final String message) {

        logger.debug(LOG_TAG + ".distributeMessage()");

        for (ClientBean clientBean : listClientBean) {
            clientBean.getClient().sendMessage(message);
        }
    }

    //************************************************************************
    //*                 declare
    //************************************************************************
    private static final String LOG_TAG = "*** " + Server.class.getSimpleName();
    private static final int portNumber = 4444;

    private static Logger logger = null;

    private static Model model = null;

    private static ServerSocket serverSocket = null;
    protected static volatile boolean bContinue = false;

    private static List<ClientBean> listClientBean = new ArrayList<ClientBean>();

    private static LocalTime localTime = null;

    private static int clientId = 0;

}