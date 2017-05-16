package by.bytechs.util.multiSpanCellTable.interfaces;

import java.awt.*;

/**
 * @author Romanovich Andrei
 */
public interface CellAttribute {
    void addColumn();

    void addRow();

    void insertRow(int row);

    Dimension getSize();

    void setSize(Dimension size);
}

