package medikit.medicament;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import medikit.Medikit;

public class Medicament
{
    private IntegerProperty id = new SimpleIntegerProperty(), amount = new SimpleIntegerProperty(0);
    private StringProperty brandName = new SimpleStringProperty(""), genericName = new SimpleStringProperty(""),
            form = new SimpleStringProperty(""), presentation = new SimpleStringProperty(""), dosages = new SimpleStringProperty("");
    private DoubleProperty price = new SimpleDoubleProperty(0);
    private BooleanProperty modifed = new SimpleBooleanProperty(false), selected = new SimpleBooleanProperty(false);
    private InventoryTile inventoryTile;
    private InventorySearchTile inventorySearchTile;

    private static ObservableMap<Integer, Medicament> loadedMedicaments = FXCollections.observableHashMap();
    private static int globalId;

    public Medicament(int id, String brandName, String genericName, int amount, String form, String dosages, String presentation, double price)
    {
        this.id.set(id);
        this.brandName.set(brandName);
        this.genericName.set(genericName);
        this.amount.set(amount);
        this.form.set(form);
        this.dosages.set(dosages);
        this.presentation.set(presentation);
        this.price.set(price);
    }
    
    public Medicament()
    {
        this.id.set(-1);
    }
    
    public static void updateAmount(Medicament medicament, int amount)
    {
        String query = String.format("UPDATE Medicament "
                + "SET amount = %d "
                + "WHERE id = %d;", amount, medicament.getId());
        Medikit.execute(query);
        
        medicament.setAmount(amount);
    }
    
    public static void update(Medicament oldMedicament, Medicament newMedicament)
    {
        String query = String.format("UPDATE Medicament "
                + "SET brandName = '%s', genericName = '%s', amount = %d, form = '%s', dosages = '%s', presentation = '%s', price = %f "
                + "WHERE id = %d;", newMedicament.getBrandName(), newMedicament.getGenericName(), newMedicament.getAmount(), newMedicament.getForm(), 
                newMedicament.getDosages(), newMedicament.getPresentation(), newMedicament.getPrice(), oldMedicament.getId());
        Medikit.execute(query);
        
        oldMedicament.brandName.set(newMedicament.getBrandName());
        oldMedicament.genericName.set(newMedicament.getGenericName());
        oldMedicament.amount.set(newMedicament.getAmount());
        oldMedicament.form.set(newMedicament.getForm());
        oldMedicament.dosages.set(newMedicament.getDosages());
        oldMedicament.presentation.set(newMedicament.getPresentation());
        oldMedicament.price.set(newMedicament.getPrice());
    }

    public final boolean isModifed()
    {
        return modifed.get();
    }

    public final void setModifed(boolean value)
    {
        modifed.set(value);
    }

    public BooleanProperty modifedProperty()
    {
        return modifed;
    }

    public final boolean isSelected()
    {
        return selected.get();
    }

    public final void setSelected(boolean value)
    {
        if(inventoryTile != null)
            inventoryTile.select(value);
        else if(inventorySearchTile != null)
            inventorySearchTile.select(value);
        selected.set(value);
    }

    public InventorySearchTile getInventorySearchTile()
    {
        return inventorySearchTile;
    }

    public void setInventorySearchTile(InventorySearchTile inventorySearchTile)
    {
        this.inventorySearchTile = inventorySearchTile;
    }

    public BooleanProperty selectedProperty()
    {
        return selected;
    }

    public InventoryTile getInventoryTile()
    {
        return inventoryTile;
    }

    public void setInventoryTile(InventoryTile inventoryTile)
    {
        this.inventoryTile = inventoryTile;
    }
    
    public static ObservableMap<Integer, Medicament> getLoadedMedicaments()
    {
        return loadedMedicaments;
    }
    
    public static int nextId()
    {
        return ++globalId;
    }

    public static int getGlobalId()
    {
        return globalId;
    }
    
    public static void setGlobalId(int globalId)
    {
        Medicament.globalId = globalId;
    }
    
    public final int getId()
    {
        return id.get();
    }

    public final void setId(int value)
    {
        id.set(value);
    }

    public IntegerProperty idProperty()
    {
        return id;
    }

    public final int getAmount()
    {
        return amount.get();
    }

    public final void setAmount(int value)
    {
        amount.set(value);
    }

    public IntegerProperty amountProperty()
    {
        return amount;
    }

    public final String getBrandName()
    {
        return brandName.get();
    }

    public final void setBrandName(String value)
    {
        brandName.set(value);
    }

    public StringProperty brandNameProperty()
    {
        return brandName;
    }

    public final String getGenericName()
    {
        return genericName.get();
    }

    public final void setGenericName(String value)
    {
        genericName.set(value);
    }

    public StringProperty genericNameProperty()
    {
        return genericName;
    }

    public final String getForm()
    {
        return form.get();
    }

    public final void setForm(String value)
    {
        form.set(value);
    }

    public StringProperty formProperty()
    {
        return form;
    }

    public final String getPresentation()
    {
        return presentation.get();
    }

    public final void setPresentation(String value)
    {
        presentation.set(value);
    }

    public StringProperty presentationProperty()
    {
        return presentation;
    }

    public final String getDosages()
    {
        return dosages.get();
    }

    public final void setDosages(String value)
    {
        dosages.set(value);
    }

    public StringProperty dosagesProperty()
    {
        return dosages;
    }

    public final double getPrice()
    {
        return price.get();
    }

    public final void setPrice(double value)
    {
        price.set(value);
    }

    public DoubleProperty priceProperty()
    {
        return price;
    }
    
}