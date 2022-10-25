package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.group.Group;
import seedu.address.model.item.AbstractContainerItem;
import seedu.address.model.item.DisplayItem;

/**
 * Jackson-friendly version of {@link DisplayItem}.
 */
public abstract class JsonAdaptedDisplayItem {

    private final String title;

    public JsonAdaptedDisplayItem(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

}

