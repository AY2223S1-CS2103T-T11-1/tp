package seedu.address.logic.commands.logicalcommand;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.item.DisplayItem;

public class ContainsAttributeCommand extends Command {

    public static final String COMMAND_WORD = "contains";
    private static final String NO_SELECTED = "No item were selected!";
    private static final String INVALID_INPUT = "The input is not of type DisplayItem!";

    DisplayItem item = null;
    private final String attributeType;

    public ContainsAttributeCommand(String attributeType) {
        this.attributeType = attributeType;
    }

    @Override
    public void setInput(Object additionalData) throws CommandException {
        if (additionalData == null || !(additionalData instanceof DisplayItem)) {
            throw new CommandException(INVALID_INPUT);
        }
        item = (DisplayItem) additionalData;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (item == null) {
            throw new CommandException(NO_SELECTED);
        }
        Boolean res = item.getAttribute(attributeType).isPresent();
        return new CommandResult(String.format("result is %s", res), false, false, res);
    }

}