import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ManagersTest {

    @Test
    void shouldGetDefaultInitializeInMemoryTaskManager() {
        assertNotNull(Managers.getDefault());
    }

    @Test
    void shouldGetDefaultHistoryInitializeInMemoryHistoryManager() {
      assertNotNull(Managers.getDefaultHistory());
    }
}