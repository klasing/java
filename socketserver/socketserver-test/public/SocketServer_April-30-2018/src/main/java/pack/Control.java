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
        logger.debug(LOG_TAG + "<<constructor>> Control()");

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
     * Conveys the start command from {@link Panel4Control}
     * to {@link Model}.
     */
    //************************************************************************
    //*                 start
    //************************************************************************
    protected void start() {

        logger.debug(LOG_TAG + ".start()");

        // send message to model
        model.start();
    }

    /**
     * Conveys new connection data from {@link Client} to {@link Panel4Connection}.
     * @param client_id to indentify the client thread
     * @param time_stamp for moment the connection was made
     * @param email_address which is used by the client
     * @param remote_address of the client
     */
    //************************************************************************
    //*                 newConnection
    //************************************************************************
    protected void newConnection(final int client_id, final String time_stamp,
        final String email_address, final String remote_address) {

        logger.debug(LOG_TAG + ".newConnection()");

        view.panel4Connection.newConnection(client_id, time_stamp,
            email_address, remote_address);

    }

    /**
     * Conveys a terminated connection from {@link Client} to {@link Panel4Connection}.
     * @param client_id to indentify the client thread
     */
    //************************************************************************
    //*                 removeConnection
    //************************************************************************
    protected void removeConnection(final int client_id) {

        logger.debug(LOG_TAG + ".removeConnection()");

        view.panel4Connection.removeConnection(client_id);

    }

    /**
     * Conveys the stop command from {@link Panel4Control} to {@link Model}.
     * Clears the {@link DataTable4Connection} content.
     */
    //************************************************************************
    //*                 stop
    //************************************************************************
    protected void stop() {

        logger.debug(LOG_TAG + ".stop()");

        // clear dataTable4Connection, remove backwards
        for (int i = view.panel4Connection.dtm4Connection.getRowCount() - 1; i >= 0; i--) {
            view.panel4Connection.dtm4Connection.removeData(i);
        }

        // send message to model
        model.stop();
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

        // stop server
        stop();

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
