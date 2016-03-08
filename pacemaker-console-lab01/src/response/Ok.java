package response;

public class Ok extends Response
{
  public Ok(final String response)
  {
    super(("ok\n" + response));
  }
}
