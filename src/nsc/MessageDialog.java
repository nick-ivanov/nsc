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

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MessageDialog {

    private CenterContainer container;

    public MessageDialog(CenterContainer container, String message) {
        this.container = container;
    }

    public void go() {
        final Stage dialog = new Stage();
        dialog.setTitle("Enter binary message");

        dialog.initModality(Modality.NONE);
        dialog.initOwner(container.getScene().getWindow());

        VBox vbox = new VBox();
        vbox.setPadding(new Insets(15));
        vbox.setSpacing(15);
        vbox.setAlignment(Pos.BASELINE_CENTER);

        HBox hbox1 = new HBox();
        hbox1.setPadding(new Insets(15));
        hbox1.setSpacing(15);
        hbox1.setAlignment(Pos.BASELINE_CENTER);

        HBox hbox2 = new HBox();
        hbox2.setPadding(new Insets(15));
        hbox2.setSpacing(15);
        hbox2.setAlignment(Pos.BASELINE_CENTER);

        HBox hbox3 = new HBox();
        hbox3.setPadding(new Insets(15));
        hbox3.setSpacing(15);

        hbox3.setAlignment(Pos.BASELINE_CENTER);

        Label title = new Label("Entry binary message");
        title.setFont(new Font(24));

        Label label1 = new Label("Binary message:");
        TextField bookmarkNameTextInput = new TextField();
        bookmarkNameTextInput.setPrefColumnCount(25);

        Label label2 = new Label("URL:");
        TextField urlTextInput = new TextField();
        urlTextInput.setPrefColumnCount(25);

        Button addButton = new Button("Set binary message");
        addButton.setDisable(true);
        Button cancelButton = new Button("Cancel");

        hbox1.getChildren().addAll(label1, bookmarkNameTextInput);
        hbox2.getChildren().addAll(label2, urlTextInput);
        hbox3.getChildren().addAll(addButton, cancelButton);

        vbox.getChildren().addAll(title, hbox1, hbox2, hbox3);

        HBox dialogHbox = new HBox(20);
        dialogHbox.setAlignment(Pos.CENTER);

        bookmarkNameTextInput.setOnKeyReleased((EventHandler<javafx.event.Event>) event -> {
            if (!bookmarkNameTextInput.getText().equals("")) {
                addButton.setDisable(false);
            } else {
                addButton.setDisable(true);
            }
        });

        urlTextInput.setOnKeyReleased((EventHandler<javafx.event.Event>) event -> {
            if (!urlTextInput.getText().equals("")) {
                addButton.setDisable(false);
            } else {
                addButton.setDisable(true);
            }
        });

        addButton.addEventHandler(MouseEvent.MOUSE_CLICKED,
                e -> {
                    System.out.println("data: " + bookmarkNameTextInput.getText());
                    dialog.close();
                });

        cancelButton.addEventHandler(MouseEvent.MOUSE_CLICKED,
                e -> dialog.close());

        Scene dialogScene = new Scene(vbox, 640, 480);
        dialog.setScene(dialogScene);
        dialog.show();
    }
}