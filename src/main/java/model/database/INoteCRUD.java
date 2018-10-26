package model.database;

import model.INote;
import model.Notes;

import java.util.List;

public interface INoteCRUD {
    void addNote(INote note);
    boolean removeNote(INote note);
    void updateNote(INote note);
    List<INote> getNotes();
    List<INote> getNotes(Notes typeOfNote);
}
