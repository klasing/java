package pack;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JPanel;

import org.apache.log4j.Logger;

/**
 * Panel that displays the {@link ProducerThread} activity and
 * the {@link ConsumerThread} activity.
 */
public class Panel4Queue extends JPanel {
    /**
     * Constructor.
     * @param control {@link Control}
     */
    protected Panel4Queue(final Control control) {

        logger = logger.getLogger(Panel4Queue.class);
        logger.debug(LOG_TAG + "<<constructor>> Panel4Queue()");

        this.control = control;

        GridBagLayout gridBagLayout = new GridBagLayout();
        setLayout(gridBagLayout);

        GridBagConstraints constraint = new GridBagConstraints();
        constraint.insets = new Insets(5, 5, 5, 5);

        for (int i = 0; i < NOF_BUCKET; i++) {
            JPanel jPanel = new JPanel();
            jPanel.setPreferredSize(new Dimension(BUCKET_WIDTH,
                BUCKET_HEIGHT));
            jPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            constraint.gridx = i;
            constraint.gridy = 0;
            constraint.gridwidth = 1;
            constraint.gridheight = 1;
            constraint.anchor = GridBagConstraints.CENTER;
            gridBagLayout.setConstraints(jPanel, constraint);
            add(jPanel);
            // add to array
            aQueue[i] = jPanel;
        }

        titledBorder = BorderFactory.createTitledBorder("title");
        setBorder(titledBorder);
    }

    /**
     * Sets a bucket into the produced state.
     */
    //************************************************************************
    //*                 produce
    //************************************************************************
    protected void produce() {

        logger.debug(LOG_TAG + ".produce()");

        if (index == NOF_BUCKET) {
            control.interruptProducerFromQueue();
        } else {
            aQueue[index++].setBackground(Color.RED);
        }
    }

    /**
     * Sets a bucket into the consumed state.
     */
    //************************************************************************
    //*                 consume
    //************************************************************************
    protected void consume() {

        logger.debug(LOG_TAG + ".consume()");

        if (index == 0) {
            control.interruptConsumerFromQueue();
        } else {
            aQueue[--index].setBackground(null);
        }
    }

    //************************************************************************
    //*                 declare
    //************************************************************************
    private static final String LOG_TAG = "*** " + Panel4Queue.class.getSimpleName();
    private static final int NOF_BUCKET = 20;
    private static final int BUCKET_WIDTH = 15;
    private static final int BUCKET_HEIGHT = 15;

    private static Logger logger = null;

    private static Control control = null;

    protected static TitledBorder titledBorder = null;

    protected static JPanel[] aQueue = new JPanel[NOF_BUCKET];
    private static int index = 0;

}
