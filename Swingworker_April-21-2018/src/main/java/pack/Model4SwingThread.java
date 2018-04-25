package pack;

import org.apache.log4j.Logger;

/**
 * The application's model for {@link SwingThread}
 */
public class Model4SwingThread {
    protected Model4SwingThread(final Control control) {

        logger = logger.getLogger(Model4SwingThread.class);
        logger.debug(LOG_TAG + "<<constructor>> Model4SwingThread()");

        this.control = control;
    }

    /**
     * Start the {@link SwingThread} background thread.
     */
    //************************************************************************
    //*                 startThread
    //************************************************************************
    protected void startThread() {

        logger.debug(LOG_TAG + ".startThread()");

        swingThread = new SwingThread(this);
        swingThread.execute();

    }

    /**
     * Cancel the {@link SwingThread} background thread.
     */
    //************************************************************************
    //*                 cancelThread
    //************************************************************************
    protected void cancelThread() {

        logger.debug(LOG_TAG + ".cancelThread()");

        swingThread.cancel(true);

    }

    /**
     * Conveys an intermediate result from {@link SwingThread} to {@link Control}.
     * @param string containing an intermediate result from {@link SwingThread}
     */
    //************************************************************************
    //*                 setResult
    //************************************************************************
    protected void setResult(final String string) {

        logger.debug(LOG_TAG + ".setResult()");

        control.setResultSwingThread(string);

    }

    /**
     * Conveys progress from {@link SwingThread} to {@link Control}.
     * @param dProgress containing percentage of progress made by {@link SwingThread}
     */
    //************************************************************************
    //*                 setProgress
    //************************************************************************
    protected void setProgress(final double dProgress) {

        logger.debug(LOG_TAG + ".setProgress()");

        control.setProgressSwingThread(dProgress);

    }

    /**
     * Conveys the state from {@link SwingThread} to {@link Control}.
     * @param state the {@link SwingThread} is in
     */
    //************************************************************************
    //*                 setState
    //************************************************************************
    protected void setState(final String state) {

        logger.debug(LOG_TAG + ".setState()");

        control.setStateSwingThread(state);

    }

    //************************************************************************
    //*                 declare
    //************************************************************************
    private static final String LOG_TAG = "*** " + Model4SwingThread.class.getSimpleName();

    private static Logger logger = null;

    private static Control control = null;

    private static SwingThread swingThread = null;

}
