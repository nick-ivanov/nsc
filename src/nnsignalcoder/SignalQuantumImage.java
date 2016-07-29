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
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;

public class SignalQuantumImage extends Region {
    Canvas canvas;
    GraphicsContext graphicsContext;
    private double width, height, thickness;
    private Color color;
    private String code;

    // TODO: Use enum instead of string
    SignalQuantumImage(double width, double height, Color color, double thickness, String code) {
        super();
        canvas = new Canvas(width, height);
        graphicsContext = canvas.getGraphicsContext2D();
        this.setPrefSize(width+thickness, height+thickness);
        this.setMinSize(width+thickness, height+thickness);
        this.getChildren().add(canvas);
        this.width = width;
        this.height = height;
        this.color = color;
        this.thickness = thickness;
        this.code = code;

        canvas.setWidth(width);
        canvas.setHeight(height);
        this.setPadding(new Insets(0));
        this.setMaxSize(width, height);
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void drawQuantum() {
        graphicsContext.setStroke(this.color);
        graphicsContext.setLineWidth(this.thickness);
        graphicsContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        double x1, y1, x2, y2;
        char c1, c2;

        for(int i = 1; i < code.length(); i++) {
            c1 = code.charAt(i-1);
            c2 = code.charAt(i);

            // TODO: Needs to consider thickness
            switch(c1) {
                case '1': x1 = 0; y1 = 0; break;
                case '2': x1 = width/2+1; y1 = 0; break;
                case '3': x1 = width; y1 = 0; break;
                case '4': x1 = 0; y1 = height/2+1; break;
                case '5': x1 = width/2+1; y1 = height/2+1; break;
                case '6': x1 = width; y1 = height/2+1; break;
                case '7': x1 = 0; y1 = height; break;
                case '8': x1 = width/2+1; y1 = height; break;
                case '9': x1 = width; y1 = height; break;
                default: x1 = 0; y1 = 0;
            }

            switch(c2) {
                case '1': x2 = 0; y2 = 0; break;
                case '2': x2 = width/2+1; y2 = 0; break;
                case '3': x2 = width; y2 = 0; break;
                case '4': x2 = 0; y2 = height/2+1; break;
                case '5': x2 = width/2+1; y2 = height/2+1; break;
                case '6': x2 = width; y2 = height/2+1; break;
                case '7': x2 = 0; y2 = height-1; break;
                case '8': x2 = width/2+1; y2 = height; break;
                case '9': x2 = width; y2 = height; break;
                default: x2 = 0; y2 = 0; break;
            }

            graphicsContext.strokeLine(x1, y1, x2, y2);
        }
    }

    public void drawQuantum(String code) {
        setCode(code);
        drawQuantum();
    }

}
