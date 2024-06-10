import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class InMemoryTaskManagerTest {

    private static TaskManager taskManager;

    @BeforeEach
    public void beforeEach() {
        taskManager = Managers.getDefault();
    }

    @Test
    void addTaskAndFindByID() {
        Task taskExpected = taskManager.createTask(new Task("New Task for test addTaskAndFindByID", "New Task1", Status.NEW));
        Task taskActual = taskManager.getTaskByID(taskExpected.getID());
        assertNotNull(taskActual, "Task not found");
        assertEquals(taskExpected, taskActual, "Tasks not equals");
    }

    @Test
    void addEpicAndSubtasksAndFindByID() {
        Epic epicExpected = taskManager.createEpic(new Epic ("New Epic for addEpicAndSubtasksAndFindByID", "New Epic1", Status.NEW));
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
   final List<Task> tasks = taskManager.getTasks();
    assertNotNull(tasks, "Задачи не возвращаются.");
    assertEquals(1, tasks.size(), "Неверное количество задач.");
    assertEquals(task, tasks.getFirst(), "Задачи не совпадают.");
}