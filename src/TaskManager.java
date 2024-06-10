import java.util.List;

public interface TaskManager {
    int generateID();

    List<Task> getAllTasks();

    List<Epic> getAllEpics();

    List<Subtask> getAllSubtasks();

    void deleteAllTasks();

    void deleteAllEpics();

    void deleteAllSubtasks();

    Task getTaskByID(int id);

    Epic getEpicByID(int id);

    Subtask getSubtaskByID(int id);

    Task createTask(Task task);

    Epic createEpic(Epic epic);

    Subtask createSubtask(Subtask subtask);

    void updateTask(Task task);

    void updateStatusEpic(Epic epic);

    void updateEpic(Epic epic);

    void updateSubtask(Subtask subtask);

    void deleteTaskByID(int id);

    void deleteEpicByID(int id);

    void deleteSubtaskByID(int id);

    List<Subtask> getAllSubtasksByEpicID(int id);

    List<Task> getHistory();
}
