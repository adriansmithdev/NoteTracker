package model;

import java.util.List;

/**
 * @author Adrian Smith
 * @author Kyle Johnson
 * @version 1.0
 */
public class ToDo implements INote {
    private String title;
    private String dateCreated;
    private List<ToDoItem> toDoItems;
    private Notes typeOfNote;

    /**
     * @param title       title of to-do list
     * @param dateCreated date list was created
     * @param toDoItems   list of ToDoItems in list
     */
    public ToDo(String title, String dateCreated, List<ToDoItem> toDoItems) {
        this.title = title;
        this.dateCreated = dateCreated;
        this.toDoItems = toDoItems;
        this.typeOfNote = Notes.TO_DO;
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public String getDateCreated() {
        return this.dateCreated;
    }

    @Override
    public Notes getType() {
        return this.typeOfNote;
    }

    /**
     * @return to-do items being stored in to-do list
     */
    public List<ToDoItem> getToDoItems() {
        return this.toDoItems;
    }

    /**
     * @return true if all list is completed, false if not
     */
    public boolean isListCompleted() {
        for (ToDoItem task : this.toDoItems) {
            if (!task.isCompleted()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return "ToDo{" +
                "title='" + title + '\'' +
                ", dateCreated='" + dateCreated + '\'' +
                ", toDoItems=" + toDoItems +
                ", typeOfNote=" + typeOfNote +
                '}';
    }
}
