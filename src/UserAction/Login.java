package UserAction;
import UserInfo.NowLoginUser;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import updateCalendar.UpdateCalendar;

public class Login implements PopUp{
	Frame frame = new Frame();
    public Login() {
        // Frame 설정
        frame.setTitle("로그인 폼");
        frame.setSize(300, 120);
        frame.setLayout(new GridLayout(2, 2));
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
            	frame.dispose();
            }
        });

        Panel inputPanel = new Panel(new GridLayout(2, 2));
        Label idLabel = new Label("아이디:");
        TextField idField = new TextField();
        Label pwLabel = new Label("비밀번호:");
        TextField pwField = new TextField();
        pwField.setEchoChar('*'); // 비밀번호 입력 시 숨기기

        inputPanel.add(idLabel);
        inputPanel.add(idField);
        inputPanel.add(pwLabel);
        inputPanel.add(pwField);

        frame.add(inputPanel, BorderLayout.CENTER);

     // 버튼 패널 생성
     // 버튼 패널 생성
        Panel buttonPanel = new Panel(new GridLayout(1, 2));

        // 로그인 버튼 패널 생성 및 추가
        Panel loginPanel = new Panel(new FlowLayout(FlowLayout.LEFT));
        Button loginButton = new Button("로그인");
        loginPanel.add(loginButton);
        buttonPanel.add(loginPanel, BorderLayout.WEST);

        // 회원가입 버튼 패널 생성 및 추가
        Panel registerPanel = new Panel(new FlowLayout(FlowLayout.RIGHT));
        Button registerButton = new Button("회원가입");
        registerPanel.add(registerButton);
        buttonPanel.add(registerPanel, BorderLayout.EAST);


        frame.add(buttonPanel, BorderLayout.SOUTH);
        
        loginButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				CheckInfo ci = new CheckInfo(idField.getText(),pwField.getText());
				if(ci.getAllclear()) {
					NowLoginUser.setID(idField.getText(),pwField.getText());
					new UpdateCalendar();
					frame.dispose();
				}
				else {
					herePopUp("아이디 혹은 패스워드가 틀렸습니다");
				}
				
			}

			
		});
        registerButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new Register(idField.getText(),pwField.getText());
				
			}
		});
        // 화면에 프레임 표시
       frame.setVisible(true);
    }
   public void herePopUp(String here){
    	Frame frame = new Frame();
		 frame.setSize(400, 100);
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
