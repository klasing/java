package pack;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.List;

import javax.swing.SwingWorker;

import org.apache.log4j.Logger;

/**
 * Background thread.
 */
public class SwingThread extends SwingWorker<List<String>, String>
    implements PropertyChangeListener {
    /**
     * Constructor.
     * @param model4SwingThread {@link Model4SwingThread}
     */
    protected SwingThread(final Model4SwingThread model4SwingThread) {

        logger = logger.getLogger(SwingThread.class);
        logger.debug(LOG_TAG + "<<constructor>> SwingThread()");

        this.model4SwingThread = model4SwingThread;

        addPropertyChangeListener(this);

    }

    /**
     * The job that runs in the background.
     * @return list the data produced by the background job
     * @throws Exception for whatever reason
     */
    //************************************************************************
    //*                 doInBackground
    //************************************************************************
    @Override
    public List<String> doInBackground() throws Exception {

        logger.debug(LOG_TAG + ".doInBackground()");

        double dDelta = 100.0 / 27.0;
        dProgress = 0.0;
        List<String> list = new ArrayList<String>();
        String string = null;
        char uniChar = 'A';

        for (int i = 0; i < 26; i++) {
            TimeUnit.SECONDS.sleep(1);

            string = new Character(uniChar).toString();
            list.add(string);
            publish(string);

            dProgress += dDelta;
            setProgress((int)Math.floor(dProgress));

            uniChar++;
        }

        return list;
    }

    /**
     * Processes the intermediate result from the background job.
     * @param chunks list with intermediate data, produced by the background job
     */
    //************************************************************************
    //*                 process
    //************************************************************************
    @Override
    protected void process(List<String> chunks) {

        logger.debug(LOG_TAG + ".process()");

        for (String string : chunks) {
            model4SwingThread.setResult(string);
        }

    }

    /**
     * Called when the background job has finished its task.
     */
    //************************************************************************
    //*                 done
    //************************************************************************
    @Override
    protected void done() {

        logger.debug(LOG_TAG + ".done()");

    }

    /**
     * State changes of the background thread, indicating the state the background thread is in.
     * @param e ProperChangeEvent, triggered by a PropertyChangeListener
     */
    //************************************************************************
    //*                 propertyChange
    //************************************************************************
    public void propertyChange(PropertyChangeEvent e) {

        logger.debug(LOG_TAG + ".propertyChangeListener()");

        switch(e.getPropertyName()) {
            case "progress":
                logger.debug(LOG_TAG + "progress: " + e.getNewValue());
                model4SwingThread.setProgress(dProgress);
                break;
            case "state":
                switch((StateValue)e.getNewValue()) {
                    case STARTED:
                        logger.debug(LOG_TAG + "state: STARTED");
                        model4SwingThread.setState("STARTED");
                        break;
                    case PENDING:
                        logger.debug(LOG_TAG + "state: PENDING");
                        model4SwingThread.setState("PENDING");
                        break;
                    case DONE:
                        logger.debug(LOG_TAG + "state: DONE");
                        model4SwingThread.setState("DONE");
                        break;
                } // eof switch
        } // eof switch

    }

    //************************************************************************
    //*                 declare
    //************************************************************************
    private static final String LOG_TAG = "*** " + SwingThread.class.getSimpleName();

    private static Logger logger = null;

    private static Model4SwingThread model4SwingThread = null;

    private static double dProgress = 0.0;

}
