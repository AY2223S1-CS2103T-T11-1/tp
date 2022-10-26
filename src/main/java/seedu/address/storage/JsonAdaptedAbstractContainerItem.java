package seedu.address.storage;

import java.util.List;

import seedu.address.model.item.AbstractContainerItem;

/**
 * Jackson-friendly version of {@link AbstractContainerItem}.
 */
abstract class JsonAdaptedAbstractContainerItem extends JsonAdaptedAbstractDisplayItem {

    private final String fullPath;

    /**
     * Constructs a {@code JsonAdaptedAbstractContainerItem} with the given details.
     */
    public JsonAdaptedAbstractContainerItem(List<JsonAdaptedAttribute> attributes, String uid,
                                            List<JsonAdaptedTag> tagged, String fullPath) {
        super(attributes, uid, tagged);
        this.fullPath = fullPath;
    }

    public String getFullPath() {
        return fullPath;
    }
}
