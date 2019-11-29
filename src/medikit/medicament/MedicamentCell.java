package medikit.medicament;

import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ListCell;

public class MedicamentCell extends ListCell<Medicament>
{
    @Override
    protected void updateItem(Medicament item, boolean empty)
    {
        super.updateItem(item, empty);
        if(!empty)
        {
            this.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            InventoryTile inventoryTile = new InventoryTile(item);
            item.setInventoryTile(inventoryTile);
            this.setGraphic(inventoryTile);
        }
        else
        {
            this.setGraphic(null);
            this.setText("");
        }
    }
}