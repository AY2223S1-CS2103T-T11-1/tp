package seedu.address.logic.commands.tasks;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.Task;

//@@author connlim

/**
 * Unmarks a task as complete.
 */
public class UnmarkTaskCommand extends TaskCommand {
    public static final String SUBCOMMAND_WORD = "unmark";

    public static final String MESSAGE_USAGE =
            TaskCommand.getFullCommand(SUBCOMMAND_WORD) + ": Marks the task as incomplete\n"
                    + "Parameters: INDEX (must be a positive integer)\n" + "Example: " + COMMAND_WORD + " 1\n";

    public static final String ALREADY_UNMARKED = " task %s is already incomplete%n";
    public static final String UNMARK_SUCCESS = " task %s is marked as incomplete%n";

    private final Index targetIndex;

    public UnmarkTaskCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Task> lastShownList = model.getFilteredTaskList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Task task = lastShownList.get(targetIndex.getZeroBased());
        Task newTask = task.unmark();
        if (newTask == task) {
            throw new CommandException(ALREADY_UNMARKED);
        }
        model.setTask(task, task.unmark());
        return new CommandResult(String.format(UNMARK_SUCCESS, task));
    }
}
