package medikit.notification;

public class Notification extends NotificationBase
{
    public Notification(int amount)
    {
        super();
        this.amount.setText("" + amount);
    }
}