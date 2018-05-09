package pack;

import javax.swing.JFrame;

import org.apache.log4j.Logger;

/**
 * The application's business logic.
 */
public class Control {

    /**
     * Constructor
     */
    public Control() {

        logger = logger.getLogger(Control.class);
        logger.debug(LOG_TAG + ".Control()");

        // create Model instance
        model = new Model(this);

        // create View instance
        view = new View(this);

    }

    /**
     * Called when GUI frame is build and visible on screen.
     */
    //************************************************************************
    //*                 frameIsVisible
    //************************************************************************
    protected void frameIsVisible() {

        logger.debug(LOG_TAG + ".frameIsVisible()");
    }

    /**
     * The returns the frame in which the application is visible.
     * @return the application frame
     */
    //************************************************************************
    //*                 getFrame
    //************************************************************************
    protected JFrame getFrame() {

        logger.debug(LOG_TAG + ".getFrame()");

        return view.getFrame();
    }

    /**
     * Connects a client to the server.
     * @param email_address or user name, used by the client
     * @return connect result from this operation
     */
    //************************************************************************
    //*                 connect
    //************************************************************************
    protected boolean connect(final String email_address) {

        logger.debug(LOG_TAG + ".connect()");

        return model.connect(email_address);
    }

    /**
     * Sets the state the buttons on the GUI have to be in, depending on the connected state.
     * @param enable determines the state the buttons have to be in
     */
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

    /**
     * Conveys a message from the {@link Panel4Send} to the {@link SocketThread}.
     * @param message the message to be send
     */
    //************************************************************************
    //*                 sendMessage
    //************************************************************************
    protected void sendMessage(final String message) {

        logger.debug(LOG_TAG + ".sendMessage()");

        model.sendMessage(message);

    }

    /**
     * Conveys a message received by the {@link SocketThread} to the {@link Panel4Send}.
     * @param message the received message
     */
    //************************************************************************
    //*                 receiveMessage
    //************************************************************************
    protected void receiveMessage(String message) {

        logger.debug(LOG_TAG + ".receiveMessage()");

        switch (message) {
            case "@welcome":
                message = ">Welcome";
                view.panel4Receive.receiveMessage(message);
                break;
            case "@goodbye":
                message = ">Goodbye";
                view.panel4Receive.receiveMessage(message);
                model.disconnect();
                break;
            case "@server_down":
                message = ">Server down";
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

    /**
     * Conveys the exit command to {@link Main}.
     * @param exit_code the exit code for exiting
     */
    //************************************************************************
    //*                 exit
    //************************************************************************
    protected void exit(final int exit_code) {

        logger.debug(LOG_TAG + ".exit()");

        // send message to disconnect
        sendMessage("@disconnect");

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