package medikit.treatment;

import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ListCell;

public class TreatmentCell extends ListCell<Treatment>
{
    @Override
    protected void updateItem(Treatment item, boolean empty)
    {
        super.updateItem(item, empty);
        if(!empty)
        {
            this.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            TreatmentTile treatmentTile = new TreatmentTile(item);
            item.setTreatmentTile(treatmentTile);
            this.setGraphic(treatmentTile);
        }
        else
        {
            this.setGraphic(null);
            this.setText("");
        }
    }
}