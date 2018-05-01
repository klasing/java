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

public class ServerSocketThread extends SwingWorker<List<String>, String>
    implements PropertyChangeListener {

    public ServerSocketThread(final Model4ServerSocket model4ServerSocket) {

        logger = logger.getLogger(ServerSocketThread.class);
        logger.debug(LOG_TAG + "<<constructor>> ServerSocketThread()");

        this.model4ServerSocket = model4ServerSocket;

        addPropertyChangeListener(this);

    }

    //************************************************************************
    //*                 doInBackground
    //************************************************************************
    @Override
    public List<String> doInBackground() throws Exception {

        logger.debug(LOG_TAG + ".doInBackground()");

        // create a ServerSocket instance to listen for incoming connection
        // requests
        serverSocket = new ServerSocket(4444);

        List<String> list = new ArrayList<String>();
        while (listening) {
            // relax output a bit
            //Thread.sleep(250);

            // now wait for a new connection to arrive
            // when a connection arrives, create a new thread to facilitate
            // the new connection
            // the method accept() blocks until a connection has arrived
            Socket socket = serverSocket.accept();
            SocketThread socketThread =
                new SocketThread(model4ServerSocket, socket);
            socketThread.execute();
            listSocket.add(socketThread);
            ++threadId;

            LocalTime localTime = new LocalTime();
            list.add("" + localTime);
            publish("" + localTime);
        }

        return list;

    }

    //************************************************************************
    //*                 process
    //************************************************************************
    @Override
    protected void process(List<String> chunks) {

        logger.debug(LOG_TAG + ".process()");

        logger.info("new connection at: " + chunks.get(chunks.size() - 1));

        // send message about new connection
        model4ServerSocket.newConnection(threadId, chunks.get(chunks.size() - 1));
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
    //*                 startServerSocketThread
    //************************************************************************
    protected synchronized void startServerSocketThread() {

        logger.debug(LOG_TAG + ".startServerSocketThread()");

        listening = true;
        execute();

    }

    //************************************************************************
    //*                 stopServerSocketThread
    //************************************************************************
    protected synchronized void stopServerSocketThread() {

        logger.debug(LOG_TAG + ".stopServerSocketThread()");

        // close all socket connections
        for (SocketThread socketThread : listSocket) {
            socketThread.sendMessage("@server_down");
            socketThread.stopSocketThread();
        }

        // close the serverSocket
        try {
            serverSocket.close();
        } catch(Exception e) {
            logger.debug(LOG_TAG + e.getClass() + ": " + e.getMessage());
        }

        listening = false;

    }

    //************************************************************************
    //*                 declare
    //************************************************************************
    private static final String LOG_TAG = "*** " + ServerSocketThread.class.getSimpleName();

    private static Logger logger = null;

    private static Model4ServerSocket model4ServerSocket = null;

    private static ServerSocket serverSocket = null;
    private static volatile boolean listening = true;

    private static List<SocketThread> listSocket = new ArrayList<SocketThread>();

    private static int threadId = 0;

}