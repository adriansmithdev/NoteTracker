package views;

import javafx.scene.layout.VBox;
import model.INote;

import java.util.List;

/**
 * @author Adrian Smith
 * @author Kyle Johnson
 * @version 1.0
 */
public interface INoteCreator {
    /**
     * @param note note being viewed
     * @return minimal version of note
     */
    VBox createSampleView(INote note);

    /**
     * @param note note being viewed
     * @return detailed version of note
     */
    VBox createExpandedView(INote note);

    /**
     * @return labels that belong to that note
     */
    List<String> getLabels();
}
