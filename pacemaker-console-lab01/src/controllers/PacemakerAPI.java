package controllers;

import java.util.Collection;
import com.google.common.base.Optional;
import java.util.HashMap;
import java.util.Map;

import utils.ISerializer;
import utils.XMLSerializer;
import models.Activity;
import models.Location;
import models.User;
import utils.DateTimeFormatters;

public class PacemakerAPI
{
  static long userIndex = 0;
  static long activityIndex = 0;
  
  private Map<Long,   User>   userMap         = new HashMap<>();
  private Map<String, User>   userEmailMap    = new HashMap<>();
  private Map<Long, Activity> activityMap     = new HashMap<>();
  public  Collection<User>    users           = userMap.values();
  public ISerializer serializer;


    private String datastore   = "testdatastore";

  public PacemakerAPI()
  {
    userIndex = 0;
    activityIndex = 0;
      serializer = ISerializer.getCustomSerializer(XMLSerializer.prefix, datastore);
  }
  
  @SuppressWarnings("unchecked")
  public void load() throws Exception
  {
    serializer.read();
    activityIndex = (Long) serializer.pop();
    userIndex     = (Long) serializer.pop();
    activityMap   = (Map<Long, Activity>) serializer.pop();
    userEmailMap  = (Map<String, User>) serializer.pop();
    userMap       = (Map<Long, User>) serializer.pop();
    users         = userMap.values();
  }
  
  public void store() throws Exception
  {
    serializer.push(userMap);
    serializer.push(userEmailMap);
    serializer.push(activityMap);
    serializer.push(userIndex);
    serializer.push(activityIndex);
    serializer.write();
  }
  
  public Collection<User> getUsers ()
  {
    return userMap.values();
  }
  
  public  void deleteUsers() 
  {
    userMap.clear();
    userEmailMap.clear();
  }
  
  public Long createUser(String firstName, String lastName, String email, String password) 
  {
    userIndex = userIndex + 1;
    User user = new User (userIndex, firstName, lastName, email, password);
    userMap.put(user.id, user);
    userEmailMap.put(email, user);
    return user.id;
  }
  
  public User getUser(String email) 
  {
    return userEmailMap.get(email);
  }

  public User getUser(Long id) 
  {
    return userMap.get(id);
  }

  public void deleteUser(Long id) 
  {
    User user = userMap.remove(id);
    userEmailMap.remove(user.email);
  }

    public void deleteUserByEmail(String email)
    {
        User user = getUser(email);
        userMap.remove(user.id);
        userEmailMap.remove(user.email);
    }

  public Activity createActivity(Long id, String type, String location, double distance, String dateStr, String durationStr)
  {
    Activity activity = null;
    Optional<User> user = Optional.fromNullable(userMap.get(id));
    if (user.isPresent())
    {
      activityIndex = activityIndex + 1;
      activity = new Activity (activityIndex, type, location, distance, DateTimeFormatters.parseDateTime(dateStr), DateTimeFormatters.parseDuration(durationStr));
      user.get().activities.put(activity.id, activity);
      activityMap.put(activity.id, activity);
    }
    return activity;
  }
  
  public Activity getActivity (Long id)
  {
    return activityMap.get(id);
  }
  
  public void addLocation (Long id, float latitude, float longitude)
  {
    Optional<Activity> activity = Optional.fromNullable(activityMap.get(id));
    if (activity.isPresent())
    {
      activity.get().route.add(new Location(latitude, longitude));
    }
  }

    public void cff(String fmt)
    {
        serializer = ISerializer.getCustomSerializer(fmt, datastore);
    }
}
