package models;

import static com.google.common.base.Objects.toStringHelper;
import com.google.common.base.Objects;

public class Location
{
  public float latitude;
  public float longitude;
  
  public Location()
  {
  }
  
  public Location (float latitude, float longitude)
  {
    this.latitude  = latitude;
    this.longitude = longitude;
  }
  
  @Override
  public String toString()
  {
    return toStringHelper(this).addValue(latitude)
                               .addValue(longitude)                              
                               .toString();
  }
  
  @Override
  public boolean equals(final Object obj)
  {
    if (obj instanceof Location)
    {
      final Location other = (Location) obj;
      return Objects.equal(latitude, other.latitude) 
          && Objects.equal(longitude, other.longitude);
    }
    else
    {
      return false;
    }
  }
  
  @Override  
  public int hashCode()  
  {  
     return Objects.hashCode(this.latitude, this.longitude);  
  }


  public float getLatitude()
  {
    return latitude;
  }

  public void setLatitude(float latitude)
  {
    this.latitude = latitude;
  }

  public float getLongitude()
  {
    return longitude;
  }

  public void setLongitude(float longitude)
  {
    this.longitude = longitude;
  } 
}
