package medikit.patient;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public abstract class PatientTileBase extends TitledPane {

    protected final HBox hBox;
    protected final Separator separator;
    protected final Text patientName;
    protected final Separator separator0;
    protected final VBox vBox;
    protected final Text text0;
    protected final Text lastVisit;
    protected final Separator separator1;
    protected final Button saveButton;
    protected final StackPane stackPane;
    protected final StackPane savedIcon;
    protected final Separator separator2;
    protected final CheckBox selectionBox;
    protected final StackPane stackPane1;
    protected final GridPane gridPane;
    protected final ColumnConstraints columnConstraints;
    protected final ColumnConstraints columnConstraints0;
    protected final RowConstraints rowConstraints;
    protected final RowConstraints rowConstraints0;
    protected final VBox vBox0;
    protected final Text text2;
    protected final Text appointmentsAmount;
    protected final TextField nameField;
    protected final TextField weightField;
    protected final DatePicker birthDatePicker;
    protected final Text text4;
    protected final TextField genderField;
    protected final TextField phoneField;
    protected final TextField addressField;
    protected final TextArea backgroundField;
    protected final Label label;
    protected final StackPane stackPane2;

    public PatientTileBase() {

        hBox = new HBox();
        separator = new Separator();
        patientName = new Text();
        separator0 = new Separator();
        vBox = new VBox();
        text0 = new Text();
        lastVisit = new Text();
        separator1 = new Separator();
        saveButton = new Button();
        stackPane = new StackPane();
        savedIcon = new StackPane();
        separator2 = new Separator();
        selectionBox = new CheckBox();
        stackPane1 = new StackPane();
        gridPane = new GridPane();
        columnConstraints = new ColumnConstraints();
        columnConstraints0 = new ColumnConstraints();
        rowConstraints = new RowConstraints();
        rowConstraints0 = new RowConstraints();
        vBox0 = new VBox();
        text2 = new Text();
        appointmentsAmount = new Text();
        nameField = new TextField();
        weightField = new TextField();
        birthDatePicker = new DatePicker();
        text4 = new Text();
        genderField = new TextField();
        phoneField = new TextField();
        addressField = new TextField();
        backgroundField = new TextArea();
        label = new Label();
        stackPane2 = new StackPane();

        setExpanded(false);
        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefWidth(730.0);
        getStylesheets().add("/medikit/css/Lists.css");

        hBox.setAlignment(javafx.geometry.Pos.CENTER);
        hBox.setMaxHeight(USE_PREF_SIZE);
        hBox.setMaxWidth(Double.MAX_VALUE);
        hBox.setMinHeight(USE_PREF_SIZE);
        hBox.setMinWidth(USE_PREF_SIZE);
        hBox.setPrefHeight(50.0);

        separator.setMaxHeight(USE_PREF_SIZE);
        separator.setMaxWidth(USE_PREF_SIZE);
        separator.setMinHeight(USE_PREF_SIZE);
        separator.setMinWidth(USE_PREF_SIZE);
        separator.setOrientation(javafx.geometry.Orientation.VERTICAL);
        separator.setPrefHeight(50.0);
        HBox.setMargin(separator, new Insets(0.0, 0.0, 0.0, 20.0));

        patientName.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        patientName.setStrokeWidth(0.0);
        patientName.getStyleClass().add("tile-text");
        patientName.setText("Fernanda Fernanda Hernandez Monteverde");
        patientName.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        patientName.setWrappingWidth(260.0);
        HBox.setMargin(patientName, new Insets(0.0, 20.0, 0.0, 20.0));

        separator0.setMaxHeight(USE_PREF_SIZE);
        separator0.setMaxWidth(USE_PREF_SIZE);
        separator0.setMinHeight(USE_PREF_SIZE);
        separator0.setMinWidth(USE_PREF_SIZE);
        separator0.setOrientation(javafx.geometry.Orientation.VERTICAL);
        separator0.setPrefHeight(50.0);
        HBox.setMargin(separator0, new Insets(0.0));

        vBox.setAlignment(javafx.geometry.Pos.CENTER);
        vBox.setMaxHeight(USE_PREF_SIZE);
        vBox.setMaxWidth(USE_PREF_SIZE);
        vBox.setMinHeight(USE_PREF_SIZE);
        vBox.setMinWidth(USE_PREF_SIZE);
        vBox.setPrefWidth(100.0);
        vBox.setSpacing(5.0);

        text0.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text0.setStrokeWidth(0.0);
        text0.getStyleClass().add("tile-text");
        text0.setText("Ultima visita:");

        lastVisit.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        lastVisit.setStrokeWidth(0.0);
        lastVisit.getStyleClass().add("last-visit-text");
        lastVisit.setText("31 de Ago del 18");
        HBox.setMargin(vBox, new Insets(0.0, 20.0, 0.0, 20.0));
        vBox.setPadding(new Insets(5.0));

        separator1.setMaxHeight(USE_PREF_SIZE);
        separator1.setMaxWidth(USE_PREF_SIZE);
        separator1.setMinHeight(USE_PREF_SIZE);
        separator1.setMinWidth(USE_PREF_SIZE);
        separator1.setOrientation(javafx.geometry.Orientation.VERTICAL);
        separator1.setPrefHeight(50.0);

        saveButton.setContentDisplay(javafx.scene.control.ContentDisplay.TOP);
        saveButton.setMaxHeight(USE_PREF_SIZE);
        saveButton.setMaxWidth(USE_PREF_SIZE);
        saveButton.setMinHeight(USE_PREF_SIZE);
        saveButton.setMinWidth(USE_PREF_SIZE);
        saveButton.setMnemonicParsing(false);
        saveButton.setPrefHeight(45.0);
        saveButton.setPrefWidth(100.0);
        saveButton.getStyleClass().add("save-button");
        saveButton.setText("Guardar");
        saveButton.setDisable(true);

        stackPane.setMaxHeight(USE_PREF_SIZE);
        stackPane.setMaxWidth(USE_PREF_SIZE);
        stackPane.setMinHeight(USE_PREF_SIZE);
        stackPane.setMinWidth(USE_PREF_SIZE);
        stackPane.setPrefHeight(20.0);
        stackPane.setPrefWidth(20.0);
        stackPane.getStyleClass().add("save-icon-background");

        savedIcon.setMaxHeight(USE_PREF_SIZE);
        savedIcon.setMaxWidth(USE_PREF_SIZE);
        savedIcon.setMinHeight(USE_PREF_SIZE);
        savedIcon.setMinWidth(USE_PREF_SIZE);
        savedIcon.setPrefHeight(20.0);
        savedIcon.setPrefWidth(20.0);
        savedIcon.getStyleClass().add("saved-icon");
        saveButton.setGraphic(stackPane);
        HBox.setMargin(saveButton, new Insets(0.0, 20.0, 0.0, 20.0));

        separator2.setMaxHeight(USE_PREF_SIZE);
        separator2.setMaxWidth(USE_PREF_SIZE);
        separator2.setMinHeight(USE_PREF_SIZE);
        separator2.setMinWidth(USE_PREF_SIZE);
        separator2.setOrientation(javafx.geometry.Orientation.VERTICAL);
        separator2.setPrefHeight(50.0);

        selectionBox.setAlignment(javafx.geometry.Pos.CENTER);
        selectionBox.setContentDisplay(javafx.scene.control.ContentDisplay.CENTER);
        selectionBox.setMnemonicParsing(false);
        HBox.setMargin(selectionBox, new Insets(0.0, 0.0, 0.0, 20.0));
        setGraphic(hBox);

        stackPane1.setMaxHeight(USE_PREF_SIZE);
        stackPane1.setMaxWidth(USE_PREF_SIZE);
        stackPane1.setMinHeight(USE_PREF_SIZE);
        stackPane1.setMinWidth(USE_PREF_SIZE);

        gridPane.setAlignment(javafx.geometry.Pos.CENTER);
        gridPane.setHgap(20.0);
        gridPane.setMaxHeight(USE_PREF_SIZE);
        gridPane.setMaxWidth(USE_PREF_SIZE);
        gridPane.setMinHeight(USE_PREF_SIZE);
        gridPane.setMinWidth(USE_PREF_SIZE);
        gridPane.getStyleClass().add("patient-information-background");

        columnConstraints.setHalignment(javafx.geometry.HPos.CENTER);
        columnConstraints.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints.setMaxWidth(USE_PREF_SIZE);
        columnConstraints.setMinWidth(USE_PREF_SIZE);
        columnConstraints.setPercentWidth(50.0);
        columnConstraints.setPrefWidth(320.0);

        columnConstraints0.setHalignment(javafx.geometry.HPos.CENTER);
        columnConstraints0.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints0.setMaxWidth(USE_PREF_SIZE);
        columnConstraints0.setMinWidth(USE_PREF_SIZE);
        columnConstraints0.setPrefWidth(320.0);

        rowConstraints.setMaxHeight(USE_PREF_SIZE);
        rowConstraints.setMinHeight(USE_PREF_SIZE);
        rowConstraints.setPrefHeight(50.0);
        rowConstraints.setValignment(javafx.geometry.VPos.TOP);
        rowConstraints.setVgrow(javafx.scene.layout.Priority.ALWAYS);

        rowConstraints0.setMaxHeight(USE_PREF_SIZE);
        rowConstraints0.setMinHeight(USE_PREF_SIZE);
        rowConstraints0.setPrefHeight(300.0);
        rowConstraints0.setValignment(javafx.geometry.VPos.TOP);
        rowConstraints0.setVgrow(javafx.scene.layout.Priority.ALWAYS);

        GridPane.setRowIndex(vBox0, 1);
        vBox0.setAlignment(javafx.geometry.Pos.TOP_CENTER);
        vBox0.setMaxHeight(Double.MAX_VALUE);
        vBox0.setMaxWidth(Double.MAX_VALUE);
        vBox0.setMinHeight(USE_PREF_SIZE);
        vBox0.setMinWidth(USE_PREF_SIZE);
        vBox0.setPrefHeight(200.0);
        vBox0.setPrefWidth(100.0);
        vBox0.setSpacing(5.0);

        text2.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text2.setStrokeWidth(0.0);
        text2.getStyleClass().add("tile-text");
        text2.setText("Cantidad de consultas:");

        appointmentsAmount.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        appointmentsAmount.setStrokeWidth(0.0);
        appointmentsAmount.getStyleClass().add("appointments-amount");
        appointmentsAmount.setText("1000");

        nameField.setAlignment(javafx.geometry.Pos.CENTER);
        nameField.setMaxHeight(USE_PREF_SIZE);
        nameField.setMaxWidth(Double.MAX_VALUE);
        nameField.setMinHeight(USE_PREF_SIZE);
        nameField.setMinWidth(USE_PREF_SIZE);
        nameField.setPrefHeight(30.0);
        nameField.setPrefWidth(0.0);
        nameField.setPromptText("Nombre");
        VBox.setMargin(nameField, new Insets(0.0));

        weightField.setAlignment(javafx.geometry.Pos.CENTER);
        weightField.setMaxHeight(USE_PREF_SIZE);
        weightField.setMaxWidth(Double.MAX_VALUE);
        weightField.setMinHeight(USE_PREF_SIZE);
        weightField.setMinWidth(USE_PREF_SIZE);
        weightField.setPrefHeight(30.0);
        weightField.setPrefWidth(0.0);
        weightField.setPromptText("Peso");

        birthDatePicker.setMaxHeight(USE_PREF_SIZE);
        birthDatePicker.setMaxWidth(Double.MAX_VALUE);
        birthDatePicker.setMinHeight(USE_PREF_SIZE);
        birthDatePicker.setMinWidth(USE_PREF_SIZE);
        birthDatePicker.setPrefHeight(30.0);
        birthDatePicker.setPromptText("Fecha de nacimiento");

        text4.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text4.setStrokeWidth(0.0);
        text4.getStyleClass().add("opcional-message");
        text4.setText("OPCIONAL");
        VBox.setMargin(text4, new Insets(10.0, 0.0, 0.0, 0.0));

        genderField.setAlignment(javafx.geometry.Pos.CENTER);
        genderField.setMaxHeight(USE_PREF_SIZE);
        genderField.setMaxWidth(Double.MAX_VALUE);
        genderField.setMinHeight(USE_PREF_SIZE);
        genderField.setMinWidth(USE_PREF_SIZE);
        genderField.setPrefHeight(30.0);
        genderField.setPrefWidth(0.0);
        genderField.setPromptText("Genero");

        phoneField.setAlignment(javafx.geometry.Pos.CENTER);
        phoneField.setMaxHeight(USE_PREF_SIZE);
        phoneField.setMaxWidth(Double.MAX_VALUE);
        phoneField.setMinHeight(USE_PREF_SIZE);
        phoneField.setMinWidth(USE_PREF_SIZE);
        phoneField.setPrefHeight(30.0);
        phoneField.setPrefWidth(0.0);
        phoneField.setPromptText("Numero de Telefono");

        addressField.setAlignment(javafx.geometry.Pos.CENTER);
        addressField.setMaxHeight(USE_PREF_SIZE);
        addressField.setMaxWidth(Double.MAX_VALUE);
        addressField.setMinHeight(USE_PREF_SIZE);
        addressField.setMinWidth(USE_PREF_SIZE);
        addressField.setPrefHeight(30.0);
        addressField.setPrefWidth(0.0);
        addressField.setPromptText("Direccion");
        GridPane.setMargin(vBox0, new Insets(10.0));

        GridPane.setColumnIndex(backgroundField, 1);
        GridPane.setHalignment(backgroundField, javafx.geometry.HPos.CENTER);
        GridPane.setRowIndex(backgroundField, 1);
        GridPane.setValignment(backgroundField, javafx.geometry.VPos.CENTER);
        backgroundField.setMaxHeight(Double.MAX_VALUE);
        backgroundField.setMaxWidth(Double.MAX_VALUE);
        backgroundField.setMinHeight(USE_PREF_SIZE);
        backgroundField.setMinWidth(USE_PREF_SIZE);
        backgroundField.setPrefHeight(200.0);
        backgroundField.setPrefWidth(200.0);
        backgroundField.setPromptText("Antecedentes");
        backgroundField.setWrapText(true);
        GridPane.setMargin(backgroundField, new Insets(10.0));

        GridPane.setColumnSpan(label, 2);
        label.setAlignment(javafx.geometry.Pos.CENTER);
        label.setMaxHeight(USE_PREF_SIZE);
        label.setMaxWidth(Double.MAX_VALUE);
        label.setMinHeight(USE_PREF_SIZE);
        label.setMinWidth(USE_PREF_SIZE);
        label.setPrefHeight(40.0);
        label.setPrefWidth(200.0);
        label.getStyleClass().add("patient-information-title");
        label.setText("Informacion general");

        stackPane2.setMaxHeight(USE_PREF_SIZE);
        stackPane2.setMaxWidth(USE_PREF_SIZE);
        stackPane2.setMinHeight(USE_PREF_SIZE);
        stackPane2.setMinWidth(USE_PREF_SIZE);
        stackPane2.setPrefHeight(30.0);
        stackPane2.setPrefWidth(30.0);
        stackPane2.getStyleClass().add("patient-information-icon");
        label.setGraphic(stackPane2);
        GridPane.setMargin(label, new Insets(10.0));
        StackPane.setMargin(gridPane, new Insets(2.0, 0.0, 2.0, 0.0));
        stackPane1.setPadding(new Insets(2.0));
        setContent(stackPane1);

        hBox.getChildren().add(separator);
        hBox.getChildren().add(patientName);
        hBox.getChildren().add(separator0);
        vBox.getChildren().add(text0);
        vBox.getChildren().add(lastVisit);
        hBox.getChildren().add(vBox);
        hBox.getChildren().add(separator1);
        stackPane.getChildren().add(savedIcon);
        hBox.getChildren().add(saveButton);
        hBox.getChildren().add(separator2);
        hBox.getChildren().add(selectionBox);
        gridPane.getColumnConstraints().add(columnConstraints);
        gridPane.getColumnConstraints().add(columnConstraints0);
        gridPane.getRowConstraints().add(rowConstraints);
        gridPane.getRowConstraints().add(rowConstraints0);
        vBox0.getChildren().add(text2);
        vBox0.getChildren().add(appointmentsAmount);
        vBox0.getChildren().add(nameField);
        vBox0.getChildren().add(weightField);
        vBox0.getChildren().add(birthDatePicker);
        vBox0.getChildren().add(text4);
        vBox0.getChildren().add(genderField);
        vBox0.getChildren().add(phoneField);
        vBox0.getChildren().add(addressField);
        gridPane.getChildren().add(vBox0);
        gridPane.getChildren().add(backgroundField);
        gridPane.getChildren().add(label);
        stackPane1.getChildren().add(gridPane);

    }
}
