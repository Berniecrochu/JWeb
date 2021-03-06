package com.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Database.DAONew;
import com.Database.DAOProduct;
import com.Database.DAOUser;
import com.beans.News;
import com.beans.Product;
import com.beans.User;
import com.beans.Comment;

/**
 * Servlet implementation class Admin
 */
@WebServlet("/lol")
public class Admin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Admin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getSession().getAttribute("isAdmin") == null || (boolean)request.getSession().getAttribute("isAdmin") == false) {
			response.sendRedirect("/JWeb/");
			return;
		}
		
		List<User> users = DAOUser.getUserList();
		request.setAttribute("users", users);
		this.getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		String title = request.getParameter("title");
		String content = request.getParameter("content");
		if (title != null && content != null && title.length() > 0 && content.length() > 0) {
			session.setAttribute("newsid", session.getAttribute("newsid") == null ? 0 : (int)session.getAttribute("newsid") + 1);
			User user = DAOUser.getUser((String)session.getAttribute("login"), (String)session.getAttribute("password"));
			DAONew.addNews(new News((int)session.getAttribute("newsid"), title, content, user));
			response.sendRedirect("/JWeb/Admin/");
		}
		
		String productName = request.getParameter("name");
		float price = Float.valueOf(request.getParameter("price"));
		String description = request.getParameter("description");
		if (productName != null && productName.length() > 0) {
			session.setAttribute("productid", session.getAttribute("productid") == null ? 0 : (int)session.getAttribute("productid") + 1);
			DAOProduct.addProduct(new Product((int)session.getAttribute("productid"), productName, price, description == null ? "" : description, new ArrayList<Comment>()));
		}
		response.sendRedirect("/JWeb/Admin/");
	}

}
