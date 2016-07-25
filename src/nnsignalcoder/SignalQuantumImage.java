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

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

public class SignalQuantumImage extends Region {
    Canvas canvas;
    GraphicsContext graphicsContext;
    private double width, height, thickness;
    private String code;
    private Color color;

    // TODO: Use enum instead of string
    SignalQuantumImage(double width, double height, String code, Color color, double thickness) {
        super();
        canvas = new Canvas(width, height);
        graphicsContext = canvas.getGraphicsContext2D();
        this.setPadding(new Insets(20));
        this.getChildren().add(canvas);
        this.width = width;
        this.height = height;
        this.code = code;
        this.color = color;
        this.thickness = thickness;

    }

    public void drawQuantum(String quantum) {
        graphicsContext.setStroke(this.color);
        graphicsContext.setLineWidth(this.thickness);

        if(quantum.equals("s06")) {
            graphicsContext.strokeLine(0, 0, 0, height/2);
            graphicsContext.strokeLine(0, height/2, width-1, height/2);

        }
    }

}
