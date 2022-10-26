package seedu.address.storage;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.List;

import seedu.address.model.item.AbstractDisplayItem;

/**
 * Jackson-friendly version of {@link AbstractDisplayItem}.
 */
abstract class JsonAdaptedAbstractDisplayItem {

    private final List<JsonAdaptedAttribute> attributes = new ArrayList<>();
    private final String uid;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedAbstractContainerItem} with the given AbstractDisplayItem details.
     */
    protected JsonAdaptedAbstractDisplayItem(List<JsonAdaptedAttribute> attributes, String uid,
                                             List<JsonAdaptedTag> tagged) {
        requireAllNonNull(attributes, uid, tagged);
        this.attributes.addAll(attributes);
        this.uid = uid;
        this.tagged.addAll(tagged);
    }

    protected List<JsonAdaptedAttribute> getAttributes() {
        return attributes;
    }

    protected String getUid() {
        return uid;
    }

    protected List<JsonAdaptedTag> getTagged() {
        return tagged;
    }

}

