package controllers;

 
import play.mvc.*;
import play.data.*;
import static play.data.Form.*;
import play.data.validation.Constraints.*;
import views.html.*;

public class Application extends Controller {
    
    /**
     * Describes the Calc form.
     */
    public static class Hello {
        
        @Required  public int a;
        @Required  public int b;
        @Required  public int c;
        
        public String result;
        
    } 
    
    // -- Actions
  
    /**
     * Home page
     */
    public static Result index() {
        return ok(
            index.render(form(Hello.class))
        );
    }
  
    /**
     * Handles the form submission.
     */
    public static Result sayHello() {
        Form<Hello> form = form(Hello.class).bindFromRequest();
        if(form.hasErrors()) {
            return badRequest(index.render(form));
        } else {
            Hello data = form.get();
            double delta=((data.b*data.b)- 4*data.a*data.c);
    	  double delat_sqt=Math.sqrt(delta);
    	  if (delta >0)
    	  {		  
    		  data.result= "x1=  "+((-1*data.b)-delat_sqt)/(2*data.a) +"  ,  x2=   "+((-1*data.b)+delat_sqt)/(2*data.a);		  
    	  }
    	  else if (delta ==0)
    	  {
    		  data.result= "x1= x2=  "+ ((-1*data.b)/(2*data.a)) ;		  
    	  }	
    	  
    	  else  
    	  {
    		  data.result= "";	  
    	  }	 
            
            return ok(
                  hello.render(data.result)
            );
        }
    }
  
}
