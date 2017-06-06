package by.bytechs.desktopUI;

import by.bytechs.services.interfaces.CashUnitInfoService;
import org.jdesktop.swingx.*;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Romanovich Andrei
 */
@Component
public class MainWindows extends JXFrame implements WindowListener {
    private ConfigurableApplicationContext context;
    private JXDatePicker startDatePicker;
    private JXDatePicker endDatePicker;
    private JFormattedTextField startFormatField;
    private JFormattedTextField endFormatField;
    private final WaitPanel waitPanel = new WaitPanel();

    public MainWindows(ConfigurableApplicationContext context) {
        this.context = context;
        setIconImage(new ImageIcon(ClassLoader.getSystemResource("images/mainWindowsIcon.png")).getImage());
        setTitle("Вспомогательная утилита SMARTiS");
        setBounds(100, 100, 600, 300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        init();
    }

    private void init() {
        JXPanel mainPanel = new JXPanel();
        mainPanel.setBackground(new Color(108, 160, 240).brighter());
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(Box.createVerticalStrut(5));
        getContentPane().add(mainPanel);

        JXLabel overallLabel = new JXLabel("Выберите необходимое действие:");
        overallLabel.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        overallLabel.setBackground(mainPanel.getBackground());
        mainPanel.add(overallLabel);

        JXPanel oneOperationPanel = new JXPanel();
        oneOperationPanel.setBackground(mainPanel.getBackground());
        oneOperationPanel.setLayout(new BoxLayout(oneOperationPanel, BoxLayout.X_AXIS));
        mainPanel.add(oneOperationPanel);

        JXLabel oneOperationLabel = new JXLabel("Поиск xml и сохранение в базу статусов кассет");
        oneOperationLabel.setBackground(oneOperationPanel.getBackground());
        oneOperationPanel.add(oneOperationLabel);
        JXButton oneOperationButton = new JXButton("Начать выполнение");
        oneOperationButton.setIcon(new ImageIcon(ClassLoader.getSystemResource("images/operator.png")));
        oneOperationButton.setBackground(new Color(168, 204, 241));
        oneOperationPanel.add(Box.createHorizontalStrut(5));
        oneOperationPanel.add(oneOperationButton);
        oneOperationButton.addActionListener(e -> oneOperationAction());

        JXPanel twoOperationPanel = new JXPanel();
        twoOperationPanel.setBackground(mainPanel.getBackground());
        twoOperationPanel.setLayout(new BoxLayout(twoOperationPanel, BoxLayout.X_AXIS));
        mainPanel.add(twoOperationPanel);

        JXLabel twoOperationLabel = new JXLabel("Поиск данных в CSV файле и создание xml о задержаных картах");
        twoOperationLabel.setBackground(twoOperationPanel.getBackground());
        twoOperationPanel.add(twoOperationLabel);
        JXButton twoOperationButton = new JXButton("Начать выполнение");
        twoOperationButton.setIcon(new ImageIcon(ClassLoader.getSystemResource("images/operator.png")));
        twoOperationButton.setBackground(new Color(168, 204, 241));
        twoOperationPanel.add(Box.createHorizontalStrut(5));
        twoOperationPanel.add(twoOperationButton);
        twoOperationButton.addActionListener(e -> twoOperationAction());

    }

    private void oneOperationAction() {
        JDialog dialog = new JDialog();
        dialog.setTitle("Поиск xml и сохранение в базу статусов кассет");
        dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setBounds(100, 100, 400, 200);

        JXPanel panel = new JXPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        dialog.add(panel);

        JPanel datePanel = new JPanel();
        datePanel.setLayout(new BorderLayout());
        datePanel.setPreferredSize(new Dimension(350, 55));
        datePanel.setMaximumSize(new Dimension(350, 55));
        panel.add(datePanel);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm:ss");

        {
            JPanel startDatePanel = new JPanel();
            startDatePanel.setPreferredSize(new Dimension(350, 25));
            startDatePanel.setMaximumSize(new Dimension(350, 25));
            startDatePanel.setLayout(new BoxLayout(startDatePanel, BoxLayout.X_AXIS));
            datePanel.add(startDatePanel, BorderLayout.PAGE_START);

            JLabel startDateLabel = new JLabel("Дата начала периода:");
            startDatePanel.add(startDateLabel);
            startDatePanel.add(Box.createHorizontalStrut(28));

            startDatePicker = new JXDatePicker();
            startDatePicker.setFormats(new SimpleDateFormat("dd.MM.yyyy"));
            startDatePicker.setDate(calendar.getTime());
            startDatePicker.setEnabled(true);
            startDatePanel.add(startDatePicker);
            startDatePanel.add(Box.createHorizontalStrut(5));

            try {
                startFormatField = new JFormattedTextField(new MaskFormatter("##:##:##"));
                startFormatField.setValue("00:00:00");
                startDatePanel.add(startFormatField);
            } catch (ParseException e1) {
                JOptionPane.showMessageDialog(null,
                        "Неверный формат время начала периода! Введите время в формате ЧЧ:мм:сс!");
            }
        }

        {
            JPanel endDatePanel = new JPanel();
            endDatePanel.setPreferredSize(new Dimension(350, 25));
            endDatePanel.setMaximumSize(new Dimension(350, 25));
            endDatePanel.setLayout(new BoxLayout(endDatePanel, BoxLayout.X_AXIS));
            datePanel.add(endDatePanel, BorderLayout.PAGE_END);

            JLabel endDateLabel = new JLabel("Дата окончания периода:");
            endDatePanel.add(endDateLabel);
            endDatePanel.add(Box.createHorizontalStrut(10));

            endDatePicker = new JXDatePicker();
            endDatePicker.setFormats(new SimpleDateFormat("dd.MM.yyyy"));
            endDatePicker.setDate(calendar.getTime());
            endDatePicker.setEnabled(true);
            endDatePanel.add(endDatePicker);
            endDatePanel.add(Box.createHorizontalStrut(5));

            try {
                endFormatField = new JFormattedTextField(new MaskFormatter("##:##:##"));
                endFormatField.setValue(formatTime.format(calendar.getTime()));
                endDatePanel.add(endFormatField);
            } catch (ParseException e1) {
                JOptionPane.showMessageDialog(null,
                        "Неверный формат время окончания периода! Введите время в формате ЧЧ:мм:сс!");
            }
        }

        panel.add(waitPanel);
        waitPanel.setVisible(false);
        panel.add(Box.createVerticalStrut(10));
        JXPanel buttonPanel = new JXPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        panel.add(buttonPanel);
        JXButton applyButton = new JXButton("Выполнить");
        buttonPanel.add(applyButton);
        buttonPanel.add(Box.createHorizontalStrut(10));
        JXButton cancelButton = new JXButton("Отмена");
        cancelButton.addActionListener(e1 -> dialog.setVisible(false));
        buttonPanel.add(cancelButton);

        applyButton.addActionListener(e1 -> {
            Calendar calendar1 = Calendar.getInstance();
            SimpleDateFormat formatTime1 = new SimpleDateFormat("HH:mm:ss");
            Calendar tempCalendar = Calendar.getInstance();

            Date startDate = startDatePicker.getDate();
            Date endDate = endDatePicker.getDate();
            Date tempStart = null;
            Date tempEnd = null;

            try {
                tempStart = formatTime1.parse(startFormatField.getText());
                tempEnd = formatTime1.parse(endFormatField.getText());
            } catch (ParseException e2) {
                JOptionPane.showMessageDialog(null,
                        "Неверный формат времини! Введите время в формате ЧЧ:мм:сс!");
            }

            {
                tempCalendar.setTime(tempStart);
                calendar1.setTime(startDate);
                calendar1.set(Calendar.HOUR_OF_DAY, tempCalendar.get(Calendar.HOUR_OF_DAY));
                calendar1.set(Calendar.MINUTE, tempCalendar.get(Calendar.MINUTE));
                calendar1.set(Calendar.SECOND, tempCalendar.get(Calendar.SECOND));
                startDate = calendar1.getTime();
            }

            {
                tempCalendar.setTime(tempEnd);
                calendar1.setTime(endDate);
                calendar1.set(Calendar.HOUR_OF_DAY, tempCalendar.get(Calendar.HOUR_OF_DAY));
                calendar1.set(Calendar.MINUTE, tempCalendar.get(Calendar.MINUTE));
                calendar1.set(Calendar.SECOND, tempCalendar.get(Calendar.SECOND));
                endDate = calendar1.getTime();
            }
            Date date1 = startDate;
            Date date2 = endDate;
            CashUnitInfoService cashUnitInfoService = context.getBean(CashUnitInfoService.class);
            SwingWorker<Boolean, Void> swingWorker = new SwingWorker<Boolean, Void>() {
                @Override
                protected Boolean doInBackground() throws Exception {
                    waitPanel.setVisible(true);
                    waitPanel.setBusy(true);
                    return cashUnitInfoService.saveCashUnitInfo(date1, date2);
                }

                @Override
                protected void done() {
                    try {
                        boolean status = get();
                        JOptionPane.showMessageDialog(applyButton, status ? "Операция выполнена успешно!" : "Произошла ошибка");
                        if (status) {
                            dialog.setVisible(false);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                        waitPanel.setBusy(false);
                        waitPanel.setVisible(false);
                    }
                }
            };
            swingWorker.execute();

        });
        dialog.setVisible(true);
    }

    private void twoOperationAction() {
        JDialog dialog = new JDialog();
        dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setTitle("Поиск данных в CSV файле и создание xml о задержаных картах");
        dialog.setBounds(100, 100, 500, 300);

        JXPanel panel = new JXPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        dialog.add(panel);

        JXPanel selectFilePanel = new JXPanel();
        selectFilePanel.setLayout(new BoxLayout(selectFilePanel, BoxLayout.X_AXIS));
        panel.add(selectFilePanel);
        JXLabel selectFileName = new JXLabel();
        selectFilePanel.add(selectFileName);
        selectFilePanel.add(Box.createHorizontalStrut(5));
        JXButton selectFileButton = new JXButton("Выберите файл");
        selectFilePanel.add(selectFileButton);
        selectFileButton.addActionListener(e -> {
            JFileChooser selectFileChooser = new JFileChooser();
            selectFileChooser.setFileFilter(new FileNameExtensionFilter("CSV", "csv"));
            int ret = selectFileChooser.showDialog(null, "Выбрать файл");
            if (ret == JFileChooser.APPROVE_OPTION) {
                File file = selectFileChooser.getSelectedFile();
                selectFileName.setText(file.getAbsolutePath());
            }
        });
        dialog.setVisible(true);

    }


    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        if (isVisible()) {
            setVisible(false);
        }
        dispose();
        context.stop();
        System.exit(1);
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
