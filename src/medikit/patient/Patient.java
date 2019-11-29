package medikit.patient;

import java.time.LocalDate;
import java.util.LinkedList;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import medikit.appointment.Appointment;
import medikit.Medikit;

public class Patient
{
    private StringProperty name = new SimpleStringProperty(""), background = new SimpleStringProperty(""), phone = new SimpleStringProperty(""),
            gender = new SimpleStringProperty(""), address = new SimpleStringProperty("");
    private ObjectProperty<LocalDate> birthDate = new SimpleObjectProperty<>(null); 
    private IntegerProperty id = new SimpleIntegerProperty();
    private DoubleProperty weight = new SimpleDoubleProperty(0);
    private final ObservableList<Appointment> appointmentsList = FXCollections.observableList(new LinkedList<>());
    private BooleanProperty modifed = new SimpleBooleanProperty(false), selected = new SimpleBooleanProperty(false);
    private PatientTile patientTile;
    
    private static ObservableMap<Integer, Patient> loadedPatients = FXCollections.observableHashMap();
    private static int globalId;
    
    public Patient(int id, String name, String background, double weight, LocalDate birthDate, String gender, String phone, String address)
    {
        this.id.set(id);
        this.name.set(name);
        this.background.set(background);
        this.phone.set(phone);
        this.address.set(address);
        this.gender.set(gender);
        this.birthDate.set(birthDate);
        this.weight.set(weight);
    }
    
    public Patient()
    {
        this.id.set(-1);
    }
    
    public static void update(Patient oldPatient, Patient newPatient)
    {
        String query = String.format("UPDATE Patient "
                + "SET name = '%s', background = '%s', weight = %f, birthDate = '%s', gender = '%s', address = '%s' "
                + "WHERE id = %d;", newPatient.getName(), newPatient.getBackground(), newPatient.getWeight(), newPatient.getBirthDate(), 
                newPatient.getGender(), newPatient.getAddress(), oldPatient.getId());
        Medikit.execute(query);
        
        oldPatient.name.set(newPatient.getName());
        oldPatient.background.set(newPatient.getBackground());
        oldPatient.weight.set(newPatient.getWeight());
        oldPatient.birthDate.set(newPatient.getBirthDate());
        oldPatient.gender.set(newPatient.getGender());
        oldPatient.address.set(newPatient.getAddress());
    }
    
    public void updateLabels()
    {
        if(patientTile != null)
            patientTile.updateLabels();
    }

    public final boolean isSelected()
    {
        return selected.get();
    }

    public final void setSelected(boolean value)
    {
        if(patientTile != null)
            patientTile.select(value);
        selected.set(value);
    }

    public void setPatientTile(PatientTile patientTile)
    {
        this.patientTile = patientTile;
    }

    public BooleanProperty selectedProperty()
    {
        return selected;
    }

    public final boolean isModifed()
    {
        return modifed.get();
    }

    public final void setModifed(boolean value)
    {
        modifed.set(value);
    }

    public BooleanProperty modifedProperty()
    {
        return modifed;
    }

    public ObservableList<Appointment> getAppointmentsList()
    {
        return appointmentsList;
    }

    public static ObservableMap<Integer, Patient> getLoadedPatients()
    {
        return loadedPatients;
    }
    
    public static int nextId()
    {
        return ++globalId;
    }

    public static void setGlobalId(int globalId)
    {
        Patient.globalId = globalId;
    }

    public final String getAddress()
    {
        return address.get();
    }

    public final void setAddress(String value)
    {
        address.set(value);
    }

    public StringProperty addressProperty()
    {
        return address;
    }

    public final String getName()
    {
        return name.get();
    }

    public final void setName(String value)
    {
        name.set(value);
    }

    public StringProperty nameProperty()
    {
        return name;
    }

    public final String getBackground()
    {
        return background.get();
    }

    public final void setBackground(String value)
    {
        background.set(value);
    }

    public StringProperty backgroundProperty()
    {
        return background;
    }

    public final String getPhone()
    {
        return phone.get();
    }

    public final void setPhone(String value)
    {
        phone.set(value);
    }

    public StringProperty phoneProperty()
    {
        return phone;
    }

    public final String getGender()
    {
        return gender.get();
    }

    public final void setGender(String value)
    {
        gender.set(value);
    }

    public StringProperty genderProperty()
    {
        return gender;
    }

    public final LocalDate getBirthDate()
    {
        return (LocalDate) birthDate.get();
    }

    public final void setBirthDate(LocalDate localDate)
    {
        birthDate.set(localDate);
    }

    public ObjectProperty<LocalDate> birthDateProperty()
    {
        return birthDate;
    }

    public final int getId()
    {
        return id.get();
    }

    public final void setId(int value)
    {
        id.set(value);
    }

    public IntegerProperty idProperty()
    {
        return id;
    }

    public final double getWeight()
    {
        return weight.get();
    }

    public final void setWeight(double value)
    {
        weight.set(value);
    }

    public DoubleProperty weightProperty()
    {
        return weight;
    }
}