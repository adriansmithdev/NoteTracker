package views;

import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.INote;
import model.ToDo;
import model.ToDoItem;

import java.util.List;

public class ToDoFactory implements INoteCreator  {
    @Override
    public HBox createSampleView(INote note) {
        ToDo toDoList = (ToDo) note;
        HBox toDoView = new HBox();

        Label title = new Label(toDoList.getTitle() + " To-Do's");

        toDoView.getChildren().addAll(title);

        return toDoView;
    }

    @Override
    public VBox createExpandedView(INote note) {
        ToDo toDoList = (ToDo) note;
        VBox toDoView = new VBox();

        Label title = new Label(toDoList.getTitle() + " To-Do's");
        List<ToDoItem> listOfToDos = toDoList.getToDoItems();

        CheckBox[] toDoBoxes = new CheckBox[listOfToDos.size()];

        for(int i = 0; i < listOfToDos.size(); i++){
            ToDoItem individualToDo = listOfToDos.get(i);
            toDoBoxes[i] = new CheckBox(individualToDo.getToDo());
            toDoBoxes[i].setSelected(individualToDo.isCompleted());
        }

        toDoView.getChildren().add(title);
        toDoView.getChildren().addAll(toDoBoxes);

        return toDoView;
    }
}
