package AdminProduct;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/AdminProductDeleteServlet")
public class AdminProductDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String product_id = request.getParameter("product_id");
		boolean isTrue;
		isTrue = AdminProductController.deletedata(product_id);
		
		 if(isTrue == true) {
	            String alertMessage = "Data Delete Successful";
	            response.getWriter().println("<script>alert('" + alertMessage + "');" + 
	                                        "window.location.href='AdmingetallServlet';</script>");
	        } else {
	            List<AdminProductModel> productdetails = AdminProductController.getById(product_id);
	            request.setAttribute("productdetails", productdetails);
	            
	            RequestDispatcher dispatcher = request.getRequestDispatcher("wrong.jsp");
	            dispatcher.forward(request, response);
		}
		
	}

}
