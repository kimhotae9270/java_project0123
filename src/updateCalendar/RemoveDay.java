package updateCalendar;

public class RemoveDay extends RemoveSca{
    @Override
    public void removeSca() {
        file.delete();
    }
}
