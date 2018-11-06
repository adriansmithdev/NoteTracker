package controller;

import model.*;
import model.database.DBData;
import model.database.INoteCRUD;
import views.NoteFactory;
import views.NoteInputType;
import views.NoteTrackerUI;

import java.util.*;

public class Controller {
    private INoteCRUD model;
    private NoteTrackerUI ui;

    public Controller()
    {
        model = new DBData();
    }

    public void createNoteFromUI(Map<String, String> inputValues, Notes noteType) {
        List<String> labels = new NoteFactory().getNoteFor(noteType).getLabels();

        INote result = null;
        switch (noteType) {
            case QUOTATION:
                result = new Quotation(
                        inputValues.get(labels.get(0)),
                        inputValues.get(labels.get(1)),
                        inputValues.get(labels.get(2)),
                        "");
                break;
            case CODE_BLOCK:
                result = new CodeBlock(
                        inputValues.get(labels.get(0)),
                        "",
                        inputValues.get(labels.get(1)));
                break;
            case WEBLINK:
                result = new WebLink(
                        inputValues.get(labels.get(0)),
                        "",
                        inputValues.get(labels.get(1)));
                break;
            case TO_DO:
                String header = inputValues.get(labels.get(0));
                inputValues.remove(labels.get(0));
                inputValues.remove("CardType");

                List<ToDoItem> toDoItems = new ArrayList<>();

                for (String key : inputValues.keySet()) {
                    toDoItems.add(new ToDoItem(inputValues.get(key), false));
                }

                result = new ToDo(
                        header,
                        "",
                        toDoItems
                );
                break;
        }

        model.createNote(result);
    }

    public void addNote(INote note)
    {
        model.createNote(note);
    }

    public List<INote> getNotes()
    {
        return model.getNotes();
    }
    
    public List<INote> getNotes(Notes typeOfNote)
    {
        return model.getNotes(typeOfNote);
    }
    
    public boolean removeNote(INote note)
    {
        return model.removeNote(note);
    }

    public void updateNote(INote note)
    {
        model.updateNote(note);
    }
}
