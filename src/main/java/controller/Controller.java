package controller;

import model.INote;
import views.NoteTrackerUI;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    private List<INote> model;
    private NoteTrackerUI view;

    public Controller(NoteTrackerUI view) {
        this.view = view;
        model = new ArrayList<>();
    }

}
