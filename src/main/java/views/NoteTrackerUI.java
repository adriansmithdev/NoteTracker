package views;

import controller.Controller;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class NoteTrackerUI extends Application {

    public static final int WIN_HEIGHT = 500;
    public static final int WIN_WIDTH = 700;
    private Stage stage;
    private Controller controller = new Controller();
    private static final String BUTTON_CLASS = "button-element";
    private static final String HEADER_ID = "header";
    private static final String FILTER_ID = "filter";
    private static final String CREATE_ID = "create";

    @Override
    public void start(Stage stage){
        this.stage = stage;
        stage.setScene(assembleScene());
        stage.setTitle("NOTES");
        stage.show();
    }

    private Scene assembleScene()
    {
        VBox panel = new VBox();

        // add Notes
        panel.getChildren().addAll(assembleMenuBar(), assembleCards());

        Scene scene = new Scene(panel, WIN_WIDTH, WIN_HEIGHT);
        scene.getStylesheets().add(
                getClass().getClassLoader()
                        .getResource("NoteTrackerUI.css")
                        .toExternalForm()
        );

        return scene;
    }



    private BorderPane assembleMenuBar()
    {
        BorderPane bar = new BorderPane();
        Button createNote = new Button("+ Note");
        Button filterNotes = new Button("Filter");

        createNote.getStyleClass().add(BUTTON_CLASS);
        filterNotes.getStyleClass().add(BUTTON_CLASS);
        bar.setId(HEADER_ID);
        filterNotes.setId(FILTER_ID);
        createNote.setId(CREATE_ID);

        bar.setLeft(createNote);
        bar.setRight(filterNotes);

        return bar;
    }

    private TilePane assembleCards() {
//        TilePane pane = new TilePane();
//        pane.setPrefColumns(2);
//        pane.setPrefWidth(8);
//        pane.getChildren().addAll(
//                new Label("TEST1"),
//                new Label("TEST2"),
//                new Label("TEST3"),
//                new Label("TEST4"),
//                new Label("TEST5")
//        );

        TilePane tile = new TilePane();
        tile.setHgap(8);
        tile.setPrefColumns(4);
        for (int i = 0; i < 20; i++) {
            tile.getChildren().add(new Label("TEST2"));
        }

        return tile;
    }
}
