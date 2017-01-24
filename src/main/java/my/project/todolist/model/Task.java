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
    private long createdMilliseconds;
    private String createdDate;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        createdMilliseconds = new Date().getTime() / MILLISECONDS;
        retrieveCreatedDate(createdMilliseconds);
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

    public long getCreatedMilliseconds() {
        return createdMilliseconds;
    }

    public void setCreatedMilliseconds(long createdMilliseconds) {
        this.createdMilliseconds = createdMilliseconds;
        retrieveCreatedDate(createdMilliseconds);
    }

    public boolean isDone() {
        return isDone;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", createdMilliseconds=" + createdMilliseconds +
                ", isDone=" + isDone +
                '}';
    }

}
