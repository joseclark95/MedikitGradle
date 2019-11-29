package medikit.appointment;

import java.time.LocalDate;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import medikit.Medikit;
import medikit.ctrl.AppointmentsPane;
import medikit.misc.WindowStyle;

public class AppointmentTile extends AppointmentTileBase
{
    private Appointment appointment;

    public AppointmentTile(Appointment appointment)
    {
        super();
        this.appointment = appointment;
        setFields();
        onEvent();
        onFieldsModify();
        
        if(this.appointment.isSelected())
            selectionBox.setSelected(true);
    }
    
    private void onEvent()
    {
        this.addEventHandler(ActionEvent.ACTION, (ActionEvent event) ->
        {
            ((AppointmentsPane) Medikit.getController(WindowStyle.APPOINTMENTS)).setAppointment(appointment);
        });
        
        selectionBox.selectedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) ->
        {
            ((AppointmentsPane) Medikit.getController(WindowStyle.APPOINTMENTS)).selectAppointment(this.appointment, newValue);
            this.appointment.setSelected(newValue);
        });
    }
    
    private void setFields()
    {
        if(!this.appointment.isModified())
        {
            this.date.setValue(this.appointment.getDate());
            this.priceField.setText((this.appointment.getPrice() == 0) ? "" : "" + this.appointment.getPrice());
        }
        else
        {
            this.date.setValue(this.appointment.getTemporalDate());
            this.priceField.setText((this.appointment.getTemporalPrice() == 0) ? "" : "" + this.appointment.getTemporalPrice());
        }
    }
    
    private void onFieldsModify()
    {
        this.priceField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) ->
        {
            if(!newValue.matches("[0-9]*[.]?[0-9]*"))
                priceField.setText(oldValue);
            else
            {
                if(!newValue.isEmpty())
                {
                    ((AppointmentsPane) Medikit.getController(WindowStyle.APPOINTMENTS)).setModified(true);
                    this.appointment.setTemporalPrice(Double.parseDouble(newValue));
                    this.appointment.setModified(true);
                }
            }
        });
        
        this.date.valueProperty().addListener((ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) ->
        {
            ((AppointmentsPane) Medikit.getController(WindowStyle.APPOINTMENTS)).setModified(true);
            this.appointment.setTemporalDate(newValue);
            this.appointment.setModified(true);
        });
        
        this.date.getEditor().focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) ->
        {
            this.hBox.requestFocus();
            this.date.show();
        });
    }
    
    public void select(boolean selected)
    {
        this.selectionBox.setSelected(selected);
    }

    public Appointment getAppointment()
    {
        return appointment;
    }

    public void setAppointment(Appointment appointment)
    {
        this.appointment = appointment;
    }
}