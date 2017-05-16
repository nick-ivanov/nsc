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

import javafx.geometry.Orientation;
import javafx.print.PrinterJob;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MainToolBar extends ToolBar {
    private BitBox bitBox;

    MainToolBar(CenterContainer centerContainer) {
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

        Separator separator = new Separator(Orientation.VERTICAL);
        this.getItems().addAll(openButton, saveButton, printButton, clearButton, separator, bitBox);
    }

    public BitBox getBitBox() {
        return bitBox;
    }
}
