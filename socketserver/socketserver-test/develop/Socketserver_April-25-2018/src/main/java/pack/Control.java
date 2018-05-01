package pack;

import javax.swing.JFrame;

import org.apache.log4j.Logger;

public class Control {
    public Control() {

        logger = logger.getLogger(Control.class);
        logger.debug(LOG_TAG + "<<constructor>> Control()");

        model4ServerSocket = new Model4ServerSocket(this);

        view = new View(this);

    }

    //************************************************************************
    //*                 frameIsVisible
    //************************************************************************
    protected void frameIsVisible() {

        logger.debug(LOG_TAG + ".frameIsVisible()");
    }

    //************************************************************************
    //*                 getFrame
    //************************************************************************
    protected JFrame getFrame() {
        logger.debug(LOG_TAG + ".getFrame()");

        return view.getFrame();
    }

    //************************************************************************
    //*                 startServerSocketThread
    //************************************************************************
    protected void startServerSocketThread() {

        logger.debug(LOG_TAG + ".startServerSocketThread()");

        model4ServerSocket.startServerSocketThread();
    }

    //************************************************************************
    //*                 newConnection
    //************************************************************************
    protected void newConnection(final int thread_id, final String time_stamp) {

        logger.debug(LOG_TAG + ".newConnection()");

        view.panel4ServerConnection.newConnection(thread_id, time_stamp);

    }

    //************************************************************************
    //*                 setUserName
    //************************************************************************
    protected void setUserName(final String user_name) {

        logger.debug(LOG_TAG + ".setUserName()");

        view.panel4ServerConnection.setUserName(user_name);
    }

    //************************************************************************
    //*                 removeData
    //************************************************************************
    protected void removeData(final int idx) {

        logger.debug(LOG_TAG + ".removeData()");

        view.panel4ServerConnection.removeData(idx);
    }

    //************************************************************************
    //*                 stopServerSocketThread
    //************************************************************************
    protected void stopServerSocketThread() {

        logger.debug(LOG_TAG + ".stopServerSocketThread()");

        model4ServerSocket.stopServerSocketThread();
    }

    //************************************************************************
    //*                 exit
    //************************************************************************
    protected void exit(final int exit_code) {

        logger.debug(LOG_TAG + ".exit()");

        // let client know, server is shutting down
        stopServerSocketThread();

        Main.exit(exit_code);
    }

    //************************************************************************
    //*                 declare
    //************************************************************************
    private static final String LOG_TAG = "*** " + Control.class.getSimpleName();

    private static Logger logger = null;

    private static Model4ServerSocket model4ServerSocket = null;
    private static View view = null;

}