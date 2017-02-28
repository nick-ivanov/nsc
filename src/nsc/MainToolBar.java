package nsc;

import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MainToolBar extends ToolBar {
    private Button saveButton;
    private Button clearButton;

    public MainToolBar() {

        ImageView saveButtonImage = new ImageView(new Image("nsc/images/save_button.png"));
        saveButton = new Button();
        saveButton.setGraphic(saveButtonImage);

        ImageView clearButtonImage = new ImageView(new Image("nsc/images/clear_button.png"));
        clearButton = new Button();
        clearButton.setGraphic(clearButtonImage);


        BitBox bitBox = new BitBox();

        this.getItems().addAll(saveButton, clearButton, bitBox);
    }
}
