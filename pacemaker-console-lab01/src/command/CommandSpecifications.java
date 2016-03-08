package command;

import com.budhash.cliche.Command;
import com.budhash.cliche.Param;

public class CommandSpecifications
{
    @Command(description="List all users details")
    public void listUsers () throws Exception
    {}

    @Command(description="Create a new User")
    public void createUser (@Param(name="first name") String firstname, @Param(name="last name") String lastname,
                            @Param(name="email")      String email,     @Param(name="password")  String password) throws Exception
    {}

    @Command(description="Delete a User")
    public void deleteUser (@Param(name="id") Long id)
    {}

    @Command(description="undo last command")
    public void undo () throws Exception
    {}

    @Command(description="redo last command")
    public void redo () throws Exception
    {}
}