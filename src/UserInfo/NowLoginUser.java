package UserInfo;

public class NowLoginUser{
	private static String ID;
	private static String PW;
	public static void setID(String ID,String PW) {
		NowLoginUser.ID = ID;
		NowLoginUser.PW = PW;
	}
	public static String getID() {
		return NowLoginUser.ID;
	}
}
