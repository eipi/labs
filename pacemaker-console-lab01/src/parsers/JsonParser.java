package parsers;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.User;
import models.Activity;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIgnore;

interface UserMixin
{
  @JsonIgnore
  String getActivities();

  @JsonIgnore
  Long getId();
}

interface ActivityMixin
{
  @JsonIgnore
  Long getId();
}

public class JsonParser extends Parser
{
  private ObjectMapper mapper = new ObjectMapper();

  public JsonParser()
  {
    mapper.addMixInAnnotations(User.class, UserMixin.class);
    mapper.addMixInAnnotations(Activity.class, ActivityMixin.class);
  }

  public String renderUser(User user) throws Exception
  {
    return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(user);
  }

  public String renderActivities(Collection<Activity> activities) throws Exception
  {
    String activitiesStr = "";
    if (!activities.isEmpty())
    {
      activitiesStr = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(activities);
    }
    return activitiesStr;
  }

  public String renderUsers(Collection<User> users) throws Exception
  {
    String usersStr = "";
    if (!users.isEmpty())
    {
      usersStr = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(users);
    }
    return usersStr;
  }
}