package pack;

import java.lang.Thread;

import org.apache.log4j.Logger;

/**
 * The application's model for {@link JavaThread}.
 */
public class Model4JavaThread {
    protected Model4JavaThread(final Control control) {

        logger = logger.getLogger(Model4JavaThread.class);
        logger.debug(LOG_TAG + "<<constructor>> Model4JavaThread()");

        this.control = control;

        javaThread_1 = new JavaThread(this, 1, control.JAVA_THREAD_INITIAL_DELAY);
        javaThread_2 = new JavaThread(this, 2, control.JAVA_THREAD_INITIAL_DELAY);

    }

    /**
     * Conveys the delay from {@link Control} to a {@link JavaThread}.
     * @param instanceNumber number for the {@link JavaThread} instance
     * @param msDelay the delay, in millisecond, for the background thread
     */
    //************************************************************************
    //*                 setDelayJavaThread
    //************************************************************************
    protected void setDelayJavaThread(final int instanceNumber,
        final int msDelay) {

        logger.debug(LOG_TAG + ".setDelayJavaThread()" + " " + instanceNumber);

        switch (instanceNumber) {
            case 1:
                javaThread_1.setDelayJavaThread(msDelay);
                break;
            case 2:
                javaThread_2.setDelayJavaThread(msDelay);
                break;
        } // eof switch

    }

    /**
     * Conveys the run command from the {@link Control} to a {@link JavaThread}.
     * @param instanceNumber number for the {@link JavaThread} instance
     */
    //************************************************************************
    //*                 runJavaThread
    //************************************************************************
    protected void runJavaThread(final int instanceNumber) {

        logger.debug(LOG_TAG + ".runJavaThread()" + " " + instanceNumber);

        switch (instanceNumber) {
            case 1:
                if (javaThread_1.getState().toString()
                    .equals(Thread.State.TERMINATED.toString())) {
                    // when a thread ran and has terminated,
                    // a new instance has to be created
                    javaThread_1 = new JavaThread(this, 1,
                        control.JAVA_THREAD_INITIAL_DELAY);
                }
                javaThread_1.start();
                break;
            case 2:
                if (javaThread_2.getState().toString()
                    .equals(Thread.State.TERMINATED.toString())) {
                    // when a thread ran and has terminated,
                    // a new instance has to be created
                    javaThread_2 = new JavaThread(this, 2,
                        control.JAVA_THREAD_INITIAL_DELAY);
                }
                javaThread_2.start();
                break;
        } // eof switch

    }

    /**
     * Conveys the interrupt command from the {@link Control} to a {@link JavaThread}.
     * @param instanceNumber number for the {@link JavaThread} instance
     * @param interrupt flag for interruption
     */
    //************************************************************************
    //*                 interruptJavaThread
    //************************************************************************
    protected void interruptJavaThread(final int instanceNumber,
        final boolean interrupt) {

        logger.debug(LOG_TAG + ".interuptJavaThread()" + " " + instanceNumber);

        switch (instanceNumber) {
            case 1:
                javaThread_1.interruptJavaThread(interrupt);
                break;
            case 2:
                javaThread_2.interruptJavaThread(interrupt);
                break;
        } // eof switch

    }

    /**
     * Conveys the stop command from the {@link Control} to a {@link JavaThread}.
     * @param instanceNumber number for the {@link JavaThread} instance
     */
    //************************************************************************
    //*                 stopJavaThread
    //************************************************************************
    protected void stopJavaThread(final int instanceNumber) {

        logger.debug(LOG_TAG + ".stopJavaThread()" + " " + instanceNumber);

        switch (instanceNumber) {
            case 1:
                javaThread_1.stopJavaThread();
                break;
            case 2:
                javaThread_2.stopJavaThread();
                break;
        } // eof switch

    }

    /**
     * Conveys the name from a {@link JavaThread} to the {@link Control}.
     * @param instanceNumber number for the {@link JavaThread} instance
     * @param name for the background thread
     */
    //************************************************************************
    //*                 setNameJavaThread
    //************************************************************************
    protected void setNameJavaThread(final int instanceNumber,
        final String name) {

        logger.debug(LOG_TAG + ".setNameJavaThread()");

        control.setNameJavaThread(instanceNumber, name);

    }

    /**
     * Conveys the state from a {@link JavaThread} to the {@link Control}.
     * @param instanceNumber number for the {@link JavaThread} instance
     * @param state the background thread is in
     */
    //************************************************************************
    //*                 setStateJavaThread
    //************************************************************************
    protected void setStateJavaThread(final int instanceNumber,
        final String state) {

        logger.debug(LOG_TAG + ".setStateJavaThread()");

        control.setStateJavaThread(instanceNumber, state);

    }

    /**
     * Conveys an intermidiate result from a {@link JavaThread} to the {@link Control}.
     * @param instanceNumber number for the {@link JavaThread} instance
     * @param uniChar an intermediate result from the background thread
     */
    //************************************************************************
    //*                 setResultJavaThread
    //************************************************************************
    protected void setResultJavaThread(final int instanceNumber,
        final char uniChar) {

        logger.debug(LOG_TAG + ".setResultJavaThread()");

        control.setResultJavaThread(instanceNumber, uniChar);

    }

    /**
     * Conveys the progress from a {@link JavaThread} to the {@link Control}.
     * @param instanceNumber number for the {@link JavaThread} instance
     * @param dProgress made by the background thread in percentage
     */
    //************************************************************************
    //*                 setProgressJavaThread
    //************************************************************************
    protected void setProgressJavaThread(final int instanceNumber,
        final double dProgress) {

        logger.debug(LOG_TAG + ".setProgressJavaThread()");

        control.setProgressJavaThread(instanceNumber, dProgress);

    }

    //************************************************************************
    //*                 declare
    //************************************************************************
    private static final String LOG_TAG = "*** " + Model4JavaThread.class.getSimpleName();

    private static Logger logger = null;

    private static Control control = null;

    private static JavaThread javaThread_1 = null;
    private static JavaThread javaThread_2 = null;

}
