package pack;

import javax.swing.JFrame;

import org.apache.log4j.Logger;

/**
 * The application's business logic.
 */
public class Control {
    /**
     * Constructor
     */
    protected Control() {

        logger = logger.getLogger(Control.class);
        logger.debug(LOG_TAG + "<<constructor>> Control()");

        view = new View(this);

    }

    /**
     * Called when GUI frame is build and visible on screen.
     */
    //************************************************************************
    //*                 frameIsVisible
    //************************************************************************
    protected void frameIsVisible() {

        logger.debug(LOG_TAG + ".frameIsVisible()");

        model4SwingThread = new Model4SwingThread(this);
        model4JavaThread = new Model4JavaThread(this);
        model4ProdCons = new Model4ProdCons(this);

    }

    /**
     * The returns the frame in which the application is visible.
     * @return the application frame
     */
    //************************************************************************
    //*                 getFrame
    //************************************************************************
    protected JFrame getFrame() {
        logger.debug(LOG_TAG + ".getFrame()");

        return view.getFrame();
    }

    /**
     * Conveys the start command from {@link Panel4SwingThread}
     * to {@link Model4SwingThread}.
     */
    //************************************************************************
    //*                 startSwingThread
    //************************************************************************
    protected void startSwingThread() {

        logger.debug(LOG_TAG + ".startSwingThread()");

        model4SwingThread.startThread();

    }

    /**
     * Conveys the cancel command from {@link Panel4SwingThread}
     * to {@link Model4SwingThread}.
     */
    //************************************************************************
    //*                 cancelSwingThread
    //************************************************************************
    protected void cancelSwingThread() {

        logger.debug(LOG_TAG + ".cancelSwingThread()");

        model4SwingThread.cancelThread();

    }

    /**
     * Conveys an intermediate result from {@link Model4SwingThread}
     * to {@link Panel4SwingThread}.
     * @param string containing an intermediate result from {@link SwingThread}
     */
    //************************************************************************
    //*                 setResultSwingThread
    //************************************************************************
    protected void setResultSwingThread(final String string) {

        logger.debug(LOG_TAG + ".setResultSwingThread()");

        view.panel4Tab.panel4SwingThread.setResultSwingThread(string);

    }

    /**
     * Conveys progress from {@link Model4SwingThread} to {@link Panel4SwingThread}.
     * @param dProgress containing percentage of progress made by {@link SwingThread}
     */
    //************************************************************************
    //*                 setProgressSwingThread
    //************************************************************************
    protected void setProgressSwingThread(final double dProgress) {

        logger.debug(LOG_TAG + ".setProgressSwingThread()");

        view.panel4Tab.panel4SwingThread.setProgressSwingThread(dProgress);

    }

    /**
     * Conveys state from {@link Model4SwingThread} to {@link Panel4SwingThread}.
     * @param state containing state of {@link SwingThread}
     */
    //************************************************************************
    //*                 setStateSwingThread
    //************************************************************************
    protected void setStateSwingThread(final String state) {

        logger.debug(LOG_TAG + ".setStateSwingThread()");

        view.panel4Tab.panel4SwingThread.setStateSwingThread(state);

    }

    /**
     * Conveys the delay from {@link Panel4JavaThread} to {@link JavaThread}.
     * @param instanceNumber number for the {@link JavaThread} instance
     * @param msDelay delay in millisecond
     */
    //************************************************************************
    //*                 setDelayJavaThread
    //************************************************************************
    protected void setDelayJavaThread(final int instanceNumber,
        final int msDelay) {

        logger.debug(LOG_TAG + ".setDelayJavaThread()" + " " + instanceNumber);

        model4JavaThread.setDelayJavaThread(instanceNumber, msDelay);

    }

    /**
     * Conveys the run command from {@link Panel4JavaThread} to {@link JavaThread}.
     * @param instanceNumber number for the {@link JavaThread} instance
     */
    //************************************************************************
    //*                 runJavaThread
    //************************************************************************
    protected void runJavaThread(final int instanceNumber) {
        logger.debug(LOG_TAG + ".runJavaThread()" + " " + instanceNumber);

        model4JavaThread.runJavaThread(instanceNumber);

    }

    /**
     * Conveys the interrupt command from {@link Panel4JavaThread} to {@link JavaThread}.
     * @param instanceNumber number for the {@link JavaThread} instance
     * @param interrupt flag for interruption
     */
    //************************************************************************
    //*                 interruptJavaThread
    //************************************************************************
    protected void interruptJavaThread(final int instanceNumber,
        final boolean interrupt) {
        logger.debug(LOG_TAG + ".interuptJavaThread()" + " " + instanceNumber);

        model4JavaThread.interruptJavaThread(instanceNumber, interrupt);

    }

    /**
     * Conveys the stop command from {@link Panel4JavaThread} to {@link JavaThread}.
     * @param instanceNumber number for the {@link JavaThread} instance
     */
    //************************************************************************
    //*                 stopJavaThread
    //************************************************************************
    protected void stopJavaThread(final int instanceNumber) {
        logger.debug(LOG_TAG + ".stopJavaThread()" + " " + instanceNumber);

        model4JavaThread.stopJavaThread(instanceNumber);

    }

    /**
     * Conveys the name of the background thread from {@link JavaThread}
     * to {@link Panel4JavaThread}.
     * @param instanceNumber number for the {@link JavaThread} instance
     * @param name for the {@link JavaThread}
     */
    //************************************************************************
    //*                 setNameJavaThread
    //************************************************************************
    protected void setNameJavaThread(final int instanceNumber,
        final String name) {

        logger.debug(LOG_TAG + ".setNameJavaThread()");

        view.panel4Tab.panel4TwoJavaThread
            .setNameJavaThread(instanceNumber, name);

    }

    /**
     * Conveys the state of the background thread from {@link JavaThread}
     * to {@link Panel4JavaThread}.
     * @param instanceNumber number for the {@link JavaThread} instance
     * @param state of the {@link JavaThread}
     */
    //************************************************************************
    //*                 setStateJavaThread
    //************************************************************************
    protected void setStateJavaThread(final int instanceNumber,
        final String state) {

        logger.debug(LOG_TAG + ".setStateJavaThread()");

        view.panel4Tab.panel4TwoJavaThread
            .setStateJavaThread(instanceNumber, state);

    }

    /**
     * Conveys the intermediate result of the background thread from {@link JavaThread}
     * to {@link Panel4JavaThread}.
     * @param instanceNumber number for the {@link JavaThread} instance
     * @param uniChar the intermediate result of the {@link JavaThread}
     */
    //************************************************************************
    //*                 setResultJavaThread
    //************************************************************************
    protected void setResultJavaThread(final int instanceNumber,
        final char uniChar) {

        logger.debug(LOG_TAG + ".setResultJavaThread()");

        view.panel4Tab.panel4TwoJavaThread
            .setResultJavaThread(instanceNumber, uniChar);

    }

    /**
     * Conveys the progress made by the background thread from {@link JavaThread}
     * to {@link Panel4JavaThread}.
     * @param instanceNumber number for the {@link JavaThread} instance
     * @param dProgress the progress made by the {@link JavaThread}
     */
    //************************************************************************
    //*                 setProgressJavaThread
    //************************************************************************
    protected void setProgressJavaThread(final int instanceNumber,
        final double dProgress) {

        logger.debug(LOG_TAG + ".setProgressJavaThread()");

        view.panel4Tab.panel4TwoJavaThread
            .setProgressJavaThread(instanceNumber, dProgress);

    }

    /**
     * Conveys a message to set the delay for the {@link ProducerThread} to
     * the {@link Model4ProdCons}.
     * @param msDelay delay in millisecond
     */
    //************************************************************************
    //*                 setDelayProducerThread
    //************************************************************************
    protected void setDelayProducerThread(final int msDelay) {
        logger.debug(LOG_TAG + ".setDelayProducerThread()");

        model4ProdCons.setDelayProducerThread(msDelay);

    }

    /**
     * Conveys the run command for the {@link ProducerThread} to the
     * {@link Model4ProdCons}.
     */
    //************************************************************************
    //*                 runProducerThread
    //************************************************************************
    protected void runProducerThread() {
        logger.debug(LOG_TAG + ".runProducerThread()");

        model4ProdCons.runProducerThread();

    }

    /**
     * Conveys an interrupt message from the {@link Panel4Queue} to the
     * {@link Panel4ProdCons}, to indicate that the queue is full.
     */
    //************************************************************************
    //*                 interruptProducerFromQueue
    //************************************************************************
    protected void interruptProducerFromQueue() {
        logger.debug(LOG_TAG + ".interruptProducerFromQueue()");

        view.panel4Tab.panel4ProdCons
            .interruptProducerFromQueue();

    }

    /**
     * Conveys the interrupt command for the {@link ProducerThread} to the
     * {@link Model4ProdCons}.
     * @param interrupt flag for interruption
     */
    //************************************************************************
    //*                 interruptProducerThread
    //************************************************************************
    protected void interruptProducerThread(final boolean interrupt) {
        logger.debug(LOG_TAG + ".interruptProducerThread()");

        model4ProdCons.interruptProducerThread(interrupt);

    }

    /**
     * Conveys the stop command for the {@link ProducerThread} to the
     * {@link Model4ProdCons}.
     */
    //************************************************************************
    //*                 stopProducerThread
    //************************************************************************
    protected void stopProducerThread() {
        logger.debug(LOG_TAG + ".stopProducerThread()");

        model4ProdCons.stopProducerThread();

    }

    /**
     * Conveys a message with the name of the {@link ProducerThread}
     * to the {@link Panel4ProdCons}.
     * @param name of the {@link ProducerThread}
     */
    //************************************************************************
    //*                 setNameProducerThread
    //************************************************************************
    protected void setNameProducerThread(final String name) {

        logger.debug(LOG_TAG + ".setNameProducerThread()");

        view.panel4Tab.panel4ProdCons
            .setNameProducerThread(name);

    }

    /**
     * Conveys a message concerning the state of the {@link ProducerThread}
     * to the {@link Panel4ProdCons}.
     * @param state the {@link ProducerThread} is in
     */
    //************************************************************************
    //*                 setStateProducerThread
    //************************************************************************
    protected void setStateProducerThread(final String state) {

        logger.debug(LOG_TAG + ".setStateProducerThread()");

        view.panel4Tab.panel4ProdCons
            .setStateProducerThread(state);

    }

    /**
     * Conveys a produce message to the {@link Panel4ProdCons}.
     */
    //************************************************************************
    //*                 produce
    //************************************************************************
    protected void produce() {

        logger.debug(LOG_TAG + ".produce()");

        view.panel4Tab.panel4ProdCons.produce();

    }

    /**
     * Conveys a message to set the delay for the {@link ConsumerThread} to
     * the {@link Model4ProdCons}.
     * @param msDelay delay in millisecond
     */
    //************************************************************************
    //*                 setDelayConsumerThread
    //************************************************************************
    protected void setDelayConsumerThread(final int msDelay) {
        logger.debug(LOG_TAG + ".setDelayConsumerThread()");

        model4ProdCons.setDelayConsumerThread(msDelay);

    }

    /**
     * Conveys the run command for the {@link ConsumerThread} to the
     * {@link Model4ProdCons}.
     */
    //************************************************************************
    //*                 runConsumerThread
    //************************************************************************
    protected void runConsumerThread() {
        logger.debug(LOG_TAG + ".runConsumerThread()");

        model4ProdCons.runConsumerThread();

    }

    /**
     * Conveys an interrupt message from the {@link Panel4Queue} to the
     * {@link Panel4ProdCons}, to indicate that the queue is empty.
     */
    //************************************************************************
    //*                 interruptConsumerFromQueue
    //************************************************************************
    protected void interruptConsumerFromQueue() {
        logger.debug(LOG_TAG + ".interruptConsumerFromQueue()");

        view.panel4Tab.panel4ProdCons
            .interruptConsumerFromQueue();

    }

    /**
     * Conveys the interrupt command for the {@link ConsumerThread} to the
     * {@link Model4ProdCons}.
     * @param interrupt flag for interruption
     */
    //************************************************************************
    //*                 interruptConsumerThread
    //************************************************************************
    protected void interruptConsumerThread(final boolean interrupt) {
        logger.debug(LOG_TAG + ".interruptConsumerThread()");

        model4ProdCons.interruptConsumerThread(interrupt);

    }

    /**
     * Conveys the stop command for the {@link ConsumerThread} to the
     * {@link Model4ProdCons}.
     */
    //************************************************************************
    //*                 stopConsumerThread
    //************************************************************************
    protected void stopConsumerThread() {
        logger.debug(LOG_TAG + ".stopConsumerThread()");

        model4ProdCons.stopConsumerThread();

    }

    /**
     * Conveys a message with the name of the {@link ConsumerThread}
     * to the {@link Panel4ProdCons}.
     * @param name of the {@link ConsumerThread}
     */
    //************************************************************************
    //*                 setNameConsumerThread
    //************************************************************************
    protected void setNameConsumerThread(final String name) {

        logger.debug(LOG_TAG + ".setNameConsumerThread()");

        view.panel4Tab.panel4ProdCons
            .setNameConsumerThread(name);

    }

    /**
     * Conveys a message concerning the state of the {@link ConsumerThread}
     * to the {@link Panel4ProdCons}.
     * @param state the {@link ConsumerThread} is in
     */
    //************************************************************************
    //*                 setStateConsumerThread
    //************************************************************************
    protected void setStateConsumerThread(final String state) {

        logger.debug(LOG_TAG + ".setStateConsumerThread()");

        view.panel4Tab.panel4ProdCons
            .setStateConsumerThread(state);

    }

    /**
     * Conveys a consume message to the {@link Panel4ProdCons}.
     */
    //************************************************************************
    //*                 consume
    //************************************************************************
    protected void consume() {

        logger.debug(LOG_TAG + ".consume()");

        view.panel4Tab.panel4ProdCons.consume();

    }

    /**
     * Conveys the exit command to {@link Main}.
     * @param exit_code the exit code for exiting
     */
    //************************************************************************
    //*                 exit
    //************************************************************************
    protected void exit(final int exit_code) {

        logger.debug(LOG_TAG + ".exit()");

        Main.exit(exit_code);
    }

    //************************************************************************
    //*                 declare
    //************************************************************************
    private static final String LOG_TAG = "*** " + Control.class.getSimpleName();
    protected static final int JAVA_THREAD_INITIAL_DELAY = 1000;

    private static Logger logger = null;

    private static Model4SwingThread model4SwingThread = null;
    private static Model4JavaThread model4JavaThread = null;
    private static Model4ProdCons model4ProdCons = null;

    private static View view = null;

}
