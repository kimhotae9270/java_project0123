package listCheck;
import java.awt.*;

import UserAction.PopUp;
import UserInfo.NowLoginUser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import UserAction.CheckInfo;
import updateCalendar.UpdateCalendar;

public class WriteCheckList implements PopUp {
	Frame writeCheckList = new Frame("할일 추가하기");
	Panel checklistPanel;
	Button addButton;

	public WriteCheckList(Runnable callback) {
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
					callback.run();
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
		String path = CheckInfo.getFolderPath()+"/"+NowLoginUser.getID()+"/schedule"+"/"+UpdateCalendar.getCurrentYear()+"/"+UpdateCalendar.getCurrentMonth();
		File folder = new File(path);
		if(!folder.exists()){
			folder.mkdirs();
		}

		String schaPath = path+"/"+UpdateCalendar.getCurrentDay()+".txt";
		try(FileWriter fw = new FileWriter(schaPath, true)){
			String[] texts = text.split("\n");
			for(int i = 0;i<texts.length;i++){
				fw.write("0;"+texts[i]);
			}

			herePopUp("추가를 완료 했습니다!");
			writeCheckList.dispose();

		}catch(IOException e){
			System.out.println("업로드 실패 "+e);
		}
		
	    

	}
	public void herePopUp(String here){
		Frame frame = new Frame();
		frame.setSize(200, 100);
		frame.setLayout(new FlowLayout());
		frame.setVisible(true);
		Label l = new Label(here) ;
		frame.add(l);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				frame.dispose();
			}
		});
	}
}
