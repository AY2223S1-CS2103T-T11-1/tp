package seedu.address.logic.commands.teams;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.group.Group;

//@@author mohamedsaf1
/**
 * Deletes a team from Contactmation
 */
public class DeleteTeamCommand extends TeamCommand {
    public static final String SUBCOMMAND_WORD = "delete";
    
    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + SUBCOMMAND_WORD
            + ": Delete the team with the specified index\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " " + SUBCOMMAND_WORD + " 1\n";

    public static final String SWITCH_SUCCESS = " Deleted %s%n";

    private Group toDelete = null;

    private final Index targetIndex;

    public DeleteTeamCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);


        if (toDelete == null) {
            List<Group> lastShownList = model.getFilteredTeamList();

            if (targetIndex.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }

            toDelete = lastShownList.get(targetIndex.getZeroBased());
        }

        model.deleteTeam(toDelete);
        return new CommandResult(String.format(SWITCH_SUCCESS, toDelete));
    }

    @Override
    public void setInput(Object additionalData) throws CommandException {
        if (additionalData == null || !(additionalData instanceof Group)) {
            toDelete = null;
            return;
        } 
        this.toDelete = (Group) additionalData;
    }
}
