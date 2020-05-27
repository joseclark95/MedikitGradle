package medikit.medicament;

import java.awt.Toolkit;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.util.Duration;
import medikit.Medikit;
import medikit.ctrl.InventoryPane;
import medikit.ctrl.Main;
import medikit.ctrl.Message2;
import medikit.misc.WindowStyle;

public class InventoryTile extends InventoryTileBase
{
    private Medicament medicament;

    public InventoryTile(Medicament medicament)
    {
        super();
        this.medicament = medicament;
        this.setFields();
        this.onEvent();
        this.onFieldsModified();

        if(this.medicament.getId() == -1)
        {
            ObservableList<String> styleClass = this.savedIcon.getStyleClass();
            
            styleClass.clear();
            styleClass.add("not-saved-icon");
            this.saveButton.setDisable(false);
            this.setExpanded(true);
        }
        
        if(this.medicament.isSelected())
            selectionBox.setSelected(true);
    }
    
    private void setFields()
    {
        int amount = this.medicament.getAmount();
        this.amount.setText("" + amount);
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
        this.saveButton.addEventHandler(ActionEvent.ACTION, (ActionEvent event) ->
        {
            String brandName = brandField.getText(), genericName = genericField.getText(), amount = amountField.getText(), 
                    dosages = dosagesField.getText(), form = formField.getText(), presentation = presentationField.getText(), 
                    price = priceField.getText();
            
            if(!brandName.isEmpty() && !genericName.isEmpty() && !amount.isEmpty() && !dosages.isEmpty() && !form.isEmpty() && !presentation.isEmpty()
                    && !price.isEmpty())
            {
                if(this.medicament.getId() == -1)
                {
                    this.medicament.setId(Medicament.nextId());
                    this.medicament.setBrandName(brandName);
                    this.medicament.setGenericName(genericName);
                    this.medicament.setAmount(Integer.parseInt(amount));
                    this.medicament.setDosages(dosages);
                    this.medicament.setForm(form);
                    this.medicament.setPresentation(presentation);
                    this.medicament.setPrice(Double.parseDouble(price));
                    
                    Medicament.getLoadedMedicaments().put(medicament.getId(), medicament);
                    
                    ObservableList<String> styleClass = this.savedIcon.getStyleClass();
                    styleClass.clear();
                    styleClass.add("saved-icon");
                    this.saveButton.setDisable(true);
                }
                else
                {
                    Medicament newMedicament = new Medicament(this.medicament.getId(), brandName, genericName, Integer.parseInt(amount), 
                            form, dosages, presentation, Double.parseDouble(price));
                    Medicament.update(medicament, newMedicament);
                }
                
                int quantity = this.medicament.getAmount();
                this.amount.setText("" + quantity);
                this.brandName.setText(brandName);
                this.genericName.setText(genericName);
                if(quantity < 5)
                    this.amount.setStyle(this.amount.getStyle() + "; -fx-fill: crimson;");
                else
                    this.amount.setStyle(this.amount.getStyle() + "; -fx-fill: violet;");
                this.medicament.setModifed(false);
                this.setExpanded(false);
            }
            else
            {
                ((Message2) Medikit.getController(WindowStyle.MESSAGE2)).initController("warning-icon", "Informacion incompleta", 
                        "Todos los campos no opcionales deben contener informacion", "Aceptar");
                Toolkit.getDefaultToolkit().beep();
                ((Main) Medikit.getController(WindowStyle.MAIN)).showPopup(Medikit.getWindow(WindowStyle.MESSAGE2));
            }
        });
        
        selectionBox.selectedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) ->
        {
            ((InventoryPane) Medikit.getController(WindowStyle.INVENTORY)).select(this.medicament, newValue);
            this.medicament.setSelected(newValue);
        });
        
        this.expandedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) ->
        {
            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100), (ActionEvent event) -> 
                this.amountField.requestFocus()));
            timeline.play();
        });
    }
    
    private void onFieldsModified()
    {
        this.medicament.modifedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) ->
        {
            ObservableList<String> styleClass = this.savedIcon.getStyleClass();
            
            styleClass.clear();
            styleClass.add((newValue) ? "not-saved-icon" : "saved-icon");
            this.saveButton.setDisable(!newValue);
        });
        
        this.priceField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) ->
        {
            if(!newValue.matches("[0-9]*[.]?[0-9]*"))
                priceField.setText(oldValue);
            this.medicament.setModifed(true);
        });
        
        this.amountField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) ->
        {
            if(!newValue.matches("[0-9]*"))
                amountField.setText(oldValue);
            this.medicament.setModifed(true);
        });
        
        this.brandField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) ->
        {
            this.medicament.setModifed(true);
        });
        
        this.genericField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) ->
        {
            this.medicament.setModifed(true);
        });
        
        this.dosagesField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) ->
        {
            this.medicament.setModifed(true);
        });
        
        this.formField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) ->
        {
            this.medicament.setModifed(true);
        });
        
        this.presentationField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) ->
        {
            this.medicament.setModifed(true);
        });
    }
    
    public void select(boolean selected)
    {
        this.selectionBox.setSelected(selected);
    }
}