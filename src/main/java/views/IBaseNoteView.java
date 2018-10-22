package views;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public interface IBaseNoteView {
    HBox getHBoxRepresentation();
    VBox getExpandedInfo();
}
