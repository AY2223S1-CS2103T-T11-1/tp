package seedu.address.storage;

import seedu.address.commons.exceptions.IllegalValueException;

/**
 * Jackson-friendly version of {@link Attribute}.
 */
class JsonAdaptedAttribute {

    private final String attributeTitle;
    private final String content;

    JsonAdaptedAttribute(Attribute attribute) {
        attribute.toSaveableFormat();
        attributeTitle = attribute.getKey();
        content = attribute.getValue();
    }

    /**
     * Converts this Jackson-friendly adapted attribute object into the model's {@code Attribute} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted attribute.
     */
    public Attribute toModelType() throws IllegalValueException {
        if (!Attribute.isValidAttribute(key)) {
            throw new IllegalValueException(Attribute.MESSAGE_CONSTRAINTS);
        }
        return new Attribute(attributeTitle, content);
    }
}
