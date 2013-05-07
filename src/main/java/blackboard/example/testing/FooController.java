package blackboard.example.testing;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import blackboard.data.user.User;
import blackboard.persist.Id;
import blackboard.persist.PersistenceException;
import blackboard.persist.user.UserDbLoader;
import blackboard.platform.spring.beans.annotations.ContextValue;

@Controller
public class FooController
{

  @Autowired
  private BarManager _barManager;
  
  @Autowired
  private Foo _foo;
  
  @Autowired
  private UserDbLoader _userLoader;
  
  public FooController()
  {
  }

  public FooController( BarManager manager, Foo foo, UserDbLoader userLoader )
  {
    _barManager = manager;
    _foo = foo;
    _userLoader = userLoader;
  }
  
  @RequestMapping( value = "/pay", method = RequestMethod.POST )
  public void handlePayBill( HttpServletRequest request, @ContextValue( "user" ) User user )
  {
    double bill = _barManager.getBill( user );
    double tip = _foo.calculateTip( bill, _barManager.getBar() );
    
    _barManager.getBar().leaveTip( tip );
  }

  @RequestMapping( value = "/pay", method = RequestMethod.GET )
  public @ResponseBody double handleGetBill( @RequestParam( "user_id" ) long userId ) throws PersistenceException
  {
    Id id = Id.generateId( User.DATA_TYPE, userId );
    User user = _userLoader.loadById( id );
    return _barManager.getBill( user );
  }
  
}
