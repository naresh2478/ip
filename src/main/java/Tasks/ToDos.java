package Tasks;

import java.util.Objects;

public class ToDos extends Task {

    protected String description;

    public ToDos(String description) {
        super(description);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ToDos toDos = (ToDos) o;
        return Objects.equals(description, toDos.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}