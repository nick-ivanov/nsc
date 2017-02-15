package nsc;
import java.util.HashMap;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MessageDialog {

    private ListView listView;
    private HashMap<Integer, String> hm;
    private HashMap<Integer, String> namehm;
    private HashMap<Integer, String> urlhm;
    private HashMap<Integer, String> idhm;
    private ListView bookmarkListView;

    public MessageDialog(ListView lv, ListView lv1, HashMap<Integer, String> hm, HashMap<Integer, String> idhm,
                               HashMap<Integer, String> namehm, HashMap<Integer, String> urlhm) {
        this.listView = lv;
        this.bookmarkListView = lv1;
        this.hm = hm;
        this.namehm = namehm;
        this.urlhm = urlhm;
        this.idhm = idhm;
    }

    public void go() {
        final Stage dialog = new Stage();
        dialog.setTitle("Enter binary message");

        dialog.initModality(Modality.NONE);
        dialog.initOwner((Stage) listView.getScene().getWindow());

        VBox vbox = new VBox();
        vbox.setPadding(new Insets(15));
        vbox.setSpacing(15);
        vbox.setAlignment(Pos.BASELINE_CENTER);

        HBox hbox1 = new HBox();
        hbox1.setPadding(new Insets(15));
        hbox1.setSpacing(15);
        hbox1.setAlignment(Pos.BASELINE_CENTER);

        HBox hbox2 = new HBox();
        hbox2.setPadding(new Insets(15));
        hbox2.setSpacing(15);
        hbox2.setAlignment(Pos.BASELINE_CENTER);

        HBox hbox3 = new HBox();
        hbox3.setPadding(new Insets(15));
        hbox3.setSpacing(15);

        hbox3.setAlignment(Pos.BASELINE_CENTER);

        Label title = new Label("Entry binary message '" + listView.getSelectionModel().getSelectedItem().toString() + "'");
        title.setFont(new Font(24));

        Label label1 = new Label("Binary message:");
        TextField bookmarkNameTextInput = new TextField();
        bookmarkNameTextInput.setPrefColumnCount(25);

        Label label2 = new Label("URL:");
        TextField urlTextInput = new TextField();
        urlTextInput.setPrefColumnCount(25);

        Button addButton = new Button("Set binary message");
        addButton.setDisable(true);
        Button cancelButton = new Button("Cancel");

        hbox1.getChildren().addAll(label1, bookmarkNameTextInput);
        hbox2.getChildren().addAll(label2, urlTextInput);
        hbox3.getChildren().addAll(addButton, cancelButton);

        vbox.getChildren().addAll(title, hbox1, hbox2, hbox3);

        HBox dialogHbox = new HBox(20);
        dialogHbox.setAlignment(Pos.CENTER);

        bookmarkNameTextInput.setOnKeyReleased((EventHandler) event -> {
            if (!bookmarkNameTextInput.getText().equals("")) {
                addButton.setDisable(false);
            } else {
                addButton.setDisable(true);
            }
        });

        urlTextInput.setOnKeyReleased((EventHandler) event -> {
            if (!urlTextInput.getText().equals("")) {
                addButton.setDisable(false);
            } else {
                addButton.setDisable(true);
            }
        });

        addButton.addEventHandler(MouseEvent.MOUSE_CLICKED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e) {
                        System.out.println("data: " + bookmarkNameTextInput.getText());
                        dialog.close();
                    }
                });
        cancelButton.addEventHandler(MouseEvent.MOUSE_CLICKED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e) {
                        dialog.close();
                    }
                });

        Scene dialogScene = new Scene(vbox, 640, 480);
        //dialogScene.getStylesheets().add("//style sheet of your choice");
        dialog.setScene(dialogScene);
        dialog.show();
    }
}