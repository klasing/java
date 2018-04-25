package pack;

import java.lang.Thread;

import java.util.concurrent.TimeUnit;
import java.util.Random;

import org.apache.log4j.Logger;

/**
 * Background thread that consumes from the {@link Panel4Queue}.
 */
public class ConsumerThread extends Thread {
    /**
     * Constructor.
     * @param model4ProdCons {@link Model4ProdCons}
     * @param msDelay initial delay for the background thread
     */
    protected ConsumerThread(final Model4ProdCons model4ProdCons,
        final int msDelay) {

        logger = logger.getLogger(ConsumerThread.class);
        logger.debug(LOG_TAG + "<<constructor>> ConsumerThread()");

        this.model4ProdCons = model4ProdCons;
        this.msDelay = msDelay;

        model4ProdCons.setNameConsumerThread(getName());
        model4ProdCons.setStateConsumerThread(getState().toString());
    }

    /**
     * Sets the delay for the background thread.
     * @param msDelay delay in millisecond
     */
    //************************************************************************
    //*                 setDelayConsumerThread
    //************************************************************************
    protected synchronized void setDelayConsumerThread(final int msDelay) {

        logger.debug(LOG_TAG + ".setDelayConsumerThread()");

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

        Random random = new Random();
        try {
            while (thread_continue) {
                // delay is a normal distribution, with a mean value
                // set to msDelay, and a standard deviation set to
                // 0.5 * msDelay
                double delay = random.nextGaussian() * (0.5 * msDelay) + msDelay;
                TimeUnit.MILLISECONDS.sleep((int)delay);

                if (!thread_continue) break;

                synchronized(lock) {
                    model4ProdCons.setStateConsumerThread(
                        Thread.State.WAITING.toString());
                    while (interrupt) lock.wait();
                }

                model4ProdCons.consume();

                model4ProdCons.setStateConsumerThread(getState().toString());
            }

        } catch(Exception e) { e.printStackTrace(); }

        model4ProdCons.setStateConsumerThread(
            Thread.State.TERMINATED.toString());
    }

    /**
     * Interrupts the background thread, and brings it into a wait state.
     * Or resumes the background thread, bringing it from a wait state to
     * a runnable state.
     * @param interrupt flag for interrupt
     */
    //************************************************************************
    //*                 interruptConsumerThread
    //************************************************************************
    protected synchronized void interruptConsumerThread(final boolean interrupt) {

        logger.debug(LOG_TAG + ".interruptConsumerThread()");

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
    //*                 stopConsumerThread
    //************************************************************************
    protected synchronized void stopConsumerThread() {

        logger.debug(LOG_TAG + ".stopConsumerThread()");

        thread_continue = false;
    }

    //************************************************************************
    //*                 declare
    //************************************************************************
    private static final String LOG_TAG = "*** " + ConsumerThread.class.getSimpleName();

    private static Logger logger = null;

    private static Model4ProdCons model4ProdCons = null;

    // initial delay for thread is 1000 ms
    private volatile int msDelay = 0;
    private volatile boolean interrupt = false;
    private volatile boolean thread_continue = true;

    private final Object lock = new Object();

}
