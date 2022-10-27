package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;

/** Pure Command represent a command that does not take in additional input */
public abstract class PureCommand extends Command {

    @Override
    public void setInput(Object additionalData) throws CommandException {
        return;
    }
}
