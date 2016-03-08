package command;

public class RedoCommand extends Command
{
    public void doCommand(Object[] parameters) throws Exception
    {
        if (CommandDispatcher.peekRedo().size() > 0)
        {
            Command command = CommandDispatcher.peekRedo().pop();
            command.redoCommand();
            CommandDispatcher.registerCommandForUndo(command);
        } else
        {
            System.out.println("Nothing to Redo");
        }
    }
}