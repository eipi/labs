package parsers;

import models.User;
import java.util.List;
import java.util.Collection;
import java.util.ArrayList;
import models.Activity;
import com.bethecoder.ascii_table.impl.CollectionASCIITableAware;
import com.bethecoder.ascii_table.ASCIITable;

public class AsciiParser extends Parser
{
  @Override
  public String renderUser(User user)
  {
    List<User> userList = new ArrayList<>();
    userList.add(user);
    return renderUsers(userList);
  }
  
  @Override
  public String renderActivities(Collection<Activity> activities)
  {
    String activitiesStr = "";
    if (!activities.isEmpty())
    {
      List<Activity> activityList = new ArrayList<>(activities);
      CollectionASCIITableAware<Activity> activitiesTable = new CollectionASCIITableAware<>(activityList, "id", "type", "location", "distance", "starttime", "duration", "route");
      activitiesStr = ASCIITable.getInstance().getTable(activitiesTable);
    }
    return activitiesStr;
  }
  
  @Override
  public String renderUsers(Collection<User> users)
  {
    String userStr = "";
    if (!users.isEmpty())
    {
      List<User> userList = new ArrayList<>(users);
      CollectionASCIITableAware<User> asciiTableAware = new CollectionASCIITableAware<>(userList, "id", "firstname", "lastname", "email", "password");
      userStr = ASCIITable.getInstance().getTable(asciiTableAware);
    }
    return userStr;
  } 
}
