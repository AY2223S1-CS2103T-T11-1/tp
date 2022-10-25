package seedu.address.storage;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.item.AbstractContainerItem;

public class JsonAdaptedAbstractContainerItem extends JsonAdaptedDisplayItem {

    private final String parentUid;
    private final String fullPath;
    private final String uid;

    public JsonAdaptedAbstractContainerItem(AbstractContainerItem containerItem) {
        super(containerItem.getName());
        this.parentUid = containerItem.getParentUid();
        this.fullPath = containerItem.getFullPathName();
        this.uid = containerItem.getUid();
    }

    public JsonAdaptedAbstractContainerItem(String name, String fullPath, String parentUid, String uid) {
        super(name);
        this.parentUid = parentUid;
        this.fullPath = fullPath;
        this.uid = uid;
    }

    /**
     * Converts this Jackson-friendly adapted abstract container item object into the model's
     * {@code AbstractContainerItem} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted abstract container item.
     */
    public AbstractContainerItem toModelType() {

    }
}
