package views;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.INote;

public class ErrorView implements INoteCreator {
    @Override
    public HBox createSampleView(INote note) {
        return null;
    }

    @Override
    public VBox createExpandedView(INote note) {
        return null;
    }
}