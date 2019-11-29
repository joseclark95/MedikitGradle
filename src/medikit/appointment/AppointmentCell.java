package medikit.appointment;

import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ListCell;

public class AppointmentCell extends ListCell<Appointment>
{
    @Override
    protected void updateItem(Appointment item, boolean empty)
    {
        super.updateItem(item, empty);
        if(!empty)
        {
            this.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            AppointmentTile appointmentTile = new AppointmentTile(item);
            item.setAppointmentTile(appointmentTile);
            this.setGraphic(appointmentTile);
        }
        else
        {
            this.setGraphic(null);
            this.setText("");
        }
    }
}