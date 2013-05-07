package blackboard.example.testing;

import java.util.Calendar;
import java.util.Date;

import com.google.common.base.Preconditions;

public class Bar
{

  private boolean _dive;

  public Bar()
  {
    this( false );
  }
  
  public Bar( boolean dive )
  {
    setDive( dive );
  }
  
  /**
   * Calculate the closing time for today.
   * @return Closing time for the bar
   */
  public Date closingTime()
  {
    Calendar cal = Calendar.getInstance();
    cal.setTime( new Date() );
    cal.set( Calendar.HOUR, 23 );
    cal.set( Calendar.MINUTE, 0);
    cal.set( Calendar.SECOND, 0);
    cal.set( Calendar.MILLISECOND, 0);
    
    return cal.getTime();
  }
  
  public void leaveTip( double tip )
  {
    Preconditions.checkArgument( tip > 2.0 && !isDive(), "Cheapskate!" );
    Preconditions.checkArgument( tip > 0.2 && isDive(), "Cheapskate!" );
  }

  public boolean isDive()
  {
    return _dive;
  }

  public void setDive( boolean dive )
  {
    _dive = dive;
  }
  
}
