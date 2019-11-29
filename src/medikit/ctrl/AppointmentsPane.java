package medikit.ctrl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.LinkedList;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import medikit.controller.Controller;
import medikit.patient.Patient;
import medikit.Medikit;
import medikit.appointment.Appointment;
import medikit.appointment.AppointmentCell;
import medikit.medicament.Medicament;
import medikit.misc.ActionStyle;
import medikit.misc.WindowStyle;
import medikit.treatment.Treatment;
import medikit.treatment.TreatmentCell;

public class AppointmentsPane implements Controller
{
    private Appointment currentAppointment;
    private boolean userAction = true;
    private BooleanProperty modified = new SimpleBooleanProperty(false);
    private LinkedList<Treatment> newlyAddedTreatments = new LinkedList<>();
    private Patient patient;
    
    private ColorAdjust colorAdjust = new ColorAdjust()
    {{
            setBrightness(-0.5);
    }};
    private static String monthName[] = {"null", "Ene", "Feb", "Mar", "Abr", "May", "Jun", "Jul", "Ago", "Sep", "Oct", "Nov", "Dec"};
    
    @FXML private VBox root;
    @FXML private Text patientName;
    @FXML private Button saveButton;
    @FXML private Button closeButton;
    @FXML private Button addAppointmentButton;
    @FXML private Button deleteAppointmentButton;
    @FXML private CheckBox appointmentSelectBox;
    @FXML private TextField searchField;
    @FXML private ListView<Appointment> appointmentsList;
    @FXML private VBox treatmentPane;
    @FXML private Text treatmentName;
    @FXML private Button addTreatmentButton;
    @FXML private Button deleteTreatmentButton;
    @FXML private CheckBox treatmentSelectBox;
    @FXML private TextArea diagnosticField;
    @FXML private TextArea notesField;
    @FXML private ListView<Treatment> treatmentsList;

    @FXML
    public void initialize()
    {
        appointmentsList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        appointmentsList.setCellFactory((ListView<Appointment> param) -> new AppointmentCell());
        
        closeButton.addEventHandler(ActionEvent.ACTION, (ActionEvent event) ->
        {
            if(modified.get())
            {
                Message1 message1 = (Message1) Medikit.getController(WindowStyle.MESSAGE1);
                message1.initController("warning-icon", "Cerrar sin guardar", 
                        "Los elementos modificados no han sido guardados.\n¿Deseas cerrar sin guardar?", "Salir", "Cancelar");
                root.setEffect(colorAdjust);
                Medikit.getWindow(WindowStyle.MESSAGE1).showAndWait();
                root.setEffect(null);

                if(message1.getActionStyle() == ActionStyle.CONFIRMED)
                    Medikit.getWindow(WindowStyle.APPOINTMENTS).hide();
            }
            else
                Medikit.getWindow(WindowStyle.APPOINTMENTS).hide();
        });
        
        // Appointment fields --------------------------------------------------
        
        appointmentSelectBox.selectedProperty().addListener(
                (ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) ->
        {
            if(newValue)
            {
                if(userAction)
                {
                    this.appointmentsList.getSelectionModel().selectAll();
                    Collection<Appointment> selection = this.appointmentsList.getSelectionModel().getSelectedItems();
                    for(Appointment appointment : selection)
                        appointment.setSelected(true);
                }
            }
            else
            {
                Collection<Appointment> selection = this.appointmentsList.getItems();
                for(Appointment appointment : selection)
                    appointment.setSelected(false);
                this.appointmentsList.getSelectionModel().clearSelection();
            }
        });
        
        deleteAppointmentButton.addEventHandler(ActionEvent.ACTION, (ActionEvent event) ->
        {
            Collection<Appointment> selection = this.getAppointmentSelection();
            if(!selection.isEmpty())
            {
                ObservableList<Appointment> items = appointmentsList.getItems();
                ObservableList<Appointment> appointmentsList = this.patient.getAppointmentsList();
                Message1 message1 = (Message1) Medikit.getController(WindowStyle.MESSAGE1);
                message1.initController("warning-icon", "Eliminar consultas", "¿Deseas eliminar las consultas seleccionadas?", "Eliminar", "Cancelar");
                root.setEffect(colorAdjust);
                Medikit.getWindow(WindowStyle.MESSAGE1).showAndWait();
                root.setEffect(null);

                if(message1.getActionStyle() == ActionStyle.CONFIRMED)
                {
                    ObservableMap<Integer, Appointment> loadedAppointments = Appointment.getLoadedAppointments();
                    for(Appointment appointment : selection)
                    {
                        items.remove(items.indexOf(appointment));
                        loadedAppointments.remove(appointment.getId());
                        try
                        {
                            appointmentsList.remove(appointmentsList.indexOf(appointment));
                        }
                        catch(Exception exception)
                        {
                        }

                        if(currentAppointment != null)
                        {
                            if(currentAppointment.equals(appointment))
                            {
                                currentAppointment = null;
                                treatmentPane.setVisible(false);
                            }
                        }
                    }
                    this.appointmentsList.getSelectionModel().clearSelection();
                }
            }
        });
        
        appointmentsList.getSelectionModel().getSelectedIndices().addListener(
                (ListChangeListener.Change<? extends Integer> c) ->
        {
            int size = appointmentsList.getSelectionModel().getSelectedIndices().size();
            switch(size)
            {
                case 0:
                {
                    appointmentSelectBox.setSelected(false);
                    deleteAppointmentButton.setDisable(true);
                    break;
                }
                default: deleteAppointmentButton.setDisable(false);
            }
        });
        
        addAppointmentButton.addEventHandler(ActionEvent.ACTION, (ActionEvent event) ->
        {
            Appointment newAppointment = new Appointment(patient);
            appointmentsList.getItems().add(0, newAppointment);
            this.modified.set(true);
        });
        
        diagnosticField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) ->
        {
            this.currentAppointment.setTemporalDiagnostic(newValue);
            this.modified.set(true);
            this.currentAppointment.setModified(true);
        });
        
        notesField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) ->
        {
            this.currentAppointment.setTemporalNotes(newValue);
            this.modified.set(true);
            this.currentAppointment.setModified(true);
        });
        
        searchField.textProperty().addListener(
                (ObservableValue<? extends String> observable, String oldValue, String newValue) ->
        {
            ObservableList<Appointment> items = appointmentsList.getItems();
            if(!newValue.isEmpty())
            {
                String query = String.format(
                "SELECT Appointment.id " +
                "	FROM Appointment " +
                "		LEFT JOIN Treatment " +
                "			ON Treatment.idAppointment = Appointment.id " +
                "		LEFT JOIN Medicament " +
                "			ON Treatment.idMedicament = Medicament.id " +
                "	WHERE Appointment.date LIKE '%%%s%%' OR Appointment.diagnostic LIKE '%%%s%%' OR Appointment.notes LIKE '%%%s%%' OR " +
                "		Treatment.treatment LIKE '%%%s%%' OR Medicament.brandName LIKE '%%%s%%' OR Medicament.genericName LIKE '%%%s%%' OR " +
                "		Medicament.dosages LIKE '%%%s%%' OR Medicament.form LIKE '%%%s%%' OR Medicament.presentation LIKE '%%%s%%';", 
                        newValue, newValue, newValue, newValue, newValue, newValue, newValue, newValue, newValue, newValue, newValue, 
                        newValue, newValue, newValue, newValue);
                
                ResultSet resultSet = Medikit.executeQuery(query);
                ObservableMap<Integer, Appointment> loadedAppointments = Appointment.getLoadedAppointments();
                items.clear();
                
                try
                {
                    while(resultSet.next())
                        items.add(loadedAppointments.get(resultSet.getInt(1)));
                } 
                catch(SQLException e)
                {
                    System.out.println("appointment search");
                    System.out.println(e.getMessage());
                }
            }
            else
                items.setAll(Appointment.getLoadedAppointments().values());
        });
        
        // Treatment fields ----------------------------------------------------
        
        treatmentsList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        treatmentsList.setCellFactory((ListView<Treatment> param) -> new TreatmentCell());
        
        addTreatmentButton.addEventHandler(ActionEvent.ACTION, (ActionEvent event) ->
        {
            Treatment treatment = new Treatment(this.currentAppointment);
            treatmentsList.getItems().add(0, treatment);
            newlyAddedTreatments.add(treatment);
            this.currentAppointment.setModified(true);
            this.modified.set(true);
        });
        
        treatmentSelectBox.selectedProperty().addListener(
                (ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) ->
        {
            if(newValue)
            {
                if(userAction)
                {
                    this.treatmentsList.getSelectionModel().selectAll();
                    Collection<Treatment> selection = this.treatmentsList.getSelectionModel().getSelectedItems();
                    for(Treatment treatment : selection)
                        treatment.setSelected(true);
                }
            }
            else
            {
                Collection<Treatment> selection = this.treatmentsList.getItems();
                for(Treatment treatment : selection)
                    treatment.setSelected(false);
                this.treatmentsList.getSelectionModel().clearSelection();
            }
        });
        
        deleteTreatmentButton.addEventHandler(ActionEvent.ACTION, (ActionEvent event) ->
        {
            Collection<Treatment> selection = this.getTreatmentSelection();
            if(!selection.isEmpty())
            {
                ObservableList<Treatment> items = treatmentsList.getItems();
                Message1 message1 = (Message1) Medikit.getController(WindowStyle.MESSAGE1);
                message1.initController("warning-icon", "Eliminar tratamientos", "¿Deseas eliminar los tratamientos seleccionados?", "Eliminar", "Cancelar");
                root.setEffect(colorAdjust);
                Medikit.getWindow(WindowStyle.MESSAGE1).showAndWait();
                root.setEffect(null);

                if(message1.getActionStyle() == ActionStyle.CONFIRMED)
                {
                    ObservableMap<Integer, Treatment> loadedTreatments = Treatment.getLoadedTreatments();
                    for(Treatment treatment : selection)
                    {
                        items.remove(items.indexOf(treatment));
                        loadedTreatments.remove(treatment.getId());

                        int newlyIndex = newlyAddedTreatments.indexOf(treatment), index = this.currentAppointment.getTreatmentsList().indexOf(treatment);
                        if(newlyIndex != -1)
                            newlyAddedTreatments.remove(newlyIndex);
                        if(index != -1)
                            this.currentAppointment.getTreatmentsList().remove(index);
                    }
                    this.treatmentsList.getSelectionModel().clearSelection();
                }
            }
        });
        
        treatmentsList.getSelectionModel().getSelectedIndices().addListener(
                (ListChangeListener.Change<? extends Integer> c) ->
        {
            int size = treatmentsList.getSelectionModel().getSelectedIndices().size();
            switch(size)
            {
                case 0:
                {
                    treatmentSelectBox.setSelected(false);
                    deleteTreatmentButton.setDisable(true);
                    break;
                }
                default: deleteTreatmentButton.setDisable(false);
            }
        });
        
        //----------------------------------------------------------------------
        
        modified.addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) ->
        {
            saveButton.setDisable(!newValue);
        });
        
        saveButton.addEventHandler(ActionEvent.ACTION, (ActionEvent event) ->
        {
            ObservableMap<Integer, Appointment> loadedAppointments = Appointment.getLoadedAppointments();
            ObservableMap<Integer, Treatment> loadedTreatments = Treatment.getLoadedTreatments();
            for(Appointment appointment : appointmentsList.getItems())
            {
                if(appointment.getId() == -1)
                {
                    appointment.setId(Appointment.nextId());
                    appointment.setPatient(this.patient);
                    appointment.setDate(appointment.getTemporalDate());
                    appointment.setDiagnostic(appointment.getTemporalDiagnostic());
                    appointment.setNotes(appointment.getTemporalNotes());
                    appointment.setPrice(appointment.getTemporalPrice());
                    this.patient.getAppointmentsList().add(appointment);
                    loadedAppointments.put(appointment.getId(), appointment);
                }
                else
                {
                    if(appointment.isModified())
                    {
                        Appointment newAppointment = new Appointment(appointment.getTemporalDate(), appointment.getTemporalDiagnostic(), 
                                appointment.getTemporalNotes(), appointment.getTemporalPrice());
                        Appointment.update(appointment, newAppointment);
                        
                        for(Treatment treatment : appointment.getTreatmentsList())
                        {
                            Treatment newTreatment = new Treatment(treatment.getTemporalMedicament(), treatment.getTemporalTreatment(), 
                                    treatment.getTemporalAmount(), treatment.getTemporalUnknownMedicament());
                            Treatment.update(treatment, newTreatment);
                            treatment.setModified(false);
                            treatment.resetTemporalFields();
                            
                            Medicament medicament = treatment.getMedicament();
                            if(treatment.isMedicamentModifed() && medicament != null)
                            {
                                int amount = medicament.getAmount() - treatment.getAmount();
                                Medicament.updateAmount(medicament, (amount < 0) ? 0 : amount);
                                treatment.setMedicamentModifed(false);
                            }
                        }
                        
                    }
                }
                if(this.currentAppointment != null)
                {
                    if(this.currentAppointment.equals(appointment))
                    {
                        LocalDate date = appointment.getDate();
                        this.treatmentName.setText(date.getDayOfMonth() + " de " + monthName[date.getMonthValue()] + " del " + date.getYear());
                    }
                }
                appointment.setModified(false);
                appointment.resetTemporalFields();
            }
            for(Treatment treatment : newlyAddedTreatments)
            {
                treatment.setId(Treatment.nextId());
                treatment.setMedicament(treatment.getTemporalMedicament());
                treatment.setTreatment(treatment.getTemporalTreatment());
                treatment.setAmount(treatment.getTemporalAmount());
                treatment.setUnknownMedicament(treatment.getTemporalUnknownMedicament());
                treatment.getAppointment().getTreatmentsList().add(0, treatment);
                loadedTreatments.put(treatment.getId(), treatment);
                treatment.setModified(false);
                treatment.resetTemporalFields();
                
                Medicament medicament = treatment.getMedicament();
                if(treatment.isMedicamentModifed() && medicament != null)
                {
                    int amount = medicament.getAmount() - treatment.getAmount();
                    Medicament.updateAmount(medicament, (amount < 0) ? 0 : amount);
                    treatment.setMedicamentModifed(false);
                }
            }
            newlyAddedTreatments.clear();
            this.modified.set(false);
        });
        
        root.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) ->
            root.requestFocus());
    }
    
    public void initController(Patient patient)
    {
        this.patient = patient;
        treatmentPane.setVisible(false);
        this.searchField.clear();
        this.modified.set(false);
        this.newlyAddedTreatments.clear();
        this.currentAppointment = null;
        
        ObservableList<Appointment> appointmentsList = patient.getAppointmentsList();
        ObservableList<Appointment> items = this.appointmentsList.getItems();
        items.clear();
        for(int x  = appointmentsList.size() - 1; x >= 0; x--)
            items.add(appointmentsList.get(x));
        this.patientName.setText(this.patient.getName());
    }
    
    public void setAppointment(Appointment appointment)
    {
        boolean modified = this.modified.get();
        this.currentAppointment = appointment;
        
        if(!appointment.isModified())
        {
            LocalDate date = appointment.getDate();
            this.treatmentName.setText(date.getDayOfMonth() + " de " + monthName[date.getMonthValue()] + " del " + date.getYear());
            this.diagnosticField.setText(appointment.getDiagnostic());
            this.notesField.setText(appointment.getNotes());
            this.treatmentsList.getItems().setAll(appointment.getTreatmentsList());
        }
        else
        {
            LocalDate date = appointment.getTemporalDate();
            this.treatmentName.setText(date.getDayOfMonth() + " de " + monthName[date.getMonthValue()] + " del " + date.getYear());
            this.diagnosticField.setText(appointment.getTemporalDiagnostic());
            this.notesField.setText(appointment.getTemporalNotes());
            this.treatmentsList.getItems().setAll(appointment.getTreatmentsList());
            for(Treatment treatment : newlyAddedTreatments)
            {
                if(treatment.getAppointment().equals(appointment))
                    this.treatmentsList.getItems().add(0, treatment);
            }
        }
        this.treatmentPane.setVisible(true);
        
        this.modified.set(modified);
    }
    
    public void selectAppointment(Appointment appointment, boolean select)
    {
        if(select)
        {
            appointmentsList.getSelectionModel().select(appointment);
            userAction = false;
            appointmentSelectBox.setSelected(true);
            userAction = true;
        }
        else
            appointmentsList.getSelectionModel().clearSelection(appointmentsList.getItems().indexOf(appointment));
    }
    
    public void selectTreatment(Treatment treatment, boolean select)
    {
        if(select)
        {
            treatmentsList.getSelectionModel().select(treatment);
            userAction = false;
            treatmentSelectBox.setSelected(true);
            userAction = true;
        }
        else
            treatmentsList.getSelectionModel().clearSelection(treatmentsList.getItems().indexOf(treatment));
    }
    
    private LinkedList<Appointment> getAppointmentSelection()
    {
        LinkedList<Appointment> selection = new LinkedList<>();
        Collection<Appointment> appointments = this.appointmentsList.getSelectionModel().getSelectedItems();
        
        for(Appointment appointment : appointments)
        {
            if(appointment.isSelected())
                selection.add(appointment);
        }
        return selection;
    }
    
    private LinkedList<Treatment> getTreatmentSelection()
    {
        LinkedList<Treatment> selection = new LinkedList<>();
        Collection<Treatment> treatments = this.treatmentsList.getSelectionModel().getSelectedItems();
        
        for(Treatment treatment : treatments)
        {
            if(treatment.isSelected())
                selection.add(treatment);
        }
        return selection;
    }
    
    public void showPopup(Stage stage)
    {
        root.setEffect(colorAdjust);
        stage.showAndWait();
        root.setEffect(null);
    }
    
    public void addAppointment(LocalDate date)
    {
        Appointment newAppointment = new Appointment(patient);
        newAppointment.setDate(date);
        appointmentsList.getItems().add(0, newAppointment);
        this.modified.set(true);
    }

    public final boolean isModified()
    {
        return modified.get();
    }

    public final void setModified(boolean value)
    {
        modified.set(value);
    }

    public BooleanProperty modifiedProperty()
    {
        return modified;
    }

    public ObservableList<Appointment> getAppointmentsList()
    {
        return appointmentsList.getItems();
    }
}