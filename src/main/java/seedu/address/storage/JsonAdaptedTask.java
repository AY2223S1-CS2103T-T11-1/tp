package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import seedu.address.model.group.Group;
import seedu.address.model.task.Task;

import java.util.List;

public class JsonAdaptedTask extends JsonAdaptedDisplayItem {

    private final String title;
    private final String description;
    private final String completedTime;
    private final String uid;
    private final String parentUid;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedTask(@JsonProperty("title") String title, @JsonProperty("description") String description,
                             @JsonProperty("completedTime") String completedTime,
                             @JsonProperty("uid") String uid, @JsonProperty("parentUid") String parentUid) {
        this.title = title;
        this.description = description;
        this.completedTime = completedTime;
        this.uid = uid;
        this.parentUid = parentUid;
    }

    /**
     * Converts a given {@code Task} into this class for Jackson use.
     */
    public JsonAdaptedTask(Task source) {
        title = source.getTitle();
        description = source.getStatus();
        completedTime = source.getCompletedTime().toString();
        uid = source.getUid();
        parentUid = source.getParentUid();
    }
}
