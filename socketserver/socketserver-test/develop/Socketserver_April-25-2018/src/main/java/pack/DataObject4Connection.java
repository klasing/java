package pack;

import org.apache.log4j.Logger;

public class DataObject4Connection {
    protected DataObject4Connection() {

        logger = logger.getLogger(DataObject4Connection.class);
        logger.debug(LOG_TAG + "<<constructor>> DataObject4Connection()");
    }

    //************************************************************************
    //*                 setObject
    //************************************************************************
    protected DataObject4Connection setObject(final Object object, final int idx) {
        //logger.debug(LOG_TAG + ".setObject()");
        //logger.info("object: " + object.toString());

        aObject[idx] = object;

        return this;
    }

    //************************************************************************
    //*                 getObject
    //************************************************************************
    protected Object getObject(final int idx) {
        //logger.debug(LOG_TAG + ".getObject()");

        return aObject[idx];
    }

    //************************************************************************
    //*                 setValue
    //************************************************************************
    protected Object setValue(final Object object, final int idx) {
        logger.debug(LOG_TAG + ".setValue()");

        aObject[idx] = object;
        return this;
    }

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

    protected static final int NOF_OBJECT = 3;

    private Object[] aObject = new Object[NOF_OBJECT];
}