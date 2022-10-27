package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.AccessDisplayFlags.GROUP;
import static seedu.address.model.AccessDisplayFlags.PERSON;

import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import seedu.address.model.attribute.Name;
import seedu.address.model.item.AbstractDisplayItem;
import seedu.address.model.item.AbstractSingleItem;
import seedu.address.model.item.DisplayItem;
import seedu.address.model.item.exceptions.ItemCannotBeParentException;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated,
 * immutable.
 */
public class Person extends AbstractDisplayItem {

    private Set<AbstractSingleItem> parents = new HashSet<>();
    private Fields fields = new Fields();

    /**
     * Every field must be present and not null.
     */
    public Person(String name) {
        super(name, PERSON, GROUP);
        requireAllNonNull(name, fields);
    }

    /**
     * Every field must be present and not null.
     */
    public Person(String name, Fields fields) {
        super(name, PERSON, GROUP);
        requireAllNonNull(name, fields);
        this.fields = fields;
    }

    public Name getName() {
        return name;
    }

    /**
     * Retrieves the Fields instance of the Person.
     *
     * @return the Fields instance of the Person.
     */
    public Fields getFields() {
        return fields;
    }

    /**
     * Adds a Field to the Fields of the Person.
     *
     * @param fieldName the field name to be added.
     */
    public void addField(String fieldName) {
        fields.addField(fieldName);
    }

    /**
     * Removes a field from the Fields of the Person
     *
     * @param fieldName the field name to be removed.
     */
    public void removeField(String fieldName) {
        fields.removeField(fieldName);
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName());
    }

    @Override
    public void setParent(DisplayItem o) throws ItemCannotBeParentException {
        if (o == null) {
            return;
        }
        if (!(o instanceof AbstractSingleItem) || parents.contains(o)) {
            throw new ItemCannotBeParentException(o);
        }

        parents.add((AbstractSingleItem) o);
    }

    @Override
    public void removeParent(DisplayItem deletedParent) {
        parents.removeIf(p -> (p.equals(deletedParent) || p.isPartOfContext(deletedParent)));
    }

    @Override
    public String getFullPath() {
        // person should not have a full path.
        return name.fullName;
    }

    @Override
    public String getRelativePath(DisplayItem parent) {
        // person should not have a relative path.
        return name.fullName;
    }

    @Override
    public Set<? extends DisplayItem> getParents() {
        return parents;
    }

    @Override
    protected String getTitle(List<String> sb, AbstractDisplayItem o) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public UUID getUuid() {
        return UUID.nameUUIDFromBytes(("Person: " + getFullPath()).getBytes(StandardCharsets.UTF_8));
    }
}
