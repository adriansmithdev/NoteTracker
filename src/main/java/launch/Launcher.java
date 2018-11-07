package launch;

import javafx.application.Application;
import views.NoteTrackerUI;

/**
 * @author Adrian Smith
 * @author Kyle Johnson
 * @version 1.0
 */
public class Launcher {

    /**
     * Launches the program window
     *
     * @param args the options to start program with
     */
    public static void main(String[] args) {
        Application.launch(NoteTrackerUI.class, args);
    }
}
