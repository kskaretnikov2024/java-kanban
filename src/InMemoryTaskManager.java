import java.util.HashMap;
import java.util.List;
import java.util.Collections;
import java.util.ArrayList;

public class InMemoryTaskManager implements TaskManager {
    private static int id = 0;
    private final HashMap<Integer, Task> tasks = new HashMap<>();
    private final HashMap<Integer, Subtask> subtasks = new HashMap<>();
    private final HashMap<Integer, Epic> epics = new HashMap<>();
    //private final AddTask addTask;
    private final HistoryManager historyManager = Managers.getDefaultHistory();

    /*public InMemoryTaskManager(AddTask addTask) {
                this.addTask = addTask;
    }*/

    /*public InMemoryTaskManager(HistoryManager historyManager) {
        this.historyManager = historyManager;
    }*/

    @Override
    public int generateID() {
                return id++;
    }

    @Override
    public List<Task> getAllTasks() {
        if (tasks.isEmpty()) {
            System.out.println("Список задач пуст");
            return Collections.emptyList();
        }
        return new ArrayList<>(tasks.values());
    }

    @Override
    public List<Epic> getAllEpics() {
        if (epics.isEmpty()) {
            System.out.println("Список Эпиков пуст");
            return Collections.emptyList();
        }
        return new ArrayList<>(epics.values());
    }

    @Override
    public List<Subtask> getAllSubtasks() {
        if (subtasks.isEmpty()) {
            System.out.println("Список подзадач пуст");
            return Collections.emptyList();
        }
        return new ArrayList<>(subtasks.values());
    }

    @Override
    public void deleteAllTasks() {
        tasks.clear();
    }

    @Override
    public void deleteAllEpics() {
        subtasks.clear();
        epics.clear();
    }

    @Override
    public void deleteAllSubtasks() {
        subtasks.clear();
        for (Epic epic : epics.values()) {
            epic.getSubtaskIDs().clear();
            updateStatusEpic(epic);
        }
    }

   /* @Override
    public Task getTaskByID(int id) {
        addTask.add(tasks.get(id));
        return tasks.get(id);
    }*/

    @Override
    public Task getTaskByID(int id) {
        historyManager.add(tasks.get(id));
        return tasks.get(id);
    }

    /*@Override
    public Epic getEpicByID(int id) {
        addTask.add(epics.get(id));
        return epics.get(id);
    }*/

    @Override
    public Epic getEpicByID(int id) {
        historyManager.add(epics.get(id));
        return epics.get(id);
    }

    /*@Override
    public Subtask getSubtaskByID(int id) {
        addTask.add(subtasks.get(id));
        return subtasks.get(id);
    }*/

    @Override
    public Subtask getSubtaskByID(int id) {
        historyManager.add(subtasks.get(id));
        return subtasks.get(id);
    }

    @Override
    public Task createTask(Task task) {
        task.setID(generateID());
        tasks.put(task.getID(), task);
        return task;
    }

    @Override
    public Epic createEpic(Epic epic) {
        epic.setID(generateID());
        epics.put(epic.getID(), epic);
        return epic;
    }

    /*@Override
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
    }*/

    @Override
    public Subtask createSubtask(Subtask subtask) {
        subtask.setID(generateID());
        Epic epic = epics.get(subtask.getEpicID());
        subtasks.put(subtask.getID(), subtask);
            updateStatusEpic(epic);
        return subtask;
    }


    @Override
    public void updateTask(Task task) {
        if (tasks.containsKey(task.getID())) {
            tasks.put(task.getID(), task);
        } else {
            System.out.println("Задача не найдена");
        }
    }

    @Override
    public void updateStatusEpic(Epic epic) {
        if (epics.containsKey(epic.getID())) {
            if (epic.getSubtaskIDs().isEmpty()) {
                epic.setStatus(Status.NEW);
            } else {
                List<Subtask> subtasksNew = new ArrayList<>();
                int countDone = 0;
                int countNew = 0;

                for (int i = 0; i < epic.getSubtaskIDs().size(); i++) {
                    subtasksNew.add(subtasks.get(epic.getSubtaskIDs().get(i)));
                }

                for (Subtask subtask : subtasksNew) {
                    if (subtask.getStatus() == Status.DONE) {
                        countDone++;
                    }
                    if (subtask.getStatus() == Status.NEW) {
                        countNew++;
                    }
                    if (subtask.getStatus() == Status.IN_PROGRESS) {
                        epic.setStatus(Status.IN_PROGRESS);
                        return;
                    }
                }

                if (countDone == epic.getSubtaskIDs().size()) {
                    epic.setStatus(Status.DONE);
                } else if (countNew == epic.getSubtaskIDs().size()) {
                    epic.setStatus(Status.NEW);
                } else {
                    epic.setStatus(Status.IN_PROGRESS);
                }
            }
        } else {
            System.out.println("Эпик не найден");
        }
    }

    @Override
    public void updateEpic(Epic epic) {
        if (epics.containsKey(epic.getID())) {
            epics.put(epic.getID(), epic);
            updateStatusEpic(epic);
        } else {
            System.out.println("Эпик не найден");
        }
    }

    @Override
    public void updateSubtask(Subtask subtask) {
        if (subtasks.containsKey(subtask.getID())) {
            subtasks.put(subtask.getID(), subtask);
            Epic epic = epics.get(subtask.getEpicID());
            updateStatusEpic(epic);
        } else {
            System.out.println("Подзадача не найдена");
        }
    }

    @Override
    public void deleteTaskByID(int id) {
        if (tasks.containsKey(id)) {
            tasks.remove(id);
        } else {
            System.out.println("Задача не найдена");
        }
    }

    @Override
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

    @Override
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

    @Override
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

    /*@Override
    public void add(Task task) {

    }*/
    @Override
    public List<Task> getHistory() {
        return historyManager.getHistory();
    }

}
