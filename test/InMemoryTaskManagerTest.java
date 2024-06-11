import Managers.Managers;
import Status.Status;
import Tasks.Epic;
import Tasks.Subtask;
import Tasks.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import Managers.TaskManager;

class InMemoryTaskManagerTest {

    private static TaskManager taskManager;

    @BeforeEach
    public void beforeEach() {
        taskManager = Managers.getDefault();
    }

    @Test
    void cantAddEpicToItselfLikeSubtask() {
        Epic epicExpected = taskManager.createEpic(new Epic("New Epic2 for cantAddEpicToItselfLikeSubtusk",
                "New Epic2", Status.NEW));
        Subtask subtaskExpected1 = taskManager.createSubtask(new Subtask("New Subtask1 for Epic1 for cantAddEpicToItselfLikeSubtusk",
                "New Subtask1 for Epic2", Status.NEW, epicExpected.Id()));
        Epic epic3 = taskManager.createEpic(new Epic("New Epic3 for cantAddEpicToItselfLikeSubtusk",
                "New Epic3", Status.NEW));

        Epic epicActual = taskManager.getEpicById(epicExpected.Id());
        Subtask subtaskActual1 = taskManager.getSubtaskById(subtaskExpected1.Id());
        Subtask subtaskActual2 = taskManager.getSubtaskById(epic3.Id());

        assertEquals(epicExpected, epicActual, "Epics not equals");
        assertEquals(subtaskExpected1, subtaskActual1, "Subtasks not equals");
        assertNotEquals(epic3, subtaskActual2, "Something went wrong");
    }

    @Test
    void cantAddSubtaskLikeOwnEpic() {
        Epic epicExpected = taskManager.createEpic(new Epic("New Tasks.Epic for cantAddSubtaskLikeOwnEpic",
                "New Epic4", Status.NEW));
        Subtask subtaskExpected1 = taskManager.createSubtask(new Subtask("New Subtask1 for Epic4 for cantAddSubtaskLikeOwnEpic",
                "New Subtask1 for Epic1", Status.NEW, epicExpected.Id()));
        Subtask subtaskExpected2 = taskManager.createSubtask(new Subtask("New Subtask2 for Epic4 for cantAddSubtaskLikeOwnEpic",
                "New Subtask2 for Epic1", Status.NEW, epicExpected.Id()));

        Epic epicActual = taskManager.getEpicById(epicExpected.Id());
        Subtask subtaskActual1 = taskManager.getSubtaskById(subtaskExpected1.Id());
        Epic subtaskActual2 = taskManager.getEpicById(subtaskExpected2.Id());

        assertEquals(epicExpected, epicActual, "Epics not equals");
        assertEquals(subtaskExpected1, subtaskActual1, "Subtasks not equals");
        assertNotEquals(epicExpected, subtaskActual2, "Something went wrong");
    }

    @Test
    void addTaskAndFindById() {
        Task taskExpected = taskManager.createTask(new Task("New Tasks.Task for test addTaskAndFindByID",
                "New Task1", Status.NEW));
        Task taskActual = taskManager.getTaskById(taskExpected.Id());
        assertNotNull(taskActual, "Tasks.Task not found");
        assertEquals(taskExpected, taskActual, "Tasks not equals");
    }

    @Test
    void addEpicAndSubtasksAndFindById() {
        Epic epicExpected = taskManager.createEpic(new Epic("New Tasks.Epic for addEpicAndSubtasksAndFindByID", "New Epic1", Status.NEW));
        Subtask subtaskExpected1 = taskManager.createSubtask(new Subtask("New Subtask1 for Epic1 for addEpicAndSubtasksAndFindByID",
                "New Subtask1 for Epic1", Status.NEW, epicExpected.Id()));
        Subtask subtaskExpected2 = taskManager.createSubtask(new Subtask("New Subtask2 for Epic1 for addEpicAndSubtasksAndFindByID",
                "New Subtask2 for Epic1", Status.NEW, epicExpected.Id()));

        Epic epicActual = taskManager.getEpicById(epicExpected.Id());
        Subtask subtaskActual1 = taskManager.getSubtaskById(subtaskExpected1.Id());
        Subtask subtaskActual2 = taskManager.getSubtaskById(subtaskExpected2.Id());

        assertNotNull(epicActual, "Tasks.Epic not found");
        assertNotNull(subtaskActual1, "Tasks.Subtask not found");
        assertEquals(epicExpected, epicActual, "Epics not equals");
        assertEquals(subtaskExpected1, subtaskActual1, "Subtasks not equals");
        assertEquals(subtaskExpected2, subtaskActual2, "Subtasks not equals");
    }

    @Test
    void noConflictsTasksByIdInsideManager() {
        Task taskExpected = taskManager.createTask(new Task("New Tasks.Task for test noConflictsTasksByIDInsideManager",
                "New Task2", Status.NEW));
        List<Task> tasks = taskManager.getAllTasks();
        assertEquals(taskExpected, tasks.get(0), "Tasks.Task not equals");
    }

    @Test
    void TaskImmutabilityAllFieldsWhenCreateTask() {
        Task taskExpected = taskManager.createTask(new Task("New Tasks.Task for test TaskImmutabilityAllFieldsWhenCreateTask",
                "New Task3", Status.IN_PROGRESS));
        taskManager.createTask(taskExpected);
        List<Task> tasks = taskManager.getAllTasks();
        Task taskActual = tasks.get(0);
        assertEquals(taskExpected.Id(), taskActual.Id(), "Tasks IDs not equals");
        assertEquals(taskExpected.getDescription(), taskActual.getDescription(), "Tasks descriptions not equals");
        assertEquals(taskExpected.getName(), taskActual.getName(), "Tasks names not equals");
        assertEquals(taskExpected.getStatus(), taskActual.getStatus(), "Tasks statuses not equals");
    }


}