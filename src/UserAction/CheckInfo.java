package UserAction;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class CheckInfo {
	private boolean isIDtrue = false;
	private boolean Allclear = false;
	private static String folderPath = "C:\\schedule_system\\User";
	private static String fileIDPath = folderPath+"\\UserID.txt";
	private String filePWPath = folderPath+"\\UserPW.txt";
	private File folder = new File(folderPath);
	CheckInfo(){}
	CheckInfo(String ID,String PassWord){
		
		if(!folder.exists()) {
			folder.mkdirs();
		}else {
        // 파일 읽기
        try (BufferedReader id = new BufferedReader(new FileReader(fileIDPath))) {
            String line;
            while ((line = id.readLine()) != null) {
                if(ID.equals(line)) {
                	isIDtrue = true;
                }
            }
            if(isIDtrue) {
            	BufferedReader pw = new BufferedReader(new FileReader(filePWPath));
            	
            	while((line = pw.readLine()) != null) {
            		if(PassWord.equals(line)) {
            			Allclear = true;
            		}
            	}
            }
        } catch (IOException e) {
            System.out.println("회원가입 먼저 진행해 주세요!");
        }}
	}
	public boolean getAllclear() {
		return Allclear;
	}
	public boolean getIsIDtrue() {
		return isIDtrue;
	}
	public static String getFolderPath() {
		return folderPath;
	}
}
