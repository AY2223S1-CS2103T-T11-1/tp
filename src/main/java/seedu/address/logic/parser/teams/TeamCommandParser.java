package seedu.address.logic.parser.teams;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.teams.AddTeamCommand;
import seedu.address.logic.commands.teams.DeleteTeamCommand;
import seedu.address.logic.commands.teams.ForEachTeamCommand;
import seedu.address.logic.commands.teams.RemoveUserFromTeamCommand;
import seedu.address.logic.commands.teams.SelectTeamCommand;
import seedu.address.logic.commands.teams.TeamCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parser for Team Command
 */
public class TeamCommandParser implements Parser<TeamCommand> {

    private static final String MESSAGE_USAGE = TeamCommand.COMMAND_WORD + " [new|delete|remove]";
    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<subcommandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution. The input must be a valid subcommand for Task.
     * There should not be a TaskCommand prefix in the input.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public TeamCommand parse(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("subcommandWord");
        final String arguments = matcher.group("arguments");

        switch (commandWord) {
        case AddTeamCommand.SUBCOMMAND_WORD:
            return new AddTeamCommandParser().parse(arguments);
        case DeleteTeamCommand.SUBCOMMAND_WORD:
            return new DeleteTeamCommandParser().parse(arguments);
        case RemoveUserFromTeamCommand.SUBCOMMAND_WORD:
            return new RemoveUserFromTeamCommandParser().parse(arguments);
        case SelectTeamCommand.SUBCOMMAND_WORD:
            return new SelectTeamCommandParser().parse(arguments);
        case ForEachTeamCommand.SUBCOMMAND_WORD:
            return new ForEachTeamCommandParser().parse(arguments);
        default:
            throw new ParseException(MESSAGE_USAGE);
        }
    }

}
