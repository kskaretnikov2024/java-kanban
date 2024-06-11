import Managers.Managers;
import Status.Status;
import Tasks.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import Managers.TaskManager;

class InMemoryHistoryManagerTest {

    private static TaskManager taskManager;

    @BeforeEach
    public void beforeEach() {
        taskManager = Managers.getDefault();
    }


    @Test
    public void getHistoryShouldSavePreviousTaskAfterUpdate() {
        Task task1 = taskManager.createTask(new Task("New Tasks.Task for test getHistoryShouldSavePreviousTaskAfterUpdate",
                "New Task1", Status.NEW));
        taskManager.createTask(task1);
        taskManager.getTaskById(task1.Id());
        taskManager.updateTask(new Task(task1.Id(), "Update for Task1 for test getHistoryShouldSavePreviousTaskAfterUpdate",
                "New Task1 Update", Status.IN_PROGRESS));
        List<Task> tasks = taskManager.getHistory();
        Task previousTask = tasks.get(0);
        assertEquals(task1.getName(), previousTask.getName(), "Not saved name of previous version of Task1 in History");
        assertEquals(task1.getDescription(), previousTask.getDescription(),
                "Not saved description of previous version of Task1 in History");

    }
}