package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.group.Group;
import seedu.address.model.item.AbstractContainerItem;
import seedu.address.model.person.*;
import seedu.address.model.tag.Tag;

import java.util.*;
import java.util.stream.Collectors;

public class JsonAdaptedGroup extends JsonAdaptedDisplayItem {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Group's %s field is missing!";

    private final String parentUid;
    private final String groupName;
    private final String fullPath;
    private final String uid;

    /**
     * Constructs a {@code JsonAdaptedGroup} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedGroup(@JsonProperty("parent") String parentUid,
        @JsonProperty("groupName") String groupName, @JsonProperty("fullPath") String fullPath,
        @JsonProperty("uid") String uid) {
        this.parentUid = parentUid;
        this.groupName = groupName;
        this.fullPath = fullPath;
        this.uid = uid;
    }

    /**
     * Converts a given {@code Group} into this class for Jackson use.
     */
    public JsonAdaptedGroup(Group source) {
        parentUid = source.getParentUid();
        this.groupName = source.getName();
        this.fullPath = source.getFullPath();
        this.uid = source.getUid();
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Group toModelType() throws IllegalValueException {
//        //TODO checks
//        if (parentUid == null) {
//            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "parentUid"));
//        }
//
        final String modelParentUid = parentUid;
        final String modelName = groupName;
        final String modelFullPath = fullPath;
        final UUID modelUid = UUID.fromString(uid);
        return new Group(modelName, null, fullPath, modelUid);
//        final List<Tag> groupTags = new ArrayList<>();
//        for (JsonAdaptedTag tag : tagged) {
//            groupTags.add(tag.toModelType());
//        }
//
//        if (name == null) {
//            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
//        }
//        if (!Name.isValidName(name)) {
//            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
//        }
//        final Name modelName = new Name(name);
//
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
//
//        // dummy fields
//        final Fields modelFields = new Fields();
//
//        final Set<Tag> modelTags = new HashSet<>(groupTags);
//        return new Person(modelName, modelPhone, modelEmail, modelAddress, modelTags, modelFields);
    }
}
