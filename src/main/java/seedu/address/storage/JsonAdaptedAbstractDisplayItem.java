package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.item.DisplayItem;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Jackson-friendly version of {@link DisplayItem}.
 */
public abstract class JsonAdaptedAbstractDisplayItem {

    private final List<JsonAdaptedAttribute> attributes = new ArrayList<>();
    private final String uid;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

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

