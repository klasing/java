package pack;

import java.lang.Thread;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

/**
 * Background thread.
 */
public class JavaThread extends Thread {
    /**
     * Constructor.
     * @param model4JavaThread {@link Model4JavaThread}
     * @param instanceNumber number for the {@link JavaThread} instance
     * @param msDelay initial delay for the background thread
     */
    protected JavaThread(final Model4JavaThread model4JavaThread,
        final int instanceNumber, final int msDelay) {

        // logger remains the same for all JavaThread instances
        logger = logger.getLogger(JavaThread.class);
        logger.debug(LOG_TAG + "<<constructor>> JavaThread()");

        this.model4JavaThread = model4JavaThread;
        this.instanceNumber = instanceNumber;
        this.msDelay = msDelay;

        model4JavaThread.setNameJavaThread(instanceNumber, getName());
        model4JavaThread.setStateJavaThread(instanceNumber,
            getState().toString());

    }

    /**
     * Sets the delay for the background thread.
     * @param msDelay the delay, in millisecond, for the background thread
     */
    //************************************************************************
    //*                 setDelayJavaThread
    //************************************************************************
    protected synchronized void setDelayJavaThread(final int msDelay) {

        logger.debug(LOG_TAG + ".setDelayJavaThread()");

        this.msDelay = msDelay;

    }

    /**
     * The job that runs in the background.
     */
    //************************************************************************
    //*                 run
    //************************************************************************
    @Override
    public void run() {

        logger.debug(LOG_TAG + ".run()");

        double dDelta = 100.0 / 27.0;
        double dProgress = 0.0;
        char uniChar = 'A';

        try {
            for (int i = 0; i < 26; i++) {
                TimeUnit.MILLISECONDS.sleep(msDelay);

                if (!thread_continue) break;

                synchronized(lock) {
                    model4JavaThread.setStateJavaThread(instanceNumber,
                        Thread.State.WAITING.toString());
                    while (interrupt) lock.wait();
                }

                //logger.info("uniChar: " + uniChar);
                model4JavaThread.setResultJavaThread(instanceNumber,
                    uniChar);

                dProgress += dDelta;
                model4JavaThread.setProgressJavaThread(instanceNumber,
                    Math.floor(dProgress));

                model4JavaThread.setStateJavaThread(instanceNumber,
                    getState().toString());

                uniChar++;

            }
        } catch(Exception e) { e.printStackTrace(); }

        model4JavaThread.setStateJavaThread(instanceNumber,
            Thread.State.TERMINATED.toString());

    }

    /**
     * Interrupts the background thread.
     * @param interrupt flag for interruption
     */
    //************************************************************************
    //*                 interruptJavaThread
    //************************************************************************
    protected synchronized void interruptJavaThread(final boolean interrupt) {

        logger.debug(LOG_TAG + ".interruptJavaThread()");

        this.interrupt = interrupt;

        if (!interrupt) {
            synchronized(lock) {
                lock.notify();
            }
        }
    }

    /**
     * Stops the background thread.
     */
    //************************************************************************
    //*                 stopJavaThread
    //************************************************************************
    protected synchronized void stopJavaThread() {

        logger.debug(LOG_TAG + ".stopJavaThread()");

        thread_continue = false;
    }

    //************************************************************************
    //*                 declare
    //************************************************************************
    private static final String LOG_TAG = "*** " + JavaThread.class.getSimpleName();

    private static Logger logger = null;

    private static Model4JavaThread model4JavaThread = null;

    private int instanceNumber = 0;
    // initial delay for thread is 1000 ms
    private volatile int msDelay = 0;
    private volatile boolean interrupt = false;
    private volatile boolean thread_continue = true;

    private final Object lock = new Object();

}
