package views;

import controller.Controller;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.INote;
import model.Notes;

import java.util.*;

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

    private static final String TYPE_HOLDER_ID = "type_holder_id";
    private static final String INPUTS_HOLDER_ID = "input_holder_id";

    private static final String CREATE_NOTE = "Create";
    private static final String CREATE_NOTE_ID = "complete-note";

    private static final String INPUT_TITLE_LABEL = "Title";
    private static final String INPUT_TITLE_ID = "title_id";
    private static final String INPUT_TYPE = "Card Type";
    private static final String INPUT_TYPE_ID = "type_id";
    private static final String INPUT_DESCRIPTION_LABEL = "Description";
    private static final String INPUT_DESCRIPTION_ID = "description_id";
    private static final String INPUT_AUTHOR_LABEL = "Author";
    private static final String INPUT_AUTHOR_ID = "author_id";

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
    private Map<NoteInputType, String> inputValues;
    private Set<Notes> currentFilters;

    @Override
    public void start(Stage stage){
        controller = new Controller();
        this.stage = stage;
        this.scene = assembleScene();
        inputValues = new HashMap<>();
        currentFilters = new HashSet<>();

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
        borderPane.setLeft(assembleStartNote());
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

    private VBox assembleStartNote() {
        VBox vBox = new VBox();
        VBox inputElementHolder = new VBox();
        inputElementHolder.setId(INPUTS_HOLDER_ID);

        Label header = new Label("Create Note");
        Label typeHeader = new Label("Note Type");

        vBox.getChildren().addAll(
                header,
                typeHeader,
                assembleTypeSelector(),
                inputElementHolder
        );

        return vBox;
    }

    private Node assembleTypeSelector() {
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.setPromptText(INPUT_TYPE);
        for (Notes noteType : Notes.values()) {
            comboBox.getItems().add(noteType.name());
        }

        comboBox.setOnAction(event -> {
            inputValues.clear();
            inputValues.put(NoteInputType.CARD_TYPE, comboBox.getValue());
            assembleNoteOptions(Notes.valueOf(comboBox.getValue()));
        });

        return comboBox;
    }

    private void assembleNoteOptions(Notes noteType) {
        Button createNote = new Button(CREATE_NOTE);
        createNote.setId(CREATE_NOTE_ID);
        createNote.setOnAction(event -> {});

        VBox title = createInputElement(INPUT_TITLE_LABEL, NoteInputType.HEADER);
        VBox description = createInputElement(INPUT_DESCRIPTION_LABEL, NoteInputType.CONTENT);
        VBox author = createInputElement("Author", NoteInputType.AUTHOR);
        VBox list = createToDoInput();

        VBox vBox = (VBox) getElement(INPUTS_HOLDER_ID);
        vBox.getChildren().clear();

        vBox.getChildren().addAll(
                title,
                description
        );

        if (noteType == Notes.QUOTATION) {
            vBox.getChildren().add(author);
        }

        if (noteType == Notes.TO_DO) {
            vBox.getChildren().add(list);
        }

        vBox.getChildren().add(createNote);
    }

    private VBox createInputElement(String labelText, NoteInputType type) {
        Label label = new Label(labelText);

        TextField inputElement = new TextField();
        inputElement.setPromptText(labelText);

        inputElement.setOnAction(value -> {
            inputValues.put(type, inputElement.getText());
        });

        return new VBox(label, inputElement);
    }

    private VBox createToDoInput() {
        VBox elements = new VBox();

        TextField textField = new TextField();
        textField.setPromptText("Enter item to do...");

        Button addItemInput = new Button("Add another item");
        elements.getChildren().addAll(textField, addItemInput);

        return elements;
    }

    private VBox assembleFilterOptions() {
        VBox vBox = new VBox();
        vBox.getChildren().add(new Label(FILTER_LABEL));

        for (Notes noteType : Notes.values()) {
            CheckBox sortSelector = new CheckBox(noteType.name());

            sortSelector.setOnAction(event -> {
                handleFilterClick(sortSelector, noteType);
            });

            vBox.getChildren().add(sortSelector);
        }

        return vBox;
    }

    private void handleFilterClick(CheckBox box, Notes noteType) {
        if (box.isSelected()) {
            currentFilters.add(noteType);
        } else {
            currentFilters.remove(noteType);
        }

        updateNotes(notesForCurrentFilters());
    }

    private List<INote> notesForCurrentFilters() {
        List<INote> result = new ArrayList<>();

        if (currentFilters.isEmpty()) {
            return controller.getNotes();
        }

        for (Notes note : currentFilters) {
            result.addAll(controller.getNotes(note));
        }

        return result;
    }

    private void updateNotes(List<INote> notes) {
        TilePane tile = (TilePane) getElement(TILEPANE_ID);

        NoteFactory factory = new NoteFactory();
        tile.getChildren().clear();

        for (INote note : notes) {
            HBox hBox = factory.getNoteFor(note.getType()).createSampleView(note);
            hBox.getStyleClass().add(CARD_CLASS);
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

    private Node getElement(String elementId) {
        return this.scene.lookup(String.format("#%s", elementId));
    }
}
