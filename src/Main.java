import Managers.Managers;
import Statuses.Status;
import Tasks.Epic;
import Tasks.Subtask;
import Tasks.Task;
import Managers.TaskManager;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        TaskManager inMemoryTaskManager = Managers.getDefault();

        inMemoryTaskManager.createTask(new Task( "Задача 1 без подзадач", "Tasks.Task 1 without subtasks",
                Status.NEW));
        inMemoryTaskManager.createTask(new Task( "Задача 2 без подзадач", "Tasks.Task 2 without subtasks",
                Status.NEW));

        inMemoryTaskManager.createEpic(new Epic("Эпик с двумя подзадачами", "Tasks.Epic with 2 subtasks",
                Status.NEW));
        inMemoryTaskManager.createSubtask(new Subtask( "Подзадача 1 к Эпику с двумя подзадачами",
                "Tasks.Subtask 1 in Tasks.Epic with 2 subtasks", Status.NEW, 2));
        inMemoryTaskManager.createSubtask(new Subtask( "Подзадача 2 к Эпику с двумя подзадачами",
                "Tasks.Subtask 2 in Tasks.Epic with 2 subtasks", Status.NEW, 2));

        inMemoryTaskManager.createEpic(new Epic("Эпик с одной подзадачей", "Tasks.Epic with 1 subtask",
                Status.NEW));
        inMemoryTaskManager.createSubtask(new Subtask( "Подзадача 1 к Эпику с одной подзадачей",
                "Tasks.Subtask 1 in Tasks.Epic with 1 subtask", Status.NEW, 5));

printAllTasks(inMemoryTaskManager);

        System.out.println("Список Эпиков:");
        ArrayList<Epic> epicsList = (ArrayList<Epic>) inMemoryTaskManager.getAllEpics();
        System.out.println(epicsList);
        System.out.println(" ");

        System.out.println("Список Задач:");
        ArrayList<Task> taskList = (ArrayList<Task>) inMemoryTaskManager.getAllTasks();
        System.out.println(taskList);
        System.out.println(" ");

        System.out.println("Список Подзадач:");
        ArrayList<Subtask> subtasksList = (ArrayList<Subtask>) inMemoryTaskManager.getAllSubtasks();
        System.out.println(subtasksList);
        System.out.println(" ");

        Task task1 = inMemoryTaskManager.getTaskById(0);
        task1.setStatus(Status.IN_PROGRESS);
        inMemoryTaskManager.updateTask(task1);

        Task task2 = inMemoryTaskManager.getTaskById(1);
        task2.setStatus(Status.DONE);
        inMemoryTaskManager.updateTask(task2);

        Subtask subtask1 = inMemoryTaskManager.getSubtaskById(3);
        subtask1.setStatus(Status.IN_PROGRESS);
        inMemoryTaskManager.updateSubtask(subtask1);
        Epic epic1 = inMemoryTaskManager.getEpicById(2);
        inMemoryTaskManager.updateStatusEpic(epic1);

        Subtask subtask2 = inMemoryTaskManager.getSubtaskById(4);
        subtask2.setStatus(Status.DONE);
        inMemoryTaskManager.updateSubtask(subtask2);
        epic1 = inMemoryTaskManager.getEpicById(2);
        inMemoryTaskManager.updateStatusEpic(epic1);

        Subtask subtask3 = inMemoryTaskManager.getSubtaskById(6);
        subtask3.setStatus(Status.DONE);
        inMemoryTaskManager.updateSubtask(subtask3);
        Epic epic2 = inMemoryTaskManager.getEpicById(5);
        inMemoryTaskManager.updateStatusEpic(epic2);

        System.out.println("Список Эпиков после изменения статусов:");
        ArrayList<Epic> epicsListAfterStatusChange = (ArrayList<Epic>) inMemoryTaskManager.getAllEpics();
        System.out.println(epicsListAfterStatusChange);
        System.out.println(" ");

        System.out.println("Список Задач после изменения статусов:");
        ArrayList<Task> taskListAfterStatusChange = (ArrayList<Task>) inMemoryTaskManager.getAllTasks();
        System.out.println(taskListAfterStatusChange);
        System.out.println(" ");

        System.out.println("Список Подзадач после изменения статусов:");
        ArrayList<Subtask> subtasksListAfterStatusChange = (ArrayList<Subtask>) inMemoryTaskManager.getAllSubtasks();
        System.out.println(subtasksListAfterStatusChange);
        System.out.println(" ");

        inMemoryTaskManager.deleteTaskById(1);
        inMemoryTaskManager.deleteEpicById(2);

        System.out.println("Список Эпиков после удаления одного Эпика:");
        ArrayList<Epic> epicsListAfterDelete = (ArrayList<Epic>) inMemoryTaskManager.getAllEpics();
        System.out.println(epicsListAfterDelete);
        System.out.println(" ");

        System.out.println("Список Задач после удаления одной задачи:");
        ArrayList<Task> taskListAfterDelete = (ArrayList<Task>) inMemoryTaskManager.getAllTasks();
        System.out.println(taskListAfterDelete);
        System.out.println(" ");

        System.out.println("Список Подзадач после удаления одного Эпика:");
        ArrayList<Subtask> subtasksListAfterDelete = (ArrayList<Subtask>) inMemoryTaskManager.getAllSubtasks();
        System.out.println(subtasksListAfterDelete);
        System.out.println(" ");
    }

    private static void printAllTasks(TaskManager manager) {
        System.out.println("Задачи:");
        for (Task task : manager.getAllTasks()) {
            System.out.println(task);
        }
        System.out.println("Эпики:");
        for (Task epic : manager.getAllEpics()) {
            System.out.println(epic);

            for (Task task : manager.getAllSubtasksByEpicId(epic.Id())) {
                System.out.println("--> " + task);
            }
        }
        System.out.println("Подзадачи:");
        for (Task subtask : manager.getAllSubtasks()) {
            System.out.println(subtask);
        }

        System.out.println("История:");
        for (Task task : manager.getHistory()) {
            System.out.println(task);
        }
    }
}