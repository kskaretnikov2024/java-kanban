import java.util.ArrayList;
import java.util.Objects;

public class Epic extends Task {
    private final ArrayList<Integer> subtaskIDs = new ArrayList<>();

    public Epic(String description, String name, Status status) {
        super(description, name, status);
    }

    public ArrayList<Integer> getSubtaskIDs() {
        return subtaskIDs;
    }

    public void setSubtaskIDs(int id) {
        subtaskIDs.add(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Epic epic = (Epic) o;
        return Objects.equals(subtaskIDs, epic.subtaskIDs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), subtaskIDs);
    }

    @Override
    public String toString() {
        return "Epic{" +
                "subtaskIDs=" + subtaskIDs +
                ", description='" + getDescription() + '\'' +
                ", ID=" + getID() +
                ", name='" + getName() + '\'' +
                ", status=" + getStatus() +
                '}';
    }
}
