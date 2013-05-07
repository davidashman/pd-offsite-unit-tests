package blackboard.example.testing.impl;

import blackboard.data.user.User;
import blackboard.example.testing.Bar;
import blackboard.example.testing.BarManager;

public class BarManagerImpl implements BarManager
{

  private Bar _bar;
  
  public BarManagerImpl( Bar bar )
  {
    _bar = bar;
  }
  
  @Override
  public double getBill( User user )
  {
    return 10;
  }

  @Override
  public Bar getBar()
  {
    return _bar;
  }

}
