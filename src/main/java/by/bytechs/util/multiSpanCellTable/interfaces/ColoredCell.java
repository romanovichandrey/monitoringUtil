package by.bytechs.util.multiSpanCellTable.interfaces;

import java.awt.*;

/**
 * @author Romanovich Andrei
 */
public interface ColoredCell {
    Color getForeground(int row, int column);
    void setForeground(Color color, int row, int column);
    void setForeground(Color color, int[] rows, int[] columns);

    Color getBackground(int row, int column);
    void setBackground(Color color, int row, int column);
    void setBackground(Color color, int[] rows, int[] columns);

}

