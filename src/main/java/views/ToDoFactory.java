package views;

import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import model.INote;
import model.ToDo;
import model.ToDoItem;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Adrian Smith
 * @author Kyle Johnson
 * @version 1.0
 */
public class ToDoFactory implements INoteCreator {
    @Override
    public VBox createSampleView(INote note) {
        ToDo toDoList = (ToDo) note;
        VBox toDoView = new VBox();

        Label title = new Label(toDoList.getTitle() + " To-Do's");

        toDoView.getChildren().addAll(title);

        if (toDoList.isListCompleted()) {
            toDoView.getStyleClass().add("completedToDo");
            Label completedStatus = new Label("DONE");
            toDoView.getChildren().add(completedStatus);
        } else {
            toDoView.getStyleClass().add("uncompletedToDo");
            Label completedStatus = new Label("NOT DONE");
            toDoView.getChildren().add(completedStatus);
        }

        return toDoView;
    }

    @Override
    public VBox createExpandedView(INote note) {
        ToDo toDoList = (ToDo) note;
        VBox toDoView = new VBox();

        Label title = new Label(toDoList.getTitle() + " To-Do's");
        List<ToDoItem> listOfToDos = toDoList.getToDoItems();

        CheckBox[] toDoBoxes = new CheckBox[listOfToDos.size()];

        for (int i = 0; i < listOfToDos.size(); i++) {
            ToDoItem individualToDo = listOfToDos.get(i);
            toDoBoxes[i] = new CheckBox(individualToDo.getToDo());
            toDoBoxes[i].setSelected(individualToDo.isCompleted());
            toDoBoxes[i].selectedProperty().addListener((observable, oldValue, newValue) ->
                    individualToDo.setCompleted(Boolean.valueOf(newValue.toString())));
        }

        toDoView.getChildren().add(title);
        toDoView.getChildren().addAll(toDoBoxes);

        return toDoView;
    }

    @Override
    public List<String> getLabels() {
        ArrayList<String> result = new ArrayList<>();
        result.add("Title");
        result.add("List");
        return result;
    }
}
