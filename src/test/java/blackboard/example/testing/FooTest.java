package blackboard.example.testing;

import junit.framework.Assert;

import org.junit.Test;
import org.mockito.Mockito;

public class FooTest
{

  // Examples of simple assertions
  
  @Test
  public void testCalculateTipWithValidArguments()
  {
    //
    // SIMPLE ASSERTION CHECKING
    //
    Foo ctr = new Foo();
    double tip = ctr.calculateTip( 10.0, 1.0 );
    Assert.assertEquals( 2.0, tip );
  }

  @Test
  public void testCalculateTipeInvalidQualityThrowsException()
  {
    Foo ctr = new Foo();
    
    try
    {
      //
      // SIMPLE EXCEPTION CHECKING
      //
      ctr.calculateTip( 10.0, -0.3 );
      Assert.fail( "Should throw IllegalArgumentException." );
    }
    catch ( IllegalArgumentException err )
    {
    }
  }
  
  // Examples of mocked collaborators

  /*
   * Collaborators are classes and methods that are required
   * by the CUT to do it's work.  In a true unit test, you
   * want to isolate the testing to just the unit of interest
   * (usually a single method) and mocking out all collaborators,
   * internal and external, guarantees that the test is isolated.
   */
  @Test
  public void testCalculateTipForDiveBar()
  {
    //
    // ARRANGE (GIVEN)
    //
    Foo ctr = new Foo();
    Bar bar = Mockito.mock( Bar.class );
    Mockito.when( bar.isDive() ).thenReturn( true );
    
    // Alternative syntax more aligned with Behavior Driven Develepment (BDD)
    //BDDMockito.given( bar.isDive() ).willReturn( true );
    
    //
    // ACT (WHEN)
    //
    double tip = ctr.calculateTip( 10.0, bar );
    
    //
    // ASSERT (THEN)
    //
    Assert.assertEquals( 0.6, tip );
  }
  
  // Examples of verification
  
  /*
   * In this example, we show why mocking out collaborators is good practice.
   * The Bar object has validation logic associated with the leaveTip() method.  If we
   * don't mock it out, we will get an exception.  Though that may be valid for an integration
   * test, it gets in the way of testing the unit to ensure that the Foo object, the
   * class under test, is behaving as expected.
   */
  @Test
  public void testLeavingTipForSwankyBar()
  {
    Bar bar = Mockito.mock( Bar.class );
    Mockito.when( bar.isDive() ).thenReturn( false );

    //Bar bar = new Bar(); 
    //bar.setDive( false );
    
    // 
    // CLASS UNDER TEST
    //
    Foo ctr = new Foo();
    ctr.payBill( 10.0, bar );
    
    // 
    // VERIFY COLLABORATION
    Mockito.verify( bar ).leaveTip( 2.0 );
  }
  
  // Examples of "spying"

  /*
   * Spying allows your to apply the same concept of mocking collaborators
   * to the class under test.  If the unit you are testing is actually a single 
   * method, you don't even want the behavior of other methods in the class under 
   * test to affect the testing of the unit.  By "spying" the CUT, you can control
   * the behaviors of the "collaboration" methods and test only the unit you 
   * are targetting.
   */
  @Test
  public void testSpyingCalculateTipInPenniesForSwankyBar()
  {
    Bar bar = Mockito.mock( Bar.class );
    Mockito.when( bar.isDive() ).thenReturn( false );
    
    Foo ctr = Mockito.spy( new Foo() );
    Mockito.when( ctr.calculateTip( 10.0, bar ) ).thenReturn( 10.0 );
    
    // We mocked out the default calculateTip() method to always return 10.0
    // so we can isolate the (trivial) pennies calculation.
    int pennies = ctr.calculateTipInPennies( 10.0, bar );
    Assert.assertEquals( 1000, pennies );
  }

  /*
   * Spying also allows your test to validate that internal "collaboration"
   * methods in the CUT are actually called from the target unit.
   */
  @Test
  public void testSpyingCalculateTipForDiveBar()
  {
    Bar bar = Mockito.mock( Bar.class );
    Mockito.when( bar.isDive() ).thenReturn( true );
    
    // "Spy" the CUT so that we can verify later.
    Foo ctr = Mockito.spy( new Foo() );
    ctr.calculateTip( 10.0, bar );
    
    // Verify that the calculateTip() method is called with
    // the expected values.
    Mockito.verify( ctr ).calculateTip( 10.0, 0.3 );
  }
  
}
