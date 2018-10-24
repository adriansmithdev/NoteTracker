package controller;

import model.INote;
import model.database.DBData;
import model.database.INoteCRUD;
import views.NoteTrackerUI;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    private INoteCRUD model; // Change List<INote> to INoteCRUD and implement the interface in another class???


    public Controller()
    {
        model = new DBData();
    }

    public List<INote> getNotes()
    {
        return model.getNotes();
    }
}
