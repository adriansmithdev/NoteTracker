package model;

import java.util.List;

public class ToDo implements INote {
    private String title;
    private String dateCreated;
    private List<ToDoItem> toDoItems;
    private Notes typeOfNote;

    public ToDo(String title, String dateCreated, List<ToDoItem> toDoItems)
    {
        this.title = title;
        this.dateCreated = dateCreated;
        this.toDoItems = toDoItems;
        this.typeOfNote = Notes.TO_DO;
    }

    @Override
    public String getTitle()
    {
        return this.title;
    }

    @Override
    public String getDateCreated()
    {
        return this.dateCreated;
    }

    @Override
    public Notes getType()
    {
        return this.typeOfNote;
    }

    public List<ToDoItem> getToDoItems()
    {
        return this.toDoItems;
    }

    public boolean isListCompleted()
    {
        for(ToDoItem task : this.toDoItems){
            if(!task.isCompleted()){
                return false;
            }
        }
        return true;
    }

}
