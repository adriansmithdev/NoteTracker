package views;

import controller.Controller;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class NoteTrackerUI extends Application {

    public static final int WIN_HEIGHT = 500;
    public static final int WIN_WIDTH = 700;
    private Stage stage;
    private Controller controller = new Controller();
    private static final String BUTTON_CLASS = "button-element";
    private static final String CREATE_ID = "create";
    private static final String[] CARD_TYPES = {
            "Code Blocks",
            "Quotations",
            "To-Do Lists",
            "Web Links"
    };

    @Override
    public void start(Stage stage){
        this.stage = stage;
        stage.setScene(assembleScene());
        stage.setTitle("Notes");
        stage.show();
    }

    private Scene assembleScene()
    {
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(assembleCards());
        borderPane.setLeft(assembleLeftPane());
        borderPane.setRight(assembleFilterOptions());

        // add Notes
        Scene scene = new Scene(borderPane, WIN_WIDTH, WIN_HEIGHT);
        scene.getStylesheets().add(
                getClass().getClassLoader()
                        .getResource("NoteTrackerUI.css")
                        .toExternalForm()
        );

        return scene;
    }

    private TilePane assembleCards() {
        TilePane tile = new TilePane();
        tile.setHgap(8);
        tile.setPrefColumns(3);
        tile.setMaxWidth(Region.USE_PREF_SIZE);
        for (int i = 0; i < 20; i++) {
            tile.getChildren().add(new Label("TEST2"));
        }

        return tile;
    }

    private VBox assembleLeftPane() {
        VBox vBox = new VBox();
        Button createNote = new Button("+ Note");

        vBox.getChildren().addAll(createNote, assembleCreateNote());
        createNote.getStyleClass().add(BUTTON_CLASS);
        createNote.setId(CREATE_ID);

        return vBox;
    }

    private VBox assembleCreateNote() {
        VBox vBox = new VBox();
        vBox.getChildren().add(new Label("Title"));

        // title
        TextField title = new TextField();
        title.setPromptText("Title");
        vBox.getChildren().add(title);

        // note type dropdown


        return vBox;
    }

    private VBox assembleFilterOptions() {
        VBox vBox = new VBox();

        for (int i = 0; i < CARD_TYPES.length; i++) {
            vBox.getChildren().add(new CheckBox(CARD_TYPES[i]));
        }

        return vBox;
    }
}
