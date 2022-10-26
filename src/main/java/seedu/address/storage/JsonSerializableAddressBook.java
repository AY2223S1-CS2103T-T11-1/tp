package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.group.Group;
import seedu.address.model.person.Person;
import seedu.address.model.task.Task;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_PERSON = "Person list contains duplicate display items(s).";
    public static final String MESSAGE_DUPLICATE_GROUP = "Group list contains duplicate display items(s).";
    public static final String MESSAGE_DUPLICATE_TASK = "Task list contains duplicate display items(s).";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();
    private final List<JsonAdaptedGroup> groups = new ArrayList<>();
    private final List<JsonAdaptedTask> tasks = new ArrayList<>();

    /*
     Instructions for assembling of nested groups, persons and tasks. Notes child to parent pairing for
     reconstruction of the AddressBook.
     */
    private final Map<String, List<String>> parentHierarchy = new HashMap<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given persons, groups, tasks, and hierarchy.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("persons") List<JsonAdaptedPerson> persons,
                                       @JsonProperty("groups") List<JsonAdaptedGroup> groups,
                                       @JsonProperty("tasks") List<JsonAdaptedTask> tasks,
                                       @JsonProperty("hierarchy") Map<String, List<String>> parentHierarchy) {

        this.persons.addAll(persons);
        this.groups.addAll(groups);
        this.tasks.addAll(tasks);
        this.parentHierarchy.putAll(parentHierarchy);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyAddressBook source) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
        groups.addAll(source.getTeamsList().stream().map(JsonAdaptedGroup::new).collect(Collectors.toList()));
        tasks.addAll(source.getTasksList().stream().map(JsonAdaptedTask::new).collect(Collectors.toList()));
        getParentHierarchy(source);
    }

    /**
     * Stores the parent child relationship of persons, groups and tasks within {@code ReadOnlyAddressBook}.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    private void getParentHierarchy(ReadOnlyAddressBook source) {
        for (Person person : source.getPersonList()) {
            parentHierarchy.put(person.getUid(),
                    person.getParents().stream().map(parent -> parent.getUid()).collect(Collectors.toList()));
        }

        for (Group group : source.getTeamsList()) {
            parentHierarchy.put(group.getUid(), List.of(group.getParentUid()));
        }

        for (Task task : source.getTasksList()) {
            parentHierarchy.put(task.getUid(), List.of(task.getParentUid()));
        }
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook toModelType() throws IllegalValueException {
        AddressBook addressBook = new AddressBook();

        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (addressBook.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            addressBook.addPerson(person);
        }

        for (JsonAdaptedGroup jsonAdaptedGroup : groups) {
            Group group = jsonAdaptedGroup.toModelType();
            if (addressBook.hasGroup(group)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_GROUP);
            }
            addressBook.addTeam(group);
        }

        for (JsonAdaptedTask jsonAdaptedTask : tasks) {
            Task task = jsonAdaptedTask.toModelType();
            if (addressBook.hasTask(task)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_TASK);
            }
            addressBook.addTask(task);
        }

        return addressBook;
    }
}
