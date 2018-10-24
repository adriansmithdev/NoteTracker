package views;

import controller.Controller;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.INote;

import java.util.ArrayList;
import java.util.List;

public class NoteTrackerUI extends Application {

    public static final int WIN_HEIGHT = 500;
    public static final int WIN_WIDTH = 700;
    private Stage stage;
    private Controller controller = new Controller();

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

        return new Scene(panel, WIN_WIDTH, WIN_HEIGHT);
    }



    private HBox assembleMenuBar()
    {
        HBox bar = new HBox();
        Button createNoteBtn = new Button("+ Note");



        return bar;
    }
}
