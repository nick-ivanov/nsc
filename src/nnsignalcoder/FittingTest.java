package nnsignalcoder;

import javafx.application.Application;
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

    GridPane grid;
    SignalQuantumImage image1 = new SignalQuantumImage(55, 55, BLUE, 3, "345");
    SignalQuantumImage image2 = new SignalQuantumImage(55, 55, RED, 5, "1473");



    @Override
    public void start(Stage primaryStage) {
        grid = new GridPane();

        grid.setGridLinesVisible(true);
        grid.add(image1, 0, 0);
        grid.add(image2, 1, 0);

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
