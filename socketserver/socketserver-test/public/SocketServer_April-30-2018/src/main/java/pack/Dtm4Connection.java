package pack;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import org.apache.log4j.Logger;

public class Dtm4Connection extends DefaultTableModel implements TableModelListener {
    protected Dtm4Connection() {

        logger = logger.getLogger(Dtm4Connection.class);
        //logger.debug(LOG_TAG + "<<constructor>> Dtm4Connection()");

        // add listener
        addTableModelListener(this);
    }

    //************************************************************************
    //*                 getRowCount
    //************************************************************************
    @Override
    public int getRowCount() {
        //logger.debug(LOG_TAG + ".getRowCount()");

        return listData.size();
    }

    //************************************************************************
    //*                 getColumnCount
    //************************************************************************
    @Override
    public int getColumnCount() {
        //logger.debug(LOG_TAG + ".getColumnCount()");

        return columnName.length;
    }

    //************************************************************************
    //*                 getColumnName
    //************************************************************************
    @Override
    public String getColumnName(final int column) {
        //logger.debug(LOG_TAG + ".getColumnName()");

        return columnName[column];
    }

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

    //************************************************************************
    //*                 getValueAt
    //************************************************************************
    @Override
    public Object getValueAt(final int row, final int column) {
        //logger.debug(LOG_TAG + ".getValueAt()");

        Object object = listData.get(row);

        return ((DataObject4Connection)object).getValue(column);
    }

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

    //************************************************************************
    //*                 tableChanged
    //************************************************************************
    public void tableChanged(TableModelEvent e) {
        //logger.debug(LOG_TAG + ".tableChanged()");
    }

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