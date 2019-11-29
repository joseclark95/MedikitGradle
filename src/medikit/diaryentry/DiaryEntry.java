package medikit.diaryentry;

import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import medikit.Medikit;
import medikit.patient.Patient;

public class DiaryEntry
{
    private LocalDate date;
    private String time;
    private Patient patient;
    private int id;
    
    private static ObservableMap<Integer, DiaryEntry> loadedDiaryEntries = FXCollections.observableHashMap();
    private static int globalId;

    public DiaryEntry(int id, LocalDate date, String time, Patient patient)
    {
        this.date = date;
        this.time = time;
        this.patient = patient;
        this.id = id;
    }

    public DiaryEntry(LocalDate date, String time, Patient patient)
    {
        this.date = date;
        this.time = time;
        this.patient = patient;
        this.id = -1;
    }
    
    public static void update(DiaryEntry oldDiaryEntry, DiaryEntry newDiaryEntry)
    {
        String query = String.format("UPDATE Diary "
                + "SET idPatient = %d, [date] = '%s', time = '%s' "
                + "WHERE id = %d;", (newDiaryEntry.getPatient() == null) ? null : newDiaryEntry.getPatient().getId(), 
                newDiaryEntry.getDate().toString(), newDiaryEntry.getTime(), oldDiaryEntry.getId());
        Medikit.execute(query);
        
        oldDiaryEntry.patient = newDiaryEntry.getPatient();
        oldDiaryEntry.date = newDiaryEntry.getDate();
        oldDiaryEntry.time = newDiaryEntry.getTime();
    }

    public LocalDate getDate()
    {
        return date;
    }

    public void setDate(LocalDate date)
    {
        this.date = date;
    }

    public String getTime()
    {
        return time;
    }

    public void setTime(String time)
    {
        this.time = time;
    }

    public Patient getPatient()
    {
        return patient;
    }

    public void setPatient(Patient patient)
    {
        this.patient = patient;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public static ObservableMap<Integer, DiaryEntry> getLoadedDiaryEntries()
    {
        return loadedDiaryEntries;
    }

    public static int getGlobalId()
    {
        return globalId;
    }

    public static void setGlobalId(int globalId)
    {
        DiaryEntry.globalId = globalId;
    }
    
    public static int nextId()
    {
        return ++globalId;
    }
}