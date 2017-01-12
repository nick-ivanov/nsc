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

package nnsignalcoder;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import static javafx.scene.paint.Color.*;

public class FittingTest extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    HBox grid;
    SignalQuantumImage image1 = new SignalQuantumImage(55, 55, BLUE, 3, "345");
    SignalQuantumImage image2 = new SignalQuantumImage(55, 55, RED, 5, "1473");

    @Override
    public void start(Stage primaryStage) {
        grid = new HBox();
        grid.setPadding(new Insets(0));
        grid.getChildren().addAll(image1, image2);


        drawImages();

        Scene scene = new Scene(grid, 1280, 640);
        primaryStage.setTitle("NNSignalCoder Fitting Test");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void drawImages() {
        image1.drawQuantum();
        image2.drawQuantum();
        image2.setCode("39745");
        image2.drawQuantum();
    }
}
