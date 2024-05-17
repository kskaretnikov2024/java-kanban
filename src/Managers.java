public class Managers {
    public static TaskManager getAddTask (AddTask addTask) {
        return new TaskManager(addTask);
    }

    public static AddTask getAddTask() {
        return new AddTask();
    }
}
