package controller;

import model.CodeBlock;
import model.INote;
import model.Notes;
import model.Quotation;
import model.database.DBData;
import model.database.INoteCRUD;
import views.NoteInputType;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

public class Controller {
    private INoteCRUD model; // Change List<INote> to INoteCRUD and implement the interface in another class???

    public Controller()
    {
        model = new DBData();
    }

    public void addNote(INote note)
    {
        model.addNote(note);
    }

    public void addNote(Map<NoteInputType, String> inputValues) {
        String cardType = inputValues.get(NoteInputType.CARD_TYPE);
        Notes cardTypeAsEnum = Notes.valueOf(cardType);

        INote result = null;
        String currentTime = Long.toString(Calendar.getInstance().getTimeInMillis());

        if (cardTypeAsEnum == Notes.QUOTATION) {
            result = new Quotation(
                    inputValues.get(NoteInputType.HEADER),
                    inputValues.get(NoteInputType.CONTENT),
                    "Hard_Type_Value",
                    "");
        } else if (cardTypeAsEnum == Notes.CODE_BLOCK) {
            result = new CodeBlock(
                    inputValues.get(NoteInputType.HEADER),
                    "",
                    inputValues.get(NoteInputType.CONTENT));
        }

        if (result == null) {
            throw new IllegalStateException("Current card type not supported");
        }

        model.addNote(result);
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
