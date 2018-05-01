package pack;

import org.apache.log4j.Logger;

/**
 * Application's entry and exit.
 */
public class Main {

    /**
     * Application's main entry.
     * @param args command line argument(s)
     */
    public static void main(String[] args) {

        logger = logger.getLogger(Main.class);
        logger.debug(LOG_TAG + ".main()");

        // create Control instance
        new Control();
    }

    /**
     * Application's exit.
     * @param exit_code the exit code for exiting
     */
    //************************************************************************
    //*                 exit
    //************************************************************************
    protected static void exit(final int exit_code) {

        logger.debug(LOG_TAG + ".exit()");

        if (exit_verbose) {
            String verbose = null;
            switch(exit_code) {
                case 0:
                    verbose = "normal exit";
                    break;
            } // eof_switch
            logger.info("exit_code: " + verbose);
        } else {
            logger.info("exit_code: " + exit_code);
        }

        System.exit(exit_code);

    }

    //************************************************************************
    //*                 declare
    //************************************************************************
    private static final String LOG_TAG = "*** " + Main.class.getSimpleName();

    private static Logger logger = null;

    private static boolean exit_verbose = true;

}