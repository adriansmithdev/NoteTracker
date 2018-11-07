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
import model.SortByDateTime;

import java.util.*;

/**
 * @author Kyle Johnson
 * @author Adrian Smith
 * @version 1.0
 */
public class NoteTrackerUI extends Application {

    private static final int WIN_HEIGHT = 600;
    private static final int WIN_WIDTH = 800;

    private static final String STAGE_TITLE = "Notes";
    private static final String CSS_SHEET = "NoteTrackerUI.css";

    private static final String INPUT_TYPE = "Card Type";

    private static final int CARD_GAP = 8;
    private static final int PREF_COLUMNS = 10;

    private static final String BUTTON_CLASS = "button-element";
    private static final String CARD_CLASS = "card";
    private static final String HEADER_CLASS = "header";
    private static final String LABEL_CLASS = "label";

    private static final String FILTER_LABEL = "Filter";
    public static final double ANCHOR_DISTANCE = 10.0;

    private Stage stage;
    private BorderPane borderPane;
    private Scene scene;
    private Controller controller;
    private Map<String, String> inputValues = new HashMap<>();
    private Set<Notes> currentFilters = new HashSet<>();

    @Override
    public void start(Stage stage) {
        controller = new Controller();
        this.stage = stage;
        this.scene = assembleScene();

        stage.setScene(this.scene);
        stage.setTitle(STAGE_TITLE);
        stage.show();
        updateNotes(controller.getNotes());
    }

    private Scene assembleScene() {
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

    private ScrollPane assembleCardViewing() {
        ScrollPane scrollPane = new ScrollPane();

        TilePane tile = new TilePane();
        tile.setHgap(CARD_GAP);
        tile.setVgap(CARD_GAP);
        tile.setPrefColumns(PREF_COLUMNS);
        tile.setMaxWidth(Region.USE_PREF_SIZE);
        tile.setId("tile-pane");
        tile.getStyleClass().add("tilepane");

        scrollPane.setContent(tile);
        scrollPane.setFitToWidth(true);

        return scrollPane;
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
        comboBox.setId("ComboBox");

        comboBox.setOnAction(event -> {
            if (comboBox.getValue() != null) {
                inputValues.clear();
                inputValues.put("CardType", comboBox.getValue());
                assembleNoteOptions(Notes.valueOf(comboBox.getValue()));
            }
        });

        return comboBox;
    }

    private void assembleNoteOptions(Notes noteType) {
        Button createNote = new Button("Create");
        createNote.getStyleClass().add(BUTTON_CLASS);
        createNote.setOnAction(event -> {
            controller.createNoteFromUI(inputValues, noteType);
            updateNotes(notesForCurrentFilters());
            resetInput();
        });

        VBox vBox = (VBox) getElement("input_holder_id");
        vBox.getChildren().clear();

        List<String> labels = new NoteFactory().getNoteFor(noteType).getLabels();

        if (noteType == Notes.TO_DO) {
            VBox title = createInputElement(labels.get(0));
            title.getChildren().add(new Label(labels.get(1)));
            VBox list = createToDoInput();
            vBox.getChildren().addAll(title, list);
        } else {
            for (String label : labels) {
                VBox element = createInputElement(label);
                vBox.getChildren().add(element);
            }
        }

        vBox.getChildren().add(createNote);
    }

    private void resetInput() {
        @SuppressWarnings("unchecked")
        ComboBox<String> comboBox = (ComboBox<String>) getElement("ComboBox");
        comboBox.getSelectionModel().clearSelection();
        inputValues.clear();
        VBox vBox = (VBox) getElement("input_holder_id");
        vBox.getChildren().clear();
    }

    private VBox createInputElement(String labelText) {
        Label label = new Label(labelText);
        label.getStyleClass().add(LABEL_CLASS);

        TextField inputElement = new TextField();
        inputElement.setPromptText(labelText);

        inputElement.textProperty().addListener((observable, oldValue, newValue) ->
                inputValues.put(labelText, newValue));

        return new VBox(label, inputElement);
    }

    private VBox createToDoInput() {
        VBox elements = new VBox();

        TextField textField = createToDoRandomItem();

        Button addItemInput = new Button("Add Item");
        addItemInput.setOnAction(event -> {
            elements.getChildren().remove(addItemInput);
            elements.getChildren().add(createToDoRandomItem());
            elements.getChildren().add(addItemInput);
        });

        elements.getChildren().addAll(textField, addItemInput);

        return elements;
    }

    private TextField createToDoRandomItem() {
        TextField textField = new TextField();
        textField.setPromptText("Enter item to do...");
        String toDoItemID = UUID.randomUUID().toString();
        textField.textProperty().addListener((observable, oldValue, newValue) ->
                inputValues.put(toDoItemID, newValue));

        return textField;
    }

    private VBox assembleFilterOptions() {
        VBox vBox = new VBox();
        vBox.getStyleClass().add("filterColumn");

        Label filterLabel = new Label(FILTER_LABEL);
        filterLabel.getStyleClass().add(HEADER_CLASS);

        vBox.getChildren().add(filterLabel);

        for (Notes noteType : Notes.values()) {
            CheckBox sortSelector = new CheckBox(noteType.name());

            sortSelector.setOnAction(event -> handleFilterClick(sortSelector, noteType));

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

        result.sort(new SortByDateTime());

        return result;
    }

    private void updateNotes(List<INote> notes) {
        TilePane tile = (TilePane) getElement("tile-pane");

        NoteFactory factory = new NoteFactory();
        tile.getChildren().clear();

        for (INote note : notes) {
            INoteCreator creator = factory.getNoteFor(note.getType());
            VBox vBox = creator.createSampleView(note);
            vBox.getStyleClass().addAll(CARD_CLASS);

            vBox.setOnMouseClicked(event -> assembleModal(creator.createExpandedView(note), note));

            tile.getChildren().add(vBox);
        }
    }

    private void assembleModal(VBox content, INote note) {
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.getStyleClass().add("anchor");

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(content);
        anchorPane.getChildren().add(scrollPane);
        AnchorPane.setTopAnchor(scrollPane, ANCHOR_DISTANCE);
        AnchorPane.setLeftAnchor(scrollPane, ANCHOR_DISTANCE);
        AnchorPane.setRightAnchor(scrollPane, ANCHOR_DISTANCE);

        Button backButton = new Button("Back");
        backButton.getStyleClass().add("button-element");
        backButton.setOnAction(event -> {
            controller.updateNote(note);
            stage.setScene(scene);
            updateNotes(notesForCurrentFilters());
        });

        anchorPane.getChildren().add(backButton);
        AnchorPane.setLeftAnchor(backButton, ANCHOR_DISTANCE);
        AnchorPane.setBottomAnchor(backButton, ANCHOR_DISTANCE);

        Scene sceneExtra = new Scene(anchorPane, WIN_WIDTH, WIN_HEIGHT);

        sceneExtra.getStylesheets().add(
                Objects.requireNonNull(getClass()
                        .getClassLoader()
                        .getResource(CSS_SHEET))
                        .toExternalForm()
        );

        stage.setScene(sceneExtra);
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
