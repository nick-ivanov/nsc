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
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class BitBox extends HBox {
    private Label messageLabel;
    private TextField messageTextField;
    private String clearString;
    private CenterContainer centerContainer;

    public BitBox(CenterContainer centerContainer) {
        super();

        this.centerContainer = centerContainer;
        int defaultBitLength = Integer.parseInt(NSCPropertyHelper.getProperty("default_bit_length"));

        clearString = "";
        for(int i = 0; i < defaultBitLength; i++) {
            clearString += "0";
        }

        this.setAlignment(Pos.BASELINE_CENTER);
        messageLabel = new Label("Message: ");
        messageTextField = new TextField(clearString);

        int messageFieldMaxLength = Integer.parseInt(NSCPropertyHelper.getProperty("message_field_max_length"));
        messageTextField.setPrefColumnCount(messageFieldMaxLength);

        messageTextField.setOnKeyReleased((EventHandler) event -> {
            centerContainer.resetSpinners(messageTextField.getText());
        });

        this.getChildren().addAll(messageLabel, messageTextField);
    }

    public void clear() {
        messageTextField.setText(clearString);
    }
}
