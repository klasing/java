package pack;

import org.apache.log4j.Logger;

public class Main {
    public static void main(String[] args) {

        logger = logger.getLogger(Main.class);
        logger.debug(LOG_TAG + ".main()");

    }

    //************************************************************************
    //*                 declare
    //************************************************************************
    private static final String LOG_TAG = "*** " + Main.class.getSimpleName();

    private static Logger logger = null;

}