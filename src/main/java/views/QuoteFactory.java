package views;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import model.INote;
import model.Quotation;

public class QuoteFactory implements INoteCreator {

    @Override
    public HBox createView(INote note)
    {
        Quotation quote = (Quotation) note;
        HBox noteView = new HBox();

        Label title = new Label(quote.getTitle());
        Label quoteSample = new Label("\"" + quote.getQuote().substring(0, 10) + "...\"");

        noteView.getChildren().addAll(title, quoteSample);

        return noteView;
    }
}
