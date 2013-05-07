package blackboard.example.testing;

import javax.servlet.http.HttpServletRequest;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import blackboard.data.user.User;
import blackboard.persist.Id;
import blackboard.persist.user.UserDbLoader;

@RunWith( PowerMockRunner.class )
@PrepareForTest( Id.class )
public class FooControllerTest
{

  @Test
  public void testPayBill()
  {
    BarManager mockBarManager = Mockito.mock( BarManager.class );
    Foo mockFoo = Mockito.mock( Foo.class );
    UserDbLoader mockLoader = Mockito.mock( UserDbLoader.class );
    
    //
    // TESTABLE CODE WITH MOCKS
    //
    FooController ctr = new FooController( mockBarManager, mockFoo, mockLoader );
    
    HttpServletRequest mockRequest = Mockito.mock( HttpServletRequest.class );
    User mockUser = Mockito.mock( User.class );
    
    //
    // MOCKING COLLABORATORS
    //
    Bar mockBar = Mockito.mock( Bar.class );
    Mockito.when( mockBarManager.getBar() ).thenReturn( mockBar );
    Mockito.when( mockFoo.calculateTip( Mockito.anyDouble(), Mockito.eq( mockBar ) ) ).thenReturn( 100.0 );
    
    ctr.handlePayBill( mockRequest, mockUser );
    
    //
    // VERIFYING COLLABORATIONS
    //
    Mockito.verify( mockBarManager ).getBill( mockUser );
    Mockito.verify( mockBar ).leaveTip( 100.0 );
  }

  @Test
  public void testGetBill() throws Exception
  {
    BarManager mockBarManager = Mockito.mock( BarManager.class );
    Foo mockFoo = Mockito.mock( Foo.class );
    UserDbLoader mockLoader = Mockito.mock( UserDbLoader.class );
    
    FooController ctr = new FooController( mockBarManager, mockFoo, mockLoader );
    
    User mockUser = Mockito.mock( User.class );
    Id mockId = Mockito.mock( Id.class );
    
    Mockito.when( mockLoader.loadById( mockId ) ).thenReturn( mockUser );
    Mockito.when( mockBarManager.getBill( mockUser ) ).thenReturn( 10.0 );
    
    long userId = 40L;
    
    // 
    // STATIC MOCKING
    //
    PowerMockito.mockStatic( Id.class );
    
    //
    // ARGUMENT MATCHERS
    //
    PowerMockito.when( Id.class, "generateId", User.DATA_TYPE, userId ).thenReturn( mockId );
    //PowerMockito.when( Id.class, "generateId", User.DATA_TYPE, Mockito.anyLong() ).thenReturn( mockId );
    //PowerMockito.when( Id.class, "generateId", Mockito.eq( User.DATA_TYPE ), Mockito.anyLong() ).thenReturn( mockId );
    
    double bill = ctr.handleGetBill( userId );

    Mockito.verify( mockBarManager ).getBill( mockUser );
    Assert.assertEquals( 10.0, bill );
  }
  
}
