package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.stream.Collectors;

public class JsonAdaptedTask extends JsonAdaptedAbstractDisplayItem {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Task's %s field is missing!";

    private final String completedTime;

    /**
     * Constructs a {@code JsonAdaptedTask} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedTask(@JsonProperty("attributes") List<JsonAdaptedAttribute> attributes,
                           @JsonProperty("uid") String uid,
                           @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
                           @JsonProperty("completedTime") String completedTime) {
        super(attributes, uid, tagged);
        this.completedTime = completedTime;
    }

    /**
     * Converts a given {@code Task} into this class for Jackson use.
     */
    public JsonAdaptedTask(Task source) {
        super(source.getAttributes().stream().map(JsonAdaptedAttribute::new).collect(Collectors.toList()),
                source.getUid(), source.getTags().stream().map(JsonAdaptedTag::new).collect(Collectors.toList()));
        completedTime = source.getCompletedTime().toString();
    }

    /**
     * Converts this Jackson-friendly adapted task object into the model's {@code Task} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted task.
     */
    public Task toModelType() throws IllegalValueException {
        final List<Tag> taskTags = new ArrayList<>();
        final List<Attribute> modelAttributes = new ArrayList<>();
        for (JsonAdaptedAttribute attribute : getAttributes()) {
            modelAttributes.add(attribute.toModelType());
        }

        for (JsonAdaptedTag tag : getTagged()) {
            taskTags.add(tag.toModelType());
        }

        try {
            final Set<Tag> modelTags = new HashSet<>(taskTags);
            final LocalDateTime modelCompletedTime = LocalDateTime.parse(completedTime);
            final UUID modelUid = UUID.fromString(getUid());
            return new Task(modelAttributes, modelTags, modelCompletedTime, modelUid);
        } catch (DateTimeParseException pe) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    LocalDateTime.class.getSimpleName())); //TODO change naming?
        } catch (IllegalArgumentException pe) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    UUID.class.getSimpleName()));
        }
    }
}
