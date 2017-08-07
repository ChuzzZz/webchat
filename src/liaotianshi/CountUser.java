package liaotianshi;

import java.util.HashSet;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import model.User;

/**
 * Application Lifecycle Listener implementation class CountUser
 *
 */
@WebListener
public class CountUser implements HttpSessionListener,HttpSessionAttributeListener {

    /**
     * Default constructor. 
     */
    public CountUser() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent arg0) {
        // TODO Auto-generated method stub
    	System.out.println("准备注册！");
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent arg0) {
        // TODO Auto-generated method stub
    	HttpSession session = arg0.getSession();
    	ServletContext application = session.getServletContext();
    	
    	if(application.getAttribute("OnlineCount") == null) {
    		System.out.println("???");
			int count = 0;
			application.setAttribute("OnlineCount",count);
		} else {
			System.out.println("有人走了！");
			int count = ((Integer)application.getAttribute("OnlineCount")).intValue();
			count --;
			System.out.println(count);
			application.setAttribute("OnlineCount",count);
		}
    	/*if(application.getAttribute("OnlineUsers") == null){
    		HashSet<String> users = new HashSet<String>();
    		application.setAttribute("OnlineUsers", users);
    	}else{
    		System.out.println("用户登出");
    		HashSet<String> users = (HashSet<String>)application.getAttribute("OnlineUsers");
    		users.remove((String)session.getAttribute("nickname"));
    		application.setAttribute("OnlineUsers", users);
    	}*/
    }

	@Override
	public void attributeAdded(HttpSessionBindingEvent arg0) {
		// TODO Auto-generated method stub
		HttpSession session = arg0.getSession();
		ServletContext application = session.getServletContext();
		
		User user = (User)session.getAttribute("User");
		if(user != null){
			if(application.getAttribute("OnlineCount") == null){
				System.out.println("第一个人进聊天室了");
	    		int count = 0;
	    		count++;
	    		application.setAttribute("OnlineCount", count);
			}else{
				System.out.println("又有人进聊天室了");
	    		int count = ((Integer)application.getAttribute("OnlineCount")).intValue();
	    		count++;
	    		application.setAttribute("OnlineCount", count);
			}
		}
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent arg0) {
		// TODO Auto-generated method stub
	}
	
}
