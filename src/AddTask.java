import java.util.ArrayList;

public class AddTask implements AddTaskInterface {
    private final ArrayList<Task> tasks = new ArrayList<>();

    public void add(Task task) {
        if (task != null) {
            tasks.add(task);
        } else {
            System.out.println("Самфинг вент вронг");
        }
    }

    public ArrayList<Task> getTasksList() {
        return tasks;
    }
}