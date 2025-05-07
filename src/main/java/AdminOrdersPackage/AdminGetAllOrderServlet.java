package AdminOrdersPackage;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/AdminGetAllOrderServlet")
public class AdminGetAllOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
	
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<AdminOrderModel> allOrders = AdminOrderController.getAllorder();
		request.setAttribute("allOrders", allOrders);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("AdminOrders.jsp");
		dispatcher.forward(request, response);
		

}
}
