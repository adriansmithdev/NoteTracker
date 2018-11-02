package views;

import controller.Controller;
import javafx.event.ActionEvent;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.INote;
import model.Notes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author Kyle, Adrian
 * @version 1
 */
public class NoteTrackerUI extends Application {

    private static final int WIN_HEIGHT = 500;
    private static final int WIN_WIDTH = 700;

    private static final String STAGE_TITLE = "Notes";

    private static final String CSS_SHEET = "NoteTrackerUI.css";

    private static final String TILEPANE_ID = "tile-pane";

    private static final String BEGIN_NOTE = "+ Note";
    private static final String BEGIN_NOTE_ID = "create";

    private static final String CREATE_NOTE = "Create";
    private static final String CREATE_NOTE_ID = "complete-note";

    private static final String INPUT_TITLE = "Title";
    private static final String INPUT_TITLE_ID = "title_id";

    private static final String INPUT_TYPE = "Card Type";
    private static final String INPUT_TYPE_ID = "type_id";

    private static final String INPUT_DESCRIPTION = "Description";
    private static final String INPUT_DESCRIPTION_ID = "description_id";

    private static final int CARD_GAP = 8;
    private static final int PREF_COLUMNS = 3;

    private static final String BUTTON_CLASS = "button-element";
    private static final String CARD_CLASS = "card";
    private static final String HEADER_CLASS = "header";
    private static final String DESCRIPTION_CLASS = "describe";

    private static final String FILTER_LABEL = "Filter";

    private Stage stage;
    private BorderPane borderPane;
    private Scene scene;
    private Controller controller;

    @Override
    public void start(Stage stage){
        controller = new Controller();
        this.stage = stage;
        this.scene = assembleScene();
        stage.setScene(this.scene);
        stage.setTitle(STAGE_TITLE);
        stage.show();
        updateNotes(controller.getNotes());
    }

    private Scene assembleScene()
    {
        BorderPane borderPane = new BorderPane();
        this.borderPane = borderPane;

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
        tile.setId(TILEPANE_ID);

        return tile;
    }

    private VBox assembleCreateNote() {
        VBox vBox = new VBox();

        Button beginNoteCreation = new Button(BEGIN_NOTE);
        Button createNote = new Button(CREATE_NOTE);
        createNote.setId(CREATE_NOTE_ID);
        createNote.setOnAction(event -> {});

        beginNoteCreation.getStyleClass().add(BUTTON_CLASS);
        beginNoteCreation.setId(BEGIN_NOTE_ID);

        Label labelTitle = new Label(INPUT_TITLE);
        TextField title = new TextField();
        title.setPromptText(INPUT_TITLE);

        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.setPromptText(INPUT_TYPE);
        for (Notes noteType : Notes.values()) {
            comboBox.getItems().add(noteType.name());
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

        for (Notes noteType : Notes.values()) {
            vBox.getChildren().add(new CheckBox(noteType.name()));
        }

        return vBox;
    }

    /**
     * Pulls all data from view that has been input
     * @return Map of all data entered
     */
    public Map<NoteInputType, String> getInputData() {
        HashMap<NoteInputType, String> data = new HashMap<>();

        @SuppressWarnings("unchecked")
        ComboBox<String> comboBox = (ComboBox<String>) this.scene.lookup(String.format("#%s", INPUT_TYPE_ID));
        data.put(NoteInputType.CARD_TYPE, comboBox.getValue());

        TextField title = (TextField) this.scene.lookup(String.format("#%s", INPUT_TITLE_ID));
        data.put(NoteInputType.HEADER, title.getText());

        TextField description = (TextField) this.scene.lookup(String.format("#%s", INPUT_DESCRIPTION_ID));
        data.put(NoteInputType.CONTENT, description.getText());

        return data;
    }

    private void updateNotes(List<INote> notes) {
        TilePane tile = (TilePane) this.scene.lookup(String.format("#%s", TILEPANE_ID));

        NoteFactory factory = new NoteFactory();

        for (INote note : notes) {
            String noteName = note.getType().name();
            HBox hBox = factory.getNoteFor(noteName).createSampleView(note);
            tile.getChildren().add(hBox);
        }
    }

    @Override
    public String toString() {
        return "NoteTrackerUI{" +
                "stage=" + stage +
                ", borderPane=" + borderPane +
                ", scene=" + scene +
                '}';
    }
}
