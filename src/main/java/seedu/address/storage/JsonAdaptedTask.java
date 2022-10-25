package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.task.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.UUID;

public class JsonAdaptedTask extends JsonAdaptedDisplayItem {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Task's %s field is missing!";

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
        super(title);
        this.description = description;
        this.completedTime = completedTime;
        this.uid = uid;
        this.parentUid = parentUid;
    }

    /**
     * Converts a given {@code Task} into this class for Jackson use.
     */
    public JsonAdaptedTask(Task source) {
        super(source.getTitle());
        description = source.getStatus();
        completedTime = source.getCompletedTime().toString();
        uid = source.getUid();
        parentUid = source.getParentUid();
    }

    /**
     * Converts this Jackson-friendly adapted task object into the model's {@code Task} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted task.
     */
    public Task toModelType() throws IllegalValueException {
        try {
            LocalDateTime modelCompletedTime = LocalDateTime.parse(completedTime);
            UUID modelUid = UUID.fromString(uid);
            return new Task(getTitle(), description, modelCompletedTime, modelUid);
        } catch (DateTimeParseException pe) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    LocalDateTime.class.getSimpleName())); //TODO change naming?
        } catch (IllegalArgumentException pe) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    UUID.class.getSimpleName()));
        }
    }
}
