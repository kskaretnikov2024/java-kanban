import java.util.Objects;

public class Subtask extends Task {
    private final int epicID;

    public Subtask(String description, String name, Status status, int epicID) {
        super(description, name, status);
        this.epicID = epicID;
    }

    public Subtask(int id, String description, String name, Status status, int epicID) {
        super(id, description, name, status);
        this.epicID = epicID;
    }

    public int getEpicID() {
        return epicID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Subtask subtask = (Subtask) o;
        return epicID == subtask.epicID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), epicID);
    }

    @Override
    public String toString() {
        return "Subtask{" +
                "epicID=" + getEpicID() +
                ", description='" + getDescription() + '\'' +
                ", ID=" + getID() +
                ", name='" + getName() + '\'' +
                ", status=" + getStatus() +
                '}';
    }
}
