package command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class CommandDispatcher {
    private static final Logger logger = LoggerFactory.getLogger(Command.class);

    private Map<String, Command> commands;
    private static Stack<Command> undoBuffer;
    private static Stack<Command> redoBuffer;

    public CommandDispatcher() {
        undoBuffer = new Stack<Command>();
        redoBuffer = new Stack<Command>();
        commands = new HashMap<String, Command>();

        commands.put("undo", new UndoCommand());
        commands.put("redo", new RedoCommand());
    }

    public void addCommand(String commandName, Command command) {
        commands.put(commandName, command);
    }

    public boolean dispatchCommand(String commandName, Object[] parameters) throws Exception {
        boolean dispatched = false;
        Command command = commands.get(commandName);

        if (command != null) {
            dispatched = true;
            command.doCommand(parameters);
            Command commandCopy = command.copy();
            if (commandCopy != null) {
                logger.info("Detected transactable command " + command.getClass().getName());

                undoBuffer.push(command.clone());
                redoBuffer.clear();
            }
        }
        return dispatched;
    }

    public static synchronized void registerCommandForUndo(Command command) {
        undoBuffer.push(command);
    }

    public static synchronized Stack<Command> peekUndo() {
        return undoBuffer;
    }

    public static synchronized void registerCommandForRedo(Command command) {
        redoBuffer.push(command);
    }

    public static synchronized Stack<Command> peekRedo() {
        return redoBuffer;
    }

}