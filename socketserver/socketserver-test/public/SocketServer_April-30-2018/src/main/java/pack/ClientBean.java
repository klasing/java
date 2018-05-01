package pack;

import org.apache.log4j.Logger;

/**
 * Class that holds client data.
 */
public class ClientBean {
    /**
     * Constructor.
     * @param client {@link Client} instance that has the background thread to
     * communicate with the client
     * @param client_id to identify the client
     */
    protected ClientBean(final Client client, final int client_id) {

        logger = logger.getLogger(ClientBean.class);
        logger.debug(LOG_TAG + "<<constructor>> ClientBean()");

        this.client = client;
        clientId = client_id;

    }

    /**
     * Returns a {@link Client} instance.
     * return client {@link Client} instance
     */
    //************************************************************************
    //*                 getClient
    //************************************************************************
    protected Client getClient() {

        logger.debug(LOG_TAG + ".getClient)");

        return client;

    }

    /**
     * Returns a client id.
     * @return clientId to identify a client
     */
    //************************************************************************
    //*                 getClientId
    //************************************************************************
    protected int getClientId() {

        logger.debug(LOG_TAG + ".getClientd)");

        return clientId;

    }

    //************************************************************************
    //*                 declare
    //************************************************************************
    private static final String LOG_TAG = "*** " + ClientBean.class.getSimpleName();

    private static Logger logger = null;

    private Client client = null;
    private int clientId = 0;
}