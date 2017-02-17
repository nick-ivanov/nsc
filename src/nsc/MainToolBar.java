package nsc;

import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;

public class MainToolBar extends ToolBar {
    private Button saveButton;

    public MainToolBar() {
        saveButton = new Button("\uD83D\uDCBE");
        this.getItems().add(saveButton);
    }
}
