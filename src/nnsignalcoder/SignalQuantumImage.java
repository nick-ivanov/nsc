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

import com.sun.xml.internal.fastinfoset.util.CharArray;
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

        double x1, y1, x2, y2;
        char c1, c2;

        for(int i = 1; i < quantum.length(); i++) {
            c1 = quantum.charAt(i-1);
            c2 = quantum.charAt(i);

            switch(c1) {
                case '1': x1 = 0; y1 = 0; break;
                case '2': x1 = width/2; y1 = 0; break;
                case '3': x1 = width-1; y1 = 0; break;
                case '4': x1 = 0; y1 = height/2; break;
                case '5': x1 = width/2; y1 = height/2; break;
                case '6': x1 = width-1; y1 = height/2; break;
                case '7': x1 = 0; y1 = height-1; break;
                case '8': x1 = width/2; y1 = height-1; break;
                case '9': x1 = width-1; y1 = height-1; break;
                default: x1 = 0; y1 = 0;
            }

            switch(c2) {
                case '1': x2 = 0; y2 = 0; break;
                case '2': x2 = width/2; y2 = 0; break;
                case '3': x2 = width-1; y2 = 0; break;
                case '4': x2 = 0; y2 = height/2; break;
                case '5': x2 = width/2; y2 = height/2; break;
                case '6': x2 = width-1; y2 = height/2; break;
                case '7': x2 = 0; y2 = height-1; break;
                case '8': x2 = width/2; y2 = height-1; break;
                case '9': x2 = width-1; y2 = height-1; break;
                default: x2 = 0; y2 = 0;
            }

            graphicsContext.strokeLine(x1, y1, x2, y2);
        }
    }

}
