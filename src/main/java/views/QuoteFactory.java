package views;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.INote;
import model.Quotation;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Adrian Smith
 * @author Kyle Johnson
 *
 * @version 1.0
 */
public class QuoteFactory implements INoteCreator {

    @Override
    public VBox createSampleView(INote note)
    {
        Quotation quote = (Quotation) note;
        VBox noteView = new VBox();

        Label title = new Label(quote.getTitle());
        Text quoteSample = new Text("\"" + quote.getQuote().substring(0, 10) + "...\"");

        noteView.getChildren().addAll(title, quoteSample);

        return noteView;
    }

    @Override
    public VBox createExpandedView(INote note)
    {
        Quotation quote = (Quotation) note;
        VBox expandedView = new VBox();

        Label title = new Label(quote.getTitle());
        Text completeNote = new Text("\"" + quote.getQuote() + "\"\n" + quote.getAuthor());

        expandedView.getChildren().addAll(title, completeNote);

        return expandedView;
    }

    @Override
    public List<String> getLabels() {
        ArrayList<String> result = new ArrayList<>();
        result.add("Title");
        result.add("Quote");
        result.add("Author");
        return result;
    }
}
