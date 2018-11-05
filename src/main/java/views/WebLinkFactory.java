package views;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.INote;
import model.WebLink;

public class WebLinkFactory implements INoteCreator  {
    @Override
    public HBox createSampleView(INote note) {
        WebLink link = (WebLink) note;
        HBox linkView = new HBox();

        Label title = new Label(link.getTitle());

        linkView.getChildren().addAll(title);

        return linkView;
    }

    @Override
    public VBox createExpandedView(INote note) {
        WebLink link = (WebLink) note;
        VBox linkView = new VBox();

        Label title = new Label(link.getTitle());
        Text hyperlink = new Text(link.getURL());

        linkView.getChildren().addAll(title, hyperlink);

        return linkView;
    }
}
