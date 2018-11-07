package controller;

import model.*;
import model.database.DBData;
import model.database.INoteCRUD;
import views.NoteFactory;

import java.util.*;

/**
 * @author Adrian Smith
 * @author Kyle Johnson
 * @version 1.0
 */
public class Controller {
    private INoteCRUD model;

    /**
     * just constructs by instantiating model with DBData()
     */
    public Controller() {
        model = new DBData();
    }

    /**
     * @param inputValues user input from UI
     * @param noteType    type of note being generated
     */
    public void createNoteFromUI(Map<String, String> inputValues, Notes noteType) {
        List<String> labels = new NoteFactory().getNoteFor(noteType).getLabels();

        String titleLabel = labels.get(0);
        String describerContent = labels.get(1);

        INote result = null;
        switch (noteType) {
            case QUOTATION:
                String author = labels.get(2);
                result = new Quotation(
                        inputValues.get(titleLabel),
                        inputValues.get(describerContent),
                        inputValues.get(author),
                        "");
                break;
            case CODE_BLOCK:
                result = new CodeBlock(
                        inputValues.get(titleLabel),
                        "",
                        inputValues.get(describerContent));
                break;
            case WEBLINK:
                result = new WebLink(
                        inputValues.get(titleLabel),
                        "",
                        inputValues.get(describerContent));
                break;
            case TO_DO:
                String header = inputValues.get(titleLabel);
                inputValues.remove(titleLabel);
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

    /**
     * @return List object of all notes
     */
    public List<INote> getNotes() {
        return model.getNotes();
    }

    /**
     * @param typeOfNote type of Note being retrieved from database
     * @return List object of type typeOfNote
     */
    public List<INote> getNotes(Notes typeOfNote) {
        return model.getNotes(typeOfNote);
    }

    /**
     * @param note INote object being updated
     */
    public void updateNote(INote note) {
        model.updateNote(note);
    }

    @Override
    public String toString() {
        return "Controller{" +
                "model=" + model +
                '}';
    }
}
