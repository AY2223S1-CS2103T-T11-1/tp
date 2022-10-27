package seedu.address.testutil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javafx.scene.Node;
<<<<<<< HEAD
//import seedu.address.model.attribute.Field;
import seedu.address.model.attribute.Address;
import seedu.address.model.attribute.Attribute;
import seedu.address.model.attribute.AttributeList;
import seedu.address.model.attribute.Email;
import seedu.address.model.attribute.Name;
import seedu.address.model.attribute.Phone;
import seedu.address.model.attribute.exceptions.AttributeException;
//import seedu.address.model.person.Fields;
=======
import seedu.address.model.attribute.Address;
import seedu.address.model.attribute.Attribute;
import seedu.address.model.attribute.Email;
import seedu.address.model.attribute.Field;
import seedu.address.model.attribute.Name;
import seedu.address.model.attribute.Phone;
import seedu.address.model.person.Fields;
>>>>>>> master
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";

    private Name name;
<<<<<<< HEAD
=======
    private List<Attribute<?>> attrs;
>>>>>>> master
    private Set<Tag> tags;
    private AttributeList fields;
    private List<Attribute<?>> attrs;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        attrs = new ArrayList<>();
        tags = new HashSet<>();
        fields = new AttributeList();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        attrs = new ArrayList<>(personToCopy.getAttributes());
        tags = new HashSet<>(personToCopy.getTags());
        fields = personToCopy.getFields();
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the
     * {@code Person} that we are building.
     */
    public PersonBuilder withTags(String... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
<<<<<<< HEAD
     * Adds custom attribute to the person
=======
     * Adds a custom attribute.
>>>>>>> master
     * @param name
     * @param data
     * @param <U>
     * @return
     */
    public <U> PersonBuilder addCustomAttr(String name, U data) {
        this.attrs.add(new Attribute<U>() {
            @Override
            public String getAttributeType() {
                return name;
            }

            @Override
            public U getAttributeContent() {
                return data;
            }

            @Override
            public boolean isNameMatch(String name) {
                return false;
            }

            @Override
            public boolean isVisibleInMenu() {
                return true;
            }

            @Override
            public boolean isDisplayable() {
                return true;
            }

            @Override
            public boolean isAllFlagMatch(int flag) {
                // TODO Auto-generated method stub
                return false;
            }

            @Override
            public boolean isAnyFlagMatch(int flag) {
                // TODO Auto-generated method stub
                return false;
            }

            /**
             * Returns true of any of the bits of the style flag settings is true
             *
             * @param flag
             */
            @Override
            public boolean isAnyStyleMatch(int flag) {
                return false;
            }

            /**
             * Returns true of all of the bits of the style flag settings is true
             *
             * @param flag
             */
            @Override
            public boolean isAllStyleMatch(int flag) {
                return false;
            }

            @Override
            public Node getJavaFxRepresentation() {
                return null;
            }

            @Override
            public <T> boolean isSameType(Attribute<T> o) {
                return false;
            }

            @Override
            public Map<String, Object> toSaveableData() {
                return null;
            }
        });
        return this;
    }

    /**
     * Sets the {@code Fields} of the {@code Person} that we are building.
     */
    public PersonBuilder withFields(String... fieldNames) throws AttributeException {
        fields = new AttributeList();
        for (String fieldName : fieldNames) {
            fields.addAttribute(fieldName, "dummy content");
        }
        return this;
    }

    /**
<<<<<<< HEAD
     * Builds a Person instance
=======
     * Returns a person with specified attributes in builder.
>>>>>>> master
     * @return
     */
    public Person build() {
        Person p = new Person(name.fullName, fields);
        p.setTags(tags);
        attrs.forEach(p::addAttribute);
        return p;
    }

    /**
<<<<<<< HEAD
     * Adds an Address attribute to the PersonBuilder instance
     * @param string
=======
     * Adds address attribute to person
     * @param string address
>>>>>>> master
     * @return
     */
    public PersonBuilder withAddress(String string) {
        attrs.add(new Address(string));
        return this;
    }

    /**
<<<<<<< HEAD
     * Adds an Email attribute to the PersonBuilder instance
     * @param string
=======
     * Adds email attribute to person
     * @param string email
>>>>>>> master
     * @return
     */
    public PersonBuilder withEmail(String string) {
        attrs.add(new Email(string));
        return this;
    }

    /**
<<<<<<< HEAD
     * Adds a Phone attribute to the PersonBuilder instance
     * @param string
=======
     * Adds phone attribute to person
     * @param string phone number
>>>>>>> master
     * @return
     */
    public PersonBuilder withPhone(String string) {
        attrs.add(new Phone(string));
        return this;
    }

}
