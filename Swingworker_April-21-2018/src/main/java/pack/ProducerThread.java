package pack;

import java.lang.Thread;

import java.util.concurrent.TimeUnit;
import java.util.Random;

import org.apache.log4j.Logger;

/**
 * Background thread that produces for the {@link Panel4Queue}.
 */
public class ProducerThread extends Thread {
    /**
     * Constructor.
     * @param model4ProdCons {@link Model4ProdCons}
     * @param msDelay initial delay for the background thread
     */
    protected ProducerThread(final Model4ProdCons model4ProdCons,
        final int msDelay) {

        logger = logger.getLogger(ProducerThread.class);
        logger.debug(LOG_TAG + "<<constructor>> ProducerThread()");

        this.model4ProdCons = model4ProdCons;
        this.msDelay = msDelay;

        model4ProdCons.setNameProducerThread(getName());
        model4ProdCons.setStateProducerThread(getState().toString());
    }

    /**
     * Sets the delay for the background thread.
     * @param msDelay delay in millisecond
     */
    //************************************************************************
    //*                 setDelayProducerThread
    //************************************************************************
    protected synchronized void setDelayProducerThread(final int msDelay) {

        logger.debug(LOG_TAG + ".setDelayProducerThread()");

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
                    model4ProdCons.setStateProducerThread(
                        Thread.State.WAITING.toString());
                    while (interrupt) lock.wait();
                }

                model4ProdCons.produce();

                model4ProdCons.setStateProducerThread(getState().toString());
            }

        } catch(Exception e) { e.printStackTrace(); }

        model4ProdCons.setStateProducerThread(
            Thread.State.TERMINATED.toString());

    }

    /**
     * Interrupts the background thread, and brings it into a wait state.
     * Or resumes the background thread, bringing it from a wait state to
     * a runnable state.
     * @param interrupt flag for interrupt
     */
    //************************************************************************
    //*                 interruptProducerThread
    //************************************************************************
    protected synchronized void interruptProducerThread(final boolean interrupt) {

        logger.debug(LOG_TAG + ".interruptProducerThread()");

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
    //*                 stopProducerThread
    //************************************************************************
    protected synchronized void stopProducerThread() {

        logger.debug(LOG_TAG + ".stopProducerThread()");

        thread_continue = false;
    }

    //************************************************************************
    //*                 declare
    //************************************************************************
    private static final String LOG_TAG = "*** " + ProducerThread.class.getSimpleName();

    private static Logger logger = null;

    private static Model4ProdCons model4ProdCons = null;

    // initial delay for thread is 1000 ms
    private volatile int msDelay = 0;
    private volatile boolean interrupt = false;
    private volatile boolean thread_continue = true;

    private final Object lock = new Object();

}
