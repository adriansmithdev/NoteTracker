package views;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.CodeBlock;
import model.INote;

public class CodeBlockFactory implements INoteCreator {

    @Override
    public HBox createSampleView(INote note) {
        HBox sampleView = new HBox();
        CodeBlock codeNote = (CodeBlock) note;

        Label title = new Label(codeNote.getTitle());
        Text codePreview = new Text(codeNote.getCode().substring(0, 10) + "...");

        sampleView.getChildren().add(title);

        return sampleView;
    }

    @Override
    public VBox createExpandedView(INote note) {
        VBox expandedInfoView = new VBox();
        CodeBlock codeNote = (CodeBlock) note;

        Label title = new Label(codeNote.getTitle());
        Label dateCreated = new Label("Date Created: " + codeNote.getDateCreated());
        Text codeBlock = new Text(codeNote.getCode());

        expandedInfoView.getChildren().addAll(title, dateCreated, codeBlock);

        return expandedInfoView;
    }


}
