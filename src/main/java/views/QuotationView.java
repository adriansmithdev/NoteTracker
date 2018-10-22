package views;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class QuotationView implements IBaseNoteView {
    private String title;
    private String dateCreated;
    private String author;
    private String quote;

    public QuotationView(String title, String dateCreated, String author, String quote)
    {
        this.title = title;
        this.dateCreated = dateCreated;
        this.author = author;
        this.quote = quote;
    }

    public HBox getHBoxRepresentation()
    {
        HBox panel = new HBox();
        Label titleLabel = new Label(title);
        Label quoteLabel = new Label(quote.substring(0, 10) + "...");

        panel.getChildren().addAll(titleLabel, quoteLabel);

        return panel;
    }

    public VBox getExpandedInfo()
    {
        VBox panel = new VBox();

        panel.getChildren().addAll(new Label(title));
        panel.getChildren().addAll(new Label(quote + "\n-" + author));
        panel.getChildren().add(new Label(dateCreated));


        return panel;
    }
}
