package AdminProduct;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/AdminProductInsertServlet")
public class AdminProductInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
		String product_name = request.getParameter("product_name");
		String price = request.getParameter("price");
		String description = request.getParameter("description");
		String link = request.getParameter("link");
		
   boolean isTrue;
		
		isTrue = AdminProductController.insertdata(product_name, price, description, link);
		
		if(isTrue == true) {
		
		String alertMesage = "Data Insert Successful";
		
		response.getWriter().println("<script> alert ('"+alertMesage+"');window.location.href='AdmingetallServlet'</script>");
		
		}
		else {
			
			RequestDispatcher dis2 = request.getRequestDispatcher("wrong.jsp");
			dis2.forward(request, response);
		}
		
	}


	}


