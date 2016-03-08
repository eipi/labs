package command;

import parsers.Parser;
import controllers.PacemakerAPI;
public abstract class Command
{

    protected PacemakerAPI pacemaker;
    protected Parser       parser;

    public Command()
    {}

    public Command(PacemakerAPI pacemaker, Parser parser)
    {
        this.pacemaker = pacemaker;
        this.parser    = parser;
    }

    public abstract void doCommand(Object[] parameters)  throws Exception;

    public void undoCommand() throws Exception
    {}

    public void redoCommand() throws Exception
    {}

    public Command clone() {
        return this;
    }

    public Command copy() {
        return null;
    }
}
