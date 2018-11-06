package views;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.CodeBlock;
import model.INote;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Adrian Smith
 * @author Kyle Johnson
 *
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
        Label dateCreated = new Label("Date Created: " + codeNote.getDateCreated());
        Text codeBlock = new Text(codeNote.getCode());

        expandedInfoView.getChildren().addAll(title, dateCreated, codeBlock);

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
