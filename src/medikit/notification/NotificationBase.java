package medikit.notification;

import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public abstract class NotificationBase extends HBox {

    protected final Text text;
    protected final Text amount;
    protected final Text text1;

    public NotificationBase() {

        text = new Text();
        amount = new Text();
        text1 = new Text();

        setAlignment(javafx.geometry.Pos.BOTTOM_CENTER);
        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setSpacing(4.0);
        getStylesheets().add("/medikit/css/Notification.css");

        text.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text.setStrokeWidth(0.0);
        text.getStyleClass().add("text");
        text.setText("Tienes");
        HBox.setMargin(text, new Insets(0.0, 0.0, 0.0, 30.0));

        amount.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        amount.setStrokeWidth(0.0);
        amount.getStyleClass().add("amount");
        amount.setText("10000000000000000");

        text1.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text1.setStrokeWidth(0.0);
        text1.getStyleClass().add("text");
        text1.setText("notificaciones sin ver");
        setPadding(new Insets(20.0, 30.0, 20.0, 30.0));

        getChildren().add(text);
        getChildren().add(amount);
        getChildren().add(text1);

    }
}
