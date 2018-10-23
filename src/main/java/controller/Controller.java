package controller;

import model.INote;
import views.NoteTrackerUI;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    private List<INote> model; // Change List<INote> to INoteCRUD and implement the interface in another class???


    public Controller()
    {
        model = new ArrayList<>();
    }

    public List<INote> getNotes()
    {
        return model; // Shouldn't return model itself, need to have model.getNotes instead
    }
}
