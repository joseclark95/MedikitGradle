package medikit.diaryentry;

import medikit.medicament.Medicament;

public class MedicamentSearchTile extends MedicamentSearchTileBase
{
    private Medicament medicament;

    public MedicamentSearchTile(Medicament medicament)
    {
        this.medicament = medicament;
        
        this.brand.setText(medicament.getBrandName());
        this.generic.setText(medicament.getGenericName());
        this.form.setText(medicament.getForm());
        this.presentation.setText(medicament.getPresentation());
        this.amount.setText("" + medicament.getAmount());
        this.dosages.setText(medicament.getDosages());
        
        this.amount.setStyle(this.amount.getStyle() + "-fx-text-fill: crimson;");
    }

    public Medicament getMedicament()
    {
        return medicament;
    }

    public void setMedicament(Medicament medicament)
    {
        this.medicament = medicament;
    }
}