package updateCalendar;

public class RemoveDay extends RemoveSca{


    @Override
    public void removeSca() {
        System.out.println(path);
        file.delete();
    }
}
