package medikit.patient;

import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ListCell;

public class PatientCell extends ListCell<Patient>
{
    @Override
    protected void updateItem(Patient item, boolean empty)
    {
        super.updateItem(item, empty);
        if(!empty)
        {
            this.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            PatientTile patientTile = new PatientTile(item);
            item.setPatientTile(patientTile);
            this.setGraphic(patientTile);
        }
        else
        {
            this.setGraphic(null);
            this.setText("");
        }
    }
}