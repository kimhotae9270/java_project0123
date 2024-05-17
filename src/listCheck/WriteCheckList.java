package listCheck;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
public class WriteCheckList {
	Frame writeCheckList = new Frame("할일 추가하기");
	Panel checklistPanel;
	Button addButton;
	public WriteCheckList() {
		writeCheckList.setSize(500,150);
		writeCheckList.setLayout(new BorderLayout());
		TextArea textArea = new TextArea("", 5, 20, TextArea.SCROLLBARS_VERTICAL_ONLY);
		writeCheckList.add(textArea,BorderLayout.NORTH);
		
		addButton = new Button("추가");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String newItem = textArea.getText().trim();
                if (!newItem.isEmpty()) {
                    checklistPanel.add(new Checkbox(newItem));
                    
                    writeFile(textArea.getText());
                }
            }
        });
        writeCheckList.add(addButton, BorderLayout.SOUTH);

        // 체크리스트를 담을 패널 생성
        checklistPanel = new Panel();
        checklistPanel.setLayout(new GridLayout(0, 1)); // 세로로 배치
        writeCheckList.add(checklistPanel, BorderLayout.CENTER);

        // 창 닫기 이벤트 처리
        writeCheckList.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
            	writeCheckList.dispose();
            }
        });

        writeCheckList.setVisible(true);
	}
	public void writeFile(String text) {
		 String[] texts = text.replace("\\n", "").split(" ");
	        for (int i = 0; i < texts.length; i++) {
	            System.out.println(texts[i]);
	        }

	}
}
