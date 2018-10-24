package views;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.INote;

public interface INoteCreator {
    HBox createSampleView(INote note);
    VBox createExpandedView(INote note);
}
