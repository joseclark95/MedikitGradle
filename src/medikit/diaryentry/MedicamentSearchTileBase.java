package medikit.diaryentry;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public abstract class MedicamentSearchTileBase extends HBox {

    protected final VBox vBox;
    protected final Label brand;
    protected final Label generic;
    protected final Separator separator;
    protected final VBox vBox0;
    protected final Label form;
    protected final Label presentation;
    protected final Separator separator0;
    protected final VBox vBox1;
    protected final HBox hBox;
    protected final Label label3;
    protected final Label amount;
    protected final HBox hBox0;
    protected final Label label5;
    protected final Label dosages;

    public MedicamentSearchTileBase() {

        vBox = new VBox();
        brand = new Label();
        generic = new Label();
        separator = new Separator();
        vBox0 = new VBox();
        form = new Label();
        presentation = new Label();
        separator0 = new Separator();
        vBox1 = new VBox();
        hBox = new HBox();
        label3 = new Label();
        amount = new Label();
        hBox0 = new HBox();
        label5 = new Label();
        dosages = new Label();

        setAlignment(javafx.geometry.Pos.CENTER_LEFT);
        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(70.0);
        setPrefWidth(710.0);
        setSpacing(10.0);
        getStylesheets().add("/medikit/css/Lists.css");

        vBox.setAlignment(javafx.geometry.Pos.CENTER);
        vBox.setMaxHeight(USE_PREF_SIZE);
        vBox.setMaxWidth(USE_PREF_SIZE);
        vBox.setMinHeight(USE_PREF_SIZE);
        vBox.setMinWidth(USE_PREF_SIZE);

        brand.setAlignment(javafx.geometry.Pos.CENTER);
        brand.setContentDisplay(javafx.scene.control.ContentDisplay.CENTER);
        brand.setMaxHeight(USE_PREF_SIZE);
        brand.setMaxWidth(USE_PREF_SIZE);
        brand.setMinHeight(USE_PREF_SIZE);
        brand.setMinWidth(USE_PREF_SIZE);
        brand.setPrefHeight(20.0);
        brand.setPrefWidth(250.0);
        brand.getStyleClass().add("patient-search-label");
        brand.setText("Tempra");
        brand.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);

        generic.setAlignment(javafx.geometry.Pos.CENTER);
        generic.setContentDisplay(javafx.scene.control.ContentDisplay.CENTER);
        generic.setMaxHeight(USE_PREF_SIZE);
        generic.setMaxWidth(USE_PREF_SIZE);
        generic.setMinHeight(USE_PREF_SIZE);
        generic.setMinWidth(USE_PREF_SIZE);
        generic.setPrefHeight(20.0);
        generic.setPrefWidth(250.0);
        generic.getStyleClass().add("patient-search-label");
        generic.setText("Paracetamol");
        generic.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);

        separator.setMaxHeight(USE_PREF_SIZE);
        separator.setMaxWidth(USE_PREF_SIZE);
        separator.setMinHeight(USE_PREF_SIZE);
        separator.setMinWidth(USE_PREF_SIZE);
        separator.setOrientation(javafx.geometry.Orientation.VERTICAL);
        separator.setPrefHeight(40.0);

        vBox0.setAlignment(javafx.geometry.Pos.CENTER);
        vBox0.setMaxHeight(USE_PREF_SIZE);
        vBox0.setMaxWidth(USE_PREF_SIZE);
        vBox0.setMinHeight(USE_PREF_SIZE);
        vBox0.setMinWidth(USE_PREF_SIZE);

        form.setAlignment(javafx.geometry.Pos.CENTER);
        form.setContentDisplay(javafx.scene.control.ContentDisplay.CENTER);
        form.setMaxHeight(USE_PREF_SIZE);
        form.setMaxWidth(USE_PREF_SIZE);
        form.setMinHeight(USE_PREF_SIZE);
        form.setMinWidth(USE_PREF_SIZE);
        form.setPrefHeight(20.0);
        form.setPrefWidth(200.0);
        form.getStyleClass().add("patient-search-label");
        form.setText("Tabletas");
        form.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);

        presentation.setAlignment(javafx.geometry.Pos.CENTER);
        presentation.setContentDisplay(javafx.scene.control.ContentDisplay.CENTER);
        presentation.setMaxHeight(USE_PREF_SIZE);
        presentation.setMaxWidth(USE_PREF_SIZE);
        presentation.setMinHeight(USE_PREF_SIZE);
        presentation.setMinWidth(USE_PREF_SIZE);
        presentation.setPrefHeight(20.0);
        presentation.setPrefWidth(200.0);
        presentation.getStyleClass().add("patient-search-label");
        presentation.setText("Caja con 24 tabletas");
        presentation.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);

        separator0.setMaxHeight(USE_PREF_SIZE);
        separator0.setMaxWidth(USE_PREF_SIZE);
        separator0.setMinHeight(USE_PREF_SIZE);
        separator0.setMinWidth(USE_PREF_SIZE);
        separator0.setOrientation(javafx.geometry.Orientation.VERTICAL);
        separator0.setPrefHeight(40.0);

        vBox1.setAlignment(javafx.geometry.Pos.CENTER_LEFT);
        vBox1.setMaxHeight(USE_PREF_SIZE);
        vBox1.setMaxWidth(USE_PREF_SIZE);
        vBox1.setMinHeight(USE_PREF_SIZE);
        vBox1.setMinWidth(USE_PREF_SIZE);
        vBox1.setSpacing(5.0);

        hBox.setMaxHeight(USE_PREF_SIZE);
        hBox.setMaxWidth(USE_PREF_SIZE);
        hBox.setMinHeight(USE_PREF_SIZE);
        hBox.setMinWidth(USE_PREF_SIZE);
        hBox.setSpacing(5.0);

        label3.setAlignment(javafx.geometry.Pos.CENTER);
        label3.setContentDisplay(javafx.scene.control.ContentDisplay.CENTER);
        label3.setMaxHeight(USE_PREF_SIZE);
        label3.setMaxWidth(USE_PREF_SIZE);
        label3.setMinHeight(USE_PREF_SIZE);
        label3.setMinWidth(USE_PREF_SIZE);
        label3.setPrefHeight(20.0);
        label3.getStyleClass().add("patient-search-label");
        label3.setText("Cantidad:");
        label3.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);

        amount.setAlignment(javafx.geometry.Pos.CENTER);
        amount.setContentDisplay(javafx.scene.control.ContentDisplay.CENTER);
        amount.setMaxHeight(USE_PREF_SIZE);
        amount.setMaxWidth(USE_PREF_SIZE);
        amount.setMinHeight(USE_PREF_SIZE);
        amount.setMinWidth(USE_PREF_SIZE);
        amount.setPrefHeight(20.0);
        amount.getStyleClass().add("patient-search-label");
        amount.setText("5");
        amount.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);

        hBox0.setMaxHeight(USE_PREF_SIZE);
        hBox0.setMaxWidth(USE_PREF_SIZE);
        hBox0.setMinHeight(USE_PREF_SIZE);
        hBox0.setMinWidth(USE_PREF_SIZE);
        hBox0.setSpacing(5.0);

        label5.setAlignment(javafx.geometry.Pos.CENTER);
        label5.setContentDisplay(javafx.scene.control.ContentDisplay.CENTER);
        label5.setMaxHeight(USE_PREF_SIZE);
        label5.setMaxWidth(USE_PREF_SIZE);
        label5.setMinHeight(USE_PREF_SIZE);
        label5.setMinWidth(USE_PREF_SIZE);
        label5.setPrefHeight(20.0);
        label5.getStyleClass().add("patient-search-label");
        label5.setText("Dosis");
        label5.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);

        dosages.setAlignment(javafx.geometry.Pos.CENTER);
        dosages.setContentDisplay(javafx.scene.control.ContentDisplay.CENTER);
        dosages.setMaxHeight(USE_PREF_SIZE);
        dosages.setMaxWidth(USE_PREF_SIZE);
        dosages.setMinHeight(USE_PREF_SIZE);
        dosages.setMinWidth(USE_PREF_SIZE);
        dosages.setPrefHeight(20.0);
        dosages.getStyleClass().add("patient-search-label");
        dosages.setText("150 mg");
        dosages.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        setPadding(new Insets(0.0, 10.0, 0.0, 10.0));

        vBox.getChildren().add(brand);
        vBox.getChildren().add(generic);
        getChildren().add(vBox);
        getChildren().add(separator);
        vBox0.getChildren().add(form);
        vBox0.getChildren().add(presentation);
        getChildren().add(vBox0);
        getChildren().add(separator0);
        hBox.getChildren().add(label3);
        hBox.getChildren().add(amount);
        vBox1.getChildren().add(hBox);
        hBox0.getChildren().add(label5);
        hBox0.getChildren().add(dosages);
        vBox1.getChildren().add(hBox0);
        getChildren().add(vBox1);

    }
}
