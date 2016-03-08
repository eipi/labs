package models;

import static com.google.common.base.Objects.toStringHelper;

import java.util.HashMap;
import java.util.Map;

import com.google.common.base.Objects;

public class User 
{
  public Long   id;
  public String firstname;
  public String lastname;
  public String email;
  public String password;
  
  public Map<Long, Activity> activities = new HashMap<>();
  
  public User()
  {
  }
  
  public User(Long id, String firstName, String lastName, String email, String password)
  {
    this.id        = id;
    this.firstname = firstName;
    this.lastname  = lastName;
    this.email     = email;
    this.password  = password;
  }
  
  @Override
  public String toString()
  {
    return toStringHelper(this).addValue(id)
                               .addValue(firstname)
                               .addValue(lastname)
                               .addValue(password)
                               .addValue(email)  
                               .addValue(activities)
                               .toString();
  }
  
  @Override
  public boolean equals(final Object obj)
  {
    if (obj instanceof User)
    {
      final User other = (User) obj;
      return Objects.equal(firstname,   other.firstname) 
          && Objects.equal(lastname,    other.lastname)
          && Objects.equal(email,       other.email)
          && Objects.equal(password,    other.password)
          && Objects.equal(activities,  other.activities);      
    }
    else
    {
      return false;
    }
  }
  
  @Override  
  public int hashCode()  
  {  
     return Objects.hashCode(this.id, this.lastname, this.firstname, this.email, this.password);  
  }

  public Long getId()
  {
    return id;
  }

  public void setId(Long id)
  {
    this.id = id;
  }

  public String getFirstname()
  {
    return firstname;
  }

  public void setFirstname(String firstname)
  {
    this.firstname = firstname;
  }

  public String getLastname()
  {
    return lastname;
  }

  public void setLastname(String lastname)
  {
    this.lastname = lastname;
  }

  public String getEmail()
  {
    return email;
  }

  public void setEmail(String email)
  {
    this.email = email;
  }

  public String getPassword()
  {
    return password;
  }

  public void setPassword(String password)
  {
    this.password = password;
  }

  public Map<Long, Activity> getActivities()
  {
    return activities;
  }

  public void setActivities(Map<Long, Activity> activities)
  {
    this.activities = activities;
  }  
}