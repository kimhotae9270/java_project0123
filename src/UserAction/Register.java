package UserAction;


import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Register implements PopUp{
	private String folderPath = "C:\\schedule_system\\User";
	private String fileIDPath = folderPath + "\\UserID.txt";
	private String filePWPath = folderPath + "\\UserPW.txt";
	private File folder = new File(folderPath);
	CheckInfo ci;
	Register(){}
	Register(String ID,String PassWord){
		ci = new CheckInfo(ID,PassWord);
		if (ci.getIsIDtrue()) {
			herePopUp("이미 존재하는 아이디 입니다.");
		}
		else {
		if (!folder.exists()) {
		    folder.mkdirs(); // 폴더가 존재하지 않으면 생성
		}
		try(FileWriter fw = new FileWriter(fileIDPath, true)) {
			fw.write(ID+"\n");
			try(FileWriter pw = new FileWriter(filePWPath,true)){
				pw.write(PassWord+"\n");
				herePopUp("회원가입을 완료 했습니다!");
				folder = new File(folderPath+"\\"+ID);
				folder.mkdir();
			}catch(IOException e) {
				herePopUp("회원가입에 실패했습니다."+e);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			herePopUp("회원가입에 실패했습니다."+e);
		}
	}}
	
	@Override
	public void herePopUp(String here) {
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
