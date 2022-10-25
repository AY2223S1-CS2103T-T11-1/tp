package seedu.address.storage;

import java.util.List;

public class JsonAdaptedAbstractContainerItem extends JsonAdaptedAbstractDisplayItem {

    private final String fullPath;

    public JsonAdaptedAbstractContainerItem(List<JsonAdaptedAttribute> attributes, String uid,
                                            List<JsonAdaptedTag> tagged, String fullPath) {
        super(attributes, uid, tagged);
        this.fullPath = fullPath;
    }

    public String getFullPath() {
        return fullPath;
    }
}
