package nsc;

import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MainToolBar extends ToolBar {
    private Button saveButton;

    public MainToolBar() {

        ImageView saveButtonImage = new ImageView(new Image("nsc/images/save_button.png"));
        saveButton = new Button();
        saveButton.setGraphic(saveButtonImage);
        this.getItems().add(saveButton);
    }
}
