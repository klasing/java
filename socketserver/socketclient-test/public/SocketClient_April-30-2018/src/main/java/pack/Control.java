package pack;

import javax.swing.JFrame;

import org.apache.log4j.Logger;

public class Control {
    public Control() {

        logger = logger.getLogger(Control.class);
        logger.debug(LOG_TAG + ".Control()");

        // create Model instance
        model = new Model(this);

        // create View instance
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

        return model.connect(email_address);
    }

    //************************************************************************
    //*                 enableSendState
    //************************************************************************
    protected void enableSendState(final boolean enable) {

        logger.debug(LOG_TAG + ".enableSendState()");

        view.panel4Send.enableSendState(enable);

        if (!enable) {
            // modify button state in Panel4Control
            // button "Connect" acts 'clicked', whenever the user hits the Enter/Return key
            view.getFrame().getRootPane().setDefaultButton(
                view.panel4Control.jbConnect);
        }

    }

    //************************************************************************
    //*                 sendMessage
    //************************************************************************
    protected void sendMessage(final String message) {

        logger.debug(LOG_TAG + ".sendMessage()");

        model.sendMessage(message);

    }

    //************************************************************************
    //*                 receiveMessage
    //************************************************************************
    protected void receiveMessage(String message) {

        logger.debug(LOG_TAG + ".receiveMessage()");

        switch (message) {
            case "@welcome":
                message = "> Welcome";
                view.panel4Receive.receiveMessage(message);
                break;
            case "@goodbye":
                message = "> Goodbye";
                view.panel4Receive.receiveMessage(message);
                model.disconnect();
                break;
            case "@server_down":
                message = "> Server down";
                view.panel4Receive.receiveMessage(message);
                // adjust button state
                view.panel4Control.disconnect();
                model.disconnect();
                break;
            default:
                // communication message from client
                view.panel4Receive.receiveMessage(message);
        } // eof switch
    }

    //************************************************************************
    //*                 exit
    //************************************************************************
    protected void exit(final int exit_code) {

        logger.debug(LOG_TAG + ".exit()");

        Main.exit(exit_code);
    }

    //************************************************************************
    //*                 declare
    //************************************************************************
    private static final String LOG_TAG = "*** " + Control.class.getSimpleName();

    private static Logger logger = null;

    private static Model model = null;
    private static View view = null;

}