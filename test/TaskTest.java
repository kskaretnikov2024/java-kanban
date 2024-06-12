import Statuses.Status;
import Tasks.Task;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {

    @Test
    public void tasksWithEqualIdsShouldBeEqual() {
        Task task1 = new Task(5, "Do something 1", "Do do do 1", Status.NEW);
        Task task2 = new Task(5, "Do something 2", "Do do do 2", Status.DONE);
        assertEquals(task1, task2,
                "Самфинг вент вронг! Taskи должны быть равны, если равен их id");
    }
}