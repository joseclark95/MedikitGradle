package medikit.ctrl;

import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import medikit.controller.Controller;
import medikit.misc.WindowStyle;
import medikit.Medikit;
import medikit.notification.Notification;

public class Main implements Controller
{
    private ColorAdjust colorAdjust = new ColorAdjust()
    {{
            setBrightness(-0.5);
    }};
    
    @FXML private ToggleButton notificationsToggle;
    @FXML private ToggleGroup navigationGroup;
    @FXML private ToggleButton patientsToggle;
    @FXML private ToggleButton inventoryToggle;
    @FXML private ToggleButton statisticsToggle;
    @FXML private StackPane titleIcon;
    @FXML private Text title;
    @FXML private StackPane contentPane;
    @FXML private GridPane root;

    public void initialize()
    {
        initNavigationToggles();
        this.setCurrentPane(WindowStyle.SHORTCUTS);
        
        navigationGroup.selectedToggleProperty().addListener(
                (ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) ->
            this.setCurrentPane(((newValue == null) ? WindowStyle.SHORTCUTS : (WindowStyle) newValue.getUserData())));
        
        root.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) ->
            root.requestFocus());
    }
    
    public void initController(int notificationAmount)
    {
        if(notificationAmount > 0)
            showNotificationTooltip(notificationAmount);
    }
    
    public void setCurrentPane(WindowStyle windowStyle)
    {
        switch(windowStyle)
        {
            case SHORTCUTS:
            {
                setContent(Medikit.getPane(windowStyle));
                title.setText("Atajos generales");
                titleIcon.setId("medikit-title-icon");
                break;
            }
            case PATIENTS:
            {
                PatientsPane patientsPane = (PatientsPane) Medikit.getController(windowStyle);
                patientsPane.initController();
                
                setContent(Medikit.getPane(windowStyle));
                title.setText("Pacientes");
                titleIcon.setId("patients-title-icon");
                break;
            }
            case INVENTORY:
            {
                InventoryPane inventoryPane = (InventoryPane) Medikit.getController(windowStyle);
                inventoryPane.initController();
                
                setContent(Medikit.getPane(windowStyle));
                title.setText("Inventario");
                titleIcon.setId("inventory-title-icon");
                break;
            }
            case STATISTICS:
            {
                StatisticsPane statisticsPane = (StatisticsPane) Medikit.getController(windowStyle);
                statisticsPane.initController();
                
                setContent(Medikit.getPane(windowStyle));
                title.setText("Estadísticas");
                titleIcon.setId("statistics-title-icon");
                break;
            }
            case NOTIFICATIONS:
            {
                NotificationsPane notificationsPane = (NotificationsPane) Medikit.getController(windowStyle);
                notificationsPane.initController();
                
                setContent(Medikit.getPane(windowStyle));
                title.setText("Notificaciones");
                titleIcon.setId("notifications-title-icon");
                break;
            }
        }
    }
    
    public void setContent(Region content)
    {
        ObservableList<Node> children = contentPane.getChildren();
        children.clear();
        children.add(content);
    }
    
    private void initNavigationToggles()
    {
        notificationsToggle.setUserData(WindowStyle.NOTIFICATIONS);
        patientsToggle.setUserData(WindowStyle.PATIENTS);
        inventoryToggle.setUserData(WindowStyle.INVENTORY);
        statisticsToggle.setUserData(WindowStyle.STATISTICS);
    }
    
    public void showPopup(Stage popup)
    {
        root.setEffect(colorAdjust);
        popup.showAndWait();
        root.setEffect(null);
    }
    
    private void showNotificationTooltip(int amount)
    {
        Bounds localBounds = notificationsToggle.getBoundsInLocal(), screenBounds = notificationsToggle.localToScene(localBounds);
        Tooltip tooltip = new Tooltip();
        Notification notification = new Notification(amount);
        
        tooltip.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        tooltip.setGraphic(notification);
        tooltip.setStyle("-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.4), 10, 0.2, 1, 3); " +
            "-fx-shape: \"M184.5,4.5v48c0,2.5-2,4.5-4.5,4.5c0,0,0,0,0,0H20c-2.5,0-4.5-2-4.5-4.5V30.6L0,18.5L15.5,6.3V4.5C15.5,2,17.5,0,20,0 " +
            "c0,0,0,0,0,0h160C182.5,0,184.5,2,184.5,4.5z\"; -fx-background-color: darkgray;");
        tooltip.setY(screenBounds.getMinY() + (screenBounds.getHeight() / 2));
        tooltip.setX(screenBounds.getMinX() + screenBounds.getWidth());
        tooltip.show(Medikit.getWindow(WindowStyle.MAIN));
        tooltip.setAutoHide(true);
    }
    
    public void selectToggle(WindowStyle windowStyle)
    {
        switch(windowStyle)
        {
            case SHORTCUTS:
            {
                navigationGroup.selectToggle(null);
                break;
            }
            case PATIENTS:
            {
                navigationGroup.selectToggle(patientsToggle);
                break;
            }
            case INVENTORY:
            {
                navigationGroup.selectToggle(inventoryToggle);
                break;
            }
            case STATISTICS:
            {
                navigationGroup.selectToggle(statisticsToggle);
                break;
            }
            case NOTIFICATIONS:
            {
                navigationGroup.selectToggle(notificationsToggle);
                break;
            }
        }
    }
}