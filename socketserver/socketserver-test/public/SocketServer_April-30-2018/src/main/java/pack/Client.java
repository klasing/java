package pack;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import java.net.Socket;

import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingWorker;

import org.apache.log4j.Logger;
import org.joda.time.LocalTime;

/**
 * Holds a background thread to communicate with a client.
 */
public class Client extends SwingWorker<List<String>, String>
    implements PropertyChangeListener {

    /**
     * Constructor.
     * @param server {@link Server} instance
     * @param socket that is used
     * @param clientId to identify a client
     */
    public Client(final Server server, final Socket socket,
        final int clientId) {

        logger = logger.getLogger(Client.class);
        logger.debug(LOG_TAG + "<<constructor>> Client()");

        this.server = server;
        this.socket = socket;
        this.clientId = clientId;

        addPropertyChangeListener(this);

        try {
            // create printwriter for sending message
            printWriter = new PrintWriter(socket.getOutputStream(), true);
            // create bufferedreader for receiving message
            bufferedReader = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
        } catch(Exception e) {
            logger.debug(LOG_TAG + e.getClass() + ": " + e.getMessage());
        }

    }

    /**
     * The job that runs in the background.
     * @return list contains the messages received from the client
     * @throws Exception for whatever reason
     */
    //************************************************************************
    //*                 doInBackground
    //************************************************************************
    @Override
    public List<String> doInBackground() throws Exception {

        logger.debug(LOG_TAG + ".doInBackground()");

        // list for publishing intermediate  result
        List<String> list = new ArrayList<String>();
        while (bContinue) {
            // the socket is now listening to a connection
            String receive = null;
            if ((receive = bufferedReader.readLine()) != null) {
                // receive user name
                list.add(receive);
                publish(receive);
            }
        }

        // close client socket
        socket.close();

        return list;

    }

    /**
     * Processes the intermediate message received from the client.
     * @param chunks contains a message received from the client
     */
    //************************************************************************
    //*                 process
    //************************************************************************
    @Override
    protected void process(List<String> chunks) {

        logger.debug(LOG_TAG + ".process()");

        logger.info(chunks.get(chunks.size() - 1));

        if (newConnection) {
            // save email address for distributing communication message
            emailAddress = chunks.get(chunks.size() - 1);
            // send email address and remote address to server
            server.newConnection(chunks.get(chunks.size() - 1),
                socket.getRemoteSocketAddress().toString());

            // send 'welcome' message to client
            sendMessage("@welcome");

            newConnection = false;

            return;
        }

        if (chunks.get(chunks.size() - 1).equals("@disconnect")) {
            // send message to server
            server.removeConnection(clientId);

            // send 'goodbye' message to client
            sendMessage("@goodbye");

            newConnection = true;

            return;
        }

        // communication message from client, distribute message
        // to all clients
        server.distributeMessage(emailAddress + "> " + chunks.get(chunks.size() - 1));

    }

    /**
     * Called when the {@link Client} is stopped, and the background job has
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
                logger.info("client progress: " + e.getNewValue());

                break;
            case "state":
                StateValue stateValue = ((StateValue)e.getNewValue());
                logger.info("client state: " + stateValue.toString());

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
     * Sends a message to the client.
     * @param message to be send
     */
    //************************************************************************
    //*                 sendMessage
    //************************************************************************
    protected void sendMessage(final String message) {

        logger.debug(LOG_TAG + ".sendMessage()");

        // send message to client
        printWriter.println(message);

    }

    //************************************************************************
    //*                 declare
    //************************************************************************
    private static final String LOG_TAG = "*** " + Client.class.getSimpleName();

    private static Logger logger = null;

    private static Server server = null;

    private static Socket socket = null;

    protected volatile boolean bContinue = true;

    private PrintWriter printWriter = null;
    private BufferedReader bufferedReader = null;

    private boolean newConnection = true;
    private int clientId = 0;
    private String emailAddress = null;

}