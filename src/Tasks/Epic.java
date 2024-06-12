package Tasks;

import Statuses.Status;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Epic extends Task {
    private final List<Integer> subtaskIds = new ArrayList<>();

    public Epic(String description, String name, Status status) {
        super(description, name, status);
    }

    public Epic(int id, String description, String name, Status status) {
        super(id, description, name, status);
    }

    public List<Integer> getSubtaskIds() {
        return subtaskIds;
    }

    public void setSubtaskIds(int id) {
        subtaskIds.add(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Epic epic = (Epic) o;
        return Objects.equals(subtaskIds, epic.subtaskIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), subtaskIds);
    }

    @Override
    public String toString() {
        return "Tasks.Epic{" +
                "subtaskIDs=" + subtaskIds +
                ", description='" + getDescription() + '\'' +
                ", ID=" + Id() +
                ", name='" + getName() + '\'' +
                ", status=" + getStatus() +
                '}';
    }
}