/*
    NNSignalCoder -- Network signal encoder
    Copyright (C) 2015-2016  Nick Ivanov <nnrowan@gmail.com>

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

import javafx.geometry.Insets;

import javafx.scene.paint.Color;

import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import sun.misc.Signal;

public class RootContainer extends BorderPane {
    Stage stage;
    CenterContainer centerContainer;
    TopContainer topContainer;
    SignalQuantumImage signal = new SignalQuantumImage(100, 100, "Hello", Color.RED, 2);

    RootContainer(Stage stage) {
        super();
        this.stage = stage;
        topContainer = new TopContainer(this.stage);
        centerContainer = new CenterContainer();

        this.setTop(topContainer);
        this.setCenter(centerContainer);
        this.setLeft(signal);
        //signal.drawQuantum("s06");
    }
}


