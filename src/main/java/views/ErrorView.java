package views;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.INote;

import java.util.ArrayList;
import java.util.List;

public class ErrorView implements INoteCreator {
    @Override
    public HBox createSampleView(INote note) {
        HBox hBox = new HBox();
        hBox.getChildren().add(new Button("An error occurred"));
        return hBox;
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
