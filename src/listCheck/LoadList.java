package listCheck;

import UserAction.CheckInfo;
import UserInfo.NowLoginUser;
import updateCalendar.RemoveDay;
import updateCalendar.UpdateCalendar;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LoadList {
    Frame checklistFrame = new Frame("ToDo List");
    Panel checklistPanel = new Panel();
    String path;
    Button addButton = new Button("추가");
    Button removeButton = new Button("일정 지우기");

    List<Checkbox> checkboxes = new ArrayList<>();

    public LoadList() {
        checklistPanel.setLayout(new GridLayout(0, 1, 10, 10)); // 세로로 배치, 수직 및 수평 간격 추가
        String path = CheckInfo.getFolderPath() + "/" + NowLoginUser.getID() + "/schedule"; // 사용
        File folder = new File(path);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        String filePath = path + "/" + UpdateCalendar.getCurrentYear() + "/" + UpdateCalendar.getCurrentMonth() + "/" + UpdateCalendar.getCurrentDay() + ".txt";

        loadChecklist(filePath);

        Panel buttonPanel = new Panel();
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);

        checklistFrame.setLayout(new BorderLayout());
        checklistFrame.add(checklistPanel, BorderLayout.CENTER);
        checklistFrame.add(buttonPanel, BorderLayout.SOUTH);
        checklistFrame.setSize(400, 400);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new WriteCheckList(new Runnable() {
                    @Override
                    public void run() {
                        refreshChecklist(filePath);
                    }
                });//새로운 항목 추가 클래스
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                RemoveDay rd = new RemoveDay();
                rd.removeSca();
                refreshChecklist(filePath);
            }
        });

        checklistFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                saveChecklist(filePath); // 프로그램 종료 시 체크리스트 상태 저장
                checklistFrame.dispose();
            }
        });

        checklistFrame.setVisible(true);
    }

    private void loadChecklist(String filePath) {
        checklistPanel.removeAll(); // 기존 체크리스트 항목을 제거

        try (BufferedReader lst = new BufferedReader(new FileReader(filePath))) {
            String line;
            File file = new File(filePath);
            if(file.length() == 0){
                Label l = new Label("할일이 없습니다!");
                checklistPanel.add(l);
            } else {
                while ((line = lst.readLine()) != null) {

                    String[] parts = line.split(";", 2);
                    boolean isChecked = Boolean.parseBoolean(parts[0]);
                    String label = parts[1];
                    Checkbox ch = new Checkbox(label, isChecked);
                    ch.setFont(new Font("Arial", Font.PLAIN, 18)); // 체크박스 텍스트 크기 조정
                    checkboxes.add(ch);
                    checklistPanel.add(ch);
                }
            }
        } catch (IOException e) {
            Label l = new Label("할일이 없습니다!");
            checklistPanel.add(l);
        }

        checklistPanel.revalidate();
        checklistPanel.repaint();
    }

    private void saveChecklist(String filePath) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Checkbox checkbox : checkboxes) {
                writer.write(checkbox.getState() + ";" + checkbox.getLabel());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("할일이 없습니다");
        }
    }

    private void refreshChecklist(String filePath) {
        checkboxes.clear();
        loadChecklist(filePath); // 체크리스트를 다시 로드하여 새로고침
    }
}