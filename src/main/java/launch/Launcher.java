package launch;

import controller.Controller;
import javafx.application.Application;
import model.database.INoteCRUD;
import views.NoteTrackerUI;

public class Launcher {

    public static void main(String[] args) {
        Application.launch(NoteTrackerUI.class, args);
    }
}
