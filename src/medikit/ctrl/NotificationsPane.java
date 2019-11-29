package medikit.ctrl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import medikit.Medikit;
import medikit.controller.Controller;
import medikit.diaryentry.DiaryEntry;
import medikit.diaryentry.DiaryEntryCell;
import medikit.diaryentry.MedicamentSearchCell;
import medikit.medicament.Medicament;
import medikit.misc.ActionStyle;
import medikit.misc.WindowStyle;
import medikit.patient.Patient;

public class NotificationsPane implements Controller
{
    @FXML private VBox root;
    @FXML private ListView<DiaryEntry> diaryList;
    @FXML private Button executeEntryButton;
    @FXML private Button removeEntryButton;
    @FXML private Button addEntryButton;
    @FXML private ListView<Medicament> inventoryList;
    @FXML private Button editEntryButton;

    public void initialize()
    {
        diaryList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        diaryList.setCellFactory((ListView<DiaryEntry> param) -> new DiaryEntryCell());
        
        addEntryButton.addEventHandler(ActionEvent.ACTION, (ActionEvent event) ->
        {
            AddDiaryEntry addDiaryEntry = (AddDiaryEntry) Medikit.getController(WindowStyle.ADD_DIARY);
            addDiaryEntry.initController();
            ((Main) Medikit.getController(WindowStyle.MAIN)).showPopup(Medikit.getWindow(WindowStyle.ADD_DIARY));

            if(addDiaryEntry.getActionStyle() == ActionStyle.CONFIRMED)
            {
                LocalDate date = addDiaryEntry.getDate();
                int hour = addDiaryEntry.getHour(), minute = addDiaryEntry.getMinute();
                String time = ((hour < 10) ? "0" + hour : "" + hour) + ":" + ((minute < 10) ? "0" + minute : "" + minute) + " " + addDiaryEntry.getTime();
                
                DiaryEntry diaryEntry = new DiaryEntry(DiaryEntry.nextId(), date, time, addDiaryEntry.getSelectedPatient());
                DiaryEntry.getLoadedDiaryEntries().put(diaryEntry.getId(), diaryEntry);
                diaryList.getItems().add(0, diaryEntry);
            }
        });
        
        editEntryButton.addEventHandler(ActionEvent.ACTION, (ActionEvent event) ->
        {
            DiaryEntry diaryEntry = this.diaryList.getSelectionModel().getSelectedItem();
            AddDiaryEntry addDiaryEntry = (AddDiaryEntry) Medikit.getController(WindowStyle.ADD_DIARY);
            addDiaryEntry.initController(diaryEntry);
            ((Main) Medikit.getController(WindowStyle.MAIN)).showPopup(Medikit.getWindow(WindowStyle.ADD_DIARY));

            if(addDiaryEntry.getActionStyle() == ActionStyle.CONFIRMED)
            {
                LocalDate date = addDiaryEntry.getDate();
                int hour = addDiaryEntry.getHour(), minute = addDiaryEntry.getMinute();
                String time = ((hour < 10) ? "0" + hour : "" + hour) + ":" + ((minute < 10) ? "0" + minute : "" + minute) + " " + addDiaryEntry.getTime();
                
                DiaryEntry newDiaryEntry = new DiaryEntry(DiaryEntry.nextId(), date, time, addDiaryEntry.getSelectedPatient());
                DiaryEntry.update(diaryEntry, newDiaryEntry);
                
                ObservableList<DiaryEntry> items = this.diaryList.getItems();
                int index = items.indexOf(diaryEntry);
                
                diaryList.getItems().remove(index);
                diaryList.getItems().add(index, diaryEntry);
            }
        });
        
        diaryList.getSelectionModel().getSelectedIndices().addListener(
                (ListChangeListener.Change<? extends Integer> c) ->
        {
            int size = diaryList.getSelectionModel().getSelectedIndices().size();
            switch(size)
            {
                default:
                case 0:
                {
                    removeEntryButton.setDisable(true);
                    executeEntryButton.setDisable(true);
                    editEntryButton.setDisable(true);
                    break;
                }
                case 1: 
                {
                    removeEntryButton.setDisable(false);
                    executeEntryButton.setDisable(false);
                    editEntryButton.setDisable(false);
                    break;
                }
            }
        });
        
        removeEntryButton.addEventHandler(ActionEvent.ACTION, (ActionEvent event) ->
        {
            DiaryEntry diaryEntry = diaryList.getSelectionModel().getSelectedItem();
            ObservableList<DiaryEntry> items = diaryList.getItems();
            
            items.remove(items.indexOf(diaryEntry));
            DiaryEntry.getLoadedDiaryEntries().remove(diaryEntry.getId());
        });
        
        executeEntryButton.addEventHandler(ActionEvent.ACTION, (ActionEvent event) ->
        {
            ObservableList<DiaryEntry> items = diaryList.getItems();
            DiaryEntry diaryEntry = diaryList.getSelectionModel().getSelectedItem();
            Main main = (Main) Medikit.getController(WindowStyle.MAIN);
            PatientsPane patientsPane = (PatientsPane) Medikit.getController(WindowStyle.PATIENTS);
            Patient patient = diaryEntry.getPatient();
            
            main.selectToggle(WindowStyle.PATIENTS);
            if(patient != null)
            {
                AppointmentsPane appointmentsPane = (AppointmentsPane) Medikit.getController(WindowStyle.APPOINTMENTS);
                patient.setSelected(true);
                patientsPane.select(patient, true);
                patientsPane.open(diaryEntry.getDate());
            }
            else
                patientsPane.add();
            
            items.remove(items.indexOf(diaryEntry));
            DiaryEntry.getLoadedDiaryEntries().remove(diaryEntry.getId());
        });
        
        //----------------------------------------------------------------------
        
        inventoryList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        inventoryList.setCellFactory((ListView<Medicament> param) -> new MedicamentSearchCell());
        
        root.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) ->
            root.requestFocus());
    }
    
    public void initController()
    {
        diaryList.getItems().setAll(DiaryEntry.getLoadedDiaryEntries().values());
        ObservableList<Medicament> items = inventoryList.getItems();
        ObservableMap<Integer, Medicament> loadedMedicaments = Medicament.getLoadedMedicaments();
        items.clear();
        try
        {
            String query = String.format(
                "SELECT id " +
                "	FROM Medicament " +
                "	WHERE amount < 6;");
            ResultSet resultSet = Medikit.executeQuery(query);
            while(resultSet.next())
                items.add(loadedMedicaments.get(resultSet.getInt(1)));
        } 
        catch(SQLException e)
        {
        }
    }
    
    public void add()
    {
        addEntryButton.fire();
    }
}