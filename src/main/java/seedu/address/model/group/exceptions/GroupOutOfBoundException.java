package seedu.address.model.group.exceptions;

import seedu.address.logic.commands.exceptions.CommandException;

/**
 * Encapsulates an OutOfBoundException for a Group
 */
public class GroupOutOfBoundException extends CommandException {

    public GroupOutOfBoundException(int length, int index) {
        super(String.format("Group out of bounds. Length is only %d yet index %d supplied.", length, index));
    }

}
