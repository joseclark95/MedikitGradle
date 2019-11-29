package medikit.ctrl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
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
import medikit.medicament.MedicamentCell;
import medikit.misc.ActionStyle;
import medikit.misc.WindowStyle;

public class InventoryPane implements Controller
{
    private boolean userAction = true;
    
    @FXML private VBox root;
    @FXML private Button addMedicamentButton;
    @FXML private Button deleteButton;
    @FXML private TextField searchField;
    @FXML private CheckBox selectAllBox;
    @FXML private ListView<Medicament> medicamentsList;

    @FXML
    public void initialize()
    {
        medicamentsList.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> event.consume());
        
        medicamentsList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        medicamentsList.setCellFactory((ListView<Medicament> param) -> new MedicamentCell());
        
        selectAllBox.selectedProperty().addListener(
                (ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) ->
        {
            if(newValue)
            {
                if(userAction)
                {
                    this.medicamentsList.getSelectionModel().selectAll();
                    Collection<Medicament> selection = this.medicamentsList.getSelectionModel().getSelectedItems();
                    for(Medicament medicament : selection)
                        medicament.setSelected(true);
                }
            }
            else
            {
                Collection<Medicament> selection = this.medicamentsList.getItems();
                for(Medicament medicament : selection)
                    medicament.setSelected(false);
                this.medicamentsList.getSelectionModel().clearSelection();
            }
        });
        
        deleteButton.addEventHandler(ActionEvent.ACTION, (ActionEvent event) ->
        {
            Collection<Medicament> selection = this.getSelection();
            ObservableList<Medicament> items = medicamentsList.getItems();
            Message1 message1 = (Message1) Medikit.getController(WindowStyle.MESSAGE1);
            message1.initController("warning-icon", "Eliminar medicamentos", "¿Deseas eliminar los medicamentos seleccionados?", "Eliminar", "Cancelar");
            ((Main) Medikit.getController(WindowStyle.MAIN)).showPopup(Medikit.getWindow(WindowStyle.MESSAGE1));

            if(message1.getActionStyle() == ActionStyle.CONFIRMED)
            {
                ObservableMap<Integer, Medicament> loadedMedicaments = Medicament.getLoadedMedicaments();
                for(Medicament medicament : selection)
                {
                    items.remove(items.indexOf(medicament));
                    loadedMedicaments.remove(medicament.getId());
                }
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
            
            switch(size)
            {
                case 0:
                {
                    selectAllBox.setSelected(false);
                    deleteButton.setDisable(true);
                    break;
                }
                default: deleteButton.setDisable(false);
            }
        });
        
        addMedicamentButton.addEventHandler(ActionEvent.ACTION, (ActionEvent event) ->
        {
            Medicament newMedicament = new Medicament();
            newMedicament.setModifed(true);
            medicamentsList.getItems().add(0, newMedicament);
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
        if(select)
        {
            medicamentsList.getSelectionModel().select(medicament);
            userAction = false;
            selectAllBox.setSelected(true);
            userAction = true;
        }
        else
            medicamentsList.getSelectionModel().clearSelection(medicamentsList.getItems().indexOf(medicament));
    }
    
    private LinkedList<Medicament> getSelection()
    {
        LinkedList<Medicament> selection = new LinkedList<>();
        Collection<Medicament> medicaments = this.medicamentsList.getSelectionModel().getSelectedItems();
        
        for(Medicament medicament : medicaments)
        {
            if(medicament.isSelected())
                selection.add(medicament);
        }
        return selection;
    }
    
    public void add()
    {
        addMedicamentButton.fire();
    }
}