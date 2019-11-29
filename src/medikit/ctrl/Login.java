package medikit.ctrl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import medikit.controller.Controller;
import medikit.Medikit;
import medikit.misc.ActionStyle;
import medikit.misc.WindowStyle;

public class Login implements Controller
{
    private ActionStyle actionStyle = ActionStyle.NULL;
    
    @FXML private Button closeButton;
    @FXML private TextField userField;
    @FXML private PasswordField passwordField;
    @FXML private Text warningText;
    @FXML private Button warningCloseButton;
    @FXML private Button loginButton;
    @FXML private VBox root;
    @FXML private HBox warningPane;
    
    public void initialize()
    {
        loginButton.addEventHandler(ActionEvent.ACTION, (ActionEvent event) ->
        {
            String user = userField.getText(), password = passwordField.getText();
            warningText.setText("");
            
            if(user.isEmpty() || password.isEmpty())
            {
                warningText.setText("Los campos de texto no pueden estar vacíos");
                warningPane.setVisible(true);
            }
            else
            {
                Medikit.setPassword(password);
                Medikit.setUser(user);
                if(!Medikit.connect())
                {
                    warningText.setText("El usuario o contraseña es incorrecto");
                    warningPane.setVisible(true);
                }
                else
                {
                    warningPane.setVisible(false);
                    Medikit.getWindow(WindowStyle.LOGIN).close();
                }
            }
        });
        
        closeButton.addEventHandler(ActionEvent.ACTION, (ActionEvent event) ->
            System.exit(0));
        
        warningCloseButton.addEventHandler(ActionEvent.ACTION, (ActionEvent event) ->
            warningPane.setVisible(false));
        
        root.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) ->
            root.requestFocus());
    }
    
    public void initController()
    {
        userField.requestFocus();
    }

    public ActionStyle getActionStyle()
    {
        return actionStyle;
    }
}