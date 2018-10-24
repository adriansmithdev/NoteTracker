package model;

public class ToDoItem {
    private String toDo;
    private boolean isCompleted;

    public ToDoItem(String toDo, boolean isCompleted)
    {
        this.toDo = toDo;
        this.isCompleted = isCompleted;
    }

    public String getToDo()
    {
        return toDo;
    }

    public boolean isCompleted()
    {
        return isCompleted;
    }

    public void setCompleted(boolean completed)
    {
        isCompleted = completed;
    }
}
