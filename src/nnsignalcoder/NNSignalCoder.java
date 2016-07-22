/*
FILE NAME:     NSSignalCoder.java
VERSION:       1.0
DESCRIPTION:   Signal encoder for NRZ-L, Polar NRZ-L, NRZ-I, Bipolar AMI,
		    Manchester, and Differential Manchester
CLASS:         COMP 306, Spring 2016 Semester @ SMSU
AUTHOR:	       Nick Ivanov <nnrowan@gmail.com>
LICENSE:       GNU General Public License v.3
               [http://www.gnu.org/licenses/gpl-3.0.en.html]
DATE:          1/28/2016

DOCUMENTATION: Ask Wikipedia
*/

package nnsignalcoder;

import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.text.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.event.*;
import javafx.application.*;
import javafx.geometry.*;

/**
 *
 * @author Nikolay Ivanov a.k.a. Nick
 */
public class NNSignalCoder extends Application {
    Scene scene;
    VBox vbox;
    GridPane grid;
    Button quit;
    Label intro;
    Label initial;
    Spinner [] sp;
    Spinner prev;
    ImageView [][] im;

    Label nrzl, nrzpolar, nrzi, bipolar, manchester, dmanchester;
    ImageView inrzl, inrzpolar, inrzi, ibipolar, imanchester, idmanchester;

    public void set_nrzl()
    {
        int a, p;

        p = (int) prev.getValue();

        for(int i = 0; i < 16; i++) {
            a = (int) sp[i].getValue();

            if(a == 0) {
                if(p == 0) { im[0][i].setImage(new Image(getClass().getResourceAsStream("images/s02.png"))); }
                else { im[0][i].setImage(new Image(getClass().getResourceAsStream("images/s03.png"))); }
            } else {
                if(p == 0) { im[0][i].setImage(new Image(getClass().getResourceAsStream("images/s04.png"))); }
                else { im[0][i].setImage(new Image(getClass().getResourceAsStream("images/s01.png"))); }
            }

            p = (int) sp[i].getValue();
        }
    }

    public void set_nrzpolar()
    {
        int a, p;

        p = (int) prev.getValue();

        for(int i = 0; i < 16; i++) {
            a = (int) sp[i].getValue();

            if(a == 0) {
                if(p == 0) { im[1][i].setImage(new Image(getClass().getResourceAsStream("images/s02.png"))); }
                else { im[1][i].setImage(new Image(getClass().getResourceAsStream("images/s03.png"))); }
            } else {
                if(p == 0) { im[1][i].setImage(new Image(getClass().getResourceAsStream("images/s04.png"))); }
                else { im[1][i].setImage(new Image(getClass().getResourceAsStream("images/s01.png"))); }
            }

            p = (int) sp[i].getValue();
        }
    }

    public void set_nrzi()
    {
        int a, p;

        p = (int) prev.getValue();

        for(int i = 0; i < 16; i++) {
            a = (int) sp[i].getValue();

            if(a == 1) {
                if(p == 1) { im[2][i].setImage(new Image(getClass().getResourceAsStream("images/s03.png"))); p = 0; }
                else { im[2][i].setImage(new Image(getClass().getResourceAsStream("images/s04.png"))); p = 1; }
            } else {
                if(p == 1) { im[2][i].setImage(new Image(getClass().getResourceAsStream("images/s01.png"))); p = 1; }
                else { im[2][i].setImage(new Image(getClass().getResourceAsStream("images/s02.png"))); p = 0; }
            }

        }
    }

    public void set_bipolar()
    {
        int a, p, pp;

        p = (int) prev.getValue();
        pp = -1;

        for(int i = 0; i < 16; i++) {
            a = (int) sp[i].getValue();

            if(a == 0) {
                if(p == 1) { im[3][i].setImage(new Image(getClass().getResourceAsStream("images/s06.png"))); p = 0; }
                else if(p == -1) { im[3][i].setImage(new Image(getClass().getResourceAsStream("images/s08.png"))); p = 0; }
                else { im[3][i].setImage(new Image(getClass().getResourceAsStream("images/s05.png"))); p = 0; }
            } else { // a == 1
                if(pp == -1) {
                    if(p == 0) { im[3][i].setImage(new Image(getClass().getResourceAsStream("images/s09.png"))); p = 1; pp = 1; }
                    else { im[3][i].setImage(new Image(getClass().getResourceAsStream("images/s04.png"))); p = 1; pp = 1; }
                } else {
                    if(p == 0) { im[3][i].setImage(new Image(getClass().getResourceAsStream("images/s07.png"))); p = -1; pp = -1; }
                    else { im[3][i].setImage(new Image(getClass().getResourceAsStream("images/s03.png"))); p = -1; pp = -1; }
                }

            }

        }
    }

    public void set_manchester()
    {
        int a, p;

        p = (int) prev.getValue();

        for(int i = 0; i < 16; i++) {
            a = (int) sp[i].getValue();

            if(a == 0) {
                if(p == 1) { im[4][i].setImage(new Image(getClass().getResourceAsStream("images/s10.png"))); }
                else { im[4][i].setImage(new Image(getClass().getResourceAsStream("images/s11.png"))); }
            } else {
                if(p == 1) { im[4][i].setImage(new Image(getClass().getResourceAsStream("images/s13.png"))); }
                else { im[4][i].setImage(new Image(getClass().getResourceAsStream("images/s12.png"))); }
            }

            p = (int) sp[i].getValue();
        }
    }

    public void set_dmanchester()
    {
        int a, p;

        p = (int) prev.getValue();

        for(int i = 0; i < 16; i++) {
            a = (int) sp[i].getValue();

            if(a == 0) {
                if(p == 1) { im[5][i].setImage(new Image(getClass().getResourceAsStream("images/s13.png"))); p = 1; }
                else { im[5][i].setImage(new Image(getClass().getResourceAsStream("images/s11.png"))); p = 0; }
            } else {
                if(p == 1) { im[5][i].setImage(new Image(getClass().getResourceAsStream("images/s10.png"))); p = 0; }
                else { im[5][i].setImage(new Image(getClass().getResourceAsStream("images/s12.png"))); p = 1; }
            }
        }
    }

    public void update_sig()
    {
        set_nrzl();
        set_nrzpolar();
        set_nrzi();
        set_bipolar();
        set_manchester();
        set_dmanchester();
    }

    @Override
    public void start(Stage primaryStage) {

        // ========== DECLARATIONS ============

        quit = new Button("Quit");
        intro = new Label("Welcome to NNSignalCoder!");
        vbox = new VBox();
        grid = new GridPane();
        prev = new Spinner(0, 1, 0);

        initial = new Label("BITS:");

        nrzl = new Label("NRZ-L");
        nrzpolar = new Label("Polar NRZ-L");
        nrzi = new Label("NRZ-I");
        bipolar = new Label("Bipolar-AMI");
        manchester = new Label("Manchester");
        dmanchester = new Label("Diff. Manchester");

        inrzl = new ImageView(
                new Image(getClass().getResourceAsStream("images/01.png")));
        inrzpolar = new ImageView(
                new Image(getClass().getResourceAsStream("images/02.png")));
        inrzi = new ImageView(
                new Image(getClass().getResourceAsStream("images/03.png")));
        ibipolar = new ImageView(
                new Image(getClass().getResourceAsStream("images/04.png")));
        imanchester = new ImageView(
                new Image(getClass().getResourceAsStream("images/05.png")));
        idmanchester = new ImageView(
                new Image(getClass().getResourceAsStream("images/06.png")));

        sp = new Spinner[16];
        im = new ImageView[6][16];

        for(int i = 0; i < 16; i++) {
            sp[i] = new Spinner(0, 1, 0);
            sp[i].setMaxWidth(55);

            for(int j = 0; j < 6; j++) {
                im[j][i] = new ImageView(
                        new Image(getClass().getResourceAsStream("images/dummy.png")));
                im[j][i].setFitWidth(55);
                im[j][i].setFitHeight(55);
            }
        }

        update_sig();
        scene = new Scene(vbox, 1280, 640);


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

        vbox.setSpacing(10);
        vbox.setPadding(new Insets(20));
        grid.setGridLinesVisible(true);

        // ========= RELATIONS ===============
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
            for(int j = 0; j < 6; j++) {
                grid.add(im[j][i], i+2, j+1);
            }
        }

        vbox.getChildren().addAll(intro, grid, quit);

        // ======= EVENT HANDLING ==========
        quit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Platform.exit();
                System.exit(0);
            }
        });


        prev.valueProperty().addListener((obs, oldValue, newValue) -> update_sig());

        for(int i = 0; i < 16; i++) {
            sp[i].valueProperty().addListener((obs, oldValue, newValue) -> update_sig());
        }

        primaryStage.setTitle("NNSigcoder");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
