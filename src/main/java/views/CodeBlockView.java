package views;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class CodeBlockView implements IBaseNoteView {

    String title;
    String description;
    String codeSegment;
    String dateCreated;

    public CodeBlockView(String title, String description, String codeSegment, String dateCreated) {
        this.title = title;
        this.description = description;
        this.codeSegment = codeSegment;
        this.dateCreated = dateCreated;
    }

    @Override
    public HBox getHBoxRepresentation() {
        HBox panel = new HBox();
        Label titleLabel = new Label(title);
        Label date = new Label(dateCreated);

        panel.getChildren().addAll(titleLabel, date);

        return panel;
    }

    @Override
    public VBox getExpandedInfo() {
        VBox vBox = new VBox();

        return null;
    }
}
