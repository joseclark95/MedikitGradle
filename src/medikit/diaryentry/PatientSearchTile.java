package medikit.diaryentry;

import medikit.patient.Patient;

public class PatientSearchTile extends PatientSearchTileBase
{
    private Patient patient;

    public PatientSearchTile(Patient patient)
    {
        super();
        this.patient = patient;
        
        this.name.setText(patient.getName());
        this.weight.setText("" + patient.getWeight());
        this.birthDate.setText(patient.getBirthDate().toString());
        this.background.setText(patient.getBackground());
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