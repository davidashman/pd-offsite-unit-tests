package blackboard.example.testing;

import com.google.common.base.Preconditions;

public class Bar
{

  private boolean _dive;
  
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
