package medikit.ctrl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import medikit.controller.Controller;
import medikit.misc.ActionStyle;
import medikit.Medikit;
import medikit.misc.WindowStyle;

public class Message2 implements Controller
{
    private ActionStyle actionStyle = ActionStyle.NULL;
    
    @FXML private StackPane icon;
    @FXML private Text title;
    @FXML private Text message;
    @FXML private Button defaultButton;

    @FXML
    public void initialize()
    {
        defaultButton.addEventHandler(ActionEvent.ACTION, (ActionEvent event) ->
        {
            actionStyle = ActionStyle.CONFIRMED;
            Medikit.getWindow(WindowStyle.MESSAGE2).hide();
        });
    }
    
    public void initController(String iconId, String title, String message, String defaultButton)
    {
        this.icon.setId(iconId);
        this.title.setText(title);
        this.message.setText(message);
        this.defaultButton.setText(defaultButton);
    }

    public ActionStyle getActionStyle()
    {
        return actionStyle;
    }
}