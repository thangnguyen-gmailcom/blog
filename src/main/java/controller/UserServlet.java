package controller;

import model.User;
import service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.peer.PanelPeer;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "UserServlet",urlPatterns = "/user")
public class UserServlet extends HttpServlet {

    UserService userService = new UserService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action == null){
            action = "";
        }

        switch (action){
            case "login":
                login(request,response);
                break;
            case "signup":
                signUp(request,response);
                break;
        }
    }

    private void signUp(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        User users = new User(username,email,password);
        if(!password.equals(confirmPassword)){
            response.sendRedirect("signup");
            request.setAttribute("mess","password confirmed wrong !");
        }else {
            User user = null;

            try {
                user = userService.checkUser(username);
                request.setAttribute("mess","Account already exists!");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            if(user == null){
                try {
                    userService.insert(users);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                response.sendRedirect("login.jsp");
            }else {
                response.sendRedirect("signup.jsp");
            }
        }
    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("userName");
        String password = request.getParameter("password");
        User user = null;

        try {
            user = userService.login(username,password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        if(user == null){
            request.setAttribute("mess","wrong user or pass !");
            request.getRequestDispatcher("login.jsp").forward(request,response);
        }else {
            HttpSession session =request.getSession();
            session.setAttribute("user",user);
            response.sendRedirect("home");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action == null){
            action = "";
        }

        switch (action){
            case "signup":
                signUpForm(request,response);
                break;
            default:
                loginForm(request,response);
        }
    }

    private void signUpForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("signup.jsp");
        dispatcher.forward(request,response);
    }

    private void loginForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
        dispatcher.forward(request,response);
    }
}
