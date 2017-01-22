package my.project.todolist.model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Nikol on 1/5/2017.
 */
public class Task {
    public static final String DATA_FORMAT = "dd.MM.yyyy HH:mm:ss";
    private final int MILLISECONDS = 1000;

    private int id;
    private String description;
    private long created;
    private String createdDate;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        created = new Date().getTime() / MILLISECONDS;
        retrieveCreatedDate(created);
        isDone = false;
    }

    private void retrieveCreatedDate(long ms) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATA_FORMAT);
        createdDate = dateFormat.format(new Date(ms * MILLISECONDS));
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
        retrieveCreatedDate(created);
    }

    public boolean isDone() {
        return isDone;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", created=" + created +
                ", isDone=" + isDone +
                '}';
    }
}
