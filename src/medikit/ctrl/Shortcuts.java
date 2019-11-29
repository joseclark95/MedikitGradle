package medikit.ctrl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.TilePane;
import medikit.Medikit;
import medikit.controller.Controller;
import medikit.misc.WindowStyle;

public class Shortcuts implements Controller
{
    @FXML private TilePane shortcutsPane;
    @FXML private Button addAppointmentButton;
    @FXML private Button newPatientButton;
    @FXML private Button newMedicamentButton;

    public void initialize()
    {
        newPatientButton.addEventHandler(ActionEvent.ACTION, (ActionEvent event) ->
        {
            Main main = (Main) Medikit.getController(WindowStyle.MAIN);
            PatientsPane patientsPane = (PatientsPane) Medikit.getController(WindowStyle.PATIENTS);
            
            main.selectToggle(WindowStyle.PATIENTS);
            patientsPane.add();
        });
        
        newMedicamentButton.addEventHandler(ActionEvent.ACTION, (ActionEvent event) ->
        {
            Main main = (Main) Medikit.getController(WindowStyle.MAIN);
            InventoryPane inventoryPane = (InventoryPane) Medikit.getController(WindowStyle.INVENTORY);
            
            main.selectToggle(WindowStyle.INVENTORY);
            inventoryPane.add();
        });
        
        addAppointmentButton.addEventHandler(ActionEvent.ACTION, (ActionEvent event) ->
        {
            Main main = (Main) Medikit.getController(WindowStyle.MAIN);
            NotificationsPane notificationsPane = (NotificationsPane) Medikit.getController(WindowStyle.NOTIFICATIONS);
            
            main.selectToggle(WindowStyle.NOTIFICATIONS);
            notificationsPane.add();
        });
    }
}