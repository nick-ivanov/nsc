package nsc;

import javafx.print.PrinterJob;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class MainToolBar extends ToolBar {
    private BitBox bitBox;

    public MainToolBar(Stage stage, CenterContainer centerContainer) {
        ImageView openButtonImage = new ImageView(new Image("nsc/images/open_button.png"));
        Button openButton = new Button();
        openButton.setGraphic(openButtonImage);

        ImageView saveButtonImage = new ImageView(new Image("nsc/images/save_button.png"));
        Button saveButton = new Button();
        saveButton.setGraphic(saveButtonImage);

        ImageView printButtonImage = new ImageView(new Image("nsc/images/print_button.png"));
        Button printButton = new Button();
        printButton.setGraphic(printButtonImage);

        ImageView clearButtonImage = new ImageView(new Image("nsc/images/clear_button.png"));
        Button clearButton = new Button();
        clearButton.setGraphic(clearButtonImage);

        bitBox = new BitBox(centerContainer);
        centerContainer.setBitBox(bitBox);

        openButton.setOnAction(event -> {
            centerContainer.openFileCeremony();
        });

        saveButton.setOnAction(event -> {
            centerContainer.saveFileCeremony();
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

        clearButton.setOnAction(event -> {
            bitBox.clear();
            centerContainer.resetSpinners(bitBox.getMessage());
        });

        this.getItems().addAll(openButton, saveButton, printButton, clearButton, bitBox);
    }

    public BitBox getBitBox() {
        return bitBox;
    }
}
