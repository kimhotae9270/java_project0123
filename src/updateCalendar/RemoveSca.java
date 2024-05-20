package updateCalendar;

import UserAction.CheckInfo;
import UserInfo.NowLoginUser;

import java.io.File;

public abstract class RemoveSca {
    String path = CheckInfo.getFolderPath() + "/" + NowLoginUser.getID() + "/schedule/" + UpdateCalendar.getCurrentYear() + "_" + UpdateCalendar.getCurrentMonth() + "_" + UpdateCalendar.getCurrentDay() + ".txt";;
   File file;
    public RemoveSca(){
        file = new File(path);
    }
    abstract void removeSca();
}
