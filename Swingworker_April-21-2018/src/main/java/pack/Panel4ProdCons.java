package pack;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;

import org.apache.log4j.Logger;

/**
 * Panel for showing Producer/Consumer and a Queue.
 */
public class Panel4ProdCons extends JPanel {
    /**
     * Constructor.
     * @param control {@link Control} instance
     */
    protected Panel4ProdCons(final Control control) {

        logger = logger.getLogger(Panel4ProdCons.class);
        logger.debug(LOG_TAG + "<<constructor>> Panel4ProdCons()");

        this.control = control;

        GridBagLayout gridBagLayout = new GridBagLayout();
        setLayout(gridBagLayout);

        GridBagConstraints constraint = new GridBagConstraints();
        constraint.insets = new Insets(5, 5, 5, 5);

        panel4Queue = new Panel4Queue(control);
        panel4Queue.titledBorder.setTitle("Queue");
        constraint.gridx = 0;
        constraint.gridy = 0;
        constraint.gridwidth = 2;
        constraint.gridheight = 1;
        constraint.anchor = GridBagConstraints.CENTER;
        gridBagLayout.setConstraints(panel4Queue, constraint);
        add(panel4Queue);

        panel4Producer = new Panel4Producer(control);
        panel4Producer.titledBorder.setTitle("Producer");
        constraint.gridx = 0;
        constraint.gridy = 1;
        constraint.gridwidth = 1;
        constraint.gridheight = 1;
        constraint.anchor = GridBagConstraints.WEST;
        gridBagLayout.setConstraints(panel4Producer, constraint);
        add(panel4Producer);

        panel4Consumer = new Panel4Consumer(control);
        panel4Consumer.titledBorder.setTitle("Consumer");
        constraint.gridx = 1;
        constraint.gridy = 1;
        constraint.gridwidth = 1;
        constraint.gridheight = 1;
        constraint.anchor = GridBagConstraints.WEST;
        gridBagLayout.setConstraints(panel4Consumer, constraint);
        add(panel4Consumer);

    }

    /**
     * Sets the producer into a wait state, and brings the consumer into a
     * runnable state.
     */
    //************************************************************************
    //*                 interruptProducerFromQueue
    //************************************************************************
    protected void interruptProducerFromQueue() {
        logger.debug(LOG_TAG + ".interruptProducerFromQueue()");

        panel4Producer.jbInterrupt.doClick();

        // let the GUI update the state change
        try {
            Thread.sleep(control.JAVA_THREAD_INITIAL_DELAY);
        } catch(Exception e) {
            e.printStackTrace();
        }

        if (panel4Consumer.jtfState.getText()
            .equals(Thread.State.WAITING.toString()))
            panel4Consumer.jbResume.doClick();

    }

    /**
     * Sets the name of the {@link ProducerThread}.
     * @param name of the background thread
     */
    //************************************************************************
    //*                 setNameProducerThread
    //************************************************************************
    protected void setNameProducerThread(final String name) {

        logger.debug(LOG_TAG + ".setNameProducerThread()");

        panel4Producer.jtfName.setText(name);

    }

    /**
     * Sets the state of the {@link ProducerThread}.
     * @param state of the background thread
     */
    //************************************************************************
    //*                 setStateProducerThread
    //************************************************************************
    protected void setStateProducerThread(final String state) {

        logger.debug(LOG_TAG + ".setStateProducerThread()");

        panel4Producer.jtfState.setText(state);

    }

    /**
     * Sends a produce message to the {@link Panel4Queue}
     */
    //************************************************************************
    //*                 produce
    //************************************************************************
    protected void produce() {

        logger.debug(LOG_TAG + ".produce()");

        panel4Queue.produce();

    }

    /**
     * Sets the consumer into a wait state, and brings the producer into a
     * runnable state.
     */
    //************************************************************************
    //*                 interruptConsumerFromQueue
    //************************************************************************
    protected void interruptConsumerFromQueue() {
        logger.debug(LOG_TAG + ".interruptConsumerFromQueue()");

        panel4Consumer.jbInterrupt.doClick();

        // let the GUI update the state change
        try {
            Thread.sleep(control.JAVA_THREAD_INITIAL_DELAY);
        } catch(Exception e) {
            e.printStackTrace();
        }

        if (panel4Producer.jtfState.getText()
            .equals(Thread.State.WAITING.toString()))
            panel4Producer.jbResume.doClick();

    }

    /**
     * Sets the name of the {@link ConsumerThread}.
     * @param name of the background thread
     */
    //************************************************************************
    //*                 setNameConsumerThread
    //************************************************************************
    protected void setNameConsumerThread(final String name) {

        logger.debug(LOG_TAG + ".setNameConsumerThread()");

        panel4Consumer.jtfName.setText(name);

    }

    /**
     * Sets the state of the {@link ConsumerThread}.
     * @param state of the background thread
     */
    //************************************************************************
    //*                 setStateConsumerThread
    //************************************************************************
    protected void setStateConsumerThread(final String state) {

        logger.debug(LOG_TAG + ".setStateConsumerThread()");

        panel4Consumer.jtfState.setText(state);

    }

    /**
     * Sends a consume message to the {@link Panel4Queue}
     */
    //************************************************************************
    //*                 consume
    //************************************************************************
    protected void consume() {

        logger.debug(LOG_TAG + ".consume()");

        panel4Queue.consume();

    }

    //************************************************************************
    //*                 declare
    //************************************************************************
    private static final String LOG_TAG = "*** " + Panel4ProdCons.class.getSimpleName();

    private static Logger logger = null;

    private static Control control = null;

    private static Panel4Queue panel4Queue = null;
    private static Panel4Producer panel4Producer = null;
    private static Panel4Consumer panel4Consumer = null;

}
