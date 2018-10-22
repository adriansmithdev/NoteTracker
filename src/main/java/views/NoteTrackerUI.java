package views;

import controller.Controller;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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
        QuotationView quote = new QuotationView("TITLE", "2018-10-22", "ME", "something inspiring");
        panel.getChildren().add(quote.getExpandedInfo());

        return new Scene(panel, WIN_WIDTH, WIN_HEIGHT);
    }


}
