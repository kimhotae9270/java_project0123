package listCheck;

import UserAction.CheckInfo;
import UserInfo.NowLoginUser;
import updateCalendar.RemoveDay;
import updateCalendar.UpdateCalendar;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class LoadList {
    Frame checklistFrame = new Frame("ToDo List");
    Panel checklistPanel = new Panel();
    String path;
    Button addButton = new Button("추가");
    Button removeButton = new Button("일정 지우기");
    public LoadList() {
        checklistPanel.setLayout(new GridLayout(0, 1, 10, 10)); // 세로로 배치, 수직 및 수평 간격 추가
        String path = CheckInfo.getFolderPath() + "/" + NowLoginUser.getID() + "/schedule"; // 사용
        File folder = new File(path);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        String filePath = path + "/" + UpdateCalendar.getCurrentYear() + "_" + UpdateCalendar.getCurrentMonth() + "_" + UpdateCalendar.getCurrentDay() + ".txt";

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
            }
        });

        checklistFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
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
                    Label ch = new Label(line);
                    ch.setFont(new Font("Arial", Font.PLAIN, 18)); // 체크박스 텍스트 크기 조정
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

    private void refreshChecklist(String filePath) {
        loadChecklist(filePath); // 체크리스트를 다시 로드하여 새로고침
    }
}
