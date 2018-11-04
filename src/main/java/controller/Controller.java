package controller;

import javafx.application.Application;
import model.*;
import model.database.DBData;
import model.database.INoteCRUD;
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

    public void createNoteFromUI(Map<NoteInputType, String> inputValues) {
        String cardType = inputValues.get(NoteInputType.CARD_TYPE);
        Notes cardTypeAsEnum = Notes.valueOf(cardType);

        INote result = null;
        switch (cardTypeAsEnum) {
            case QUOTATION:
                result = new Quotation(
                        inputValues.get(NoteInputType.HEADER),
                        inputValues.get(NoteInputType.CONTENT),
                        inputValues.get(NoteInputType.AUTHOR),
                        "");
                break;
            case CODE_BLOCK:
                result = new CodeBlock(
                        inputValues.get(NoteInputType.HEADER),
                        "",
                        inputValues.get(NoteInputType.CONTENT));
                break;
            case WEBLINK:
                result = new WebLink(
                        inputValues.get(NoteInputType.HEADER),
                        "",
                        inputValues.get(NoteInputType.CONTENT));
                break;
            case TO_DO:
                List<ToDoItem> toDoItems = new ArrayList<>();
                result = new ToDo(
                        inputValues.get(NoteInputType.HEADER),
                        "",
                        toDoItems
                );
                break;
        }

        model.addNote(result);
    }

    public void addNote(INote note)
    {
        model.addNote(note);
    }

    public void addNote(Map<NoteInputType, String> inputValues) {

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
}
