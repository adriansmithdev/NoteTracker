package views;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.INote;
import model.Quotation;

public class QuoteFactory implements INoteCreator {

    @Override
    public HBox createSampleView(INote note)
    {
        Quotation quote = (Quotation) note;
        HBox noteView = new HBox();

        Label title = new Label(quote.getTitle());
        Text quoteSample = new Text("\"" + quote.getQuote().substring(0, 10) + "...\"");

        noteView.getChildren().addAll(title, quoteSample);

        return noteView;
    }

    public VBox createExpandedView(INote note)
    {
        Quotation quote = (Quotation) note;
        VBox expandedView = new VBox();

        Label title = new Label(quote.getTitle());
        Label dateCreated = new Label("Date Created: " + quote.getDateCreated());
        Text completeNote = new Text("\"" + quote.getQuote() + "\"\n" + quote.getAuthor());

        expandedView.getChildren().addAll(title, dateCreated, completeNote);

        return expandedView;
    }
}
