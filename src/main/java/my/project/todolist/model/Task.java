package my.project.todolist.model;

import java.util.Date;

/**
 * Created by Nikol on 1/5/2017.
 */
public class Task {
    private int id;
    private String description;
    private long created;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        created = getCurrentTime();
        isDone = false;
    }

    private long getCurrentTime() {
        Date current = new Date();
        return current.getTime()/1000;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public long getCreated() {
        return created;
    }

    public boolean isDone() {
        return isDone;
    }
}
