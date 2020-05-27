package medikit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableMap;
import javafx.scene.image.Image;
import javafx.scene.layout.Region;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import medikit.appointment.Appointment;
import medikit.controller.Controller;
import medikit.ctrl.*;
import medikit.diaryentry.DiaryEntry;
import medikit.loader.Loader;
import medikit.medicament.Medicament;
import medikit.misc.ActionStyle;
import medikit.misc.WindowStyle;
import medikit.patient.Patient;
import medikit.treatment.Treatment;

public class Medikit extends Application
{
    private static Stage mainStage, loginStage, message1Stage, message2Stage, appointmentsStage, medicamentChooserStage, 
            addDiaryEntryStage;
    private static Region shortcutsPane, patientsPane, inventoryPane, statisticsPane, notificationsPane;
    
    private static Login loginCtrl;
    private static Main mainCtrl;
    private static Shortcuts shortcutsCtrl;
    private static PatientsPane patientsPaneCtrl;
    private static Message1 message1Ctrl;
    private static Message2 message2Ctrl;
    private static AppointmentsPane appointmentsPaneCtrl;
    private static InventoryPane inventoryPaneCtrl;
    private static MedicamentChooser medicamentChooserCtrl;
    private static StatisticsPane statisticsPaneCtrl;
    private static NotificationsPane notificationsPaneCtrl;
    private static AddDiaryEntry addDiaryEntryCtrl;
    
    private static Connection connection;
    private static String hostIp = "localhost",
            dataBase = "Medikit",
            user = "",
            password = "";
    
    private static int notificationAmount = 0;
    
    public static void main(String[] args)
    {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception
    {
        Image image = new Image(this.getClass().getResourceAsStream("/Medikit.png"));
        
        shortcutsPane = Loader.loadPane(this.getClass().getResource("/medikit/xml/Shortcuts.fxml").toString());
        shortcutsCtrl = (Shortcuts) Loader.getController();
        
        patientsPane = Loader.loadPane(this.getClass().getResource("/medikit/xml/PatientsPane.fxml").toString());
        patientsPaneCtrl = (PatientsPane) Loader.getController();
        
        inventoryPane = Loader.loadPane(this.getClass().getResource("/medikit/xml/InventoryPane.fxml").toString());
        inventoryPaneCtrl = (InventoryPane) Loader.getController();
        
        statisticsPane = Loader.loadPane(this.getClass().getResource("/medikit/xml/StatisticsPane.fxml").toString());
        statisticsPaneCtrl = (StatisticsPane) Loader.getController();
        
        notificationsPane = Loader.loadPane(this.getClass().getResource("/medikit/xml/NotificationsPane.fxml").toString());
        notificationsPaneCtrl = (NotificationsPane) Loader.getController();
        
        statisticsPane = Loader.loadPane(this.getClass().getResource("/medikit/xml/StatisticsPane.fxml").toString());
        statisticsPaneCtrl = (StatisticsPane) Loader.getController();
        
        message1Stage = Loader.loadStage(this.getClass().getResource("/medikit/xml/Message1.fxml").toString(), stage);
        message1Ctrl = (Message1) Loader.getController();
        message1Stage.initStyle(StageStyle.TRANSPARENT);
        message1Stage.initModality(Modality.APPLICATION_MODAL);
        message1Stage.getIcons().addAll(image);
        
        message2Stage = Loader.loadStage(this.getClass().getResource("/medikit/xml/Message2.fxml").toString(), stage);
        message2Ctrl = (Message2) Loader.getController();
        message2Stage.initStyle(StageStyle.TRANSPARENT);
        message2Stage.initModality(Modality.APPLICATION_MODAL);
        message2Stage.getIcons().addAll(image);
        
        medicamentChooserStage = Loader.loadStage(this.getClass().getResource("/medikit/xml/MedicamentChooser.fxml").toString(), stage);
        medicamentChooserCtrl = (MedicamentChooser) Loader.getController();
        medicamentChooserStage.initStyle(StageStyle.TRANSPARENT);
        medicamentChooserStage.initModality(Modality.APPLICATION_MODAL);
        medicamentChooserStage.getIcons().addAll(image);
        
        appointmentsStage = Loader.loadStage(this.getClass().getResource("/medikit/xml/AppointmentsPane.fxml").toString(), stage);
        appointmentsPaneCtrl = (AppointmentsPane) Loader.getController();
        appointmentsStage.initStyle(StageStyle.TRANSPARENT);
        appointmentsStage.initModality(Modality.APPLICATION_MODAL);
        appointmentsStage.getIcons().addAll(image);
        
        addDiaryEntryStage = Loader.loadStage(this.getClass().getResource("/medikit/xml/AddDiaryEntry.fxml").toString(), stage);
        addDiaryEntryCtrl = (AddDiaryEntry) Loader.getController();
        addDiaryEntryStage.initStyle(StageStyle.TRANSPARENT);
        addDiaryEntryStage.initModality(Modality.APPLICATION_MODAL);
        addDiaryEntryStage.getIcons().addAll(image);
        
        mainStage = stage;
        mainStage = Loader.loadStage(this.getClass().getResource("/medikit/xml/Main.fxml").toString(), null);
        mainCtrl = (Main) Loader.getController();
        mainStage.setMinHeight(600);
        mainStage.setMinWidth(800);
        
        loginStage = Loader.loadStage(this.getClass().getResource("/medikit/xml/Login.fxml").toString(), stage);
        loginCtrl = (Login) Loader.getController();
        loginStage.initStyle(StageStyle.TRANSPARENT);
        loginCtrl.initController();
        loginStage.getIcons().addAll(image);
        loginStage.showAndWait();
        
        loadDatabase();
        mainStage.setTitle("Medikit - 1.1");
        mainStage.getIcons().addAll(image);
        mainStage.show();
        mainStage.setMaximized(true);
        mainCtrl.initController(notificationAmount);
    }
    
    public static Controller getController(WindowStyle windowStyle)
    {
        switch(windowStyle)
        {
            case MAIN: return mainCtrl;
            case LOGIN: return loginCtrl;
            case SHORTCUTS: return shortcutsCtrl;
            case PATIENTS: return patientsPaneCtrl;
            case MESSAGE1: return message1Ctrl;
            case MESSAGE2: return message2Ctrl;
            case APPOINTMENTS: return appointmentsPaneCtrl;
            case INVENTORY: return inventoryPaneCtrl;
            case MEDICAMENTS_CHOOSER: return medicamentChooserCtrl;
            case STATISTICS: return statisticsPaneCtrl;
            case NOTIFICATIONS: return notificationsPaneCtrl;
            case ADD_DIARY: return addDiaryEntryCtrl;
        }
        return null;
    }
    
    public static Region getPane(WindowStyle windowStyle)
    {
        switch(windowStyle)
        {
            case SHORTCUTS: return shortcutsPane;
            case PATIENTS: return patientsPane;
            case INVENTORY: return inventoryPane;
            case STATISTICS: return statisticsPane;
            case NOTIFICATIONS: return notificationsPane;
        }
        return null;
    }
    
    public static Stage getWindow(WindowStyle windowStyle)
    {
        switch(windowStyle)
        {
            case MAIN: return mainStage;
            case LOGIN: return loginStage;
            case MESSAGE1: return message1Stage;
            case MESSAGE2: return message2Stage;
            case APPOINTMENTS: return appointmentsStage;
            case MEDICAMENTS_CHOOSER: return medicamentChooserStage;
            case ADD_DIARY: return addDiaryEntryStage;
        }
        return null;
    }
    
    public static boolean connect()
    {
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(String.format(
                    "jdbc:sqlserver://%s:1433; databaseName = %s; user = %s; password = %s;", hostIp, dataBase, user, password));
            return true;
        } 
        catch(ClassNotFoundException classNotFoundException)
        {
			System.out.println("class not found");
            System.out.println(classNotFoundException.getMessage());
        }
        catch(SQLException sqlException)
        {
			System.out.println("sql exception");
            System.out.println(sqlException.getMessage());
        }
        return false;
    }
    
    private static void loadDatabase()
    {
        ObservableMap<Integer, Patient> loadedPatients = Patient.getLoadedPatients();
        ObservableMap<Integer, Appointment> loadedAppointments = Appointment.getLoadedAppointments();
        ObservableMap<Integer, Medicament> loadedMedicaments = Medicament.getLoadedMedicaments();
        ObservableMap<Integer, Treatment> loadedTreatments = Treatment.getLoadedTreatments();
        ObservableMap<Integer, DiaryEntry> loadedDiaryEntries = DiaryEntry.getLoadedDiaryEntries();
        String query;
        int counter = 0;
        try
        {
            /* Patients ------------------------------------------------------*/
            query = String.format("SELECT * FROM Patient;");
            ResultSet resultSet = connection.createStatement().executeQuery(query);
            
            while(resultSet.next())
            {
                Patient patient = new Patient(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getDouble(4), 
                        resultSet.getDate(5).toLocalDate(), resultSet.getString(6), resultSet.getString(7), resultSet.getString(8));
                int id = patient.getId();
                loadedPatients.put(id, patient);
                if(id > counter)
                    counter = id;
            }
            Patient.setGlobalId(counter);
            counter = 0;
            
            /* Medicaments ---------------------------------------------------*/
            query = String.format("SELECT * FROM Medicament;");
            resultSet = connection.createStatement().executeQuery(query);
            
            while(resultSet.next())
            {
                Medicament medicament = new Medicament(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4), 
                        resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), resultSet.getDouble(8));
                int id = medicament.getId();
                loadedMedicaments.put(id, medicament);
                if(id > counter)
                    counter = id;
                
                if(medicament.getAmount() < 6)
                    notificationAmount++;
            }
            Medicament.setGlobalId(counter);
            counter = 0;
            
            /* Appointments --------------------------------------------------*/
            query = String.format("SELECT * FROM Appointment ORDER BY [date] DESC;");
            resultSet = connection.createStatement().executeQuery(query);
            
            while(resultSet.next())
            {
                Appointment appointment = new Appointment(resultSet.getInt(1), loadedPatients.get(resultSet.getInt(2)), 
                        resultSet.getDate(3).toLocalDate(), resultSet.getString(4), resultSet.getString(5), resultSet.getDouble(6));
                int id = appointment.getId();
                loadedAppointments.put(id, appointment);
                if(id > counter)
                    counter = id;
            }
            Appointment.setGlobalId(counter);
            counter = 0;
            
            /* Treatments ----------------------------------------------------*/
            query = String.format("SELECT * FROM Treatment;");
            resultSet = connection.createStatement().executeQuery(query);
            
            while(resultSet.next())
            {
                Treatment treatment = new Treatment(resultSet.getInt(1), loadedAppointments.get(resultSet.getInt(2)), 
                    loadedMedicaments.get(resultSet.getInt(3)), resultSet.getString(4), resultSet.getInt(5), resultSet.getString(6));
                int id = treatment.getId();
                loadedTreatments.put(id, treatment);
                if(id > counter)
                    counter = id;
            }
            Treatment.setGlobalId(counter);
            counter = 0;
            
            /* DiaryEntries --------------------------------------------------*/
            query = String.format("SELECT * FROM Diary ORDER BY [date] ASC;");
            resultSet = connection.createStatement().executeQuery(query);
            
            while(resultSet.next())
            {
                DiaryEntry diaryEntry = new DiaryEntry(resultSet.getInt(1), resultSet.getDate(3).toLocalDate(), resultSet.getString(4),
                        loadedPatients.get(resultSet.getInt(2)));
                int id = diaryEntry.getId();
                loadedDiaryEntries.put(id, diaryEntry);
                if(id > counter)
                    counter = id;
                
                notificationAmount++;
            }
            DiaryEntry.setGlobalId(counter);
        } 
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
        
        onTransactionEvent();
    }
    
    private static void onTransactionEvent()
    {
        ObservableMap<Integer, Patient> loadedPatients = Patient.getLoadedPatients();
        ObservableMap<Integer, Appointment> loadedAppointments = Appointment.getLoadedAppointments();
        ObservableMap<Integer, Medicament> loadedMedicaments = Medicament.getLoadedMedicaments();
        ObservableMap<Integer, Treatment> loadedTreatments = Treatment.getLoadedTreatments();
        ObservableMap<Integer, DiaryEntry> loadedDiaryEntries = DiaryEntry.getLoadedDiaryEntries();
        
        loadedPatients.addListener(
                (MapChangeListener.Change<? extends Integer, ? extends Patient> change) ->
        {
            String query;
            try
            {
                if(change.wasAdded())
                {
                    Patient patient = change.getValueAdded();
                    query = String.format("INSERT INTO Patient "
                            + "VALUES (%d, '%s', '%s', %f, '%s', '%s', '%s', '%s');", patient.getId(), patient.getName(), patient.getBackground(), 
                            patient.getWeight(), patient.getBirthDate().toString(), patient.getGender(), patient.getPhone(), patient.getAddress());
                    execute(query);
                }
                else if(change.wasRemoved())
                {
                    Patient patient = change.getValueRemoved();
                    query = String.format("SELECT id FROM Appointment WHERE idPatient = %d;", patient.getId());
                    ResultSet resultSet = executeQuery(query);
                    
                    while(resultSet.next())
                        loadedAppointments.remove(resultSet.getInt(1));
                    
                    query = String.format("SELECT id FROM Diary WHERE idPatient = %d;", patient.getId());
                    resultSet = executeQuery(query);
                    
                    while(resultSet.next())
                        loadedDiaryEntries.remove(resultSet.getInt(1));
                    
                    query = String.format("DELETE FROM Patient WHERE id = %d;", patient.getId());
                    execute(query);
                }
            } 
            catch(SQLException e)
            {
            }
        });
        
        loadedMedicaments.addListener(
                (MapChangeListener.Change<? extends Integer, ? extends Medicament> change) ->
        {
            String query;
            try
            {
                if(change.wasAdded())
                {
                    Medicament medicament = change.getValueAdded();
                    query = String.format("INSERT INTO Medicament "
                            + "VALUES (%d, '%s', '%s', %d, '%s', '%s', '%s', %f);", medicament.getId(), medicament.getBrandName(), medicament.getGenericName(),
                            medicament.getAmount(), medicament.getForm(), medicament.getDosages(), medicament.getPresentation(), medicament.getPrice());
                    execute(query);
                }
                else if(change.wasRemoved())
                {
                    Medicament medicament = change.getValueRemoved();
                    query = String.format("SELECT Appointment.id "
                            + "FROM Appointment "
                            + "INNER JOIN Treatment "
                            + "ON Treatment.idAppointment = Appointment.id "
                            + "WHERE Treatment.idMedicament = %d;", medicament.getId());
                    ResultSet resultSet = executeQuery(query);
                    
                    while(resultSet.next())
                        loadedAppointments.remove(resultSet.getInt(1));
                    
                    query = String.format("DELETE FROM Medicament WHERE id = %d;", medicament.getId());
                    execute(query);
                }
            } 
            catch(SQLException e)
            {
            }
        });
        
        loadedAppointments.addListener(
                (MapChangeListener.Change<? extends Integer, ? extends Appointment> change) ->
        {
            String query;
            if(change.wasAdded())
            {
                Appointment appointment = change.getValueAdded();
                query = String.format("INSERT INTO Appointment "
                        + "VALUES (%d, %d, '%s', '%s', '%s', %f);", appointment.getId(), appointment.getPatient().getId(), 
                        appointment.getDate().toString(), appointment.getDiagnostic(), appointment.getNotes(), appointment.getPrice());
                execute(query);
            }
            else if(change.wasRemoved())
            {
                Appointment appointment = change.getValueRemoved();

                query = String.format("DELETE FROM Treatment WHERE idAppointment = %d;", appointment.getId());
                execute(query);

                query = String.format("DELETE FROM Appointment WHERE id = %d;", appointment.getId());
                execute(query);
            }
        });
        
        loadedTreatments.addListener(
                (MapChangeListener.Change<? extends Integer, ? extends Treatment> change) ->
        {
            String query;
            if(change.wasAdded())
            {
                Treatment treatment = change.getValueAdded();
                query = String.format("INSERT INTO Treatment "
                        + "VALUES (%d, %d, %d, '%s', %d, '%s');", treatment.getId(), treatment.getAppointment().getId(), 
                        (treatment.getMedicament() == null ? null : treatment.getMedicament().getId()), 
                        treatment.getTreatment(), treatment.getAmount(), treatment.getUnknownMedicament());
                execute(query);
            }
            else if(change.wasRemoved())
            {
                Treatment treatment = change.getValueRemoved();
                query = String.format("DELETE FROM Treatment WHERE id = %d;", treatment.getId());
                execute(query);
            }
        });
        
        loadedDiaryEntries.addListener(
                (MapChangeListener.Change<? extends Integer, ? extends DiaryEntry> change) ->
        {
            String query;
            if(change.wasAdded())
            {
                DiaryEntry diaryEntry = change.getValueAdded();
                Patient patient = diaryEntry.getPatient();
                
                query = String.format("INSERT INTO Diary "
                        + "VALUES (%d, %d, '%s', '%s');", diaryEntry.getId(), (patient == null) ? null : patient.getId(),
                        diaryEntry.getDate().toString(), diaryEntry.getTime());
                execute(query);
            }
            else if(change.wasRemoved())
            {
                DiaryEntry diaryEntry = change.getValueRemoved();
                query = String.format("DELETE FROM Diary WHERE id = %d;", diaryEntry.getId());
                execute(query);
            }
        });
    }
    
    public static void execute(String query)
    {
        try
        {
            connection.createStatement().execute(query);
        } 
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
            retryExecute(query);
        }
    }
    
    public static ResultSet executeQuery(String query)
    {
        try
        {
            return connection.createStatement().executeQuery(query);
        } 
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
            return retryExecuteQuery(query);
        }
    }
    
    public static void retryExecute(String query)
    {
        while(true)
        {
            message1Ctrl.initController("warning-icon", "Conexion fallida", "No se ha podido conectar con la base de datos."
                + "\nPorfavor reintente conectar o cierre la aplicacion", "Reintentar", "Salir");
            mainCtrl.showPopup(message1Stage);
            if(message1Ctrl.getActionStyle() == ActionStyle.CANCELED)
                System.exit(0);
            else if(message1Ctrl.getActionStyle() == ActionStyle.CONFIRMED)
            {
                try
                {
                    connect();
                    connection.createStatement().execute(query);
                    break;
                } 
                catch(SQLException exc)
                {
                }
            }
        }
    }
    
    public static ResultSet retryExecuteQuery(String query)
    {
        while(true)
        {
            message1Ctrl.initController("warning-icon", "Conexion fallida", "No se ha podido conectar con la base de datos."
                + "\nPorfavor reintente conectar o cierre la aplicacion", "Reintentar", "Salir");
            mainCtrl.showPopup(message1Stage);
            if(message1Ctrl.getActionStyle() == ActionStyle.CANCELED)
                System.exit(0);
            else if(message1Ctrl.getActionStyle() == ActionStyle.CONFIRMED)
            {
                try
                {
                    connect();
                    return connection.createStatement().executeQuery(query);
                } 
                catch(SQLException exc)
                {
                }
            }
        }
    }

    public static void setUser(String user)
    {
        Medikit.user = user;
    }

    public static void setPassword(String password)
    {
        Medikit.password = password;
    }
}