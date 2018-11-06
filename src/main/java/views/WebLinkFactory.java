package views;

import com.sun.java.swing.plaf.windows.DesktopProperty;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.INote;
import model.WebLink;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.awt.Desktop;

public class WebLinkFactory implements INoteCreator  {
    @Override
    public VBox createSampleView(INote note) {
        WebLink link = (WebLink) note;
        VBox linkView = new VBox();

        Label title = new Label(link.getTitle());

        linkView.getChildren().addAll(title);

        return linkView;
    }

    @Override
    public VBox createExpandedView(INote note) {
        WebLink link = (WebLink) note;
        VBox linkView = new VBox();
        Hyperlink hyperlink = new Hyperlink();

        Label title = new Label(link.getTitle());
        if(link.getURL().startsWith("https://")) {
            hyperlink.setText(link.getURL().substring(8));
        }else{
            hyperlink.setText(link.getURL().substring(7));
        }

        hyperlink.setOnAction(event -> {

            if(Desktop.isDesktopSupported()){
                try{
                    Desktop.getDesktop().browse(new URI(link.getURL()));
                } catch (URISyntaxException | IOException e) {
                    e.printStackTrace();
                }
            }
        });

        linkView.getChildren().addAll(title, hyperlink);

        return linkView;
    }

    @Override
    public List<String> getLabels() {
        ArrayList<String> result = new ArrayList<>();
        result.add("Title");
        result.add("URL");
        return result;
    }
}
