package medikit.medicament;

import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public abstract class InventorySearchTileBase extends TitledPane {

    protected final HBox hBox;
    protected final Separator separator;
    protected final VBox vBox;
    protected final Text text;
    protected final Text amount;
    protected final Separator separator0;
    protected final VBox vBox0;
    protected final Text brandName;
    protected final Text genericName;
    protected final Separator separator1;
    protected final VBox vBox1;
    protected final Text text3;
    protected final Text dosages;
    protected final Separator separator2;
    protected final CheckBox selectionBox;
    protected final StackPane stackPane;
    protected final GridPane gridPane;
    protected final ColumnConstraints columnConstraints;
    protected final ColumnConstraints columnConstraints0;
    protected final RowConstraints rowConstraints;
    protected final RowConstraints rowConstraints0;
    protected final RowConstraints rowConstraints1;
    protected final RowConstraints rowConstraints2;
    protected final VBox vBox2;
    protected final TextField amountField;
    protected final TextField brandField;
    protected final TextField genericField;
    private final Label label;
    private final StackPane stackPane0;
    private final VBox vBox3;
    protected final TextField priceField;
    protected final TextField dosagesField;
    protected final TextField formField;
    protected final TextField presentationField;

    public InventorySearchTileBase() {

        hBox = new HBox();
        separator = new Separator();
        vBox = new VBox();
        text = new Text();
        amount = new Text();
        separator0 = new Separator();
        vBox0 = new VBox();
        brandName = new Text();
        genericName = new Text();
        separator1 = new Separator();
        vBox1 = new VBox();
        text3 = new Text();
        dosages = new Text();
        separator2 = new Separator();
        selectionBox = new CheckBox();
        stackPane = new StackPane();
        gridPane = new GridPane();
        columnConstraints = new ColumnConstraints();
        columnConstraints0 = new ColumnConstraints();
        rowConstraints = new RowConstraints();
        rowConstraints0 = new RowConstraints();
        rowConstraints1 = new RowConstraints();
        rowConstraints2 = new RowConstraints();
        vBox2 = new VBox();
        amountField = new TextField();
        brandField = new TextField();
        genericField = new TextField();
        label = new Label();
        stackPane0 = new StackPane();
        vBox3 = new VBox();
        priceField = new TextField();
        dosagesField = new TextField();
        formField = new TextField();
        presentationField = new TextField();

        setExpanded(false);
        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefWidth(740.0);
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

        vBox.setAlignment(javafx.geometry.Pos.CENTER);
        vBox.setMaxHeight(USE_PREF_SIZE);
        vBox.setMaxWidth(USE_PREF_SIZE);
        vBox.setMinHeight(USE_PREF_SIZE);
        vBox.setMinWidth(USE_PREF_SIZE);
        vBox.setPrefWidth(100.0);
        vBox.setSpacing(5.0);

        text.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text.setStrokeWidth(0.0);
        text.getStyleClass().add("tile-text");
        text.setText("Cantidad:");

        amount.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        amount.setStrokeWidth(0.0);
        amount.getStyleClass().add("medicament-amount");
        amount.setText("5000");
        vBox.setPadding(new Insets(5.0));
        HBox.setMargin(vBox, new Insets(0.0, 10.0, 0.0, 10.0));

        separator0.setMaxHeight(USE_PREF_SIZE);
        separator0.setMaxWidth(USE_PREF_SIZE);
        separator0.setMinHeight(USE_PREF_SIZE);
        separator0.setMinWidth(USE_PREF_SIZE);
        separator0.setOrientation(javafx.geometry.Orientation.VERTICAL);
        separator0.setPrefHeight(50.0);
        HBox.setMargin(separator0, new Insets(0.0));

        vBox0.setAlignment(javafx.geometry.Pos.CENTER);
        vBox0.setMaxHeight(USE_PREF_SIZE);
        vBox0.setMaxWidth(USE_PREF_SIZE);
        vBox0.setMinHeight(USE_PREF_SIZE);
        vBox0.setMinWidth(USE_PREF_SIZE);
        vBox0.setPrefWidth(300.0);
        vBox0.setSpacing(5.0);

        brandName.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        brandName.setStrokeWidth(0.0);
        brandName.getStyleClass().add("tile-text");
        brandName.setText("Nombre comercial");
        brandName.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        brandName.setWrappingWidth(300.0);

        genericName.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        genericName.setStrokeWidth(0.0);
        genericName.getStyleClass().add("generic-name");
        genericName.setText("Nombre generico");
        genericName.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        genericName.setWrappingWidth(300.0);
        HBox.setMargin(vBox0, new Insets(0.0, 10.0, 0.0, 10.0));
        vBox0.setPadding(new Insets(5.0));

        separator1.setMaxHeight(USE_PREF_SIZE);
        separator1.setMaxWidth(USE_PREF_SIZE);
        separator1.setMinHeight(USE_PREF_SIZE);
        separator1.setMinWidth(USE_PREF_SIZE);
        separator1.setOrientation(javafx.geometry.Orientation.VERTICAL);
        separator1.setPrefHeight(50.0);

        vBox1.setAlignment(javafx.geometry.Pos.CENTER);
        vBox1.setMaxHeight(USE_PREF_SIZE);
        vBox1.setMaxWidth(USE_PREF_SIZE);
        vBox1.setMinHeight(USE_PREF_SIZE);
        vBox1.setMinWidth(USE_PREF_SIZE);
        vBox1.setPrefWidth(100.0);
        vBox1.setSpacing(5.0);

        text3.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text3.setStrokeWidth(0.0);
        text3.getStyleClass().add("tile-text");
        text3.setText("Dosis:");

        dosages.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        dosages.setStrokeWidth(0.0);
        dosages.getStyleClass().add("medicament-dosages");
        dosages.setText("5000");
        vBox1.setPadding(new Insets(5.0));
        HBox.setMargin(vBox1, new Insets(0.0, 10.0, 0.0, 10.0));

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

        stackPane.setMaxHeight(USE_PREF_SIZE);
        stackPane.setMaxWidth(USE_PREF_SIZE);
        stackPane.setMinHeight(USE_PREF_SIZE);
        stackPane.setMinWidth(USE_PREF_SIZE);

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
        rowConstraints0.setValignment(javafx.geometry.VPos.TOP);
        rowConstraints0.setVgrow(javafx.scene.layout.Priority.ALWAYS);

        rowConstraints1.setMaxHeight(USE_PREF_SIZE);
        rowConstraints1.setMinHeight(USE_PREF_SIZE);
        rowConstraints1.setValignment(javafx.geometry.VPos.TOP);
        rowConstraints1.setVgrow(javafx.scene.layout.Priority.ALWAYS);

        rowConstraints2.setMaxHeight(USE_PREF_SIZE);
        rowConstraints2.setMinHeight(USE_PREF_SIZE);
        rowConstraints2.setValignment(javafx.geometry.VPos.TOP);
        rowConstraints2.setVgrow(javafx.scene.layout.Priority.ALWAYS);

        GridPane.setRowIndex(vBox2, 1);
        vBox2.setAlignment(javafx.geometry.Pos.TOP_CENTER);
        vBox2.setMaxHeight(USE_PREF_SIZE);
        vBox2.setMaxWidth(Double.MAX_VALUE);
        vBox2.setMinHeight(USE_PREF_SIZE);
        vBox2.setMinWidth(USE_PREF_SIZE);
        vBox2.setSpacing(5.0);

        amountField.setAlignment(javafx.geometry.Pos.CENTER);
        amountField.setEditable(false);
        amountField.setMaxHeight(USE_PREF_SIZE);
        amountField.setMaxWidth(Double.MAX_VALUE);
        amountField.setMinHeight(USE_PREF_SIZE);
        amountField.setMinWidth(USE_PREF_SIZE);
        amountField.setPrefHeight(30.0);
        amountField.setPrefWidth(0.0);
        amountField.setPromptText("Cantidad disponible");

        brandField.setAlignment(javafx.geometry.Pos.CENTER);
        brandField.setEditable(false);
        brandField.setMaxHeight(USE_PREF_SIZE);
        brandField.setMaxWidth(Double.MAX_VALUE);
        brandField.setMinHeight(USE_PREF_SIZE);
        brandField.setMinWidth(USE_PREF_SIZE);
        brandField.setPrefHeight(30.0);
        brandField.setPrefWidth(0.0);
        brandField.setPromptText("Nombre comercial");
        VBox.setMargin(brandField, new Insets(0.0));

        genericField.setAlignment(javafx.geometry.Pos.CENTER);
        genericField.setEditable(false);
        genericField.setMaxHeight(USE_PREF_SIZE);
        genericField.setMaxWidth(Double.MAX_VALUE);
        genericField.setMinHeight(USE_PREF_SIZE);
        genericField.setMinWidth(USE_PREF_SIZE);
        genericField.setPrefHeight(30.0);
        genericField.setPrefWidth(0.0);
        genericField.setPromptText("Nombre generico");
        GridPane.setMargin(vBox2, new Insets(10.0));

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

        stackPane0.setMaxHeight(USE_PREF_SIZE);
        stackPane0.setMaxWidth(USE_PREF_SIZE);
        stackPane0.setMinHeight(USE_PREF_SIZE);
        stackPane0.setMinWidth(USE_PREF_SIZE);
        stackPane0.setPrefHeight(30.0);
        stackPane0.setPrefWidth(30.0);
        stackPane0.getStyleClass().add("patient-information-icon");
        label.setGraphic(stackPane0);
        GridPane.setMargin(label, new Insets(10.0));

        GridPane.setColumnIndex(vBox3, 1);
        GridPane.setRowIndex(vBox3, 1);
        vBox3.setAlignment(javafx.geometry.Pos.TOP_CENTER);
        vBox3.setMaxHeight(USE_PREF_SIZE);
        vBox3.setMaxWidth(Double.MAX_VALUE);
        vBox3.setMinHeight(USE_PREF_SIZE);
        vBox3.setMinWidth(USE_PREF_SIZE);
        vBox3.setSpacing(5.0);

        priceField.setAlignment(javafx.geometry.Pos.CENTER);
        priceField.setEditable(false);
        priceField.setMaxHeight(USE_PREF_SIZE);
        priceField.setMaxWidth(Double.MAX_VALUE);
        priceField.setMinHeight(USE_PREF_SIZE);
        priceField.setMinWidth(USE_PREF_SIZE);
        priceField.setPrefHeight(30.0);
        priceField.setPrefWidth(0.0);
        priceField.setPromptText("Precio");

        dosagesField.setAlignment(javafx.geometry.Pos.CENTER);
        dosagesField.setEditable(false);
        dosagesField.setMaxHeight(USE_PREF_SIZE);
        dosagesField.setMaxWidth(Double.MAX_VALUE);
        dosagesField.setMinHeight(USE_PREF_SIZE);
        dosagesField.setMinWidth(USE_PREF_SIZE);
        dosagesField.setPrefHeight(30.0);
        dosagesField.setPrefWidth(0.0);
        dosagesField.setPromptText("Dosis");

        formField.setAlignment(javafx.geometry.Pos.CENTER);
        formField.setEditable(false);
        formField.setMaxHeight(USE_PREF_SIZE);
        formField.setMaxWidth(Double.MAX_VALUE);
        formField.setMinHeight(USE_PREF_SIZE);
        formField.setMinWidth(USE_PREF_SIZE);
        formField.setPrefHeight(30.0);
        formField.setPrefWidth(0.0);
        formField.setPromptText("Forma farmaceutica");
        GridPane.setMargin(vBox3, new Insets(10.0));

        GridPane.setColumnSpan(presentationField, 2);
        GridPane.setRowIndex(presentationField, 3);
        presentationField.setAlignment(javafx.geometry.Pos.CENTER);
        presentationField.setEditable(false);
        presentationField.setMaxHeight(USE_PREF_SIZE);
        presentationField.setMaxWidth(USE_PREF_SIZE);
        presentationField.setMinHeight(USE_PREF_SIZE);
        presentationField.setMinWidth(USE_PREF_SIZE);
        presentationField.setPrefHeight(30.0);
        presentationField.setPrefWidth(300.0);
        presentationField.setPromptText("Presentacion");
        GridPane.setMargin(presentationField, new Insets(0.0, 0.0, 10.0, 0.0));
        StackPane.setMargin(gridPane, new Insets(2.0, 0.0, 2.0, 0.0));
        stackPane.setPadding(new Insets(2.0));
        setContent(stackPane);

        hBox.getChildren().add(separator);
        vBox.getChildren().add(text);
        vBox.getChildren().add(amount);
        hBox.getChildren().add(vBox);
        hBox.getChildren().add(separator0);
        vBox0.getChildren().add(brandName);
        vBox0.getChildren().add(genericName);
        hBox.getChildren().add(vBox0);
        hBox.getChildren().add(separator1);
        vBox1.getChildren().add(text3);
        vBox1.getChildren().add(dosages);
        hBox.getChildren().add(vBox1);
        hBox.getChildren().add(separator2);
        hBox.getChildren().add(selectionBox);
        gridPane.getColumnConstraints().add(columnConstraints);
        gridPane.getColumnConstraints().add(columnConstraints0);
        gridPane.getRowConstraints().add(rowConstraints);
        gridPane.getRowConstraints().add(rowConstraints0);
        gridPane.getRowConstraints().add(rowConstraints1);
        gridPane.getRowConstraints().add(rowConstraints2);
        vBox2.getChildren().add(amountField);
        vBox2.getChildren().add(brandField);
        vBox2.getChildren().add(genericField);
        gridPane.getChildren().add(vBox2);
        gridPane.getChildren().add(label);
        vBox3.getChildren().add(priceField);
        vBox3.getChildren().add(dosagesField);
        vBox3.getChildren().add(formField);
        gridPane.getChildren().add(vBox3);
        gridPane.getChildren().add(presentationField);
        stackPane.getChildren().add(gridPane);

    }
}
