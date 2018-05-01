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

    public SocketThread(final Model model,
        final Socket socket) {

        logger = logger.getLogger(SocketThread.class);
        logger.debug(LOG_TAG + "<<constructor>> SocketThread()");

        this.model = model;
        this.socket = socket;

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
                // receive welcome message
                list.add(receive);
                publish(receive);
            }

        }

        // close socket
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

        model.receiveMessage(chunks.get(chunks.size() - 1));
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
    //*                 sendMessage
    //************************************************************************
    protected void sendMessage(final String message) {

        logger.debug(LOG_TAG + ".sendMessage()");

        // send message to server
        printWriter.println(message);

    }

    //************************************************************************
    //*                 disconnect
    //************************************************************************
    protected synchronized void disconnect() {

        logger.debug(LOG_TAG + ".disconnect()");

        try {
            // stop the client
            bContinue = false;
            // close client socket
            socket.close();
            socket = null;
        } catch(Exception e) {
            logger.debug(LOG_TAG + e.getClass() + ": " + e.getMessage());
        }

    }

    //************************************************************************
    //*                 declare
    //************************************************************************
    private static final String LOG_TAG = "*** " + SocketThread.class.getSimpleName();

    private static Logger logger = null;

    private static Model model = null;
    private static Socket socket = null;

    protected static volatile boolean bContinue = true;

    private static PrintWriter printWriter = null;
    private static BufferedReader bufferedReader = null;

}