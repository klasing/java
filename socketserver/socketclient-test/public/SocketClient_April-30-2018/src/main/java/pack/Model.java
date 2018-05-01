package pack;

import java.net.Socket;

import org.apache.log4j.Logger;

public class Model {
    public Model(final Control control) {

        logger = logger.getLogger(Model.class);
        logger.debug(LOG_TAG + ".Model()");

        this.control = control;

    }

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

    //************************************************************************
    //*                 sendMessage
    //************************************************************************
    protected void sendMessage(final String message) {

        logger.debug(LOG_TAG + ".sendMessage()");

        // send message to server
        socketThread.sendMessage(message);

    }

    //************************************************************************
    //*                 receiveMessage
    //************************************************************************
    protected void receiveMessage(final String message) {

        logger.debug(LOG_TAG + ".receiveMessage()");

        control.receiveMessage(message);
    }

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