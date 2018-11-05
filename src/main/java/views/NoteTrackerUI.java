package views;

import controller.Controller;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Modality;
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

    private static final String INPUT_TYPE = "Card Type";
    private static final String[] labels_codeblock = {"Title", "Code"};
    private static final String[] labels_quote = {"Title", "Quote", "Author"};
    private static final String[] labels_todo = {"Title", "List"};
    private static final String[] labels_weblink = {"Title", "Link"};

    private static final String INPUT_TITLE_LABEL = "Title";
    private static final String INPUT_TITLE_ID = "title_id";
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
    private static final String LABEL_CLASS = "label";

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

        borderPane.getStyleClass().add("borderpane");

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
        tile.setId("tile-pane");

        return tile;
    }

    private VBox assembleStartNote() {
        VBox vBox = new VBox();
        VBox inputElementHolder = new VBox();
        inputElementHolder.setId("input_holder_id");

        Label header = new Label("Create Note");
        header.getStyleClass().add(HEADER_CLASS);

        Label typeHeader = new Label("Note Type");
        typeHeader.getStyleClass().add(LABEL_CLASS);

        vBox.getChildren().addAll(
                header,
                typeHeader,
                assembleTypeSelector(),
                inputElementHolder
        );

        vBox.setId("left_column");

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
        Button createNote = new Button("Create");
        createNote.getStyleClass().add(BUTTON_CLASS);
        createNote.setOnAction(event -> {});

        VBox title = createInputElement(INPUT_TITLE_LABEL, NoteInputType.HEADER);
        VBox description = createInputElement(INPUT_DESCRIPTION_LABEL, NoteInputType.CONTENT);
        VBox author = createInputElement("Author", NoteInputType.AUTHOR);
        VBox list = createToDoInput();

        VBox vBox = (VBox) getElement("input_holder_id");
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
        label.getStyleClass().add(LABEL_CLASS);

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

        Label filterLabel = new Label(FILTER_LABEL);
        filterLabel.getStyleClass().add(HEADER_CLASS);

        vBox.getChildren().add(filterLabel);

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
        TilePane tile = (TilePane) getElement("tile-pane");

        NoteFactory factory = new NoteFactory();
        tile.getChildren().clear();

        for (INote note : notes) {
            INoteCreator creator = factory.getNoteFor(note.getType());
            HBox hBox = creator.createSampleView(note);
            hBox.getStyleClass().addAll(CARD_CLASS);

            hBox.setOnMouseClicked(event -> {
                assembleModal(creator.createExpandedView(note));
            });

            tile.getChildren().add(hBox);
        }
    }

    private void assembleModal(VBox content) {
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.getChildren().add(content);

        Button backButton = new Button("Back");
        anchorPane.getChildren().add(backButton);
        AnchorPane.setLeftAnchor(backButton, 10.0);
        AnchorPane.setBottomAnchor(backButton, 10.0);

        stage.setScene(new Scene(anchorPane, WIN_WIDTH, WIN_HEIGHT));
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
