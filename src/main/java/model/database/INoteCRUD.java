package model.database;

import model.INote;

public interface INoteCRUD {
    void addNote(INote note);
    void removeNote(INote note);
    void updateNote(INote note);
    INote getNote(INote note);
}
