package pack;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;

import org.apache.log4j.Logger;

/**
 * Panel for showing two {@link JavaThread}.
 */
public class Panel4TwoJavaThread extends JPanel {
    /**
     * Constructor.
     * @param control {@link Control} instance
     */
    protected Panel4TwoJavaThread(final Control control) {

        logger = logger.getLogger(Panel4TwoJavaThread.class);
        logger.debug(LOG_TAG + "<<constructor>> Panel4TwoJavaThread()");

        this.control = control;

        GridBagLayout gridBagLayout = new GridBagLayout();
        setLayout(gridBagLayout);

        GridBagConstraints constraint = new GridBagConstraints();
        constraint.insets = new Insets(5, 5, 5, 5);

        panel4JavaThread_1 = new Panel4JavaThread(control, 1);
        panel4JavaThread_1.titledBorder.setTitle("Thread 1");
        constraint.gridx = 0;
        constraint.gridy = 0;
        constraint.gridwidth = 1;
        constraint.gridheight = 1;
        constraint.anchor = GridBagConstraints.WEST;
        gridBagLayout.setConstraints(panel4JavaThread_1, constraint);
        add(panel4JavaThread_1);

        panel4JavaThread_2 = new Panel4JavaThread(control, 2);
        panel4JavaThread_2.titledBorder.setTitle("Thread 2");
        constraint.gridx = 1;
        constraint.gridy = 0;
        constraint.gridwidth = 1;
        constraint.gridheight = 1;
        constraint.anchor = GridBagConstraints.WEST;
        gridBagLayout.setConstraints(panel4JavaThread_2, constraint);
        add(panel4JavaThread_2);
    }

    /**
     * Sets a name for a {@link JavaThread}.
     * @param instanceNumber number for the {@link JavaThread} instance
     * @param name name for the {@link JavaThread}
     */
    //************************************************************************
    //*                 setNameJavaThread
    //************************************************************************
    protected void setNameJavaThread(final int instanceNumber,
        final String name) {

        logger.debug(LOG_TAG + ".setNameJavaThread()");

        switch (instanceNumber) {
            case 1:
                panel4JavaThread_1.jtfName.setText(name);
                break;
            case 2:
                panel4JavaThread_2.jtfName.setText(name);
                break;
        } // eof switch

    }

    /**
     * Sets the state for a {@link JavaThread}.
     * @param instanceNumber number for the {@link JavaThread} instance
     * @param state the {@link JavaThread} is in
     */
    //************************************************************************
    //*                 setStateJavaThread
    //************************************************************************
    protected void setStateJavaThread(final int instanceNumber,
        final String state) {

        logger.debug(LOG_TAG + ".setStateJavaThread()");

        switch (instanceNumber) {
            case 1:
                panel4JavaThread_1.jtfState.setText(state);
                if (state.equals(Thread.State.TERMINATED.toString())) {

                    panel4JavaThread_1.jbRun.setEnabled(true);
                    panel4JavaThread_1.jbInterrupt.setEnabled(false);
                    panel4JavaThread_1.jbResume.setEnabled(false);
                    panel4JavaThread_1.jbStop.setEnabled(false);

                    if (panel4JavaThread_1.result.length() == 26)
                        panel4JavaThread_1.jpbProgress.setValue(100);
                }
                break;
            case 2:
                panel4JavaThread_2.jtfState.setText(state);
                if (state.equals(Thread.State.TERMINATED.toString())) {

                    panel4JavaThread_2.jbRun.setEnabled(true);
                    panel4JavaThread_2.jbInterrupt.setEnabled(false);
                    panel4JavaThread_2.jbResume.setEnabled(false);
                    panel4JavaThread_2.jbStop.setEnabled(false);

                    if (panel4JavaThread_2.result.length() == 26)
                        panel4JavaThread_2.jpbProgress.setValue(100);
                }
                break;
        } // eof switch

    }

    /**
     * Sets an intermediate result from a {@link JavaThread}.
     * @param instanceNumber number for the {@link JavaThread} instance
     * @param uniChar a intermediate result, produced by a {@link JavaThread}
     */
    //************************************************************************
    //*                 setResultJavaThread
    //************************************************************************
    protected void setResultJavaThread(final int instanceNumber,
        final char uniChar) {

        logger.debug(LOG_TAG + ".setResultJavaThread()");

        switch (instanceNumber) {
            case 1:
                panel4JavaThread_1.setResultJavaThread(uniChar);
                break;
            case 2:
                panel4JavaThread_2.setResultJavaThread(uniChar);
                break;
        } // eof switch

    }

    /**
     * Sets the progress made by a {@link JavaThread}.
     * @param instanceNumber number for the {@link JavaThread} instance
     * @param dProgress the progress made by a {@link JavaThread} in percentage
     */
    //************************************************************************
    //*                 setProgressJavaThread
    //************************************************************************
    protected void setProgressJavaThread(final int instanceNumber,
        final double dProgress) {

        logger.debug(LOG_TAG + ".setProgressJavaThread()");

        switch (instanceNumber) {
            case 1:
                panel4JavaThread_1.jpbProgress.setValue((int)dProgress);
                break;
            case 2:
                panel4JavaThread_2.jpbProgress.setValue((int)dProgress);
                break;
        } // eof switch

    }

    //************************************************************************
    //*                 declare
    //************************************************************************
    private static final String LOG_TAG = "*** " + Panel4TwoJavaThread.class.getSimpleName();

    private static Logger logger = null;

    private static Control control = null;

    private static Panel4JavaThread panel4JavaThread_1 = null;
    private static Panel4JavaThread panel4JavaThread_2 = null;

}
