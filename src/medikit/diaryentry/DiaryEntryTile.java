package medikit.diaryentry;

import java.time.LocalDate;

public class DiaryEntryTile extends DiaryEntryTileBase
{
    private DiaryEntry diaryEntry;

    private static String monthName[] = {"null", "Ene", "Feb", "Mar", "Abr", "May", "Jun", "Jul", "Ago", "Sep", "Oct", "Nov", "Dec"};
    
    public DiaryEntryTile(DiaryEntry diaryEntry)
    {
        super();
        this.diaryEntry = diaryEntry;
        
        LocalDate date = diaryEntry.getDate();
        if(isToday(date))
            this.date.setText("Hoy");
        else
            this.date.setText(date.getDayOfMonth() + " de " + monthName[date.getMonthValue()] + " del " + date.getYear());
        this.time.setText(diaryEntry.getTime());
    }

    public DiaryEntry getDiaryEntry()
    {
        return diaryEntry;
    }

    public void setDiaryEntry(DiaryEntry diaryEntry)
    {
        this.diaryEntry = diaryEntry;
    }
    
    private static boolean isToday(LocalDate date)
    {
        LocalDate today = LocalDate.now();
        return (today.getYear() == date.getYear() && today.getMonthValue() == date.getMonthValue() && today.getDayOfYear() == date.getDayOfYear());
    }
}