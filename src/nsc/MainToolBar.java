package nsc;

import javafx.print.PrinterJob;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

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
            FileChooser fileChooser = new FileChooser();
            File selectedFile = fileChooser.showOpenDialog(null);

            if (selectedFile != null) {
                System.out.println("File selected: " + selectedFile.getAbsolutePath());

                String s = "";

                try {
                    Scanner in = new Scanner(new FileReader(selectedFile.getAbsolutePath()));
                    StringBuilder sb = new StringBuilder();
                    while(in.hasNext()) {
                        sb.append(in.next());
                    }
                    in.close();
                    s = sb.toString();
                    in.close();
                } catch (Exception ex) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Can't open file.");
                    alert.setContentText(ex.getMessage());
                    alert.showAndWait();                }

                System.out.println("Result: " + isLegitFile(s));

                if(isLegitFile(s)) {
                    bitBox.setMessage(s);
                    centerContainer.resetSpinners(s);
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Can't open file.");
                    alert.setContentText("The file can't be open or the data is corrupt.");
                    alert.showAndWait();
                }
            } else {
                System.out.println("File selection cancelled.");
            }
        });

        saveButton.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            File selectedFile = fileChooser.showSaveDialog(null);

            if (selectedFile != null) {
                System.out.println("File selected: " + selectedFile.getAbsolutePath());
                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter(selectedFile.getAbsolutePath(), false));
                    writer.append(centerContainer.getMessage());
                    writer.close();
                } catch (Exception ex) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Can't save file.");
                    alert.setContentText(ex.getMessage());
                    alert.showAndWait();                }
            } else {
                System.out.println("File selection cancelled.");
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

        if(msg.length() > messageFieldMaxLength) {
            return false;
        }

        return true;
    }
}
