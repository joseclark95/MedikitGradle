package medikit.loader;

import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import medikit.controller.Controller;

public class Loader
{
    private static Controller controller;
    
    public static Region loadPane(String resource) throws Exception
    {
        FXMLLoader loader = new FXMLLoader(new URL(resource));
        Region pane = loader.load();
        controller = loader.getController();
        return pane;
    }

    public static Stage loadStage(String resource, Stage owner) throws Exception
    {
        FXMLLoader loader = new FXMLLoader(new URL(resource));
        Parent parent = loader.load();
        controller = loader.getController();
        
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene); 
        stage.initOwner(owner);
        return stage;
    }

    public static Controller getController()
    {
        return controller;
    }
}