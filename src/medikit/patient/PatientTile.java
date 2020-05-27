package medikit.patient;

import java.awt.Toolkit;
import java.time.LocalDate;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.util.Duration;
import medikit.Medikit;
import medikit.appointment.Appointment;
import medikit.ctrl.Main;
import medikit.ctrl.Message2;
import medikit.ctrl.PatientsPane;
import medikit.misc.WindowStyle;

public class PatientTile extends PatientTileBase
{
    private Patient patient;
    
    private static String monthName[] = {"null", "Ene", "Feb", "Mar", "Abr", "May", "Jun", "Jul", "Ago", "Sep", "Oct", "Nov", "Dec"};
    
    public PatientTile(Patient patient)
    {
        super();
        this.patient = patient;
        this.setFields();
        this.onEvent();
        this.onFieldsModified();

        if(this.patient.getId() == -1)
        {
            ObservableList<String> styleClass = this.savedIcon.getStyleClass();
            
            styleClass.clear();
            styleClass.add("not-saved-icon");
            this.saveButton.setDisable(false);
            this.setExpanded(true);
        }
        
        if(this.patient.isSelected())
            selectionBox.setSelected(true);
    }
    
    private void onEvent()
    {
        this.saveButton.addEventHandler(ActionEvent.ACTION, (ActionEvent event) ->
        {
            String name = nameField.getText(), weight = weightField.getText(), background = backgroundField.getText();
            LocalDate birthDate = birthDatePicker.getValue();
            
            if(!name.isEmpty() && !weight.isEmpty() && !background.isEmpty() && birthDate != null)
            {
                if(this.patient.getId() == -1)
                {
                    this.patient.setId(Patient.nextId());
                    this.patient.setName(name);
                    this.patient.setBackground(background);
                    this.patient.setWeight(Double.parseDouble(weight));
                    this.patient.setBirthDate(birthDate);
                    this.patient.setGender(genderField.getText());
                    this.patient.setPhone(this.phoneField.getText());
                    this.patient.setAddress(this.addressField.getText());
                    
                    Patient.getLoadedPatients().put(this.patient.getId(), this.patient);
                    
                    ObservableList<String> styleClass = this.savedIcon.getStyleClass();
                    styleClass.clear();
                    styleClass.add("saved-icon");
                    this.saveButton.setDisable(true);
                }
                else
                {
                    Patient newPatient = new Patient(this.patient.getId(), name, background, Double.parseDouble(weight), birthDate, 
                            this.genderField.getText(), this.phoneField.getText(), this.addressField.getText());
                    Patient.update(this.patient, newPatient);
                }
                
                this.patient.setModifed(false);
                this.patientName.setText(name);
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
            ((PatientsPane) Medikit.getController(WindowStyle.PATIENTS)).select(this.patient, newValue);
            this.patient.setSelected(newValue);
        });
        
        this.expandedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) ->
        {
            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100), (ActionEvent event) -> 
                this.nameField.requestFocus()));
            timeline.play();
        });
    }
    
    private void onFieldsModified()
    {
        this.patient.modifedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) ->
        {
            ObservableList<String> styleClass = this.savedIcon.getStyleClass();
            
            styleClass.clear();
            styleClass.add((newValue) ? "not-saved-icon" : "saved-icon");
            this.saveButton.setDisable(!newValue);
        });
        
        this.nameField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) ->
        {
            this.patient.setModifed(true);
        });
        
        this.weightField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) ->
        {
            if(!newValue.matches("[0-9]*[.]?[0-9]*"))
                weightField.setText(oldValue);
            this.patient.setModifed(true);
        });
        
        this.birthDatePicker.valueProperty().addListener((ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) ->
        {
            this.patient.setModifed(true);
        });
        
        this.birthDatePicker.getEditor().focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) ->
        {
            this.stackPane1.requestFocus();
            this.birthDatePicker.show();
        });
        
        this.genderField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) ->
        {
            if(oldValue.isEmpty())
            {
                if(newValue.matches("[m | M]"))
                    genderField.setText("Masculino");
                else if(newValue.matches("[f | f]"))
                    genderField.setText("Femenino");
            }
            this.patient.setModifed(true);
        });
        
        this.phoneField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) ->
        {
            if(!newValue.matches("[0-9]*"))
                phoneField.setText(oldValue);
            this.patient.setModifed(true);
        });
        
        this.addressField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) ->
        {
            this.patient.setModifed(true);
        });
        
        this.backgroundField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) ->
        {
            this.patient.setModifed(true);
        });
    }
    
    private void setFields()
    {
        ObservableList<Appointment> appointmentsList = this.patient.getAppointmentsList();
        
        this.patientName.setText(this.patient.getName());
        this.nameField.setText(this.patient.getName());
        this.weightField.setText((this.patient.getWeight() == 0) ? "" : "" + this.patient.getWeight());
        this.birthDatePicker.setValue(this.patient.getBirthDate());
        this.genderField.setText(this.patient.getGender());
        this.phoneField.setText(this.patient.getPhone());
        this.addressField.setText(this.patient.getAddress());
        this.backgroundField.setText(this.patient.getBackground());
        
        if(appointmentsList.isEmpty())
        {
            this.lastVisit.setStyle(this.lastVisit.getStyle() + "; -fx-fill: crimson;");
            this.lastVisit.setText("Sin atender");
            this.appointmentsAmount.setStyle(this.appointmentsAmount.getStyle() + "; -fx-fill: crimson;");
            this.appointmentsAmount.setText("Ninguna");
        }
        else
        {
            int listSize = appointmentsList.size();
            Appointment lastAppointment = appointmentsList.get(listSize - 1);
            LocalDate date = lastAppointment.getDate();
            
            this.lastVisit.setStyle(this.lastVisit.getStyle() + "; -fx-fill: dodgerblue;");
            
            if(PatientTile.isToday(date))
                this.lastVisit.setText("Hoy");
            else
                this.lastVisit.setText(String.format("%d de %s del %d", date.getDayOfMonth(), monthName[date.getMonthValue()], date.getYear()));
                
            this.appointmentsAmount.setStyle(this.appointmentsAmount.getStyle() + "; -fx-fill: dodgerblue;");
            this.appointmentsAmount.setText("" + listSize);
        }
    }
    
    public void updateLabels()
    {
        ObservableList<Appointment> appointmentsList = this.patient.getAppointmentsList();
        if(appointmentsList.isEmpty())
        {
            this.lastVisit.setStyle(this.lastVisit.getStyle() + "; -fx-fill: crimson;");
            this.lastVisit.setText("Sin atender");
            this.appointmentsAmount.setStyle(this.appointmentsAmount.getStyle() + "; -fx-fill: crimson;");
            this.appointmentsAmount.setText("Ninguna");
        }
        else
        {
            int listSize = appointmentsList.size();
            Appointment lastAppointment = appointmentsList.get(listSize - 1);
            LocalDate date = lastAppointment.getDate();
            
            this.lastVisit.setStyle(this.lastVisit.getStyle() + "; -fx-fill: dodgerblue;");
            
            if(PatientTile.isToday(date))
                this.lastVisit.setText("Hoy");
            else
                this.lastVisit.setText(String.format("%d de %s del %d", date.getDayOfMonth(), monthName[date.getMonthValue()], date.getYear()));
                
            this.appointmentsAmount.setStyle(this.appointmentsAmount.getStyle() + "; -fx-fill: dodgerblue;");
            this.appointmentsAmount.setText("" + listSize);
        }
    }
    
    public void select(boolean selected)
    {
        this.selectionBox.setSelected(selected);
    }
    
    private static boolean isToday(LocalDate date)
    {
        LocalDate today = LocalDate.now();
        return (today.getYear() == date.getYear() && today.getMonthValue() == date.getMonthValue() && today.getDayOfYear() == date.getDayOfYear());
    }

    public Patient getPatient()
    {
        return patient;
    }

    public void setPatient(Patient patient)
    {
        this.patient = patient;
    }
}