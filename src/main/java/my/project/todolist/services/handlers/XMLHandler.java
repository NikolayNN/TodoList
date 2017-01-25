package my.project.todolist.services.handlers;

import com.thoughtworks.xstream.XStream;
import my.project.todolist.model.Task;

import java.util.List;

/**
 * Created by Nikol on 1/25/2017.
 */
public class XMLHandler implements Handler {
    private XStream xStream;
    private final String CONTENT_TYPE = "text/xml";

    public XMLHandler() {
        xStream = new XStream();
    }

    public String getContentType() {
        return CONTENT_TYPE;
    }

    /**
     * The method convert task list to xml/
     * @param tasks tasks list
     * @return xml.
     */
    public String handle(List<Task> tasks){
        xStream.alias("task", Task.class);
        return xStream.toXML(tasks);
    }
}
