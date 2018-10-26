package views;

import controller.Controller;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.INote;

import java.util.List;
import java.util.Objects;

public class NoteTrackerUI extends Application {

    private static final int WIN_HEIGHT = 500;
    private static final int WIN_WIDTH = 700;

    private static final String STAGE_TITLE = "Notes";

    private static final String CSS_SHEET = "NoteTrackerUI.css";

    private static final String BEGIN_NOTE = "+ Note";
    private static final String CREATE_NOTE = "Create";
    private static final String INPUT_TITLE = "Title";
    private static final String INPUT_TYPE = "Card Type";
    private static final String INPUT_DESCRIPTION = "Description";

    private static final String FILTER_LABEL = "Filter";

    private static final int CARD_GAP = 8;
    private static final int PREF_COLUMNS = 3;

    private Stage stage;
    private Controller controller = new Controller();

    private static final String BUTTON_CLASS = "button-element";
    private static final String CREATE_ID = "create";
    private static final String CARD_CLASS = "card";
    private static final String HEADER_CLASS = "header";
    private static final String DESCRIPTION_CLASS = "describe";

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
        stage.setTitle(STAGE_TITLE);
        stage.show();
    }

    private Scene assembleScene()
    {
        BorderPane borderPane = new BorderPane();

        borderPane.setCenter(assembleCardViewing());
        borderPane.setLeft(assembleCreateNote());
        borderPane.setRight(assembleFilterOptions());

        Scene scene = new Scene(borderPane, WIN_WIDTH, WIN_HEIGHT);

        scene.getStylesheets().add(
                Objects.requireNonNull(getClass()
                        .getClassLoader()
                        .getResource(CSS_SHEET))
                        .toExternalForm()
        );

        return scene;
    }

    private TilePane assembleCardViewing() {
        TilePane tile = new TilePane();
        tile.setHgap(CARD_GAP);
        tile.setVgap(CARD_GAP);
        tile.setPrefColumns(PREF_COLUMNS);
        tile.setMaxWidth(Region.USE_PREF_SIZE);

        tile.getChildren().add(assembleSingleNote("Test Card!", "Test value!"));
        tile.getChildren().add(assembleSingleNote("Test Card2!", "Test longer value!"));
        tile.getChildren().add(assembleSingleNote("Test Card3!", "Test abcascdssdfaflksfjdafljks value!"));
        List<INote> notes = controller.getNotes();
        for (INote note : notes) {
            tile.getChildren().add(assembleSingleNote(note.getTitle(), "Test content"));
        }

        return tile;
    }

    private VBox assembleCreateNote() {
        VBox vBox = new VBox();

        Button beginNoteCreation = new Button(BEGIN_NOTE);
        Button createNote = new Button(CREATE_NOTE);

        beginNoteCreation.getStyleClass().add(BUTTON_CLASS);
        beginNoteCreation.setId(CREATE_ID);

        Label labelTitle = new Label(INPUT_TITLE);
        TextField title = new TextField();
        title.setPromptText(INPUT_TITLE);

        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.setPromptText(INPUT_TYPE);
        for (String CARD_TYPE : CARD_TYPES) {
            comboBox.getItems().add(CARD_TYPE);
        }

        Label labelDescription = new Label(INPUT_DESCRIPTION);
        TextField description = new TextField();
        description.setPromptText(INPUT_DESCRIPTION);

        vBox.getChildren().addAll(
                beginNoteCreation,
                labelTitle,
                title,
                comboBox,
                labelDescription,
                description,
                createNote
        );

        return vBox;
    }

    private VBox assembleFilterOptions() {
        VBox vBox = new VBox();
        vBox.getChildren().add(new Label(FILTER_LABEL));

        for (String CARD_TYPE : CARD_TYPES) {
            vBox.getChildren().add(new CheckBox(CARD_TYPE));
        }

        return vBox;
    }

    private VBox assembleSingleNote(String title, String content) {
        VBox vBox = new VBox();

        Label titleLabel = new Label(title);
        Label contentLabel = new Label(content);

        titleLabel.getStyleClass().add(HEADER_CLASS);
        contentLabel.getStyleClass().add(DESCRIPTION_CLASS);
        vBox.getStyleClass().add(CARD_CLASS);

        vBox.getChildren().addAll(titleLabel, contentLabel);

        return vBox;
    }
}
