package blackboard.example.testing;

import blackboard.data.user.User;

public interface BarManager
{

  /**
   * Get the bar this manager manages.
   * @return The Bar managed by this class
   */
  public Bar getBar();
  
  /**
   * Gets the final bill for the given user based on what they've drunk that night.
   * @param user The User object for the bar-goer
   * @return The total bill as a double value.
   */
  public double getBill( User user );
  
}
