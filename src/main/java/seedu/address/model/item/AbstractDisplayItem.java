package seedu.address.model.item;

import seedu.address.model.person.Name;

import java.util.UUID;

public abstract class AbstractDisplayItem implements DisplayItem {

    private final Name name;
    private final UUID uid;

    protected AbstractDisplayItem(Name name) {
        this.name = name;
    }

    public Name getName() {
        return name;
    }
}
