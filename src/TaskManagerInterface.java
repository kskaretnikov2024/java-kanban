import java.util.List;

public interface TaskManagerInterface {

    void createTask(Task task);

    void createEpic(Epic epic);

    void createSubtask(Subtask subtask);

    void deleteTaskByID(int id);

    void deleteEpicByID(int id);

    Epic getEpicByID(int id);

    Task getTaskByID(int id);

    Subtask getSubtaskByID(int id);

    List<Task> getAllTasks();

    List<Epic> getAllEpics();

    List<Subtask> getAllSubtasks();

    void updateTask(Task task);

    void updateEpic(Epic epic);

    void updateStatusEpic(Epic epic);

    void updateSubtask(Subtask subtask);

}
