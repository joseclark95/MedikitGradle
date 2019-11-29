package medikit.appointment;

import java.time.LocalDate;
import java.util.LinkedList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import medikit.Medikit;
import medikit.patient.Patient;
import medikit.treatment.Treatment;

public class Appointment
{
    private final LinkedList<Treatment> treatmentsList = new LinkedList<>();
    
    private int id;
    private Patient patient;
    private LocalDate date;
    private String diagnostic, notes;
    private double price;
    private boolean selected, modified;
    
    private AppointmentTile appointmentTile;
    private String temporalDiagnostic = "", temporalNotes = "";
    private LocalDate temporalDate = LocalDate.now();
    private double temporalPrice = 0.0;
    
    private static ObservableMap<Integer, Appointment> loadedAppointments = FXCollections.observableHashMap();
    private static int globalId;
    
    public Appointment(int id, Patient patient, LocalDate date, String diagnostic, String notes, double price)
    {
        this.id = id;
        this.patient = patient;
        this.date = date;
        this.diagnostic = diagnostic;
        this.notes = notes;
        this.price = price;
        
        patient.getAppointmentsList().add(0, this);
        temporalDate = date;
        temporalDiagnostic = diagnostic;
        temporalNotes = notes;
        temporalPrice = price;
    }
    
    public Appointment(LocalDate date, String diagnostic, String notes, double price)
    {
        this.date = date;
        this.diagnostic = diagnostic;
        this.notes = notes;
        this.price = price;
    }
    
    public Appointment(Patient patient)
    {
        this.id = -1;
        this.patient = patient;
        this.date = LocalDate.now();
    }
    
    public static void update(Appointment oldAppointment, Appointment newAppointment)
    {
        String query = String.format("UPDATE Appointment "
                + "SET [date] = '%s', diagnostic = '%s', notes = '%s', price = %f "
                + "WHERE id = %d;", newAppointment.getDate().toString(), newAppointment.getDiagnostic(), newAppointment.getNotes(), 
                newAppointment.getPrice(), oldAppointment.getId());
        Medikit.execute(query);
        
        oldAppointment.date = newAppointment.getDate();
        oldAppointment.diagnostic = newAppointment.getDiagnostic();
        oldAppointment.notes = newAppointment.getNotes();
        oldAppointment.price = newAppointment.getPrice();
    }
    
    public void resetTemporalFields()
    {
        temporalDate = date;
        temporalDiagnostic = diagnostic;
        temporalNotes = notes;
        temporalPrice = price;
    }

    public LinkedList<Treatment> getTreatmentsList()
    {
        return treatmentsList;
    }

    public boolean isSelected()
    {
        return selected;
    }

    public String getTemporalDiagnostic()
    {
        return temporalDiagnostic;
    }

    public void setTemporalDiagnostic(String temporalDiagnostic)
    {
        this.temporalDiagnostic = temporalDiagnostic;
    }

    public String getTemporalNotes()
    {
        return temporalNotes;
    }

    public void setTemporalNotes(String temporalNotes)
    {
        this.temporalNotes = temporalNotes;
    }

    public LocalDate getTemporalDate()
    {
        return temporalDate;
    }

    public void setTemporalDate(LocalDate temporalDate)
    {
        this.temporalDate = temporalDate;
    }

    public double getTemporalPrice()
    {
        return temporalPrice;
    }

    public void setTemporalPrice(double temporalPrice)
    {
        this.temporalPrice = temporalPrice;
    }

    public final void setSelected(boolean value)
    {
        if(appointmentTile != null)
            appointmentTile.select(value);
        selected = value;
    }

    public static ObservableMap<Integer, Appointment> getLoadedAppointments()
    {
        return loadedAppointments;
    }

    public static void setGlobalId(int globalId)
    {
        Appointment.globalId = globalId;
    }
    
    public static int nextId()
    {
        return ++globalId;
    }

    public static int getGlobalId()
    {
        return globalId;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public Patient getPatient()
    {
        return patient;
    }

    public void setPatient(Patient patient)
    {
        this.patient = patient;
    }

    public LocalDate getDate()
    {
        return date;
    }

    public void setDate(LocalDate date)
    {
        this.date = date;
    }

    public String getDiagnostic()
    {
        return diagnostic;
    }

    public void setDiagnostic(String diagnostic)
    {
        this.diagnostic = diagnostic;
    }

    public String getNotes()
    {
        return notes;
    }

    public void setNotes(String notes)
    {
        this.notes = notes;
    }

    public double getPrice()
    {
        return price;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }

    public boolean isModified()
    {
        return modified;
    }

    public void setModified(boolean modified)
    {
        this.modified = modified;
    }

    public AppointmentTile getAppointmentTile()
    {
        return appointmentTile;
    }

    public void setAppointmentTile(AppointmentTile appointmentTile)
    {
        this.appointmentTile = appointmentTile;
    }

    @Override
    public String toString()
    {
        return "Appointment{" + "id=" + id + ", date=" + date + ", diagnostic=" + diagnostic + ", notes=" + notes + ", price=" + price + ", temporalDiagnostic=" + temporalDiagnostic + ", temporalNotes=" + temporalNotes + ", temporalDate=" + temporalDate + ", temporalPrice=" + temporalPrice + '}';
    }
}