package launch;

import controller.Controller;
import javafx.application.Application;
import model.database.INoteCRUD;
import views.NoteTrackerUI;

/**
 * @author Adrian Smith
 * @author Kyle Johnson
 * @version 1.0
 */
public class Launcher {

    public static void main(String[] args) {
        Application.launch(NoteTrackerUI.class, args);
    }
}
