import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EpicTest {

    @Test
    public void epicsWithEqualIDsShouldBeEqual() {
        Epic epic1 = new Epic(7, "Do something 1", "Do do do 1", Status.NEW);
        Epic epic2 = new Epic(7, "Do something 2", "Do do do 2", Status.IN_PROGRESS);
        assertEquals(epic1, epic2,
                "Самфинг вент вронг! Epicи должны быть равны, если равен их id");
    }
}