package updateCalendar;

import UserAction.CheckInfo;
import UserInfo.NowLoginUser;
import org.w3c.dom.ls.LSOutput;

import java.io.File;

public abstract class RemoveSca {
    String path = CheckInfo.getFolderPath() + "/" + NowLoginUser.getID() + "/schedule/" + UpdateCalendar.getCurrentYear() + "/" + UpdateCalendar.getCurrentMonth() + "/" + UpdateCalendar.getCurrentDay() + ".txt";;
   File file;
    public RemoveSca(){
        file = new File(path);
    }
    abstract void removeSca();
    
}
