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

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;

public class CenterContainer extends VBox {
    private final double quantumWidth = 80;
    private final double quantumHeight = 80;
    private final Color quantumColor = Color.BROWN;
    private final double quantumThickness = 10;

    private GridPane grid = new GridPane();
    private Button quit = new Button("Quit");
    private Label intro = new Label("Welcome to NNSignalCoder!");
    private Label initial = new Label("BITS:");
    private Spinner[] sp = new Spinner[16];
    private Spinner prev = new Spinner(0, 1, 0);
    private ImageView[][] im = new ImageView[6][16];

    private SignalQuantumImage[][] qim = new SignalQuantumImage[6][16];

    private Label nrzl = new Label("NRZ-L");
    private Label nrzpolar = new Label("Polar NRZ-L");
    private Label nrzi = new Label("NRZ-I");
    private Label bipolar = new Label("Bipolar-AMI");
    private Label manchester = new Label("Manchester");
    private Label dmanchester = new Label("Diff. Manchester");

//    private SignalQuantumImage nrzlQuantum = new SignalQuantumImage(quantumWidth, quantumHeight, quantumColor, quantumThickness, "13");
//    private SignalQuantumImage nrzpolarQuantum = new SignalQuantumImage(quantumWidth, quantumHeight, quantumColor, quantumThickness, "79");
//    private SignalQuantumImage nrziQuantum = new SignalQuantumImage(quantumWidth, quantumHeight, quantumColor, quantumThickness, "179");
//    private SignalQuantumImage bipolarQuantum = new SignalQuantumImage(quantumWidth, quantumHeight, quantumColor, quantumThickness, "713");
//    private SignalQuantumImage manchesterQuantum = new SignalQuantumImage(quantumWidth, quantumHeight, quantumColor, quantumThickness, "46");
//    private SignalQuantumImage dmanchesterQuantum = new SignalQuantumImage(quantumWidth, quantumHeight, quantumColor, quantumThickness, "146");

    ImageView inrzl = new ImageView(
                new Image(getClass().getResourceAsStream("images/01.png")));
    ImageView inrzpolar = new ImageView(
                new Image(getClass().getResourceAsStream("images/02.png")));
    ImageView inrzi = new ImageView(
                new Image(getClass().getResourceAsStream("images/03.png")));
    ImageView ibipolar = new ImageView(
                new Image(getClass().getResourceAsStream("images/04.png")));
    ImageView imanchester = new ImageView(
                new Image(getClass().getResourceAsStream("images/05.png")));
    ImageView idmanchester = new ImageView(
                new Image(getClass().getResourceAsStream("images/06.png")));

    CenterContainer() {
        super();

        for(int i = 0; i < 16; i++) {
            sp[i] = new Spinner(0, 1, 0);
            //sp[i].setMaxWidth(55);

            for(int j = 0; j < 6; j++) {
                //im[j][i] = new ImageView(
                        //new Image(getClass().getResourceAsStream("images/dummy.png")));
                //im[j][i].setFitWidth(55);
                //im[j][i].setFitHeight(55);

                qim[j][i] = new SignalQuantumImage(quantumWidth, quantumHeight, quantumColor, quantumThickness, "00");
            }
        }

        updateSignals();

        // ======= LOOK =================

        intro.setFont(Font.font("Helvetica", 20));

        prev.setMaxWidth(65);
        prev.setPadding(new Insets(5));
        prev.setStyle("-fx-background-color: red;");
        initial.setPadding(new Insets(5));

        nrzl.setPadding(new Insets(10));
        nrzl.setMinHeight(70);

        nrzpolar.setPadding(new Insets(10));
        nrzpolar.setMinHeight(70);

        nrzi.setPadding(new Insets(10));
        nrzi.setMinHeight(70);

        bipolar.setPadding(new Insets(10));
        bipolar.setMinHeight(70);

        manchester.setPadding(new Insets(10));
        manchester.setMinHeight(70);

        dmanchester.setPadding(new Insets(10));
        dmanchester.setMinHeight(70);

        this.setSpacing(10);
        this.setPadding(new Insets(20));
        grid.setGridLinesVisible(true);

        // ========= RELATIONS ===============
        grid.setPadding(new Insets(0));
        grid.setHgap(0);
        grid.setVgap(15);
        grid.add(initial, 0, 0);
        grid.add(prev, 1, 0);

        grid.add(nrzl, 0, 1);
        grid.add(nrzpolar, 0, 2);
        grid.add(nrzi, 0, 3);
        grid.add(bipolar, 0, 4);
        grid.add(manchester, 0, 5);
        grid.add(dmanchester, 0, 6);

        grid.add(inrzl, 1, 1);
        grid.add(inrzpolar, 1, 2);
        grid.add(inrzi, 1, 3);
        grid.add(ibipolar, 1, 4);
        grid.add(imanchester, 1, 5);
        grid.add(idmanchester, 1, 6);

        for(int i = 0; i < 16; i++) {
            grid.add(sp[i], i+2, 0);
            sp[i].setMaxWidth(quantumWidth-5);
            for(int j = 0; j < 6; j++) {
                // grid.add(im[j][i], i+2, j+1);
                grid.add(qim[j][i], i+2, j+1);
            }
        }

        this.getChildren().addAll(intro, grid, quit);

        // ======= EVENT HANDLING ==========
        quit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Platform.exit();
                System.exit(0);
            }
        });

        prev.valueProperty().addListener((obs, oldValue, newValue) -> updateSignals());

        for(int i = 0; i < 16; i++) {
            sp[i].valueProperty().addListener((obs, oldValue, newValue) -> updateSignals());
        }

    }

    private void processNRZL()
    {
        int a, p;

        p = (int) prev.getValue();

        for(int i = 0; i < 16; i++) {
            a = (int) sp[i].getValue();

//            if(a == 0) {
//                if(p == 0) { im[0][i].setImage(new Image(getClass().getResourceAsStream("images/s02.png"))); }
//                else { im[0][i].setImage(new Image(getClass().getResourceAsStream("images/s03.png"))); }
//            } else {
//                if(p == 0) { im[0][i].setImage(new Image(getClass().getResourceAsStream("images/s04.png"))); }
//                else { im[0][i].setImage(new Image(getClass().getResourceAsStream("images/s01.png"))); }
//            }

            if(a == 0) {
                if(p == 0) { qim[0][i].drawQuantum("79"); }
                else { qim[0][i].drawQuantum("179"); }
            } else {
                if(p == 0) { qim[0][i].drawQuantum("713"); }
                else { qim[0][i].drawQuantum("13"); }
            }

            p = (int) sp[i].getValue();
        }
    }

    private void processNRZLPolar()
    {
        int a, p;

        p = (int) prev.getValue();

        for(int i = 0; i < 16; i++) {
            a = (int) sp[i].getValue();

//            if(a == 0) {
//                if(p == 0) { im[1][i].setImage(new Image(getClass().getResourceAsStream("images/s02.png"))); }
//                else { im[1][i].setImage(new Image(getClass().getResourceAsStream("images/s03.png"))); }
//            } else {
//                if(p == 0) { im[1][i].setImage(new Image(getClass().getResourceAsStream("images/s04.png"))); }
//                else { im[1][i].setImage(new Image(getClass().getResourceAsStream("images/s01.png"))); }
//            }

            if(a == 0) {
                if(p == 0) { qim[1][i].drawQuantum("79"); }
                else { qim[1][i].drawQuantum("179"); }
            } else {
                if(p == 0) { qim[1][i].drawQuantum("713"); }
                else { qim[1][i].drawQuantum("13"); }
            }

            p = (int) sp[i].getValue();
        }
    }

    private void processNRZI()
    {
        int a, p;

        p = (int) prev.getValue();

        for(int i = 0; i < 16; i++) {
            a = (int) sp[i].getValue();

//            if(a == 1) {
//                if(p == 1) { im[2][i].setImage(new Image(getClass().getResourceAsStream("images/s03.png"))); p = 0; }
//                else { im[2][i].setImage(new Image(getClass().getResourceAsStream("images/s04.png"))); p = 1; }
//            } else {
//                if(p == 1) { im[2][i].setImage(new Image(getClass().getResourceAsStream("images/s01.png"))); p = 1; }
//                else { im[2][i].setImage(new Image(getClass().getResourceAsStream("images/s02.png"))); p = 0; }
//            }

            if(a == 1) {
                if(p == 1) { qim[2][i].drawQuantum("179"); p = 0; }
                else { qim[2][i].drawQuantum("713"); p = 1; }
            } else {
                if(p == 1) { qim[2][i].drawQuantum("13"); p = 1; }
                else { qim[2][i].drawQuantum("79"); p = 0; }
            }


        }
    }

    private void processBipolar()
    {
        int a, p, pp;

        p = (int) prev.getValue();
        pp = -1;

        for(int i = 0; i < 16; i++) {
            a = (int) sp[i].getValue();

//            if(a == 0) {
//                if(p == 1) { im[3][i].setImage(new Image(getClass().getResourceAsStream("images/s06.png"))); p = 0; }
//                else if(p == -1) { im[3][i].setImage(new Image(getClass().getResourceAsStream("images/s08.png"))); p = 0; }
//                else { im[3][i].setImage(new Image(getClass().getResourceAsStream("images/s05.png"))); p = 0; }
//            } else { // a == 1
//                if(pp == -1) {
//                    if(p == 0) { im[3][i].setImage(new Image(getClass().getResourceAsStream("images/s09.png"))); p = 1; pp = 1; }
//                    else { im[3][i].setImage(new Image(getClass().getResourceAsStream("images/s04.png"))); p = 1; pp = 1; }
//                } else {
//                    if(p == 0) { im[3][i].setImage(new Image(getClass().getResourceAsStream("images/s07.png"))); p = -1; pp = -1; }
//                    else { im[3][i].setImage(new Image(getClass().getResourceAsStream("images/s03.png"))); p = -1; pp = -1; }
//                }
//            }

            if(a == 0) {
                if(p == 1) { qim[3][i].drawQuantum("146"); p = 0; }
                else if(p == -1) { qim[3][i].drawQuantum("746"); p = 0; }
                else { qim[3][i].drawQuantum("46"); p = 0; }
            } else { // a == 1
                if(pp == -1) {
                    if(p == 0) { qim[3][i].drawQuantum("413"); p = 1; pp = 1; }
                    else { qim[3][i].drawQuantum("713"); p = 1; pp = 1; }
                } else {
                    if(p == 0) { qim[3][i].drawQuantum("479"); p = -1; pp = -1; }
                    else { qim[3][i].drawQuantum("179"); p = -1; pp = -1; }
                }
            }
        }
    }

    private void processManchester()
    {
        int a, p;

        p = (int) prev.getValue();

        for(int i = 0; i < 16; i++) {
            a = (int) sp[i].getValue();

//            if(a == 0) {
//                if(p == 1) { qim[4][i].setImage(new Image(getClass().getResourceAsStream("images/s10.png"))); }
//                else { qim[4][i].setImage(new Image(getClass().getResourceAsStream("images/s11.png"))); }
//            } else {
//                if(p == 1) { qim[4][i].setImage(new Image(getClass().getResourceAsStream("images/s13.png"))); }
//                else { qim[4][i].setImage(new Image(getClass().getResourceAsStream("images/s12.png"))); }
//            }

            if(a == 0) {
                if(p == 1) { qim[4][i].drawQuantum("1289"); }
                else { qim[4][i].drawQuantum("71289"); }
            } else {
                if(p == 1) { qim[4][i].drawQuantum("17823"); }
                else { qim[4][i].drawQuantum("7823"); }
            }

            p = (int) sp[i].getValue();
        }
    }

    private void processDManchester()
    {
        int a, p;

        p = (int) prev.getValue();

        for(int i = 0; i < 16; i++) {
            a = (int) sp[i].getValue();

//            if(a == 0) {
//                if(p == 1) { im[5][i].setImage(new Image(getClass().getResourceAsStream("images/s13.png"))); p = 1; }
//                else { im[5][i].setImage(new Image(getClass().getResourceAsStream("images/s11.png"))); p = 0; }
//            } else {
//                if(p == 1) { im[5][i].setImage(new Image(getClass().getResourceAsStream("images/s10.png"))); p = 0; }
//                else { im[5][i].setImage(new Image(getClass().getResourceAsStream("images/s12.png"))); p = 1; }
//            }

            if(a == 0) {
                if(p == 1) { qim[5][i].drawQuantum("17823"); p = 1; }
                else { qim[5][i].drawQuantum("71289"); p = 0; }
            } else {
                if(p == 1) { qim[5][i].drawQuantum("1289"); p = 0; }
                else { qim[5][i].drawQuantum("7823"); p = 1; }
            }
        }
    }

    private void updateSignals()
    {
        processNRZL();
        processNRZLPolar();
        processNRZI();
        processBipolar();
        processManchester();
        processDManchester();
    }



}
