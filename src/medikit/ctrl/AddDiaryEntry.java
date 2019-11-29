package medikit.ctrl;

import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import medikit.controller.Controller;
import medikit.misc.ActionStyle;
import medikit.Medikit;
import medikit.diaryentry.DiaryEntry;
import medikit.diaryentry.PatientSearchCell;
import medikit.misc.WindowStyle;
import medikit.patient.Patient;

public class AddDiaryEntry implements Controller
{
    private Patient selectedPatient;
    private ActionStyle actionStyle = ActionStyle.NULL;
    private ColorAdjust colorAdjust = new ColorAdjust()
    {{
            setBrightness(-0.5);
    }};
    
    @FXML private ListView<Patient> patientsList;
    @FXML private Button acceptButton;
    @FXML private Button closeButton;
    @FXML private TextField searchField;
    @FXML private DatePicker dateField;
    @FXML private TextField hourField;
    @FXML private ComboBox<String> timeField;
    @FXML private GridPane root;
    @FXML private TextField minuteField;

    public void initialize()
    {
        timeField.getItems().setAll("PM", "AM");
        timeField.getSelectionModel().select(0);
        
        patientsList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        patientsList.setCellFactory((ListView<Patient> param) -> new PatientSearchCell());
        
        acceptButton.addEventHandler(ActionEvent.ACTION, (ActionEvent event) ->
        {
            String hour = hourField.getText(), minute = minuteField.getText();
            LocalDate date = dateField.getValue();
            if(!hour.isEmpty() && !minute.isEmpty() && date != null)
            {
                actionStyle = ActionStyle.CONFIRMED;
                selectedPatient = patientsList.getSelectionModel().getSelectedItem();
                Medikit.getWindow(WindowStyle.ADD_DIARY).hide();
            }
            else
            {
                ((Message2) Medikit.getController(WindowStyle.MESSAGE2)).initController("warning-icon", "Información incompleta", 
                        "Todos los campos no opcionales deben contener información", "Aceptar");
                Toolkit.getDefaultToolkit().beep();
                this.showPopup(Medikit.getWindow(WindowStyle.MESSAGE2));
            }
        });
        
        searchField.textProperty().addListener(
                (ObservableValue<? extends String> observable, String oldValue, String newValue) ->
        {
            ObservableList<Patient> items = patientsList.getItems();
            if(!newValue.isEmpty())
            {
                String query = String.format(
                "SELECT Patient.id " +
                "	FROM Patient " +
                "	WHERE Patient.name LIKE '%%%s%%' OR Patient.background LIKE '%%%s%%' OR Patient.gender LIKE '%%%s%%' OR " +
                "		Patient.birthDate LIKE '%%%s%%' OR Patient.phone LIKE '%%%s%%' OR Patient.address LIKE '%%%s%%';",
                        newValue, newValue, newValue, newValue, newValue, newValue, newValue, newValue, newValue, newValue, newValue, 
                        newValue, newValue, newValue, newValue);
                
                ResultSet resultSet = Medikit.executeQuery(query);
                ObservableMap<Integer, Patient> loadedPatients = Patient.getLoadedPatients();
                items.clear();
                
                try
                {
                    while(resultSet.next())
                        items.add(loadedPatients.get(resultSet.getInt(1)));
                } 
                catch(SQLException e)
                {
                    System.out.println("patient search");
                    System.out.println(e.getMessage());
                }
            }
            else
                items.setAll(Patient.getLoadedPatients().values());
        });
        
        this.hourField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) ->
        {
            if(!newValue.matches("[0-9]*"))
                hourField.setText(oldValue);
        });
        
        this.minuteField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) ->
        {
            if(!newValue.matches("[0-9]*"))
                minuteField.setText(oldValue);
        });
        
        closeButton.addEventHandler(ActionEvent.ACTION, (ActionEvent event) ->
            Medikit.getWindow(WindowStyle.ADD_DIARY).hide());
        
        root.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) ->
            root.requestFocus());
    }
    
    public void initController()
    {
        patientsList.getItems().setAll(Patient.getLoadedPatients().values());
        hourField.clear();
        minuteField.clear();
        dateField.setValue(LocalDate.now());
    }
    
    public void initController(DiaryEntry diaryEntry)
    {
        patientsList.getItems().setAll(Patient.getLoadedPatients().values());
        hourField.clear();
        minuteField.clear();
        dateField.setValue(LocalDate.now());
        acceptButton.setText("Guardar cambios");
        
        char[] time = diaryEntry.getTime().toCharArray();
        String hour = "", minute = "", temp = "";
        for(int x = 0; x < time.length; x++)
        {
            if(time[x] == ':')
            {
                hour = temp;
                temp = "";
                continue;
            }
            else if(time[x] == ' ')
                break;
            temp += time[x];
        }
        minute = temp;
        
        patientsList.getSelectionModel().select(diaryEntry.getPatient());
        minuteField.setText(minute);
        hourField.setText(hour);
        dateField.setValue(diaryEntry.getDate());
    }
    
    public void showPopup(Stage stage)
    {
        root.setEffect(colorAdjust);
        stage.showAndWait();
        root.setEffect(null);
    }
    
    public int getHour()
    {
        return Integer.parseInt(hourField.getText());
    }
    
    public int getMinute()
    {
        return Integer.parseInt(minuteField.getText());
    }
    
    public String getTime()
    {
        return timeField.getSelectionModel().getSelectedItem();
    }
    
    public LocalDate getDate()
    {
        return dateField.getValue();
    }

    public Patient getSelectedPatient()
    {
        return selectedPatient;
    }

    public void setSelectedPatient(Patient selectedPatient)
    {
        this.selectedPatient = selectedPatient;
    }

    public ActionStyle getActionStyle()
    {
        return actionStyle;
    }

    public void setActionStyle(ActionStyle actionStyle)
    {
        this.actionStyle = actionStyle;
    }
}