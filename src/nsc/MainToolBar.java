package nsc;

import javafx.print.PrinterJob;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

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

        BitBox bitBox = new BitBox(centerContainer);

        openButton.setOnAction(event -> {
            centerContainer.openFileCeremony(bitBox);
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

    private boolean isLegitFile(String msg) {
        for(char ch : msg.toCharArray()) {
            if(ch != '1' && ch != '0') {
                return false;
            }
        }

        int messageFieldMaxLength = Integer.parseInt(NSCPropertyHelper.getProperty("message_field_max_length"));

        if (msg.length() > messageFieldMaxLength) {
            return false;
        }

        return true;
    }
}
