package by.bytechs.util.multiSpanCellTable.interfaces;

import java.awt.*;

/**
 * @author Romanovich Andrei
 */
public interface CellFont {
    Font getFont(int row, int column);
    void setFont(Font font, int row, int column);
    void setFont(Font font, int[] rows, int[] columns);

}

