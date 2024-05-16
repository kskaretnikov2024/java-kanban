public class Managers {
    public static TaskManagerInterface getAddTaskInterface(AddTaskInterface addTaskInterface) {
        return new TaskManager(addTaskInterface);
    }

    public static AddTaskInterface getAddTask() {
        return new AddTask();
    }
}
