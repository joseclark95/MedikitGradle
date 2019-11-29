package medikit.medicament;

import javafx.beans.value.ObservableValue;
import medikit.Medikit;
import medikit.ctrl.MedicamentChooser;
import medikit.misc.WindowStyle;

public class InventorySearchTile extends InventorySearchTileBase
{
    private Medicament medicament;

    public InventorySearchTile(Medicament medicament)
    {
        super();
        this.medicament = medicament;
        this.setFields();
        this.onEvent();
        
        if(this.medicament.isSelected())
            selectionBox.setSelected(true);
    }
    
    private void setFields()
    {
        int amount = this.medicament.getAmount();
        this.amount.setText("" + amount);
        this.dosages.setText(this.medicament.getDosages());
        this.brandName.setText(this.medicament.getBrandName());
        this.genericName.setText(this.medicament.getGenericName());
        this.amountField.setText((amount == 0) ? "" : "" + amount);
        this.brandField.setText(medicament.getBrandName());
        this.genericField.setText(medicament.getGenericName());
        this.dosagesField.setText(medicament.getDosages());
        this.formField.setText(medicament.getForm());
        this.presentationField.setText(medicament.getPresentation());
        this.priceField.setText((medicament.getPrice() == 0) ? "" : "" + medicament.getPrice());
        
        if(amount < 5)
            this.amount.setStyle(this.amount.getStyle() + "; -fx-fill: crimson;");
        else
            this.amount.setStyle(this.amount.getStyle() + "; -fx-fill: violet;");
    }
    
    private void onEvent()
    {
        selectionBox.selectedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) ->
        {
            ((MedicamentChooser) Medikit.getController(WindowStyle.MEDICAMENTS_CHOOSER)).select(this.medicament, newValue);
            this.medicament.setSelected(newValue);
        });
    }
    
    public void select(boolean selected)
    {
        this.selectionBox.setSelected(selected);
    }
}