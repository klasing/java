package pack;

import org.apache.log4j.Logger;

public class Model4ServerSocket {
    public Model4ServerSocket(final Control control) {

        logger = logger.getLogger(Model4ServerSocket.class);
        logger.debug(LOG_TAG + "<<constructor>> Model4ServerSocket()");

        this.control = control;

    }

    //************************************************************************
    //*                 startServerSocketThread
    //************************************************************************
    protected void startServerSocketThread() {

        logger.debug(LOG_TAG + ".startServerSocketThread()");

        serverSocketThread = new ServerSocketThread(this);
        serverSocketThread.startServerSocketThread();

    }

    //************************************************************************
    //*                 newConnection
    //************************************************************************
    protected void newConnection(final int thread_id, final String time_stamp) {

        logger.debug(LOG_TAG + ".newConnection()");

        control.newConnection(thread_id, time_stamp);

    }

    //************************************************************************
    //*                 setUserName
    //************************************************************************
    protected void setUserName(final String user_name) {

        logger.debug(LOG_TAG + ".setUserName()");

        control.setUserName(user_name);
    }

    //************************************************************************
    //*                 removeData
    //************************************************************************
    protected void removeData(final int idx) {

        logger.debug(LOG_TAG + ".removeData()");

        control.removeData(idx);
    }

    //************************************************************************
    //*                 stopServerSocketThread
    //************************************************************************
    protected void stopServerSocketThread() {

        logger.debug(LOG_TAG + ".stopServerSocketThread()");

        serverSocketThread.stopServerSocketThread();

    }

    //************************************************************************
    //*                 declare
    //************************************************************************
    private static final String LOG_TAG = "*** " + Model4ServerSocket.class.getSimpleName();

    private static Logger logger = null;

    private static Control control = null;

    private static ServerSocketThread serverSocketThread = null;

}