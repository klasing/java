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

public class SocketThread extends SwingWorker<List<String>, String>
    implements PropertyChangeListener {

    public SocketThread(final Model4ServerSocket model4ServerSocket,
        final Socket socket) {

        logger = logger.getLogger(SocketThread.class);
        logger.debug(LOG_TAG + "<<constructor>> SocketThread()");

        this.model4ServerSocket = model4ServerSocket;
        this.socket = socket;

        addPropertyChangeListener(this);

        try {
            // printwriter is for sending message
            printWriter = new PrintWriter(socket.getOutputStream(), true);
            // bufferedreader is for receiving message
            bufferedReader = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
        } catch(Exception e) {
            logger.debug(LOG_TAG + e.getClass() + ": " + e.getMessage());
        }

    }

    //************************************************************************
    //*                 doInBackground
    //************************************************************************
    @Override
    public List<String> doInBackground() throws Exception {

        logger.debug(LOG_TAG + ".doInBackground()");

        List<String> list = new ArrayList<String>();
        while (listening) {
            // relax output a bit
            //Thread.sleep(100);

            // the socket is now listening to a connection
            String receive = null;
            if ((receive = bufferedReader.readLine()) != null) {
                // receive user name
                list.add(receive);
                publish(receive);
            }
        }

        // close the serverSocket
        socket.close();

        return list;

    }

    //************************************************************************
    //*                 process
    //************************************************************************
    @Override
    protected void process(List<String> chunks) {

        logger.debug(LOG_TAG + ".process()");

        logger.info(chunks.get(chunks.size() - 1));

        if (chunks.get(chunks.size() - 1).equals("@disconnect")) {
            // remove connection data from data table model
            model4ServerSocket.removeData(0);

            // send message to client
            sendMessage("@goodbye");
        } else {
            model4ServerSocket.setUserName(chunks.get(chunks.size() - 1));
            // send welcome message
            sendMessage("@welcome");
        }
    }

    //************************************************************************
    //*                 done
    //************************************************************************
    @Override
    protected void done() {

        logger.debug(LOG_TAG + ".done()");

    }

    //************************************************************************
    //*                 propertyChange
    //************************************************************************
    public void propertyChange(PropertyChangeEvent e) {

        logger.debug(LOG_TAG + ".propertyChangeListener()");
        String propertyName = e.getPropertyName();

        switch(propertyName) {
            case "progress":
                logger.info("progress: " + e.getNewValue());

                break;
            case "state":
                StateValue stateValue = ((StateValue)e.getNewValue());
                logger.info("state: " + stateValue.toString());

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

    //************************************************************************
    //*                 startSocketThread
    //************************************************************************
    protected synchronized void startSocketThread() {

        logger.debug(LOG_TAG + ".startSocketThread()");

        listening = true;
        execute();
    }

    //************************************************************************
    //*                 sendMessage
    //************************************************************************
    protected void sendMessage(final String message) {

        logger.debug(LOG_TAG + ".sendMessage()");

        // send message to client
        printWriter.println(message);

    }

    //************************************************************************
    //*                 stopSocketThread
    //************************************************************************
    protected synchronized void stopSocketThread() {

        logger.debug(LOG_TAG + ".stopSocketThread()");

        listening = false;

    }

    //************************************************************************
    //*                 declare
    //************************************************************************
    private static final String LOG_TAG = "*** " + SocketThread.class.getSimpleName();

    private static Logger logger = null;

    private static Model4ServerSocket model4ServerSocket = null;
    private static Socket socket = null;

    private static volatile boolean listening = true;

    private static PrintWriter printWriter = null;
    private static BufferedReader bufferedReader = null;

}