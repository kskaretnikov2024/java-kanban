package Tasks;

import Status.Status;

import java.util.Objects;

public class Subtask extends Task {
    private final int epicId;

    public Subtask(String description, String name, Status status, int epicId) {
        super(description, name, status);
        this.epicId = epicId;
    }

    public Subtask(int id, String description, String name, Status status, int epicId) {
        super(id, description, name, status);
        this.epicId = epicId;
    }

    public int getEpicId() {
        return epicId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Subtask subtask = (Subtask) o;
        return epicId == subtask.epicId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), epicId);
    }

    @Override
    public String toString() {
        return "Tasks.Subtask{" +
                "epicID=" + getEpicId() +
                ", description='" + getDescription() + '\'' +
                ", ID=" + Id() +
                ", name='" + getName() + '\'' +
                ", status=" + getStatus() +
                '}';
    }
}
