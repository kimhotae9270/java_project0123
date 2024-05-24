package updateCalendar;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import listCheck.LoadList;

public class UpdateCalendar extends Frame {
    private static int currentYear;
    private static int currentMonth;
    private static int currentDay;
    private Label monthLabel;
    private Panel calendarPanel;

    public UpdateCalendar() {
        currentYear = LocalDate.now().getYear();
        currentMonth = LocalDate.now().getMonthValue();

        setTitle(currentYear+"년 "+currentMonth+"월");
        setSize(300, 300);
        setLayout(new BorderLayout());

        monthLabel = new Label();
        updateMonthLabel();
        add(monthLabel, BorderLayout.NORTH);

        calendarPanel = new Panel(new GridLayout(0, 7));
        updateCalendarPanel();
        add(calendarPanel, BorderLayout.CENTER);

        Panel buttonPanel = new Panel(new BorderLayout());

        Button prevButton = new Button("이전 달");
        prevButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (currentMonth == 1) {
                    currentMonth = 12;
                    currentYear--;
                } else {
                    currentMonth--;
                }
                setTitle(currentYear+"년 "+currentMonth+"월");
                updateMonthLabel();
                updateCalendarPanel();
            }
        });
        buttonPanel.add(prevButton, BorderLayout.WEST);

        Button nextButton = new Button("다음 달");
        nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (currentMonth == 12) {
                    currentMonth = 1;
                    currentYear++;
                } else {
                    currentMonth++;
                }
                setTitle(currentYear+"년 "+currentMonth+"월");
                updateMonthLabel();
                updateCalendarPanel();
            }
        });
        buttonPanel.add(nextButton, BorderLayout.EAST);

        add(buttonPanel, BorderLayout.SOUTH);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }

    private void updateMonthLabel() {
        monthLabel.setText(currentYear + "년 " + currentMonth + "월");
    }

    private void updateCalendarPanel() {
        calendarPanel.removeAll();
        String[] daysOfWeek = {"일", "월", "화", "수", "목", "금", "토"};
        for (String day : daysOfWeek) {
            Label label = new Label(day, Label.CENTER);
            calendarPanel.add(label);
        }
        
        LocalDate firstDayOfMonth = LocalDate.of(currentYear, currentMonth, 1);
        int startDayOfWeek = firstDayOfMonth.getDayOfWeek().getValue();

        for (int i = 1; i < startDayOfWeek; i++) {
            Label label = new Label("", Label.CENTER);
            calendarPanel.add(label);
        }

        int daysInMonth = firstDayOfMonth.lengthOfMonth();
        for (int day = 1; day <= daysInMonth; day++) {
            final int finalday = day;
            Button button = new Button(String.valueOf(finalday));
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    currentDay = finalday;
                    // 버튼 클릭 시 다음 화면으로 이동하는 동작 추가
                    new LoadList();
                }
            });
            calendarPanel.add(button);
        }

        revalidate();
    
        
        repaint();
        setVisible(true);
    }
    


    public static int getCurrentYear() {
        return currentYear;
    }
    public static int getCurrentMonth(){
        return currentMonth;
    }
    public static int getCurrentDay(){
        return currentDay;
    }
}
