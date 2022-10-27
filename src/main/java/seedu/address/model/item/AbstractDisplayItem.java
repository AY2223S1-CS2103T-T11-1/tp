package seedu.address.model.item;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.model.attribute.Attribute;
import seedu.address.model.attribute.Name;
import seedu.address.model.item.exceptions.ItemCannotBeParentException;
import seedu.address.model.tag.Tag;

/**
 * Abstract class to represent an item that can contain other items.
 */
public abstract class AbstractDisplayItem implements DisplayItem {

    protected Name name;
    private int typeFlag;
    private int parentTypeFlag;
    private List<Attribute<?>> attributes;
    private Set<Tag> tags;

    protected AbstractDisplayItem(String name, int typeFlag, int parentTypeFlag) {
        requireAllNonNull(name, typeFlag);
        this.name = new Name(name);
        this.typeFlag = typeFlag;
        this.parentTypeFlag = parentTypeFlag;
        attributes = new ArrayList<>();
        tags = new HashSet<>();
    }

    @Override
    public void rename(String newName) {
        assert Name.isValidName(newName);
        this.name = new Name(newName);
    }

    @Override
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    @Override
    public void addTags(String... descriptions) {
        for (String description : descriptions) {
            Tag toAdd = new Tag(description);
            if (!tags.contains(toAdd)) {
                tags.add(toAdd);
            }
        }
    }

    @Override
    public void deleteTag(String description) {
        Tag toDelete = new Tag(description);
        tags.removeIf(tag -> tag.equals(toDelete));
    }

    @Override
    public Optional<Attribute<?>> getAttribute(String type) {
        return getAttributes().stream()
                .filter(attr -> attr.getAttributeType()
                        .equals(type))
                .findFirst();
    }

    @Override
    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    protected boolean canBeChildOf(AbstractDisplayItem o) {
        return (parentTypeFlag & o.typeFlag) > 0;
    }

    protected abstract String getTitle(List<String> sb, AbstractDisplayItem o);

    @Override
    public void addAttribute(Attribute<?> attribute) {
        requireNonNull(attribute);
        if (!attribute.isAllFlagMatch(typeFlag)) {
            throw new ItemCannotBeParentException(this);
        }
        if (attributes.stream().anyMatch(x -> x.equals(attribute))) {
            throw new ItemCannotBeParentException(this);
        }
        attributes.add(attribute);
    }

    @Override
    public int getTypeFlag() {
        return typeFlag;
    }

    @Override
    public void deleteAttribute(String type) {
        attributes.removeIf(attr -> attr.getAttributeType().equals(type));
    }

    @Override
    public String toString() {
        return name.toString();
    }

    @Override
    public Name getName() {
        return name;
    }

    @Override
    public boolean stronglyEqual(DisplayItem o) {
        if (!weaklyEqual(o)) {
            return false;
        }
        AbstractDisplayItem g = (AbstractDisplayItem) o;
        return g.getParents().equals(getParents())
                && g.getAttributes().equals(getAttributes())
                && g.getTags().equals(getTags());
    }

    @Override
    public boolean weaklyEqual(DisplayItem o) {
        if (!(o instanceof AbstractDisplayItem)) {
            return false;
        }
        return (o instanceof AbstractDisplayItem)
                && ((AbstractDisplayItem) o).getFullPath().equals(getFullPath())
                && ((AbstractDisplayItem) o).typeFlag == typeFlag;
    }

    @Override
    public boolean isPartOfContext(DisplayItem o) {
        if (o == null || getParents().contains(o)) {
            return true;
        }
        for (DisplayItem parent : getParents()) {
            if (parent.isPartOfContext(o)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Attribute<?>> getAttributes() {
        return attributes;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AbstractDisplayItem)) {
            return false;
        }
        return stronglyEqual((AbstractDisplayItem) obj);
    }
}
