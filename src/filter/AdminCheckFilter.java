package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import auth.service.User;

public class AdminCheckFilter implements Filter {
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpSession session = request.getSession(false);
		
		if(session == null || session.getAttribute("authUser") == null
				|| ((User)session.getAttribute("authUser")).getGrade() != 9999) {
			HttpServletResponse response = (HttpServletResponse)res;
			RequestDispatcher dispatcher = request.getRequestDispatcher("/view/errorPage/invalidAccessPage.jsp");
			dispatcher.forward(request, response);
		} else {
			chain.doFilter(req, res);
		}
	}
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}
	
	@Override
	public void destroy() {
	}
	
}
