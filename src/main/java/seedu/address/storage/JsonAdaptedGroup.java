package seedu.address.storage;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.group.Group;
import seedu.address.model.tag.Tag;

public class JsonAdaptedGroup extends JsonAdaptedAbstractContainerItem {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Team's %s field is missing!";

    /**
     * Constructs a {@code JsonAdaptedGroup} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedGroup(@JsonProperty("attributes") List<JsonAdaptedAttribute> attributes,
                            @JsonProperty("uid") String uid,
            @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
                            @JsonProperty("fullPath") String fullPath) {
        super(attributes, uid, tagged, fullPath);
    }

    /**
     * Converts a given {@code Group} into this class for Jackson use.
     */
    public JsonAdaptedGroup(Group source) {
        super(source.getAttributes().stream().map(JsonAdaptedAttribute::new).collect(Collectors.toList()),
                source.getUid(), source.getTags().stream().map(JsonAdaptedTag::new).collect(Collectors.toList()),
                source.getFullPathName());
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Group toModelType() throws IllegalValueException {
        final List<Attributes> modelAttributes = getAttributes().stream().map(attribute -> attribute.toModelType())
                .collect(Collectors.toList());
        final UUID modelUid = UUID.fromString(getUid());
        final Set<Tag> modelTags = getTagged().stream().map(tag -> tag.toModelType()).collect(Collectors.toSet());
        final String modelFullPath = getFullPath();

        return new Group(modelAttributes, modelUid, modelTags, modelFullPath);
    }
}
