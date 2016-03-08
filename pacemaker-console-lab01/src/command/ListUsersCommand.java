package command;

import parsers.Parser;
import controllers.PacemakerAPI;

public class ListUsersCommand extends Command
{
    public ListUsersCommand(PacemakerAPI pacemaker, Parser parser)
    {
        super(pacemaker, parser);
    }

    public void doCommand(Object[] parameters) throws Exception
    {
        System.out.println(parser.renderUsers(pacemaker.getUsers()));
    }

}