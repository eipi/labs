package command;

public class UndoCommand extends Command
{

    public void doCommand(Object[] parameters) throws Exception
    {
        if (CommandDispatcher.peekUndo().size() > 0)
        {
            Command command = CommandDispatcher.peekUndo().pop();
            command.undoCommand();
            CommandDispatcher.registerCommandForRedo(command);
        } else
        {
            System.out.println("Nothing to Undo");
        }
    }

}