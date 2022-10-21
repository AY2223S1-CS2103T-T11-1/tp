package seedu.address.storage;

import java.util.*;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.item.AbstractContainerItem;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Fields;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson extends JsonAdaptedDisplayItem {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private final Set<JsonAdaptedAbstractContainerItem> parents = new HashSet<>();
    private final JsonAdaptedFields fields;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
            @JsonProperty("parents") HashSet<JsonAdaptedAbstractContainerItem> parents,
            @JsonProperty("fields") JsonAdaptedFields fields) {
        this.name = name;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
        if (parents != null) {
            this.parents.addAll(parents);
        }
        this.fields = fields;
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        name = source.getName().fullName;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        parents.addAll(source.getParents().stream()
                .map(JsonAdaptedAbstractContainerItem::new)
                .collect(Collectors.toSet()));
        fields = new JsonAdaptedFields(source.getFields());
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Person toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            personTags.add(tag.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

//        if (phone == null) {
//            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
//        }
//        if (!Phone.isValidPhone(phone)) {
//            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
//        }
//        final Phone modelPhone = new Phone(phone);
//
//        if (email == null) {
//            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
//        }
//        if (!Email.isValidEmail(email)) {
//            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
//        }
//        final Email modelEmail = new Email(email);
//
//        if (address == null) {
//            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
//        }
//        if (!Address.isValidAddress(address)) {
//            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
//        }
//        final Address modelAddress = new Address(address);

        // dummy fields
        final Fields modelFields = new Fields();
        final Set<AbstractContainerItem> modelParents = parents.stream().map(item -> item.toModelType())
                .collect(Collectors.toSet());
        final Set<Tag> modelTags = new HashSet<>(personTags);
        return new Person(modelName, modelPhone, modelEmail, modelAddress, modelTags, modelFields);
    }

}
