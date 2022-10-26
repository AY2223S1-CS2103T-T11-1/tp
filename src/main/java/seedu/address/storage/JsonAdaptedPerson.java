package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson extends JsonAdaptedAbstractDisplayItem {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     *
     * @param attributes that are part of the current person representation.
     * @param uid a unique identity that is given to each specific instance.
     * @param tagged tags on a person.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("attributes") List<JsonAdaptedAttribute> attributes,
            @JsonProperty("uid") String uid, @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        super(attributes, uid, tagged);
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        super(source.getAttributes().stream().map(JsonAdaptedAttribute::new).collect(Collectors.toList()),
                source.getUid(), source.getTags().stream().map(JsonAdaptedTag::new).collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Person toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        final List<Attribute> modelAttributes = new ArrayList<>();
        for (JsonAdaptedTag tag : getTagged()) {
            personTags.add(tag.toModelType());
        }

        for (JsonAdaptedAttribute attribute : getAttributes()) {
            personAttributes.add(attribute.toModelType());
        }

        try {
            final Set<Tag> modelTags = new HashSet<>(personTags);
            final UUID modelUid = UUID.fromString(getUid());
            return new Person(modelAttributes, modelTags, modelUid);
        } catch (IllegalArgumentException pe) {
            throw new IllegalValueException(pe.getMessage());
        }
    }

}
