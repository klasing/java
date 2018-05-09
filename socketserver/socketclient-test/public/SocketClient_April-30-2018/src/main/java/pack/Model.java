package pack;

import java.net.Socket;

import org.apache.log4j.Logger;

/**
 * The application's model logic.
 */
public class Model {

    /**
     * Constructor.
     * @param control {@link Control} instance
     */
    public Model(final Control control) {

        logger = logger.getLogger(Model.class);
        logger.debug(LOG_TAG + ".Model()");

        this.control = control;

    }

    /**
     * Creates a {@link SocketThread} instance, to communicate with the server.
     * @param email_address or the user_name, used by the client
     * @return connect the result of this operation
     */
    //************************************************************************
    //*                 connect
    //************************************************************************
    protected boolean connect(final String email_address) {

        logger.debug(LOG_TAG + ".connect()");

        try {
            // use local host as server
            socket = new Socket(host_address, portNumber);
            // create background thread
            socketThread = new SocketThread(this, socket);
            // set bContinue
            socketThread.bContinue = true;
            // start background thread for communication with server
            socketThread.execute();
        } catch(Exception e) {
            logger.debug(LOG_TAG + e.getClass() + ": " + e.getMessage());
            return false;
        }

        return true;
    }

    /**
     * Conveys a message from the {@link Panel4Send} to the {@link SocketThread}.
     * @param message the message to be send
     */
    //************************************************************************
    //*                 sendMessage
    //************************************************************************
    protected void sendMessage(final String message) {

        logger.debug(LOG_TAG + ".sendMessage()");

        // avoid sending message, without a connection
        // e.g. closing the app, before connecting
        if (socketThread != null)
            // send message to server
            socketThread.sendMessage(message);

    }

    /**
     * Conveys a message received by the {@link SocketThread} to the {@link Panel4Send}.
     * @param message the received message
     */
    //************************************************************************
    //*                 receiveMessage
    //************************************************************************
    protected void receiveMessage(final String message) {

        logger.debug(LOG_TAG + ".receiveMessage()");

        control.receiveMessage(message);
    }

    /**
     * Disconnects a client from the server.
     */
    //************************************************************************
    //*                 disconnect
    //************************************************************************
    protected void disconnect() {

        logger.debug(LOG_TAG + ".disconnect()");

        // stop background thread for communication with server
        socketThread.disconnect();

        socketThread = null;

    }

    //************************************************************************
    //*                 declare
    //************************************************************************
    private static final String LOG_TAG = "*** " + Model.class.getSimpleName();
    private static final String host_address = "127.0.0.1";
    private static final int portNumber = 4444;

    private static Logger logger = null;

    private static Control control = null;

    private static Socket socket = null;
    private static SocketThread socketThread = null;

}