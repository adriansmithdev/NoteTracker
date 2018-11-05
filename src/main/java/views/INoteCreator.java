package views;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.INote;

import java.util.ArrayList;
import java.util.List;

public interface INoteCreator {
    HBox createSampleView(INote note);
    VBox createExpandedView(INote note);
    List<String> getLabels();
}
