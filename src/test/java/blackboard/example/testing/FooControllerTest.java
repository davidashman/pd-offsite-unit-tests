package blackboard.example.testing;

import junit.framework.Assert;

import org.junit.Test;
import org.mockito.Mockito;

public class FooControllerTest
{

  // Examples of simple assertions
  
  @Test
  public void testCalculateTipWithValidArguments()
  {
    FooController ctr = new FooController();
    double tip = ctr.calculateTip( 10.0, 1.0 );
    Assert.assertEquals( 2.0, tip );
  }

  @Test
  public void testCalculateTipeInvalidQualityThrowsException()
  {
    FooController ctr = new FooController();
    
    try
    {
      ctr.calculateTip( 10.0, -0.3 );
      Assert.fail( "Should throw IllegalArgumentException." );
    }
    catch ( IllegalArgumentException err )
    {
    }
  }
  
  // Examples of mocked assertions
  
  @Test
  public void testCalculateTipForDiveBar()
  {
    Bar bar = Mockito.mock( Bar.class );
    Mockito.when( bar.isDive() ).thenReturn( true );
    
    FooController ctr = new FooController();
    double tip = ctr.calculateTip( 10.0, bar );
    Assert.assertEquals( 0.6, tip );
  }
  
  @Test
  public void testCalculateTipForSwankyBar()
  {
    Bar bar = Mockito.mock( Bar.class );
    Mockito.when( bar.isDive() ).thenReturn( false );
    
    FooController ctr = new FooController();
    double tip = ctr.calculateTip( 10.0, bar );
    Assert.assertEquals( 2.0, tip );
  }

  // Examples of verification
  
  @Test
  public void testLeavingTipForSwankyBar()
  {
    Bar bar = Mockito.mock( Bar.class );
    Mockito.when( bar.isDive() ).thenReturn( false );

    //Bar bar = new Bar(); 
    //bar.setDive( false );
    
    FooController ctr = new FooController();
    ctr.payBill( 10.0, bar );
    
    Mockito.verify( bar ).leaveTip( 2.0 );
  }
  
  // Examples of "spying"

  @Test
  public void testSpyingCalculateTipForSwankyBar()
  {
    Bar bar = Mockito.mock( Bar.class );
    Mockito.when( bar.isDive() ).thenReturn( false );
    
    FooController ctr = Mockito.spy( new FooController() );
    ctr.calculateTip( 10.0, bar );
    Mockito.verify( ctr ).calculateTip( 10.0, 1.0 );
  }
  
  @Test
  public void testSpyingCalculateTipForDiveBar()
  {
    Bar bar = Mockito.mock( Bar.class );
    Mockito.when( bar.isDive() ).thenReturn( true );
    
    FooController ctr = Mockito.spy( new FooController() );
    ctr.calculateTip( 10.0, bar );
    Mockito.verify( ctr ).calculateTip( 10.0, 0.3 );
  }
  
}
