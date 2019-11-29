package medikit.diaryentry;

import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ListCell;
import medikit.patient.Patient;

public class PatientSearchCell extends ListCell<Patient>
{
    @Override
    protected void updateItem(Patient item, boolean empty)
    {
        super.updateItem(item, empty);
        if(!empty)
        {
            this.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            PatientSearchTile patientSearchTile = new PatientSearchTile(item);
            this.setGraphic(patientSearchTile);
        }
        else
        {
            this.setGraphic(null);
            this.setText("");
        }
    }
}