package controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ErrorServlet", urlPatterns = "/error")
public class ErrorServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");
        switch (code){
            case "01":
                request.setAttribute("mess","lỗi đầu vào");
                break;
            case "02":
                request.setAttribute("mess","không tìm thấy id");
                break;
            default:
                request.setAttribute("mess","lỗi hệ thống");
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("startbootstrap/dist/erro.jsp");
        dispatcher.forward(request,response);
    }
}
