package medikit.diaryentry;

import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ListCell;
import medikit.medicament.Medicament;

public class MedicamentSearchCell extends ListCell<Medicament>
{
    @Override
    protected void updateItem(Medicament item, boolean empty)
    {
        super.updateItem(item, empty);
        if(!empty)
        {
            this.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            MedicamentSearchTile medicamentSearchTile = new MedicamentSearchTile(item);
            this.setGraphic(medicamentSearchTile);
        }
        else
        {
            this.setGraphic(null);
            this.setText("");
        }
    }
}