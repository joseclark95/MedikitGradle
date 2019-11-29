package medikit.treatment;

import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import medikit.Medikit;
import medikit.appointment.Appointment;
import medikit.medicament.Medicament;

public class Treatment
{
    private int id;
    private Appointment appointment;
    private Medicament medicament = null;
    private String treatment, unknownMedicament;
    private int amount;
    private boolean modified, selected, medicamentModifed;
    
    private TreatmentTile treatmentTile;
    private Medicament temporalMedicament = null;
    private String temporalTreatment = "", temporalUnknownMedicament = "";
    private int temporalAmount = 0;
    
    private static ObservableMap<Integer, Treatment> loadedTreatments = FXCollections.observableHashMap();
    private static int globalId;

    public Treatment(int id, Appointment appointment, Medicament medicament, String treatment, int amount, String unknownMedicament)
    {
        this.id = id;
        this.appointment = appointment;
        this.medicament = medicament;
        this.treatment = treatment;
        this.amount = amount;
        this.unknownMedicament = unknownMedicament;
        
        appointment.getTreatmentsList().add(this);
        temporalAmount = amount;
        temporalMedicament = medicament;
        temporalTreatment = treatment;
        temporalUnknownMedicament = unknownMedicament;
    }

    public Treatment(Medicament medicament, String treatment, int amount, String unknownMedicament)
    {
        this.medicament = medicament;
        this.treatment = treatment;
        this.amount = amount;
        this.unknownMedicament = unknownMedicament;
    }

    public Treatment(Appointment appointment)
    {
        this.appointment = appointment;
        this.medicament = null;
        this.treatment = "";
        this.amount = 0;
        this.id = -1;
        this.unknownMedicament = "";
    }
    
    public static void update(Treatment oldTreatment, Treatment newTreatment)
    {
        String query = String.format("UPDATE Treatment "
                + "SET idMedicament = %d, treatment = '%s', amount = %d, unknownMedicament = '%s' "
                + "WHERE id = %d;", (newTreatment.getMedicament() == null ? null : newTreatment.getMedicament().getId()), 
                newTreatment.getTreatment(), newTreatment.getAmount(), 
                newTreatment.getUnknownMedicament(), oldTreatment.getId());
        Medikit.execute(query);
        
        oldTreatment.medicament = newTreatment.getMedicament();
        oldTreatment.treatment = newTreatment.getTreatment();
        oldTreatment.amount = newTreatment.getAmount();
        oldTreatment.unknownMedicament = newTreatment.getUnknownMedicament();
    }
    
    public void resetTemporalFields()
    {
        temporalAmount = amount;
        temporalMedicament = medicament;
        temporalTreatment = treatment;
        temporalUnknownMedicament = unknownMedicament;
    }

    public boolean isMedicamentModifed()
    {
        return medicamentModifed;
    }

    public void setMedicamentModifed(boolean medicamentModifed)
    {
        this.medicamentModifed = medicamentModifed;
    }

    public boolean isSelected()
    {
        return selected;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public Appointment getAppointment()
    {
        return appointment;
    }

    public void setAppointment(Appointment appointment)
    {
        this.appointment = appointment;
    }

    public Medicament getMedicament()
    {
        return medicament;
    }

    public void setMedicament(Medicament medicament)
    {
        this.medicament = medicament;
    }

    public String getTreatment()
    {
        return treatment;
    }

    public void setTreatment(String treatment)
    {
        this.treatment = treatment;
    }

    public String getUnknownMedicament()
    {
        return unknownMedicament;
    }

    public void setUnknownMedicament(String unknownMedicament)
    {
        this.unknownMedicament = unknownMedicament;
    }

    public int getAmount()
    {
        return amount;
    }

    public void setAmount(int amount)
    {
        this.amount = amount;
    }

    public boolean isModified()
    {
        return modified;
    }

    public void setModified(boolean modified)
    {
        this.modified = modified;
    }

    public Medicament getTemporalMedicament()
    {
        return temporalMedicament;
    }

    public void setTemporalMedicament(Medicament temporalMedicament)
    {
        this.temporalMedicament = temporalMedicament;
    }

    public String getTemporalTreatment()
    {
        return temporalTreatment;
    }

    public void setTemporalTreatment(String temporalTreatment)
    {
        this.temporalTreatment = temporalTreatment;
    }

    public String getTemporalUnknownMedicament()
    {
        return temporalUnknownMedicament;
    }

    public void setTemporalUnknownMedicament(String temporalUnknownMedicament)
    {
        this.temporalUnknownMedicament = temporalUnknownMedicament;
    }

    public int getTemporalAmount()
    {
        return temporalAmount;
    }

    public void setTemporalAmount(int temporalAmount)
    {
        this.temporalAmount = temporalAmount;
    }

    public static ObservableMap<Integer, Treatment> getLoadedTreatments()
    {
        return loadedTreatments;
    }

    public static int getGlobalId()
    {
        return globalId;
    }

    public static void setGlobalId(int globalId)
    {
        Treatment.globalId = globalId;
    }
    
    public static int nextId()
    {
        return ++globalId;
    }

    public TreatmentTile getTreatmentTile()
    {
        return treatmentTile;
    }

    public void setTreatmentTile(TreatmentTile treatmentTile)
    {
        this.treatmentTile = treatmentTile;
    }

    public final void setSelected(boolean value)
    {
        if(treatmentTile != null)
            treatmentTile.select(value);
        selected = value;
    }

    @Override
    public String toString()
    {
        return "Treatment{" + "id=" + id + ", treatment=" + treatment + ", unknownMedicament=" + unknownMedicament + ", amount=" + amount + '}';
    }
}