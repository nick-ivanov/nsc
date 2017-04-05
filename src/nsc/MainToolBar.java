package nsc;

import javafx.print.PrinterJob;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class MainToolBar extends ToolBar {
    private Button saveButton;
    private Button clearButton;
    private Button printButton;
    private Button openButton;

    private CenterContainer centerContainer;

    public MainToolBar(Stage stage, CenterContainer centerContainer) {
        this.centerContainer = centerContainer;

        ImageView openButtonImage = new ImageView(new Image("nsc/images/open_button.png"));
        openButton = new Button();
        openButton.setGraphic(openButtonImage);

        ImageView saveButtonImage = new ImageView(new Image("nsc/images/save_button.png"));
        saveButton = new Button();
        saveButton.setGraphic(saveButtonImage);

        ImageView printButtonImage = new ImageView(new Image("nsc/images/print_button.png"));
        printButton = new Button();
        printButton.setGraphic(printButtonImage);

        ImageView clearButtonImage = new ImageView(new Image("nsc/images/clear_button.png"));
        clearButton = new Button();
        clearButton.setGraphic(clearButtonImage);

        openButton.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            File selectedFile = fileChooser.showOpenDialog(null);

            if (selectedFile != null) {
                System.out.println("File selected: " + selectedFile.getName());
            } else {
                System.out.println("File selection cancelled.");
            }
        });

        // TODO: Fix this
        saveButton.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Resource File");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                    new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),
                    new FileChooser.ExtensionFilter("Audio Files", "*.wav", "*.mp3", "*.aac"),
                    new FileChooser.ExtensionFilter("All Files", "*.*"));
            File selectedFile = fileChooser.showOpenDialog(stage);
            if (selectedFile != null) {
                System.out.println("Length: " + selectedFile.length());
            }
        });


        printButton.setOnAction(event -> {
            PrinterJob job = PrinterJob.createPrinterJob();
            if (job != null && job.showPrintDialog(centerContainer.getScene().getWindow())){
                boolean success = job.printPage(centerContainer);
                if (success) {
                    job.endJob();
                }
            }
        });

        BitBox bitBox = new BitBox(centerContainer);

        clearButton.setOnAction(event -> {
            bitBox.clear();
            centerContainer.resetSpinners(bitBox.getMessage());
        });

        this.getItems().addAll(openButton, saveButton, printButton, clearButton, bitBox);
    }
}
