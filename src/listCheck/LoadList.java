package listCheck;

import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.*;
import java.awt.Panel;
import java.awt.Checkbox;
import java.awt.BorderLayout;
import java.awt.Button;

public class LoadList {
	Frame checklistFrame = new Frame("ToDo List");
	Panel checklistPanel = new Panel();
    
    Button addButton = new Button("추가");

    public LoadList(int month, int finalDay) {
        checklistPanel.setLayout(new GridLayout(0, 1)); // 체크박스를 세로로 배치하기 위해 GridLayout 사용
        

        Panel buttonPanel = new Panel();
        buttonPanel.add(addButton);

        checklistFrame.setLayout(new BorderLayout());
        checklistFrame.add(checklistPanel, BorderLayout.CENTER);
        checklistFrame.add(buttonPanel, BorderLayout.SOUTH);
        checklistFrame.setSize(400, 400);
        addButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new WriteCheckList();
				
			}
		});
        System.out.println(month + "월" + finalDay + "일 입니다");
        checklistFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                checklistFrame.dispose();
            }
        });

        checklistFrame.setVisible(true);
    }
}
