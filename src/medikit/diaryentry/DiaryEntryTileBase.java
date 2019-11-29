package medikit.diaryentry;

import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public abstract class DiaryEntryTileBase extends HBox {

    protected final Text date;
    private final Separator separator;
    protected final Text time;

    public DiaryEntryTileBase() {

        date = new Text();
        separator = new Separator();
        time = new Text();

        setAlignment(javafx.geometry.Pos.CENTER);
        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(40.0);
        setPrefWidth(560.0);
        setSpacing(20.0);
        getStylesheets().add("/medikit/css/Lists.css");

        date.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        date.setStrokeWidth(0.0);
        date.getStyleClass().add("notification-text");
        date.setText("22 de Nov del 2018");
        date.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        date.setWrappingWidth(240.0);

        separator.setMaxHeight(USE_PREF_SIZE);
        separator.setMaxWidth(USE_PREF_SIZE);
        separator.setMinHeight(USE_PREF_SIZE);
        separator.setMinWidth(USE_PREF_SIZE);
        separator.setOrientation(javafx.geometry.Orientation.VERTICAL);
        separator.setPrefHeight(30.0);

        time.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        time.setStrokeWidth(0.0);
        time.getStyleClass().add("notification-text");
        time.setText("09:00");
        time.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        time.setWrappingWidth(240.0);

        getChildren().add(date);
        getChildren().add(separator);
        getChildren().add(time);

    }
}
