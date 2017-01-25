package my.project.todolist.services.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import my.project.todolist.model.Task;
import com.fasterxml.jackson.core.*;


import java.util.List;

/**
 * Created by Nikol on 1/25/2017.
 */
public class JsonHandler implements Handler {
    private ObjectMapper mapper;
    private final String CONTENT_TYPE = "text/json";

    public JsonHandler() {
        mapper = new ObjectMapper();
    }

    public String getContentType() {
        return CONTENT_TYPE;
    }

    @Override
    public String handle(List<Task> tasks) {
        try {
            return mapper.writeValueAsString(tasks);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
