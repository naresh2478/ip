public class ToDos extends Task {

    protected String description;

    public ToDos(String description) {
        super(description);
    }


    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}