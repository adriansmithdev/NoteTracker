package views;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.CodeBlock;
import model.INote;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Adrian Smith
 * @author Kyle Johnson
 * @version 1.0
 */
public class CodeBlockFactory implements INoteCreator {

    @Override
    public VBox createSampleView(INote note) {
        VBox sampleView = new VBox();
        CodeBlock codeNote = (CodeBlock) note;

        Label title = new Label(codeNote.getTitle());
        Label codePreview = new Label(codeNote.getCode());

        sampleView.getChildren().addAll(title, codePreview);

        return sampleView;
    }

    @Override
    public VBox createExpandedView(INote note) {
        VBox expandedInfoView = new VBox();
        CodeBlock codeNote = (CodeBlock) note;

        Label title = new Label(codeNote.getTitle());
        title.getStyleClass().add("header");
        Label dateCreated = new Label("Date Created: " + codeNote.getDateCreated());
        dateCreated.getStyleClass().add("italic");
        Label subHeader = new Label("Code:");
        subHeader.getStyleClass().add("h3");
        Label codeBlock = new Label(codeNote.getCode());
        codeBlock.getStyleClass().add("codeSnippet");

        expandedInfoView.getChildren().addAll(title, dateCreated, subHeader, codeBlock);

        return expandedInfoView;
    }

    @Override
    public List<String> getLabels() {
        ArrayList<String> result = new ArrayList<>();
        result.add("Title");
        result.add("Code");
        return result;
    }

}
