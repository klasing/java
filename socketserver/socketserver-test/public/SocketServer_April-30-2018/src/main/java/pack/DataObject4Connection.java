package pack;

import org.apache.log4j.Logger;

/**
 * Object for the client connection data.
 */
public class DataObject4Connection {

    /**
     * Constructor.
     */
    protected DataObject4Connection() {

        logger = logger.getLogger(DataObject4Connection.class);
        logger.debug(LOG_TAG + "<<constructor>> DataObject4Connection()");
    }

    /**
     * Sets an object element to the {@link DataObject4Connection#aObject} array.
     * @param object the object element to be set
     * @param idx the row at wich the object has to be set.
     * @return dataObject4Connection this {@link DataObject4Connection} instance
     */
    //************************************************************************
    //*                 setObject
    //************************************************************************
    protected DataObject4Connection setObject(final Object object, final int idx) {
        //logger.debug(LOG_TAG + ".setObject()");
        //logger.info("object: " + object.toString());

        aObject[idx] = object;

        return this;
    }

    /**
     * Gets an object element from the {@link DataObject4Connection#aObject} array.
     * @param idx the index in the {@link DataObject4Connection#aObject} array
     * from wich the object has to be returned.
     * @return object from the {@link DataObject4Connection#aObject} array
     */
    //************************************************************************
    //*                 getObject
    //************************************************************************
    protected Object getObject(final int idx) {
        //logger.debug(LOG_TAG + ".getObject()");

        return aObject[idx];
    }

    /**
     * Sets an object element value to the {@link DataObject4Connection#aObject} array.
     * @param object the object value to be set
     * @param idx the row at wich the object has to be set.
     * @return object this object value from a {@link DataObject4Connection} instance
     */
    //************************************************************************
    //*                 setValue
    //************************************************************************
    protected Object setValue(final Object object, final int idx) {
        logger.debug(LOG_TAG + ".setValue()");

        aObject[idx] = object;
        return this;
    }

    /**
     * Gets a value from the {@link DataObject4Connection#aObject} array.
     * @param idx the index in the {@link DataObject4Connection#aObject} array
     * from wich the object value has to be returned.
     * @return object value from the {@link DataObject4Connection#aObject} array
     */
    //************************************************************************
    //*                 getValue
    //************************************************************************
    protected Object getValue(final int idx) {
        //logger.debug(LOG_TAG + ".getValue()");

        return aObject[idx];
    }

    //************************************************************************
    //*                 declare
    //************************************************************************
    private static final String LOG_TAG = "*** " + DataObject4Connection.class.getSimpleName();

    private static Logger logger = null;

    protected static final int NOF_OBJECT = 4;

    private Object[] aObject = new Object[NOF_OBJECT];
}