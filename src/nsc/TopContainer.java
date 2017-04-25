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

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.print.PrinterJob;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

class TopContainer extends VBox {
    final private Menu fileMenu = new Menu("File");
    final private Menu editMenu = new Menu("Edit");
    final private Menu viewMenu = new Menu("View");
    final private Menu helpMenu = new Menu("Help");

    final private MenuBar menuBar;
    final private MainToolBar toolBar;

    final private MenuItem openMenuItem = new MenuItem("Open File...");
    final private MenuItem saveMenuItem = new MenuItem("Save File...");
    final private MenuItem printMenuItem = new MenuItem("Print...");
    final private MenuItem quitMenuItem = new MenuItem("Quit");

    final private MenuItem resetMenuItem = new MenuItem("Reset Message");

    final private MenuItem manualMenuItem = new MenuItem("Documentation");
    final private MenuItem aboutMenuItem = new MenuItem("About");

    private CenterContainer centerContainer;

    TopContainer(Stage stage, CenterContainer centerContainer) {
        this.centerContainer = centerContainer;

        fileMenu.getItems().addAll(openMenuItem, saveMenuItem, printMenuItem, quitMenuItem);

        editMenu.getItems().addAll(resetMenuItem);

        helpMenu.getItems().addAll(manualMenuItem, aboutMenuItem);

        menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu, editMenu, viewMenu, helpMenu);

        toolBar = new MainToolBar(stage, centerContainer);

        this.getChildren().addAll(menuBar, toolBar);
        setEvents(stage);
    }

    private void setEvents(Stage stage) {
        EventHandler<ActionEvent> MEHandler =
                ae -> {
                    String name = ((MenuItem)ae.getTarget()).getText();

                    if(name.equals("Open File...")) {
                        centerContainer.openFileCeremony();
                    }

                    if(name.equals("Save File...")) {
                        centerContainer.saveFileCeremony();
                    }

                    if(name.equals("Print...")) {
                        PrinterJob job = PrinterJob.createPrinterJob();
                        if (job != null && job.showPrintDialog(centerContainer.getScene().getWindow())){
                            boolean success = job.printPage(centerContainer);
                            if (success) {
                                job.endJob();
                            }
                        }
                    }

                    if(name.equals("Quit")) { Platform.exit(); }

                    if(name.equals("Reset Message")) {
                        System.out.println("Reset Message stub");
                        centerContainer.getBitBox().clear();

                        int defaultBitLength = Integer.parseInt(NSCPropertyHelper.getProperty("default_bit_length"));

                        String clearString = "";
                        for(int i = 0; i < defaultBitLength; i++) {
                            clearString += "0";
                        }

                        centerContainer.resetSpinners(clearString);
                    }

                    if(name.equals("Documentation")) {
                        Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Network Signal Coder Help");
                        alert.setHeaderText("How to use Network Signal Coder");
                        String content = "This program is designed with an idea that software should " +
                                "be intuitively simple. If you cannot use this program without documentation, " +
                                "throw this program out and let us know that we screwed things up.";
                        alert.setContentText(content);
                        alert.showAndWait();
                    }

                    if(name.equals("About")) {
                        Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("About Network Signal Coder");
                        alert.setHeaderText("About Network Signal Coder");
                        String content = "Copyright (C) 2015-2017 Nick Ivanov (nnrowan@gmail.com)\n\n" +
                                "Distributed under GNU GPL v.3";
                        alert.setContentText(content);
                        alert.showAndWait();
                    }


                };

        openMenuItem.setOnAction(MEHandler);
        saveMenuItem.setOnAction(MEHandler);
        printMenuItem.setOnAction(MEHandler);
        quitMenuItem.setOnAction(MEHandler);
        resetMenuItem.setOnAction(MEHandler);
        manualMenuItem.setOnAction(MEHandler);
        aboutMenuItem.setOnAction(MEHandler);

    }

    public BitBox getBitBox() {
        return toolBar.getBitBox();
    }

}
