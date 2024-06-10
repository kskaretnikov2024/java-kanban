import java.util.Objects;

public class Task {
    private final String name;
    private final String description;
    private int id;
    private Status status;

    public Task( String description, String name, Status status) {
            this.description = description;
        this.name = name;
        this.status = status;
    }

    public Task(int id, String name, String description, Status status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
    }


    public String getDescription() {

        return description;
    }

    public int getID() {

        return id;
    }

    public void setID(int id) {

        this.id = id;
    }

    public String getName() {

        return name;
    }

    public Status getStatus() {

        return status;
    }

    public void setStatus(Status status) {

        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id;
    }

    @Override
    public int hashCode() {

        return Objects.hash(description, id, name, status);
    }

    @Override
    public String toString() {
        return "Task{" +
                "description='" + description + '\'' +
                ", ID=" + id +
                ", name='" + name + '\'' +
                ", status=" + status +
                '}';
    }
}