package pack;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import org.apache.log4j.Logger;

/**
 * Table model for the client connection data.
 */
public class Dtm4Connection extends DefaultTableModel implements TableModelListener {

    /**
     * Constructor.
     */
    protected Dtm4Connection() {

        logger = logger.getLogger(Dtm4Connection.class);
        //logger.debug(LOG_TAG + "<<constructor>> Dtm4Connection()");

        // add listener
        addTableModelListener(this);
    }

    /**
     * Returns the number of rows in the data table model.
     * @return int number of rows
     */
    //************************************************************************
    //*                 getRowCount
    //************************************************************************
    @Override
    public int getRowCount() {
        //logger.debug(LOG_TAG + ".getRowCount()");

        return listData.size();
    }

    /**
     * Returns the number of columns in the data table model.
     * @return int number of columns
     */
    //************************************************************************
    //*                 getColumnCount
    //************************************************************************
    @Override
    public int getColumnCount() {
        //logger.debug(LOG_TAG + ".getColumnCount()");

        return columnName.length;
    }

    /**
     * Returns the name for a column in the data table model.
     * @return String name for the column
     */
    //************************************************************************
    //*                 getColumnName
    //************************************************************************
    @Override
    public String getColumnName(final int column) {
        //logger.debug(LOG_TAG + ".getColumnName()");

        return columnName[column];
    }

    /**
     * Sets an object in an element of the data table model.
     * Fires an event when the row is updated.
     * @param object {@link DataObject4Connection} object that is to be set
     * @param row the row where the {@link DataObject4Connection} object that is to be set
     * @param column the column where the {@link DataObject4Connection} object that is to be set
     */
    //************************************************************************
    //*                 setValueAt
    //************************************************************************
    @Override
    public void setValueAt(Object object, int row, int column) {
        logger.debug(LOG_TAG + ".setValueAt()");

        Object obj = listData.get(row);
        ((DataObject4Connection)obj).setValue(object, column);

        // let DataTable4Connection be updated
        fireTableCellUpdated(row, column);
    }

    /**
     * Delivers an object from an element in the data table model.
     * @param row the row for the wanted object
     * @param column the column for the wanted object
     * @return object the wanted {@link DataObject4Connection} object
     */
    //************************************************************************
    //*                 getValueAt
    //************************************************************************
    @Override
    public Object getValueAt(final int row, final int column) {
        //logger.debug(LOG_TAG + ".getValueAt()");

        Object object = listData.get(row);

        return ((DataObject4Connection)object).getValue(column);
    }

    /**
     * Adds an object with data for a client connection to the data table model.
     * Fires an event when the row is inserted.
     * @param dataObject4Connection the object that will be inserted
     */
    //************************************************************************
    //*                 addData
    //************************************************************************
    protected void addData(final DataObject4Connection dataObject4Connection) {
        //logger.debug(LOG_TAG + ".addData()");

        listData.add(dataObject4Connection);

        // trigger table model event
        // one row inserted: firstRow = 1, lastRow = 1
        fireTableRowsInserted(1, 1);
    }

    /**
     * Removes a row from the data table model. Fires an event when the row is deleted.
     * @param idx index of the row that is to be deleted
     */
    //************************************************************************
    //*                 removeData
    //************************************************************************
    protected void removeData(final int idx) {
        //logger.debug(LOG_TAG + ".removeData()");

        listData.remove(idx);

        // trigger table model event
        // one row deleted: firstRow at idx, lastRow at idx
        fireTableRowsDeleted(idx, idx);
    }

    /**
     * Does nothing
     * @param e table model event, triggered by a {@link TableModelListener}
     */
    //************************************************************************
    //*                 tableChanged
    //************************************************************************
    public void tableChanged(TableModelEvent e) {
        //logger.debug(LOG_TAG + ".tableChanged()");
    }

    /**
     * Finds a row with data for a client connection.
     * The client_id is garanteed to be found in the data table model.
     * @param client_id to identify a client
     */
    //************************************************************************
    //*                 removeDataOnClientId
    //************************************************************************
    protected void removeDataOnClientId(final int client_id) {

        logger.debug(LOG_TAG + ".removeDataOnClientId()");

        int i = 0;
        while (!String.valueOf(client_id).equals((String)getValueAt(i++, 0)));

        removeData(i - 1);

    }

    //************************************************************************
    //*                 declare
    //************************************************************************
    private static final String LOG_TAG = "*** " + Dtm4Connection.class.getSimpleName();

    private static Logger logger = null;

    private static String[] columnName =
        {"Client ID", "Timestamp", "User name", "Remote"};
    private static List<DataObject4Connection> listData = new ArrayList<>();
}