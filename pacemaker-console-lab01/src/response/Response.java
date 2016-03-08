package response;

public abstract class Response
{
  private String response;

  public Response(final String response)
  {
    this.response = response;
  }

  public String toString()
  {
    return this.response;
  }
}
