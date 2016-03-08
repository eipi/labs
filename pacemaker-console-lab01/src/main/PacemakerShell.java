package main;

import parsers.AsciiParser;
import parsers.Parser;
import command.CommandDispatcher;
import command.CommandSpecifications;
import command.CreateUserCommand;
import command.DeleteUserCommand;
import command.ListUsersCommand;
import controllers.PacemakerAPI;

import com.budhash.cliche.CommandProcessor;
import com.budhash.cliche.Shell;
import com.budhash.cliche.ShellCommand;
import com.budhash.cliche.ShellFactory;

public class PacemakerShell implements CommandProcessor
{
    private CommandDispatcher dispatcher;
    private PacemakerAPI       paceApi;

    public PacemakerShell()
    {
        Parser parser = new AsciiParser();
        paceApi    = new PacemakerAPI();
        dispatcher = new CommandDispatcher();
        dispatcher.addCommand("list-users",  new ListUsersCommand(paceApi,  parser));
        dispatcher.addCommand("create-user", new CreateUserCommand(paceApi, parser));
        dispatcher.addCommand("delete-user", new DeleteUserCommand(paceApi, parser));
    }

    @Override
    public void doCommand(ShellCommand command, Object[] parameters)
    {
        try
        {
            dispatcher.dispatchCommand(command.getName(), parameters);
        }
        catch (Exception e)
        {
            System.err.println("Error executing command");
            System.err.println(e.getMessage());
        }
    }

    public static void main(String[] args) throws Exception
    {
        PacemakerShell main = new PacemakerShell();
        CommandSpecifications commandSpecs = new CommandSpecifications();

        Shell shell = ShellFactory.createConsoleShell("pm", "Welcome to pacemaker-console - ?help for instructions", commandSpecs, main);
        shell.commandLoop();
    }
}
