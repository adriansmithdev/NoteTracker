package views;

import javafx.scene.layout.HBox;
import model.INote;

public interface INoteCreator {
    HBox createView(INote note);
}
