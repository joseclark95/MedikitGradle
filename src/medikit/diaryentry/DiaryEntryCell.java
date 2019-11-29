package medikit.diaryentry;

import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ListCell;

public class DiaryEntryCell extends ListCell<DiaryEntry>
{
    @Override
    protected void updateItem(DiaryEntry item, boolean empty)
    {
        super.updateItem(item, empty);
        if(!empty)
        {
            this.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            DiaryEntryTile diaryEntryTile = new DiaryEntryTile(item);
            this.setGraphic(diaryEntryTile);
        }
        else
        {
            this.setGraphic(null);
            this.setText("");
        }
    }
}