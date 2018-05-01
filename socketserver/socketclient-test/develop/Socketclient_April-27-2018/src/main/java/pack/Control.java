package pack;

import javax.swing.JFrame;

import org.apache.log4j.Logger;

public class Control {
    public Control() {

        logger = logger.getLogger(Control.class);
        logger.debug(LOG_TAG + ".Control()");

        model4ClientSocket = new Model4ClientSocket(this);

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
    //*                 connect
    //************************************************************************
    protected boolean connect(final String email_address) {

        logger.debug(LOG_TAG + ".connect()");

        return model4ClientSocket.connect(email_address);
    }

    //************************************************************************
    //*                 enableSendPanel
    //************************************************************************
    protected void enableSendPanel(final boolean enable) {

        logger.debug(LOG_TAG + ".enableSendPanel()");

        view.panel4ClientSend.enableSendPanel(enable);

    }

    //************************************************************************
    //*                 setDefaultButton
    //************************************************************************
    protected void setDefaultButton() {

        logger.debug(LOG_TAG + ".setDefaultButton()");

        view.getFrame().getRootPane()
            .setDefaultButton(view.panel4ClientControl.jbConnect);

    }

    //************************************************************************
    //*                 sendUserName
    //************************************************************************
    protected void sendUserName(final String user_name) {

        logger.debug(LOG_TAG + ".sendUserName()");

        model4ClientSocket.sendUserName(user_name);
    }

    //************************************************************************
    //*                 setReceivedMessage
    //************************************************************************
    protected void setReceivedMessage(String message) {

        logger.debug(LOG_TAG + ".setReceivedMessage()");

        switch (message) {
            case "@welcome":
                message = "Welcome";
                break;
            case "@server_down":
                message = "Server is down";
                // set client in disconnect state
                view.panel4ClientControl.jbDisconnect.doClick();
                break;
            case "@goodbye":
                message = "Goodbye";
                break;
        } // eof switch

        view.panel4ClientReceive.setReceivedMessage(message);
    }

    //************************************************************************
    //*                 disconnect
    //************************************************************************
    protected void disconnect() {

        logger.debug(LOG_TAG + ".disconnect()");

        model4ClientSocket.disconnect();
    }

    //************************************************************************
    //*                 stopSocketThread
    //************************************************************************
    protected void stopSocketThread() {

        logger.debug(LOG_TAG + ".stopSocketThread()");

        model4ClientSocket.stopSocketThread();

    }

    //************************************************************************
    //*                 exit
    //************************************************************************
    protected void exit(final int exit_code) {

        logger.debug(LOG_TAG + ".exit()");

        // disconnect from server
        disconnect();

        Main.exit(exit_code);
    }

    //************************************************************************
    //*                 declare
    //************************************************************************
    private static final String LOG_TAG = "*** " + Control.class.getSimpleName();

    private static Logger logger = null;

    private static Model4ClientSocket model4ClientSocket = null;
    private static View view = null;

}