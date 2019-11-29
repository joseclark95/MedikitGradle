package medikit.appointment;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public abstract class AppointmentTileBase extends Button {

    protected final HBox hBox;
    protected final DatePicker date;
    private final Separator separator;
    protected final TextField priceField;
    private final Separator separator0;
    protected final CheckBox selectionBox;

    public AppointmentTileBase() {

        hBox = new HBox();
        date = new DatePicker();
        separator = new Separator();
        priceField = new TextField();
        separator0 = new Separator();
        selectionBox = new CheckBox();

        setAlignment(javafx.geometry.Pos.CENTER);
        setContentDisplay(javafx.scene.control.ContentDisplay.GRAPHIC_ONLY);
        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setMnemonicParsing(false);
        setPrefHeight(62.0);
        setPrefWidth(336.0);
        getStyleClass().add("appointment-tile");
        getStylesheets().add("/medikit/css/AppointmentPane.css");

        hBox.setAlignment(javafx.geometry.Pos.CENTER);
        hBox.setMaxHeight(USE_PREF_SIZE);
        hBox.setMaxWidth(USE_PREF_SIZE);
        hBox.setMinHeight(USE_PREF_SIZE);
        hBox.setMinWidth(USE_PREF_SIZE);
        hBox.setPrefHeight(60.0);
        hBox.setPrefWidth(336.0);
        hBox.setSpacing(10.0);

        date.setMaxHeight(USE_PREF_SIZE);
        date.setMaxWidth(USE_PREF_SIZE);
        date.setMinHeight(USE_PREF_SIZE);
        date.setMinWidth(USE_PREF_SIZE);
        date.setPrefHeight(30.0);
        date.setPrefWidth(150.0);
        date.setPromptText("Fecha");

        separator.setMaxHeight(USE_PREF_SIZE);
        separator.setMinHeight(USE_PREF_SIZE);
        separator.setMinWidth(USE_PREF_SIZE);
        separator.setOrientation(javafx.geometry.Orientation.VERTICAL);
        separator.setPrefHeight(50.0);

        priceField.setAlignment(javafx.geometry.Pos.CENTER);
        priceField.setMaxHeight(USE_PREF_SIZE);
        priceField.setMaxWidth(USE_PREF_SIZE);
        priceField.setMinHeight(USE_PREF_SIZE);
        priceField.setMinWidth(USE_PREF_SIZE);
        priceField.setPrefHeight(30.0);
        priceField.setPrefWidth(70.0);
        priceField.setPromptText("Importe");

        separator0.setMaxHeight(USE_PREF_SIZE);
        separator0.setMinHeight(USE_PREF_SIZE);
        separator0.setMinWidth(USE_PREF_SIZE);
        separator0.setOrientation(javafx.geometry.Orientation.VERTICAL);
        separator0.setPrefHeight(50.0);

        selectionBox.setMnemonicParsing(false);
        setGraphic(hBox);

        hBox.getChildren().add(date);
        hBox.getChildren().add(separator);
        hBox.getChildren().add(priceField);
        hBox.getChildren().add(separator0);
        hBox.getChildren().add(selectionBox);
    }
}
