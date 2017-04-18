/*
    Network Signal Coder -- a utility that converts binary
        sequences into standard network physical signals.

    Copyright (C) 2015-2017  Nick Ivanov <nnrowan@gmail.com>

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

package nsc;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import org.controlsfx.control.StatusBar;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

class CenterContainer extends VBox {
    private final double quantumWidth = 80;
    private final double quantumHeight = 80;
    private final Color quantumColor = Color.BLACK;
    private final double quantumThickness = 10;
    private int numberOfBits = 8;
    private int maximumNumberOfBits;
    private BitBox bitBox;

    private GridPane grid = new GridPane();

    private Label initial = new Label(NSCPropertyHelper.getProperty("bits_label"));
    private Spinner[] sp = new Spinner[numberOfBits];
    private Spinner prev = new Spinner(0, 1, 0);
    private String message;
    private String defaultMessage;
    private StatusBar statusBar;

    private int[] iMsg = new int[numberOfBits];

    private SignalQuantumImage[][] qim = new SignalQuantumImage[6][numberOfBits];

    private Label nrzl = new Label(NSCPropertyHelper.getProperty("nrz_l_label"));
    private Label nrzpolar = new Label(NSCPropertyHelper.getProperty("polar_nrz_l_label"));
    private Label nrzi = new Label(NSCPropertyHelper.getProperty("nrz_i_label"));
    private Label bipolar = new Label(NSCPropertyHelper.getProperty("bipolar_ami_label"));
    private Label manchester = new Label(NSCPropertyHelper.getProperty("manchester_label"));
    private Label dmanchester = new Label(NSCPropertyHelper.getProperty("dmanchester_label"));

    private ImageView inrzl = new ImageView(
                new Image(getClass().getResourceAsStream("images/inrzl_polarity.png")));
    private ImageView inrzpolar = new ImageView(
                new Image(getClass().getResourceAsStream("images/inrzpolar_polarity.png")));
    private ImageView inrzi = new ImageView(
                new Image(getClass().getResourceAsStream("images/inrzi_polarity.png")));
    private ImageView ibipolar = new ImageView(
                new Image(getClass().getResourceAsStream("images/ibipolar_polarity.png")));
    private ImageView imanchester = new ImageView(
                new Image(getClass().getResourceAsStream("images/imanchester_polarity.png")));
    private ImageView idmanchester = new ImageView(
                new Image(getClass().getResourceAsStream("images/idmanchester_polarity.png")));

    private SignalProcessor processor;

    CenterContainer(StatusBar statusBar) {
        super();
        this.statusBar = statusBar;
        int defaultBitLength = Integer.parseInt(NSCPropertyHelper.getProperty("default_bit_length"));
        maximumNumberOfBits = Integer.parseInt(NSCPropertyHelper.getProperty("message_field_max_length"));

        defaultMessage = "";
        for(int i = 0; i < defaultBitLength; i++) {
            defaultMessage += "0";
        }

        message = defaultMessage;
        reloadContainer();
    }

    public void setBitBox(BitBox bitBox) {
        this.bitBox = bitBox;
    }

    public void resetSpinners(String message) {
        int size = message.length();
        this.message = message;
        numberOfBits = size;
        grid = new GridPane();
        sp = new Spinner[numberOfBits];
        iMsg = new int[numberOfBits];
        qim = new SignalQuantumImage[6][numberOfBits];
        reloadContainer();
    }

    public void reloadContainer() {
        statusBar.setProgress((double) numberOfBits / (double) maximumNumberOfBits);
        statusBar.setText("Message length: " + numberOfBits + "/" + maximumNumberOfBits + " bits");

        for(int i = 0; i < numberOfBits; i++) {
            sp[i] = new Spinner(0, 1, 0);
            sp[i].setPadding(new Insets(5));
            sp[i].setMaxWidth(55);

            if(message.charAt(i) == '0') {
                iMsg[i] = 0;
            } else if(message.charAt(i) == '1') {
                iMsg[i] = 1;
            } else {
                System.out.println("Fatal error!");
                System.exit(1);
            }

            for(int j = 0; j < 6; j++) {
                qim[j][i] = new SignalQuantumImage(quantumWidth, quantumHeight, quantumColor, quantumThickness, "00");
            }
        }

        processor = new SignalProcessor(numberOfBits, qim, iMsg, prev, sp);
        updateSignals();

        prev.setMaxWidth(65);
        prev.setPadding(new Insets(5));
        initial.setPadding(new Insets(5));

        nrzl.setPadding(new Insets(10));
        nrzl.setMinHeight(70);

        nrzpolar.setPadding(new Insets(10));
        nrzpolar.setMinHeight(70);

        nrzi.setPadding(new Insets(10));
        nrzi.setMinHeight(70);

        bipolar.setPadding(new Insets(10));
        bipolar.setMinHeight(70);

        manchester.setPadding(new Insets(10));
        manchester.setMinHeight(70);

        dmanchester.setPadding(new Insets(10));
        dmanchester.setMinHeight(70);

        this.setSpacing(10);
        this.setPadding(new Insets(20));
        grid.setGridLinesVisible(true);

        // ========= RELATIONS ===============
        grid.setPadding(new Insets(0));
        grid.setHgap(0);
        grid.setVgap(15);
        grid.add(initial, 0, 0);
        grid.add(prev, 1, 0);

        grid.add(nrzl, 0, 1);
        grid.add(nrzpolar, 0, 2);
        grid.add(nrzi, 0, 3);
        grid.add(bipolar, 0, 4);
        grid.add(manchester, 0, 5);
        grid.add(dmanchester, 0, 6);

        grid.add(inrzl, 1, 1);
        grid.add(inrzpolar, 1, 2);
        grid.add(inrzi, 1, 3);
        grid.add(ibipolar, 1, 4);
        grid.add(imanchester, 1, 5);
        grid.add(idmanchester, 1, 6);

        for(int i = 0; i < numberOfBits; i++) {
            Label label = new Label(String.valueOf(iMsg[i]));
            GridPane.setHalignment(label, HPos.CENTER);

            grid.add(label, i+2, 0);
            sp[i].setMaxWidth(quantumWidth-5);
            for(int j = 0; j < 6; j++) {
                grid.add(qim[j][i], i+2, j+1);
            }
        }

        this.getChildren().clear();
        this.getChildren().addAll(grid);

        prev.valueProperty().addListener((obs, oldValue, newValue) -> updateSignals());

        for(int i = 0; i < numberOfBits; i++) {
            sp[i].valueProperty().addListener((obs, oldValue, newValue) -> updateSignals());
        }
    }

    public String getMessage() {
        return this.message;
    }

    public void openFileCeremony() {
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
                resetSpinners(s);
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
    }

    public void saveFileCeremony() {
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showSaveDialog(null);

        if (selectedFile != null) {
            System.out.println("File selected: " + selectedFile.getAbsolutePath());
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(selectedFile.getAbsolutePath(), false));
                writer.append(getMessage());
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

    private void updateSignals()
    {
        processor.processNRZL();
        processor.processNRZLPolar();
        processor.processNRZI();
        processor.processBipolar();
        processor.processManchester();
        processor.processDManchester();
    }
}
