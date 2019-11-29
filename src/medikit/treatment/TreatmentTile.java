package medikit.treatment;

import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import medikit.ctrl.AppointmentsPane;
import medikit.ctrl.MedicamentChooser;
import medikit.medicament.Medicament;
import medikit.misc.WindowStyle;
import medikit.Medikit;
import medikit.misc.ActionStyle;

public class TreatmentTile extends TreatmentTileBase
{
    private Treatment treatment;

    public TreatmentTile(Treatment treatment)
    {
        super();
        this.treatment = treatment;
        setFields();
        onEvent();
        onFieldsModify();
    }
    
    public void setFields()
    {
        if(!this.treatment.isModified())
        {
            Medicament medicament = this.treatment.getMedicament();
            this.medicamentField.setText(
                    (medicament == null) ? this.treatment.getUnknownMedicament() : medicament.getBrandName() + ", " + medicament.getGenericName());
            this.amountField.setText((this.treatment.getAmount() == 0) ? "" : "" + this.treatment.getAmount());
            this.treatmentField.setText(this.treatment.getTreatment());
        }
        else
        {
            Medicament medicament = this.treatment.getTemporalMedicament();
            this.medicamentField.setText(
                    (medicament == null) ? this.treatment.getTemporalUnknownMedicament() 
                            : medicament.getBrandName()+ ", " + medicament.getGenericName());
            this.amountField.setText((this.treatment.getTemporalAmount() == 0) ? "" : "" + this.treatment.getTemporalAmount());
            this.treatmentField.setText(this.treatment.getTemporalTreatment());
        }
    }
    
    public void onEvent()
    {
        search.addEventHandler(ActionEvent.ACTION, (ActionEvent event) ->
        {
            MedicamentChooser medicamentChooser = (MedicamentChooser) Medikit.getController(WindowStyle.MEDICAMENTS_CHOOSER);
            medicamentChooser.initController();
            ((AppointmentsPane) Medikit.getController(WindowStyle.APPOINTMENTS)).showPopup(Medikit.getWindow(WindowStyle.MEDICAMENTS_CHOOSER));
            
            if(medicamentChooser.getActionStyle() == ActionStyle.CONFIRMED)
            {
                Medicament selectedMedicament = medicamentChooser.getSelectedMedicament();
                selectedMedicament.setSelected(false);
                
                this.treatment.setMedicamentModifed(true);
                this.medicamentField.setText(selectedMedicament.getBrandName() + ", " + selectedMedicament.getGenericName() + ", " +
                        selectedMedicament.getDosages());
                this.treatment.setTemporalUnknownMedicament("");
                this.treatment.setTemporalMedicament(selectedMedicament);
            }
        });
        
        selectionBox.selectedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) ->
        {
            ((AppointmentsPane) Medikit.getController(WindowStyle.APPOINTMENTS)).selectTreatment(this.treatment, newValue);
            this.treatment.setSelected(newValue);
        });
    }
    
    public void onFieldsModify()
    {
        this.search.addEventHandler(ActionEvent.ACTION, (ActionEvent event) ->
        {
            this.treatment.setModified(true);
        });
        
        this.medicamentField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) ->
        {
            ((AppointmentsPane) Medikit.getController(WindowStyle.APPOINTMENTS)).setModified(true);
            this.treatment.getAppointment().setModified(true);
            this.treatment.setTemporalUnknownMedicament(newValue);
            this.treatment.setTemporalMedicament(null);
            this.treatment.setModified(true);
        });
        
        this.amountField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) ->
        {
            if(!newValue.matches("[0-9]*"))
                amountField.setText(oldValue);
            else
            {
                if(!newValue.isEmpty())
                {
                    ((AppointmentsPane) Medikit.getController(WindowStyle.APPOINTMENTS)).setModified(true);
                    this.treatment.getAppointment().setModified(true);
                    this.treatment.setModified(true);
                    this.treatment.setTemporalAmount(Integer.parseInt(newValue));
                    this.treatment.setModified(true);
                    this.treatment.setMedicamentModifed(true);
                }
            }
        });
        
        this.treatmentField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) ->
        {
            ((AppointmentsPane) Medikit.getController(WindowStyle.APPOINTMENTS)).setModified(true);
            this.treatment.getAppointment().setModified(true);
            this.treatment.setTemporalTreatment(newValue);
            this.treatment.setModified(true);
        });
    }
    
    public void select(boolean selected)
    {
        this.selectionBox.setSelected(selected);
    }

    public Treatment getTreatment()
    {
        return treatment;
    }

    public void setTreatment(Treatment treatment)
    {
        this.treatment = treatment;
    }
}