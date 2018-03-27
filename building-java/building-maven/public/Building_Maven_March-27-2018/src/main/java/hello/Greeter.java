package hello;

import org.apache.log4j.Logger;

public class Greeter {
    protected Greeter() {
        logger = logger.getLogger(HelloWorld.class);
        logger.debug(LOG_TAG + "<<constructor>> Greeter()");
    }

    protected String sayHello() {
        logger.debug(LOG_TAG + ".sayHello()");

        return "Hello Maven World!";
    }
    //************************************************************************
    //*                 declare
    //************************************************************************
    private static final String LOG_TAG = "*** " + Greeter.class.getSimpleName();

    private static Logger logger = null;
}