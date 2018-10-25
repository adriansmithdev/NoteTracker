package views;

import controller.Controller;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
        Button beginNote = new Button("+ Note");
        Button createNote = new Button("Create");

        vBox.getChildren().addAll(beginNote, assembleCreateNote(), createNote);
        beginNote.getStyleClass().add(BUTTON_CLASS);
        beginNote.setId(CREATE_ID);

        return vBox;
    }

    private VBox assembleCreateNote() {
        VBox vBox = new VBox();

        // title
        TextField title = new TextField();
        title.setPromptText("Title");

        // note type dropdown
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.setPromptText("Card Type");
        for (String CARD_TYPE : CARD_TYPES) {
            comboBox.getItems().add(CARD_TYPE);
        }

        TextField description = new TextField();
        description.setPromptText("Description");

        vBox.getChildren().addAll(new Label("Title"), title, comboBox,  new Label("Description"), description);

        return vBox;
    }

    private VBox assembleFilterOptions() {
        VBox vBox = new VBox();
        vBox.getChildren().add(new Label("Filter"));

        for (String CARD_TYPE : CARD_TYPES) {
            vBox.getChildren().add(new CheckBox(CARD_TYPE));
        }

        return vBox;
    }

    private VBox assembleSingleNote(String title, String content) {
        VBox vBox = new VBox();

        Label titleLabel = new Label(title);
        Label contentLabel = new Label(content);

        vBox.getChildren().addAll(titleLabel, contentLabel);

        return vBox;
    }
}
