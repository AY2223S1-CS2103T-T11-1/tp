package seedu.address.storage;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.item.AbstractContainerItem;

public class JsonAdaptedAbstractContainerItem {

    /**
     * Converts this Jackson-friendly adapted abstract container item object into the model's
     * {@code AbstractContainerItem} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted abstract container item.
     */
    public AbstractContainerItem toModelType() {
        return new Group()
    }
}
