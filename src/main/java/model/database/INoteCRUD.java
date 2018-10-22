package model.database;

import model.INote;

import java.util.List;

public interface INoteCRUD {
    void addNote(INote note);
    void removeNote(INote note);
    void updateNote(INote note);
    List<INote> getNotes(INote note);
}
