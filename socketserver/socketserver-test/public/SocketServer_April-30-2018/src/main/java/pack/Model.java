package pack;

import org.apache.log4j.Logger;

/**
 * The application's model logic.
 */
public class Model {

    /**
     * Constructor.
     * @param control {@link Control} instance
     */
    public Model(final Control control) {

        logger = logger.getLogger(Model.class);
        logger.debug(LOG_TAG + "<<constructor>> Model()");

        this.control = control;

    }

    /**
     * Creates a {@link Server} instance, and starts the {@link Server} background thread.
     */
    //************************************************************************
    //*                 start
    //************************************************************************
    protected void start() {

        logger.debug(LOG_TAG + ".start()");

        // create Server instance
        server = new Server(this);
        // start server thread
        server.bContinue = true;
        server.execute();

    }

    /**
     * Conveys a message from {@link Client} to {@link Panel4Connection}.
     * @param client_id to identify a client
     * @param time_stamp the moment the connection was made
     * @param email_address the clients email address, or user name
     * @param remote_address the remote hosts IP address, and port number
     */
    //************************************************************************
    //*                 newConnection
    //************************************************************************
    protected void newConnection(final int client_id, final String time_stamp,
        final String email_address, final String remote_address) {

        logger.debug(LOG_TAG + ".newConnection()");

        control.newConnection(client_id, time_stamp, email_address,
            remote_address);

    }

    /**
     * Conveys a message from {@link Client} to {@link Panel4Connection}.
     * @param client_id to identify a client
     */
    //************************************************************************
    //*                 removeConnection
    //************************************************************************
    protected void removeConnection(final int client_id) {

        logger.debug(LOG_TAG + ".removeConnection()");

        // send message to control
        control.removeConnection(client_id);

    }

    /**
     * Conveys the 'Stop' command from {@link Panel4Control} to {@link Server}.
     */
    //************************************************************************
    //*                 stop
    //************************************************************************
    protected void stop() {

        logger.debug(LOG_TAG + ".stop()");

        // stop server thread
        if (server != null) {
            server.stop();
            server = null;
        }
    }

    //************************************************************************
    //*                 declare
    //************************************************************************
    private static final String LOG_TAG = "*** " + Model.class.getSimpleName();

    private static Logger logger = null;

    private static Control control = null;

    private static Server server = null;

}