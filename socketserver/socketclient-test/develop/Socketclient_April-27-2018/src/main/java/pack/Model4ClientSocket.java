package pack;

import java.net.Socket;

import org.apache.log4j.Logger;

public class Model4ClientSocket {
    public Model4ClientSocket(final Control control) {

        logger = logger.getLogger(Model4ClientSocket.class);
        logger.debug(LOG_TAG + ".Model4ClientSocket()");

        this.control = control;

    }

    //************************************************************************
    //*                 connect
    //************************************************************************
    protected boolean connect(final String email_address) {

        logger.debug(LOG_TAG + ".connect()");

        try {
            // use the local host as server
            socket = new Socket(host_address, portNumber);
        } catch(Exception e) {
            logger.debug(LOG_TAG + e.getClass() + ": " + e.getMessage());
            return false;
        }

        // start background thread for communication with server
        socketThread = new SocketThread(this, socket);
        socketThread.startSocketThread();


        return true;
    }

    //************************************************************************
    //*                 sendUserName
    //************************************************************************
    protected void sendUserName(final String user_name) {

        logger.debug(LOG_TAG + ".sendUserName()");

        socketThread.sendUserName(user_name);
    }

    //************************************************************************
    //*                 setReceivedMessage
    //************************************************************************
    protected void setReceivedMessage(final String message) {

        logger.debug(LOG_TAG + ".setReceivedMessage()");

        control.setReceivedMessage(message);
    }

    //************************************************************************
    //*                 disconnect
    //************************************************************************
    protected void disconnect() {

        logger.debug(LOG_TAG + ".disconnect()");

        try {
            // send disconnect message to server
            socketThread.sendDisconnect();

            // stop socket thread
            //socketThread.stopSocketThread();

            //socket.close();
            //socket = null;
        } catch(Exception e) {
            logger.debug(LOG_TAG + e.getClass() + ": " + e.getMessage());
        }

    }

    //************************************************************************
    //*                 stopSocketThread
    //************************************************************************
    protected void stopSocketThread() {

        logger.debug(LOG_TAG + ".stopSocketThread()");

        try {
            // stop socket thread
            socketThread.stopSocketThread();

            socket.close();
            socket = null;
        } catch(Exception e) {
            logger.debug(LOG_TAG + e.getClass() + ": " + e.getMessage());
        }

    }

    //************************************************************************
    //*                 declare
    //************************************************************************
    private static final String LOG_TAG = "*** " + Model4ClientSocket.class.getSimpleName();
    private static final String host_address = "127.0.0.1";
    private static final int portNumber = 4444;

    private static Logger logger = null;

    private static Control control = null;

    private static Socket socket = null;
    private static SocketThread socketThread = null;

}