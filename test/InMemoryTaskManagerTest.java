import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

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
                "New Subtask1 for Epic2", Status.NEW, epicExpected.getID()));
        Epic epic3 = taskManager.createEpic(new Epic("New Epic3 for cantAddEpicToItselfLikeSubtusk",
                "New Epic3", Status.NEW));

        Epic epicActual = taskManager.getEpicByID(epicExpected.getID());
        Subtask subtaskActual1 = taskManager.getSubtaskByID(subtaskExpected1.getID());
        Subtask subtaskActual2 = taskManager.getSubtaskByID(epic3.getID());

        assertEquals(epicExpected, epicActual, "Epics not equals");
        assertEquals(subtaskExpected1, subtaskActual1, "Subtasks not equals");
        assertNotEquals(epic3, subtaskActual2, "Something went wrong");
    }

    @Test
    void cantAddSubtaskLikeOwnEpic() {
        Epic epicExpected = taskManager.createEpic(new Epic("New Epic for cantAddSubtaskLikeOwnEpic",
                "New Epic4", Status.NEW));
        Subtask subtaskExpected1 = taskManager.createSubtask(new Subtask("New Subtask1 for Epic4 for cantAddSubtaskLikeOwnEpic",
                "New Subtask1 for Epic1", Status.NEW, epicExpected.getID()));
        Subtask subtaskExpected2 = taskManager.createSubtask(new Subtask("New Subtask2 for Epic4 for cantAddSubtaskLikeOwnEpic",
                "New Subtask2 for Epic1", Status.NEW, epicExpected.getID()));

        Epic epicActual = taskManager.getEpicByID(epicExpected.getID());
        Subtask subtaskActual1 = taskManager.getSubtaskByID(subtaskExpected1.getID());
        Epic subtaskActual2 = taskManager.getEpicByID(subtaskExpected2.getID());

        assertEquals(epicExpected, epicActual, "Epics not equals");
        assertEquals(subtaskExpected1, subtaskActual1, "Subtasks not equals");
        assertNotEquals(epicExpected, subtaskActual2, "Something went wrong");
    }

    @Test
    void addTaskAndFindByID() {
        Task taskExpected = taskManager.createTask(new Task("New Task for test addTaskAndFindByID",
                "New Task1", Status.NEW));
        Task taskActual = taskManager.getTaskByID(taskExpected.getID());
        assertNotNull(taskActual, "Task not found");
        assertEquals(taskExpected, taskActual, "Tasks not equals");
    }

    @Test
    void addEpicAndSubtasksAndFindByID() {
        Epic epicExpected = taskManager.createEpic(new Epic("New Epic for addEpicAndSubtasksAndFindByID", "New Epic1", Status.NEW));
        Subtask subtaskExpected1 = taskManager.createSubtask(new Subtask("New Subtask1 for Epic1 for addEpicAndSubtasksAndFindByID",
                "New Subtask1 for Epic1", Status.NEW, epicExpected.getID()));
        Subtask subtaskExpected2 = taskManager.createSubtask(new Subtask("New Subtask2 for Epic1 for addEpicAndSubtasksAndFindByID",
                "New Subtask2 for Epic1", Status.NEW, epicExpected.getID()));

        Epic epicActual = taskManager.getEpicByID(epicExpected.getID());
        Subtask subtaskActual1 = taskManager.getSubtaskByID(subtaskExpected1.getID());
        Subtask subtaskActual2 = taskManager.getSubtaskByID(subtaskExpected2.getID());

        assertNotNull(epicActual, "Epic not found");
        assertNotNull(subtaskActual1, "Subtask not found");
        assertEquals(epicExpected, epicActual, "Epics not equals");
        assertEquals(subtaskExpected1, subtaskActual1, "Subtasks not equals");
        assertEquals(subtaskExpected2, subtaskActual2, "Subtasks not equals");
    }

    @Test
    void noConflictsTasksByIDInsideManager() {
        Task taskExpected = taskManager.createTask(new Task("New Task for test noConflictsTasksByIDInsideManager",
                "New Task2", Status.NEW));
        List<Task> tasks = taskManager.getAllTasks();
       // Task taskActual = taskManager.getTaskByID(0);
        assertEquals(taskExpected, tasks.get(0), "Task not equals");
    }

    @Test
    void TaskImmutabilityAllFieldsWhenCreateTask() {
        Task taskExpected = taskManager.createTask(new Task("New Task for test TaskImmutabilityAllFieldsWhenCreateTask",
                "New Task3", Status.IN_PROGRESS));
        taskManager.createTask(taskExpected);
        List<Task> tasks = taskManager.getAllTasks();
        Task taskActual = tasks.get(0);
        assertEquals(taskExpected.getID(), taskActual.getID(), "Tasks IDs not equals");
        assertEquals(taskExpected.getDescription(), taskActual.getDescription(), "Tasks descriptions not equals");
        assertEquals(taskExpected.getName(), taskActual.getName(), "Tasks names not equals");
        assertEquals(taskExpected.getStatus(), taskActual.getStatus(), "Tasks statuses not equals");
    }


}