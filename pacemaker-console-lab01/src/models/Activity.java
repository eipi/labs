package models;

import static com.google.common.base.Objects.toStringHelper;

import java.util.ArrayList;
import java.util.List;
import org.joda.time.DateTime;
import org.joda.time.Duration;


import com.google.common.base.Objects;

public class Activity 
{ 
  public Long   id;
  public String type;
  public String location;
  public double distance;
  public DateTime starttime;
  public Duration duration;
  
  public List<Location> route = new ArrayList<>();
 
  public Activity()
  {
  }
  
  public Activity(Long id, String type, String location, double distance, DateTime starttime, Duration duration)
  {
    this.id        = id;
    this.type      = type;
    this.location  = location;
    this.distance  = distance;
    this.starttime = starttime;
    this.duration  = duration;
  }
  
  @Override
  public String toString()
  {
    return toStringHelper(this).addValue(id)
                               .addValue(type)
                               .addValue(location)
                               .addValue(distance)
                               .addValue(starttime)         
                               .addValue(duration)                              
                               .addValue(route)
                               .toString();
  }
  
  @Override
  public boolean equals(final Object obj)
  {
    if (obj instanceof Activity)
    {
      final Activity other = (Activity) obj;
      return Objects.equal(type, other.type) 
          && Objects.equal(location,  other.location)
          && Objects.equal(distance,  other.distance)
          && Objects.equal(distance,  other.starttime)
          && Objects.equal(distance,  other.duration)          
          && Objects.equal(route,     other.route);    
    }
    else
    {
      return false;
    }
  }
  
  @Override  
  public int hashCode()  
  {  
     return Objects.hashCode(this.id, this.type, this.location, this.distance);  
  }

  public Long getId()
  {
    return id;
  }

  public void setId(Long id)
  {
    this.id = id;
  }

  public String getType()
  {
    return type;
  }

  public void setType(String type)
  {
    this.type = type;
  }

  public String getLocation()
  {
    return location;
  }

  public void setLocation(String location)
  {
    this.location = location;
  }

  public double getDistance()
  {
    return distance;
  }

  public void setDistance(double distance)
  {
    this.distance = distance;
  }

  public DateTime getStarttime()
  {
    return starttime;
  }

  public void setStarttime(DateTime starttime)
  {
    this.starttime = starttime;
  }

  public Duration getDuration()
  {
    return duration;
  }

  public void setDuration(Duration duration)
  {
    this.duration = duration;
  }

  public List<Location> getRoute()
  {
    return route;
  }

  public void setRoute(List<Location> route)
  {
    this.route = route;
  } 
}