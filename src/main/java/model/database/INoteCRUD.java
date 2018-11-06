package model.database;

import model.INote;
import model.Notes;

import java.util.List;

/**
 * @author Adrian Smith
 * @author Kyle Johnson
 *
 * @version 1.0
 */
public interface INoteCRUD {
    /**
     * @param note INote being created.
     */
    void createNote(INote note);

    /**
     * @param note INote being removed.
     * @return true if it was removed, false if not
     */
    boolean removeNote(INote note);

    /**
     * @param note INote being updated
     */
    void updateNote(INote note);

    /**
     * @return List object of all INotes
     */
    List<INote> getNotes();

    /**
     * @param typeOfNote The type of note being retrieved from the database.
     * @return List object of all INotes matching the type of note.
     */
    List<INote> getNotes(Notes typeOfNote);
}
