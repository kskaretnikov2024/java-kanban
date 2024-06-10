import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SubtaskTest {

    @Test
    public void subtasksWithEqualIDsShouldBeEqual() {
        Subtask subtask1 = new Subtask(6, "Do something 1", "Do do do 1", Status.NEW, 1);
        Task subtask2 = new Subtask(6, "Do something 2", "Do do do 2", Status.DONE, 1);
        assertEquals(subtask1, subtask2,
                "Самфинг вент вронг! Subtaskи должны быть равны, если равен их id");
    }
}