/*
    NNSignalCoder -- Network signal encoder
    Copyright (C) 2015-2016  Nick Ivanov <nnivanov@gmail.com>

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

package nnsignalcoder;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;


public class TopContainer extends MenuBar {
    final Menu menu1 = new Menu("Menu");
    final Menu menu2 = new Menu("Help");

    final MenuItem quit_item = new MenuItem("Quit");
    final MenuItem manual_item = new MenuItem("Documentation");
    final MenuItem about_item = new MenuItem("About");

    //HelpWindow hw = new HelpWindow();

    public TopContainer(Stage stage) {
        menu1.getItems().add(quit_item);
        menu2.getItems().addAll(manual_item, about_item);
        this.getMenus().addAll(menu1, menu2);

        setEvents(stage);
    }

    private void setEvents(Stage stage) {
        EventHandler<ActionEvent> MEHandler =
                new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent ae) {
                        String name = ((MenuItem)ae.getTarget()).getText();

                        if(name.equals("Quit")) { Platform.exit(); }
                        if(name.equals("Documentation")) {
                            Alert alert = new Alert(AlertType.INFORMATION);
                            alert.setTitle("IP46 Help");
                            alert.setHeaderText("How to use NNSignalCoder");
                            String content = "This program is designed with an idea that software should " +
                                    "be intuitively simple. If you cannot use this program without documentation, " +
                                    "throw this program out and let us know that we screwed things up.";
                            alert.setContentText(content);
                            alert.showAndWait();
                        }
                        if(name.equals("About")) {
                            Alert alert = new Alert(AlertType.INFORMATION);
                            alert.setTitle("About NNSignalCoder");
                            alert.setHeaderText("About NNSignalCoder");
                            String content = "Copyright (C) 2016 Nick Ivanov (nnrowan@gmail.com)\n\n" +
                                    "Distributed under GNU GPL v.3";
                            alert.setContentText(content);
                            alert.showAndWait();
                        }

                        // hw.setText(name + " chosen");
                    }
                };

        quit_item.setOnAction(MEHandler);
        manual_item.setOnAction(MEHandler);
        about_item.setOnAction(MEHandler);
    }

}