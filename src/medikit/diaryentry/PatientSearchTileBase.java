package medikit.diaryentry;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public abstract class PatientSearchTileBase extends VBox {

    protected final Text name;
    protected final HBox hBox;
    protected final Text text0;
    protected final Text weight;
    protected final Separator separator;
    protected final Text text2;
    protected final Text birthDate;
    protected final Text text4;
    protected final Label background;

    public PatientSearchTileBase() {

        name = new Text();
        hBox = new HBox();
        text0 = new Text();
        weight = new Text();
        separator = new Separator();
        text2 = new Text();
        birthDate = new Text();
        text4 = new Text();
        background = new Label();

        setAlignment(javafx.geometry.Pos.CENTER);
        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(90.0);
        setPrefWidth(370.0);
        setSpacing(5.0);
        getStylesheets().add("/medikit/css/Lists.css");

        name.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        name.setStrokeWidth(0.0);
        name.getStyleClass().add("patient-search-title");
        name.setText("Oscar Arturo Clark Hernandez");
        name.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        name.setWrappingWidth(360.0);

        hBox.setAlignment(javafx.geometry.Pos.CENTER);
        hBox.setMaxHeight(USE_PREF_SIZE);
        hBox.setMaxWidth(Double.MAX_VALUE);
        hBox.setMinHeight(USE_PREF_SIZE);
        hBox.setMinWidth(USE_PREF_SIZE);
        hBox.setSpacing(5.0);

        text0.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text0.setStrokeWidth(0.0);
        text0.getStyleClass().add("patient-search-text");
        text0.setText("Peso:");

        weight.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        weight.setStrokeWidth(0.0);
        weight.getStyleClass().add("patient-search-title");
        weight.setText("100");

        separator.setMaxHeight(USE_PREF_SIZE);
        separator.setMaxWidth(USE_PREF_SIZE);
        separator.setMinHeight(USE_PREF_SIZE);
        separator.setMinWidth(USE_PREF_SIZE);
        separator.setOrientation(javafx.geometry.Orientation.VERTICAL);
        separator.setPrefHeight(15.0);

        text2.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text2.setStrokeWidth(0.0);
        text2.getStyleClass().add("patient-search-text");
        text2.setText("Fecha de nacimiento:");

        birthDate.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        birthDate.setStrokeWidth(0.0);
        birthDate.getStyleClass().add("patient-search-title");
        birthDate.setText("100");

        text4.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text4.setStrokeWidth(0.0);
        text4.getStyleClass().add("patient-search-text");
        text4.setText("Antecedentes:");
        text4.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        text4.setWrappingWidth(360.0);
        VBox.setMargin(text4, new Insets(0.0, 0.0, -5.0, 0.0));

        background.setAlignment(javafx.geometry.Pos.CENTER);
        background.setContentDisplay(javafx.scene.control.ContentDisplay.CENTER);
        background.setMaxHeight(USE_PREF_SIZE);
        background.setMaxWidth(USE_PREF_SIZE);
        background.setMinHeight(USE_PREF_SIZE);
        background.setMinWidth(USE_PREF_SIZE);
        background.setPrefHeight(15.0);
        background.setPrefWidth(360.0);
        background.getStyleClass().add("patient-search-label");
        background.setText("Ligma balls");
        background.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);

        getChildren().add(name);
        hBox.getChildren().add(text0);
        hBox.getChildren().add(weight);
        hBox.getChildren().add(separator);
        hBox.getChildren().add(text2);
        hBox.getChildren().add(birthDate);
        getChildren().add(hBox);
        getChildren().add(text4);
        getChildren().add(background);

    }
}
