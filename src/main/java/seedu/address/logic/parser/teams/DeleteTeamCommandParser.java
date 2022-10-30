package seedu.address.logic.parser.teams;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.teams.DeleteTeamCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

// @@author mohamedsaf1
/**
 * Parses input arguments and creates a new DeleteTeamCommand object
 */
public class DeleteTeamCommandParser implements Parser<DeleteTeamCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand and returns a
     * DeleteCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteTeamCommand parse(String args) throws ParseException {
        try {
            if (args == null || args.trim().length() == 0) {
                return new DeleteTeamCommand(null);
            }
            Index index = ParserUtil.parseIndex(args);
            return new DeleteTeamCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteTeamCommand.MESSAGE_USAGE), pe);
        }
    }

}
