package medikit.medicament;

import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ListCell;

public class InventorySearchCell extends ListCell<Medicament>
{
    @Override
    protected void updateItem(Medicament item, boolean empty)
    {
        super.updateItem(item, empty);
        if(!empty)
        {
            this.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            InventorySearchTile inventoryTile = new InventorySearchTile(item);
            item.setInventorySearchTile(inventoryTile);
            this.setGraphic(inventoryTile);
        }
        else
        {
            this.setGraphic(null);
            this.setText("");
        }
    }
}