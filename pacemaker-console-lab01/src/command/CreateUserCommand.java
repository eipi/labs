package command;

import models.User;
import parsers.Parser;
import controllers.PacemakerAPI;

public class CreateUserCommand extends Command
{
    User user;

    public CreateUserCommand(PacemakerAPI pacemaker, Parser parser)
    {
        super(pacemaker, parser);
    }

    private CreateUserCommand(PacemakerAPI pacemaker, Parser parser, User user)
    {
        super(pacemaker, parser);
        this.user = user;
    }

    public void doCommand(Object[] parameters) throws Exception
    {
        Long id = pacemaker.createUser((String)parameters[0], (String)parameters[1], (String)parameters[2], (String)parameters[3]);
        System.out.println(parser.renderUser(pacemaker.getUser(id)));
        this.user = pacemaker.getUser(id);
    }

    public void undoCommand() throws Exception
    {
        pacemaker.deleteUser(user.id);
    }

    public void redoCommand() throws Exception
    {
        pacemaker.createUser(user.firstname, user.lastname, user.email, user.password);
    }

    public CreateUserCommand clone() {
      return new CreateUserCommand(pacemaker,parser,user);
    }
}