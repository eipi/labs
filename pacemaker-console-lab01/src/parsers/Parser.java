package parsers;

import models.User;

import java.util.Collection;

import models.Activity;

public class Parser 
{
  public String renderUser(User user) throws Exception
  {
  	return user.toString();
  }
  
  public String renderUsers(Collection<User> users) throws Exception
  {
  	return users.toString();
  }  
  
  public String renderActivities(Collection<Activity> activities) throws Exception
  {
  	return activities.toString();
  }
}



