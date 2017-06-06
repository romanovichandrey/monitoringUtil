package by.bytechs.desktopUI;

import org.jdesktop.swingx.JXBusyLabel;

import java.awt.*;

/**
 * @author Romanovich Andrei
 */
public class WaitPanel extends JXBusyLabel {

    public WaitPanel() {
        setSize(new Dimension(100, 100));
        getBusyPainter().setHighlightColor(new Color(44, 61, 146).darker());
        getBusyPainter().setBaseColor(new Color(168, 204, 241).brighter());
        setAlignmentX(CENTER_ALIGNMENT);
        setAlignmentY(CENTER_ALIGNMENT);
        setText("Загрузка данных...");
    }
}