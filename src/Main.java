import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        TaskManagerInterface taskManagerInterface = Managers.getAddTaskInterface(Managers.getAddTask());

        taskManagerInterface.createTask(new Task("Задача 1 без подзадач", "Task 1 without subtasks",
                Status.NEW));
        taskManagerInterface.createTask(new Task("Задача 2 без подзадач", "Task 2 without subtasks",
                Status.NEW));

        taskManagerInterface.createEpic(new Epic("Эпик с двумя подзадачами", "Epic with 2 subtasks",
                Status.NEW));
        taskManagerInterface.createSubtask(new Subtask("Подзадача 1 к Эпику с двумя подзадачами",
                "Subtask 1 in Epic with 2 subtasks", Status.NEW, 2));
        taskManagerInterface.createSubtask(new Subtask("Подзадача 2 к Эпику с двумя подзадачами",
                "Subtask 2 in Epic with 2 subtasks", Status.NEW, 2));

        taskManagerInterface.createEpic(new Epic("Эпик с одной подзадачей", "Epic with 1 subtask",
                Status.NEW));
        taskManagerInterface.createSubtask(new Subtask("Подзадача 1 к Эпику с одной подзадачей",
                "Subtask 1 in Epic with 1 subtask", Status.NEW, 5));

        System.out.println("Список Эпиков:");
        ArrayList<Epic> epicsList = (ArrayList<Epic>) taskManagerInterface.getAllEpics();
        System.out.println(epicsList);
        System.out.println(" ");

        System.out.println("Список Задач:");
        ArrayList<Task> taskList = (ArrayList<Task>) taskManagerInterface.getAllTasks();
        System.out.println(taskList);
        System.out.println(" ");

        System.out.println("Список Подзадач:");
        ArrayList<Subtask> subtasksList = (ArrayList<Subtask>) taskManagerInterface.getAllSubtasks();
        System.out.println(subtasksList);
        System.out.println(" ");

        Task task1 = taskManagerInterface.getTaskByID(0);
        task1.setStatus(Status.IN_PROGRESS);
        taskManagerInterface.updateTask(task1);

        Task task2 = taskManagerInterface.getTaskByID(1);
        task2.setStatus(Status.DONE);
        taskManagerInterface.updateTask(task2);

        Subtask subtask1 = taskManagerInterface.getSubtaskByID(3);
        subtask1.setStatus(Status.IN_PROGRESS);
        taskManagerInterface.updateSubtask(subtask1);
        Epic epic1 = taskManagerInterface.getEpicByID(2);
        epic1.setStatus(Status.IN_PROGRESS);
        taskManagerInterface.updateEpic(epic1);

        Subtask subtask2 = taskManagerInterface.getSubtaskByID(4);
        subtask2.setStatus(Status.DONE);
        taskManagerInterface.updateSubtask(subtask2);
        epic1 = taskManagerInterface.getEpicByID(2);
        epic1.setStatus(Status.IN_PROGRESS);
        taskManagerInterface.updateEpic(epic1);

        Subtask subtask3 = taskManagerInterface.getSubtaskByID(6);
        subtask3.setStatus(Status.DONE);
        taskManagerInterface.updateSubtask(subtask3);
        Epic epic2 = taskManagerInterface.getEpicByID(5);
        epic2.setStatus(Status.DONE);
        taskManagerInterface.updateEpic(epic2);

        System.out.println("Список Эпиков после изменения статусов:");
        ArrayList<Epic> epicsListAfterStatusChange = (ArrayList<Epic>) taskManagerInterface.getAllEpics();
        System.out.println(epicsListAfterStatusChange);
        System.out.println(" ");

        System.out.println("Список Задач после изменения статусов:");
        ArrayList<Task> taskListAfterStatusChange = (ArrayList<Task>) taskManagerInterface.getAllTasks();
        System.out.println(taskListAfterStatusChange);
        System.out.println(" ");

        System.out.println("Список Подзадач после изменения статусов:");
        ArrayList<Subtask> subtasksListAfterStatusChange = (ArrayList<Subtask>) taskManagerInterface.getAllSubtasks();
        System.out.println(subtasksListAfterStatusChange);
        System.out.println(" ");

        taskManagerInterface.deleteTaskByID(1);
        taskManagerInterface.deleteEpicByID(2);

        System.out.println("Список Эпиков после удаления одного Эпика:");
        ArrayList<Epic> epicsListAfterDelete = (ArrayList<Epic>) taskManagerInterface.getAllEpics();
        System.out.println(epicsListAfterDelete);
        System.out.println(" ");

        System.out.println("Список Задач после удаления одной задачи:");
        ArrayList<Task> taskListAfterDelete = (ArrayList<Task>) taskManagerInterface.getAllTasks();
        System.out.println(taskListAfterDelete);
        System.out.println(" ");

        System.out.println("Список Подзадач после удаления одного Эпика:");
        ArrayList<Subtask> subtasksListAfterDelete = (ArrayList<Subtask>) taskManagerInterface.getAllSubtasks();
        System.out.println(subtasksListAfterDelete);
        System.out.println(" ");
    }
}