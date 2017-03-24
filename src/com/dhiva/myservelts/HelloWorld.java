package com.dhiva.myservelts;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class HelloWorld
 */

public class HelloWorld extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String loginID = "aaa";
	private final String password = "bbb";
    public HelloWorld() {
        // TODO Auto-generated constructor stub
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String s = "Hello, world";
    	response.setStatus(200);
		response.setContentType("text/plain");
		response.setContentLength(s.getBytes().length);
		PrintWriter out = response.getWriter();
		out.write(s);
		out.flush();
	}
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	String login = request.getParameter("login");
		String passwd = request.getParameter("passwd");
		if(loginID.equals(login) && password.equals(passwd)){
			
			HttpSession session = request.getSession();
			session.setAttribute("login", "dhiva");
			session.setMaxInactiveInterval(30*60);
			String s = "Hello,"+login;
	    	response.setStatus(200);
			response.setContentType("text/plain");
			response.setContentLength(s.getBytes().length);
			Cookie loginCookie = new Cookie("login",login);
			//setting cookie to expiry in 30 mins
			loginCookie.setMaxAge(30*60);
			response.addCookie(loginCookie);
			PrintWriter out = response.getWriter();
			out.write(s);
			out.flush();
			//response.sendRedirect("LoginSuccess.jsp");
		}
			else{
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
			PrintWriter out= response.getWriter();
			out.println("<font color=red>Either user name or password is wrong.</font>");
			rd.include(request, response);
		}

	}	
}
