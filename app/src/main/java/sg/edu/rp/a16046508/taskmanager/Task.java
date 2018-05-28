package sg.edu.rp.a16046508.taskmanager;

public class Task {
    private int ID;
    private String taskName;
    private String description;

    public Task(int ID, String taskName, String description) {
        this.ID = ID;
        this.taskName = taskName;
        this.description = description;
    }

    public int getID() {
        return ID;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getDescription() {
        return description;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
