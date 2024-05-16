import java.util.HashMap;
import java.util.List;
import java.util.Collections;
import java.util.ArrayList;

public class TaskManager implements TaskManagerInterface {
    private static int id = 0;

    private final HashMap<Integer, Task> tasks = new HashMap<>();
    private final HashMap<Integer, Subtask> subtasks = new HashMap<>();
    private final HashMap<Integer, Epic> epics = new HashMap<>();
    private final AddTaskInterface addTaskInterface;

    public TaskManager(AddTaskInterface addTaskInterface) {
        this.addTaskInterface = addTaskInterface;
    }

    public int generateID() {
        return id++;
    }

    public List<Task> getAllTasks() {
        if (tasks.isEmpty()) {
            System.out.println("Список задач пуст");
            return Collections.emptyList();
        }
        return new ArrayList<>(tasks.values());
    }

    public List<Epic> getAllEpics() {
        if (epics.isEmpty()) {
            System.out.println("Список Эпиков пуст");
            return Collections.emptyList();
        }
        return new ArrayList<>(epics.values());
    }

    public List<Subtask> getAllSubtasks() {
        if (subtasks.isEmpty()) {
            System.out.println("Список подзадач пуст");
            return Collections.emptyList();
        }
        return new ArrayList<>(subtasks.values());
    }

    public void deleteAllTasks() {
        tasks.clear();
    }

    public void deleteAllEpics() {
        subtasks.clear();
        epics.clear();
    }

    public void deleteAllSubtasks() {
        subtasks.clear();
        for (Epic epic : epics.values()) {
            epic.getSubtaskIDs().clear();
            updateStatusEpic(epic);
        }
    }

    public Task getTaskByID(int id) {
        addTaskInterface.add(tasks.get(id));
        return tasks.get(id);
    }

    public Epic getEpicByID(int id) {
        addTaskInterface.add(epics.get(id));
        return epics.get(id);
    }

    public Subtask getSubtaskByID(int id) {
        addTaskInterface.add(subtasks.get(id));
        return subtasks.get(id);
    }

    public void createTask(Task task) {
        int newTaskID = generateID();
        task.setID(newTaskID);
        tasks.put(newTaskID, task);
    }

    public void createEpic(Epic epic) {
        int newEpicID = generateID();
        epic.setID(newEpicID);
        epics.put(newEpicID, epic);
    }

    public void createSubtask(Subtask subtask) {
        int newSubtaskID = generateID();
        subtask.setID(newSubtaskID);
        Epic epic = epics.get(subtask.getEpicID());
        if (epic != null) {
            subtasks.put(newSubtaskID, subtask);
            epic.setSubtaskIDs(newSubtaskID);
            updateStatusEpic(epic);
        } else {
            System.out.println("Эпик не найден");
        }
    }

    public void updateTask(Task task) {
        if (tasks.containsKey(task.getID())) {
            tasks.put(task.getID(), task);
        } else {
            System.out.println("Задача не найдена");
        }
    }

    public void updateStatusEpic(Epic epic) {

    }

    public void updateEpic(Epic epic) {
        if (epics.containsKey(epic.getID())) {
            epics.put(epic.getID(), epic);
            updateStatusEpic(epic);
        } else {
            System.out.println("Эпик не найден");
        }
    }

    public void updateSubtask(Subtask subtask) {
        if (subtasks.containsKey(subtask.getID())) {
            subtasks.put(subtask.getID(), subtask);
            Epic epic = epics.get(subtask.getEpicID());
            updateStatusEpic(epic);
        } else {
            System.out.println("Подзадача не найдена");
        }
    }

    public void deleteTaskByID(int id) {
        if (tasks.containsKey(id)) {
            tasks.remove(id);
        } else {
            System.out.println("Задача не найдена");
        }
    }

    public void deleteEpicByID(int id) {
        Epic epic = epics.get(id);
        if (epic != null) {
            for (Integer subtaskID : epic.getSubtaskIDs()) {
                subtasks.remove(subtaskID);
            }
            epics.remove(id);
        } else {
            System.out.println("Эпик не найден");
        }
    }

    public void deleteSubtaskByID(int id) {
        Subtask subtask = subtasks.get(id);
        if (subtask != null) {
            Epic epic = epics.get(subtask.getEpicID());
            epic.getSubtaskIDs().remove((Integer) subtask.getID());
            updateStatusEpic(epic);
            subtasks.remove(id);
        } else {
            System.out.println("Подзадача не найдена");
        }
    }

    public List<Subtask> getAllSubtasksByEpicID(int id) {
        if (epics.containsKey(id)) {
            List<Subtask> subtasksNew = new ArrayList<>();
            Epic epic = epics.get(id);
            for (int i = 0; i < epic.getSubtaskIDs().size(); i++) {
                subtasksNew.add(subtasks.get(epic.getSubtaskIDs().get(i)));
            }
            return subtasksNew;
        } else {
            return Collections.emptyList();
        }
    }
}
