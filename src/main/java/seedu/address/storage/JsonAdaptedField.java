package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.field.Field;
import seedu.address.model.tag.Tag;

public class JsonAdaptedField {

    private final String name;
    private final String content;
    private final String other;

    /**
     * Constructs a {@code JsonAdaptedField} with the given field details.
     */
    @JsonCreator
    public JsonAdaptedField(@JsonProperty("name") String name, @JsonProperty("content") String content,
                                  @JsonProperty("other") String other) {
        this.name = name;
        this.content = content;
        this.other = other;
    }

    /**
     * Converts a given {@code Tag} into this class for Jackson use.
     */
    public JsonAdaptedField(Field source) {
        name = source.name;
        content = source.value;
        other = "";
    }

    /**
     * Converts this Jackson-friendly adapted field object into the model's {@code Field} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted field.
     */
    public Field toModelType() throws IllegalValueException {
        if (!Field.isValidName(name) || !Field.isValidField(content)) {
            throw new IllegalValueException(Tag.MESSAGE_CONSTRAINTS);
        }

        return new Field(name, content);
    }
}
