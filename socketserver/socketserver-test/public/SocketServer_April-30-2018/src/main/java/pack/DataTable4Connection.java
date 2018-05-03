package pack;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import org.apache.log4j.Logger;

/**
 * Table for connection data, shown in {@link Panel4Connection}.
 */
public class DataTable4Connection extends JTable {

    /**
     * Constructor.
     * @param dtm4Connection data table model for this table
     */
    protected DataTable4Connection(final Dtm4Connection dtm4Connection) {
        super(dtm4Connection);

        logger = logger.getLogger(DataTable4Connection.class);
        logger.debug(LOG_TAG + "<<constructor>> DataTable4Connection()");
    }

    /**
     * Defines how the table elements are to be displayed.
     * @param row current row to be displayed
     * @param column current column to be displayed
     * @return tcr table cell renderer containing the display characteristics
     */
    //************************************************************************
    //*                 getCellRenderer
    //************************************************************************
    @Override
    public TableCellRenderer getCellRenderer(int row, int column) {
        //logger.debug(LOG_TAG + ".getCellRenderer()");

        TableCellRenderer tcr = getDefaultRenderer(String.class);
        Component component = tcr.getTableCellRendererComponent(
            this, getValueAt(row, column), false, false, row, column);
        if (row % 2 == 1) {
            // ((JLabel)component).setOpaque(false);
            ((JLabel)component).setBackground(new Color(0xF8, 0xF8, 0xF8));
        } else {
            // ((JLabel)component).setOpaque(true);
            ((JLabel)component).setBackground(new Color(0xFF, 0xFF, 0xFF));
        }
        return tcr;

    }

    //************************************************************************
    //*                 declare
    //************************************************************************
    private static final String LOG_TAG = "*** " + DataTable4Connection.class.getSimpleName();

    private static Logger logger = null;

}