package medikit.ctrl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import medikit.Medikit;
import medikit.controller.Controller;
import medikit.medicament.Medicament;
import medikit.medicament.InventorySearchCell;
import medikit.misc.ActionStyle;
import medikit.misc.WindowStyle;

public class MedicamentChooser implements Controller
{
    private ActionStyle actionStyle = ActionStyle.NULL;
    private Medicament selectedMedicament;
    
    @FXML private VBox root;
    @FXML private TextField searchField;
    @FXML private CheckBox selectAllBox;
    @FXML private ListView<Medicament> medicamentsList;
    @FXML private Button closeButton;
    @FXML private Button acceptButton;

    public void initialize()
    {
        medicamentsList.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> event.consume());
        
        medicamentsList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        medicamentsList.setCellFactory((ListView<Medicament> param) -> new InventorySearchCell());
        
        selectAllBox.selectedProperty().addListener(
                (ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) ->
        {
            if(!newValue)
            {
                if(selectedMedicament != null)
                    selectedMedicament.setSelected(false);
                this.medicamentsList.getSelectionModel().clearSelection();
            }
        });
        
        searchField.textProperty().addListener(
                (ObservableValue<? extends String> observable, String oldValue, String newValue) ->
        {
            ObservableList<Medicament> items = medicamentsList.getItems();
            if(!newValue.isEmpty())
            {
                String query = String.format(
                "SELECT Medicament.id " +
                "	FROM Medicament " +
                "	WHERE Medicament.brandName LIKE '%%%s%%' OR Medicament.genericName LIKE '%%%s%%' OR Medicament.form LIKE '%%%s%%' OR " +
                "		Medicament.dosages LIKE '%%%s%%' OR Medicament.presentation LIKE '%%%s%%';", 
                        newValue, newValue, newValue, newValue, newValue);
                
                ResultSet resultSet = Medikit.executeQuery(query);
                ObservableMap<Integer, Medicament> loadedMedicaments = Medicament.getLoadedMedicaments();
                items.clear();
                
                try
                {
                    while(resultSet.next())
                        items.add(loadedMedicaments.get(resultSet.getInt(1)));
                } 
                catch(SQLException e)
                {
                    System.out.println("medicament search");
                    System.out.println(e.getMessage());
                }
            }
            else
                items.setAll(Medicament.getLoadedMedicaments().values());
        });
        
        medicamentsList.getSelectionModel().getSelectedIndices().addListener(
                (ListChangeListener.Change<? extends Integer> c) ->
        {
            int size = medicamentsList.getSelectionModel().getSelectedIndices().size();
            if(size == 0)
                selectAllBox.setSelected(false);
            else if(size == 1)
                acceptButton.setDisable(false);
        });
        
        acceptButton.addEventHandler(ActionEvent.ACTION, (ActionEvent event) ->
        {
            if(selectedMedicament != null)
            {
                Collection<Medicament> selection = this.medicamentsList.getItems();
                
                actionStyle = ActionStyle.CONFIRMED;
                Medikit.getWindow(WindowStyle.MEDICAMENTS_CHOOSER).hide();
            }
        });
        
        closeButton.addEventHandler(ActionEvent.ACTION, (ActionEvent event) -> 
        {
            actionStyle = ActionStyle.CANCELED;
            if(selectedMedicament != null)
                selectedMedicament.setSelected(false);
            selectedMedicament = null;
            Medikit.getWindow(WindowStyle.MEDICAMENTS_CHOOSER).hide();
        });
        
        root.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) ->
            root.requestFocus());
    }
    
    public void initController()
    {
        ObservableList<Medicament> items = medicamentsList.getItems();
        items.setAll(Medicament.getLoadedMedicaments().values());
    }
    
    public void select(Medicament medicament, boolean select)
    {
        if(selectedMedicament != null)
            selectedMedicament.setSelected(false);
        if(select)
        {
            medicamentsList.getSelectionModel().select(medicament);
            selectAllBox.setSelected(true);
            selectedMedicament = medicament;
        }
        else
        {
            selectedMedicament = null;
            medicamentsList.getSelectionModel().clearSelection();
        }
    }

    public ActionStyle getActionStyle()
    {
        return actionStyle;
    }

    public Medicament getSelectedMedicament()
    {
        return selectedMedicament;
    }

    public void setSelectedMedicament(Medicament selectedMedicament)
    {
        this.selectedMedicament = selectedMedicament;
    }
}