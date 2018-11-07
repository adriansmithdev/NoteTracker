package model;

/**
 * @author Adrian Smith
 * @author Kyle Johnson
 * @version 1.0
 */
public class ToDoItem {
    private String toDo;
    private boolean isCompleted;

    /**
     * @param task        task to be completed
     * @param isCompleted whether or not the task is completed
     */
    public ToDoItem(String task, boolean isCompleted) {
        this.toDo = task;
        this.isCompleted = isCompleted;
    }

    /**
     * @return task being stored
     */
    public String getToDo() {
        return toDo;
    }

    /**
     * @return true if task is completed, false if not
     */
    public boolean isCompleted() {
        return isCompleted;
    }

    /**
     * @param completed sets isCompleted to completed
     */
    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    @Override
    public String toString() {
        return "ToDoItem{" +
                "toDo='" + toDo + '\'' +
                ", isCompleted=" + isCompleted +
                '}';
    }
}
