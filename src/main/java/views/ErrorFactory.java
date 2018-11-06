package views;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import model.INote;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Adrian Smith
 * @author Kyle Johnson
 *
 * @version 1.0
 */
public class ErrorFactory implements INoteCreator {
    @Override
    public VBox createSampleView(INote note) {
        VBox vBox = new VBox();
        vBox.getChildren().add(new Button("An error occurred"));
        return vBox;
    }

    @Override
    public VBox createExpandedView(INote note) {
        VBox vBox = new VBox();
        vBox.getChildren().add(new Button("An error occurred"));
        return vBox;
    }

    @Override
    public List<String> getLabels() {
        return new ArrayList<>();
    }
}
