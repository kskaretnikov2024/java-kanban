import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        TaskManager taskManager = Managers.getAddTask(Managers.getAddTask());

        taskManager.createTask(new Task("Задача 1 без подзадач", "Task 1 without subtasks",
                Status.NEW));
        taskManager.createTask(new Task("Задача 2 без подзадач", "Task 2 without subtasks",
                Status.NEW));

        taskManager.createEpic(new Epic("Эпик с двумя подзадачами", "Epic with 2 subtasks",
                Status.NEW));
        taskManager.createSubtask(new Subtask("Подзадача 1 к Эпику с двумя подзадачами",
                "Subtask 1 in Epic with 2 subtasks", Status.NEW, 2));
        taskManager.createSubtask(new Subtask("Подзадача 2 к Эпику с двумя подзадачами",
                "Subtask 2 in Epic with 2 subtasks", Status.NEW, 2));

        taskManager.createEpic(new Epic("Эпик с одной подзадачей", "Epic with 1 subtask",
                Status.NEW));
        taskManager.createSubtask(new Subtask("Подзадача 1 к Эпику с одной подзадачей",
                "Subtask 1 in Epic with 1 subtask", Status.NEW, 5));

        System.out.println("Список Эпиков:");
        ArrayList<Epic> epicsList = (ArrayList<Epic>) taskManager.getAllEpics();
        System.out.println(epicsList);
        System.out.println(" ");

        System.out.println("Список Задач:");
        ArrayList<Task> taskList = (ArrayList<Task>) taskManager.getAllTasks();
        System.out.println(taskList);
        System.out.println(" ");

        System.out.println("Список Подзадач:");
        ArrayList<Subtask> subtasksList = (ArrayList<Subtask>) taskManager.getAllSubtasks();
        System.out.println(subtasksList);
        System.out.println(" ");

        Task task1 = taskManager.getTaskByID(0);
        task1.setStatus(Status.IN_PROGRESS);
        taskManager.updateTask(task1);

        Task task2 = taskManager.getTaskByID(1);
        task2.setStatus(Status.DONE);
        taskManager.updateTask(task2);

        Subtask subtask1 = taskManager.getSubtaskByID(3);
        subtask1.setStatus(Status.IN_PROGRESS);
        taskManager.updateSubtask(subtask1);
        Epic epic1 = taskManager.getEpicByID(2);
        taskManager.updateStatusEpic(epic1);

        Subtask subtask2 = taskManager.getSubtaskByID(4);
        subtask2.setStatus(Status.DONE);
        taskManager.updateSubtask(subtask2);
        epic1 = taskManager.getEpicByID(2);
        taskManager.updateStatusEpic(epic1);

        Subtask subtask3 = taskManager.getSubtaskByID(6);
        subtask3.setStatus(Status.DONE);
        taskManager.updateSubtask(subtask3);
        Epic epic2 = taskManager.getEpicByID(5);
        taskManager.updateStatusEpic(epic2);

        System.out.println("Список Эпиков после изменения статусов:");
        ArrayList<Epic> epicsListAfterStatusChange = (ArrayList<Epic>) taskManager.getAllEpics();
        System.out.println(epicsListAfterStatusChange);
        System.out.println(" ");

        System.out.println("Список Задач после изменения статусов:");
        ArrayList<Task> taskListAfterStatusChange = (ArrayList<Task>) taskManager.getAllTasks();
        System.out.println(taskListAfterStatusChange);
        System.out.println(" ");

        System.out.println("Список Подзадач после изменения статусов:");
        ArrayList<Subtask> subtasksListAfterStatusChange = (ArrayList<Subtask>) taskManager.getAllSubtasks();
        System.out.println(subtasksListAfterStatusChange);
        System.out.println(" ");

        taskManager.deleteTaskByID(1);
        taskManager.deleteEpicByID(2);

        System.out.println("Список Эпиков после удаления одного Эпика:");
        ArrayList<Epic> epicsListAfterDelete = (ArrayList<Epic>) taskManager.getAllEpics();
        System.out.println(epicsListAfterDelete);
        System.out.println(" ");

        System.out.println("Список Задач после удаления одной задачи:");
        ArrayList<Task> taskListAfterDelete = (ArrayList<Task>) taskManager.getAllTasks();
        System.out.println(taskListAfterDelete);
        System.out.println(" ");

        System.out.println("Список Подзадач после удаления одного Эпика:");
        ArrayList<Subtask> subtasksListAfterDelete = (ArrayList<Subtask>) taskManager.getAllSubtasks();
        System.out.println(subtasksListAfterDelete);
        System.out.println(" ");
    }
}