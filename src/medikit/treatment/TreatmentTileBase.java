package medikit.treatment;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public abstract class TreatmentTileBase extends Button {

    protected final HBox hBox;
    protected final VBox vBox;
    protected final HBox hBox0;
    protected final TextField medicamentField;
    protected final Button search;
    protected final StackPane stackPane;
    protected final Separator separator;
    protected final TextField amountField;
    protected final TextField treatmentField;
    protected final CheckBox selectionBox;

    public TreatmentTileBase() {

        hBox = new HBox();
        vBox = new VBox();
        hBox0 = new HBox();
        medicamentField = new TextField();
        search = new Button();
        stackPane = new StackPane();
        separator = new Separator();
        amountField = new TextField();
        treatmentField = new TextField();
        selectionBox = new CheckBox();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setMnemonicParsing(false);
        setPrefHeight(102.0);
        setPrefWidth(420.0);
        getStyleClass().add("treatment-tile");
        getStylesheets().add("/medikit/css/AppointmentPane.css");

        hBox.setAlignment(javafx.geometry.Pos.CENTER);
        hBox.setMaxHeight(USE_PREF_SIZE);
        hBox.setMaxWidth(USE_PREF_SIZE);
        hBox.setMinHeight(USE_PREF_SIZE);
        hBox.setMinWidth(USE_PREF_SIZE);
        hBox.setPrefHeight(102.0);
        hBox.setPrefWidth(420.0);

        HBox.setHgrow(vBox, javafx.scene.layout.Priority.ALWAYS);
        vBox.setMaxHeight(USE_PREF_SIZE);
        vBox.setMaxWidth(USE_PREF_SIZE);
        vBox.setMinHeight(USE_PREF_SIZE);
        vBox.setMinWidth(USE_PREF_SIZE);
        vBox.setPrefHeight(102.0);
        vBox.setPrefWidth(390.0);
        vBox.setSpacing(20.0);

        hBox0.setAlignment(javafx.geometry.Pos.CENTER);
        hBox0.setMaxHeight(USE_PREF_SIZE);
        hBox0.setMaxWidth(Double.MAX_VALUE);
        hBox0.setMinHeight(USE_PREF_SIZE);
        hBox0.setMinWidth(USE_PREF_SIZE);

        HBox.setHgrow(medicamentField, javafx.scene.layout.Priority.ALWAYS);
        medicamentField.setMaxHeight(USE_PREF_SIZE);
        medicamentField.setMinHeight(USE_PREF_SIZE);
        medicamentField.setMinWidth(USE_PREF_SIZE);
        medicamentField.setPrefHeight(30.0);
        medicamentField.setPromptText("Buscar o ingresar medicamento");

        search.setAlignment(javafx.geometry.Pos.CENTER);
        search.setContentDisplay(javafx.scene.control.ContentDisplay.GRAPHIC_ONLY);
        search.setMaxHeight(USE_PREF_SIZE);
        search.setMaxWidth(USE_PREF_SIZE);
        search.setMinHeight(USE_PREF_SIZE);
        search.setMinWidth(USE_PREF_SIZE);
        search.setMnemonicParsing(false);
        search.setPrefHeight(30.0);
        search.setPrefWidth(30.0);
        search.getStyleClass().add("search-treatment-button");

        stackPane.setMaxHeight(USE_PREF_SIZE);
        stackPane.setMaxWidth(USE_PREF_SIZE);
        stackPane.setMinHeight(USE_PREF_SIZE);
        stackPane.setMinWidth(USE_PREF_SIZE);
        stackPane.setPrefHeight(18.0);
        stackPane.setPrefWidth(18.0);
        stackPane.getStyleClass().add("search-treatment-icon");
        search.setGraphic(stackPane);

        separator.setMaxHeight(USE_PREF_SIZE);
        separator.setMaxWidth(USE_PREF_SIZE);
        separator.setMinHeight(USE_PREF_SIZE);
        separator.setMinWidth(USE_PREF_SIZE);
        separator.setOrientation(javafx.geometry.Orientation.VERTICAL);
        separator.setPrefHeight(30.0);
        HBox.setMargin(separator, new Insets(0.0, 0.0, 0.0, 10.0));

        amountField.setMaxHeight(USE_PREF_SIZE);
        amountField.setMinHeight(USE_PREF_SIZE);
        amountField.setMinWidth(USE_PREF_SIZE);
        amountField.setPrefHeight(30.0);
        amountField.setPromptText("Cantidad");
        HBox.setMargin(amountField, new Insets(0.0, 0.0, 0.0, 10.0));

        treatmentField.setMaxHeight(USE_PREF_SIZE);
        treatmentField.setMaxWidth(Double.MAX_VALUE);
        treatmentField.setMinHeight(USE_PREF_SIZE);
        treatmentField.setMinWidth(USE_PREF_SIZE);
        treatmentField.setPrefHeight(30.0);
        treatmentField.setPromptText("Tratamiento");
        vBox.setPadding(new Insets(15.0));

        selectionBox.setMaxHeight(USE_PREF_SIZE);
        selectionBox.setMaxWidth(USE_PREF_SIZE);
        selectionBox.setMinHeight(USE_PREF_SIZE);
        selectionBox.setMinWidth(USE_PREF_SIZE);
        selectionBox.setMnemonicParsing(false);
        setGraphic(hBox);

        hBox0.getChildren().add(medicamentField);
        hBox0.getChildren().add(search);
        hBox0.getChildren().add(separator);
        hBox0.getChildren().add(amountField);
        vBox.getChildren().add(hBox0);
        vBox.getChildren().add(treatmentField);
        hBox.getChildren().add(vBox);
        hBox.getChildren().add(selectionBox);

    }
}
