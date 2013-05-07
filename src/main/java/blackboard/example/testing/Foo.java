package blackboard.example.testing;

import static com.google.common.base.Preconditions.checkArgument;

public class Foo
{

  /**
   * Calculates the tip for a bill based on a service quality.
   * 
   * @param totalBill Total bill, in dollars.
   * @param serviceQuality Quality of service on a scale from 0.0 -> 1.0.
   * @return The tip to add to the bill
   */
  public double calculateTip( double totalBill, double serviceQuality )
  {
    checkArgument( serviceQuality >= 0.0 && serviceQuality <= 1.0, "Service quality must be between 0.0 and 1.0." );
    return totalBill * ( 0.20 * serviceQuality );
  }
  
  public double calculateTip( double totalBill, Bar bar )
  {
    return calculateTip( totalBill, ( bar.isDive() ? 0.3 : 1.0 ) );
  }

  public int calculateTipInPennies( double totalBill, Bar bar )
  {
    return (int)( 100 * calculateTip( totalBill, bar ) );
  }
  
  public void payBill( double totalBill, Bar bar )
  {
    double tip = calculateTip( totalBill, bar );
    bar.leaveTip( tip );
  }
  
}
