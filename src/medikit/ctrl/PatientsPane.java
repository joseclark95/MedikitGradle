package medikit.ctrl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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
import medikit.controller.Controller;
import medikit.patient.Patient;
import medikit.patient.PatientCell;
import medikit.Medikit;
import medikit.misc.ActionStyle;
import medikit.misc.WindowStyle;

public class PatientsPane implements Controller
{    
    private boolean userAction = true;
    
    @FXML private ListView<Patient> patientsList;
    @FXML private Button addPatientButton;
    @FXML private Button deleteButton;
    @FXML private Button openButton;
    @FXML private TextField searchField;
    @FXML private CheckBox selectAllBox;
    @FXML private VBox root;
    
    public void initialize()
    {
        patientsList.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> event.consume());
        
        patientsList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        patientsList.setCellFactory((ListView<Patient> param) -> new PatientCell());
        
        selectAllBox.selectedProperty().addListener(
                (ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) ->
        {
            if(newValue)
            {
                if(userAction)
                {
                    this.patientsList.getSelectionModel().selectAll();
                    Collection<Patient> selection = this.patientsList.getSelectionModel().getSelectedItems();
                    for(Patient patient : selection)
                        patient.setSelected(true);
                }
            }
            else
            {
                Collection<Patient> selection = this.patientsList.getItems();
                for(Patient patient : selection)
                    patient.setSelected(false);
                this.patientsList.getSelectionModel().clearSelection();
            }
        });
        
        deleteButton.addEventHandler(ActionEvent.ACTION, (ActionEvent event) ->
        {
            ObservableList<Patient> items = patientsList.getItems();
            Message1 message1 = (Message1) Medikit.getController(WindowStyle.MESSAGE1);
            message1.initController("warning-icon", "Eliminar pacientes", "¿Deseas eliminar los pacientes seleccionados?", "Eliminar", "Cancelar");
            ((Main) Medikit.getController(WindowStyle.MAIN)).showPopup(Medikit.getWindow(WindowStyle.MESSAGE1));

            if(message1.getActionStyle() == ActionStyle.CONFIRMED)
            {
                ObservableMap<Integer, Patient> loadedPatients = Patient.getLoadedPatients();
                try
                {
                    Collection<Patient> selection = this.getSelection();
                    for(Patient patient : selection)
                    {
                        items.remove(items.indexOf(patient));
                        loadedPatients.remove(patient.getId());
                    }
                }
                catch(Exception exception)
                {
                    Patient last = items.get(items.size() - 1);
                    items.remove(items.size() - 1);
                    loadedPatients.remove(last.getId());
                }
                this.patientsList.getSelectionModel().clearSelection();
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
        
        patientsList.getSelectionModel().getSelectedIndices().addListener(
                (ListChangeListener.Change<? extends Integer> c) ->
        {
            int size = patientsList.getSelectionModel().getSelectedIndices().size();
            
            switch(size)
            {
                case 0:
                {
                    selectAllBox.setSelected(false);
                    deleteButton.setDisable(true);
                    openButton.setDisable(true);
                    break;
                }
                case 1:
                {
                    openButton.setDisable(false);
                    deleteButton.setDisable(false);
                    break;
                }
                default: 
                {
                    deleteButton.setDisable(false);
                    openButton.setDisable(true);
                }
            }
        });
        
        openButton.addEventHandler(ActionEvent.ACTION, (ActionEvent event) ->
        {
            AppointmentsPane appointmentsPane = (AppointmentsPane) Medikit.getController(WindowStyle.APPOINTMENTS);
            Patient selectedPatient = null;
            ObservableList<Patient> items = patientsList.getItems();
            for(Patient patient : items)
            {
                if(patient.isSelected())
                {
                    selectedPatient = patient;
                    break;
                }
            }
            
            appointmentsPane.initController(selectedPatient);
            ((Main) Medikit.getController(WindowStyle.MAIN)).showPopup(Medikit.getWindow(WindowStyle.APPOINTMENTS));
            selectedPatient.updateLabels();
        });
        
        addPatientButton.addEventHandler(ActionEvent.ACTION, (ActionEvent event) ->
        {
            Patient newPatient = new Patient();
            newPatient.setModifed(true);
            patientsList.getItems().add(0, newPatient);
        });
        
        root.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) ->
            root.requestFocus());
    }
    
    public void initController()
    {
        ObservableList<Patient> items = patientsList.getItems();
        items.setAll(Patient.getLoadedPatients().values());
    }
    
    public void select(Patient patient, boolean select)
    {
        if(select)
        {
            patientsList.getSelectionModel().select(patient);
            userAction = false;
            selectAllBox.setSelected(true);
            userAction = true;
        }
        else
            patientsList.getSelectionModel().clearSelection(patientsList.getItems().indexOf(patient));
    }
    
    private LinkedList<Patient> getSelection()
    {
        LinkedList<Patient> selection = new LinkedList<>();
        Collection<Patient> patients = this.patientsList.getSelectionModel().getSelectedItems();
        
        for(Patient patient : patients)
        {
            if(patient.isSelected())
                selection.add(patient);
        }
        return selection;
    }
    
    public void open(LocalDate date)
    {
        AppointmentsPane appointmentsPane = (AppointmentsPane) Medikit.getController(WindowStyle.APPOINTMENTS);
        Patient selectedPatient = null;
        ObservableList<Patient> items = patientsList.getItems();
        for(Patient patient : items)
        {
            if(patient.isSelected())
            {
                selectedPatient = patient;
                break;
            }
        }

        appointmentsPane.initController(selectedPatient);
        appointmentsPane.addAppointment(date);
        ((Main) Medikit.getController(WindowStyle.MAIN)).showPopup(Medikit.getWindow(WindowStyle.APPOINTMENTS));
        selectedPatient.updateLabels();
    }
    
    public void add()
    {
        this.addPatientButton.fire();
    }
}