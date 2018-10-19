package launch;

import controller.Controller;
import javafx.application.Application;
import model.database.INoteCRUD;
import views.NoteTrackerUI;

public class Launcher {

    public static void main(String[] args) {
        NoteTrackerUI view = new NoteTrackerUI();
        Controller controller = new Controller(view);
        Application.launch(view.getClass(), args);
    }
}
