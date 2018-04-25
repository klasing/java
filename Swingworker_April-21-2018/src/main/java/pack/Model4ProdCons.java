package pack;

import java.lang.Thread;

import org.apache.log4j.Logger;

/**
 * Model for the {@link ProducerThread} and the
 * {@link ConsumerThread}.
 */
public class Model4ProdCons {
    /**
     * Constructor.
     * @param control {@link Control}
     */
    protected Model4ProdCons(final Control control) {

        logger = logger.getLogger(Model4ProdCons.class);
        logger.debug(LOG_TAG + "<<constructor>> Model4ProdCons()");

        this.control = control;

        producerThread = new ProducerThread(this, control.JAVA_THREAD_INITIAL_DELAY);
        consumerThread = new ConsumerThread(this, control.JAVA_THREAD_INITIAL_DELAY);
    }

    /**
     * Sets the delay for the {@link ProducerThread}.
     * @param msDelay in millisecond
     */
    //************************************************************************
    //*                 setDelayProducerThread
    //************************************************************************
    protected void setDelayProducerThread(final int msDelay) {
        logger.debug(LOG_TAG + ".setDelayProducerThread()");

        producerThread.setDelayProducerThread(msDelay);

    }

    /**
     * Brings the {@link ProducerThread} into the runnable state.
     */
    //************************************************************************
    //*                 runProducerThread
    //************************************************************************
    protected void runProducerThread() {
        logger.debug(LOG_TAG + ".runProducerThread()");

        if (producerThread.getState().toString()
            .equals(Thread.State.TERMINATED.toString())) {
            // when a thread ran and has terminated,
            // a new instance has to be created
            producerThread = new ProducerThread(this,
                control.JAVA_THREAD_INITIAL_DELAY);
        }

        producerThread.start();

    }

    /**
     * Brings the {@link ProducerThread} into the runnable state,
     * or brings the {@link ProducerThread} into the wait state.
     * @param interrupt flag for interruption
     */
    //************************************************************************
    //*                 interruptProducerThread
    //************************************************************************
    protected void interruptProducerThread(final boolean interrupt) {
        logger.debug(LOG_TAG + ".interruptProducerThread()");

        producerThread.interruptProducerThread(interrupt);

    }

    /**
     * Stops the {@link ProducerThread} and brings it to the terminated state.
     */
    //************************************************************************
    //*                 stopProducerThread
    //************************************************************************
    protected void stopProducerThread() {
        logger.debug(LOG_TAG + ".stopProducerThread()");

        producerThread.stopProducerThread();

    }

    /**
     * Conveys the name of the {@link ProducerThread} to the {@link Control}.
     * @param name of the {@link ProducerThread}
     */
    //************************************************************************
    //*                 setNameProducerThread
    //************************************************************************
    protected void setNameProducerThread(final String name) {

        logger.debug(LOG_TAG + ".setNameProducerThread()");

        control.setNameProducerThread(name);

    }

    /**
     * Conveys the state of the {@link ProducerThread} to the {@link Control}.
     * @param state of the {@link ProducerThread}
     */
    //************************************************************************
    //*                 setStateProducerThread
    //************************************************************************
    protected void setStateProducerThread(final String state) {

        logger.debug(LOG_TAG + ".setStateProducerThread()");

        control.setStateProducerThread(state);

    }

    /**
     * Conveys a produce message to the {@link Control}.
     */
    //************************************************************************
    //*                 produce
    //************************************************************************
    protected void produce() {

        logger.debug(LOG_TAG + ".produce()");

        control.produce();

    }

    /**
     * Sets the delay for the {@link ConsumerThread}.
     * @param msDelay in millisecond
     */
    //************************************************************************
    //*                 setDelayConsumerThread
    //************************************************************************
    protected void setDelayConsumerThread(final int msDelay) {
        logger.debug(LOG_TAG + ".setDelayConsumerThread()");

        consumerThread.setDelayConsumerThread(msDelay);

    }

    /**
     * Brings the {@link ConsumerThread} into the runnable state.
     */
    //************************************************************************
    //*                 runConsumerThread
    //************************************************************************
    protected void runConsumerThread() {
        logger.debug(LOG_TAG + ".runConsumerThread()");

        if (consumerThread.getState().toString()
            .equals(Thread.State.TERMINATED.toString())) {
            // when a thread ran and has terminated,
            // a new instance has to be created
            consumerThread = new ConsumerThread(this,
                control.JAVA_THREAD_INITIAL_DELAY);
        }

        consumerThread.start();

    }

    /**
     * Brings the {@link ConsumerThread} into the runnable state,
     * or brings the {@link ConsumerThread} into the wait state.
     * @param interrupt flag for interruption
     */
    //************************************************************************
    //*                 interruptConsumerThread
    //************************************************************************
    protected void interruptConsumerThread(final boolean interrupt) {
        logger.debug(LOG_TAG + ".interruptConsumerThread()");

        consumerThread.interruptConsumerThread(interrupt);

    }

    /**
     * Stops the {@link ProducerThread} and brings it to the terminated state.
     */
    //************************************************************************
    //*                 stopConsumerThread
    //************************************************************************
    protected void stopConsumerThread() {
        logger.debug(LOG_TAG + ".stopConsumerThread()");

        consumerThread.stopConsumerThread();

    }

    /**
     * Conveys the name of the {@link ConsumerThread} to the {@link Control}.
     * @param name of the {@link ConsumerThread}
     */
    //************************************************************************
    //*                 setNameConsumerThread
    //************************************************************************
    protected void setNameConsumerThread(final String name) {

        logger.debug(LOG_TAG + ".setNameConsumerThread()");

        control.setNameConsumerThread(name);

    }

    /**
     * Conveys the state of the {@link ConsumerThread} to the {@link Control}.
     * @param state of the {@link ConsumerThread}
     */
    //************************************************************************
    //*                 setStateConsumerThread
    //************************************************************************
    protected void setStateConsumerThread(final String state) {

        logger.debug(LOG_TAG + ".setStateConsumerThread()");

        control.setStateConsumerThread(state);

    }

    /**
     * Conveys a consume message to the {@link Control}.
     */
    //************************************************************************
    //*                 consume
    //************************************************************************
    protected void consume() {

        logger.debug(LOG_TAG + ".consume()");

        control.consume();

    }

    //************************************************************************
    //*                 declare
    //************************************************************************
    private static final String LOG_TAG = "*** " + Model4ProdCons.class.getSimpleName();

    private static Logger logger = null;

    private static Control control = null;

    private static ProducerThread producerThread = null;
    private static ConsumerThread consumerThread = null;
}
