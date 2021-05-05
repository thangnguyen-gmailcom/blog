package controller;

import model.Category;
import service.CategoryService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "CategoryServlet", urlPatterns = "/Admin-category")
public class CategoryServlet extends HttpServlet {

    CategoryService categoryService = new CategoryService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }

        switch (action) {
            case "insertCategory":
                insertCategory(request, response);
                break;
            case "updateCategory":
                updateCategory(request, response);
                break;
            default:
                listCategory(request, response);
        }
    }

    private void updateCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        Category category = new Category(id, name);
        try {
            categoryService.update(category);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        request.setAttribute("category", category);
        RequestDispatcher dispatcher = request.getRequestDispatcher("updateCategory.jsp");
        dispatcher.forward(request, response);
    }

    private void insertCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String categoryName = request.getParameter("categoryName");
        Category categorys = new Category(categoryName);
        Category category = null;
        try {
            category = categoryService.checkCategoryName(categoryName);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if (category == null) {
            try {
                categoryService.insert(categorys);
                request.setAttribute("mess", "success !");
            } catch (SQLException throwables) {
                request.setAttribute("message", "input error !");
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher("insertCategory.jsp");
            dispatcher.forward(request, response);
        }else {
            request.setAttribute("message", "input error !");
            RequestDispatcher dispatcher = request.getRequestDispatcher("insertCategory.jsp");
            dispatcher.forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }

        switch (action) {
            case "insertCategory":
                insertForm(request, response);
                break;
            case "updateCategory":
                updateForm(request, response);
                break;
            case "deleteCategory":
                deleteCategory(request, response);
                break;
            default:
                listCategory(request, response);
        }
    }

    private void deleteCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            categoryService.delete(id);
            response.sendRedirect("/Admin-category");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void updateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Category category = null;
        try {
            category = categoryService.findById(id);
            if(category == null){
                response.sendRedirect("/error?code=02");
                return;
            }else {
                request.setAttribute("category", category);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("updateCategory.jsp");
        dispatcher.forward(request, response);
    }

    private void insertForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("insertCategory.jsp");
        dispatcher.forward(request, response);
    }

    private void listCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Category> categories = categoryService.selectAll();
            request.setAttribute("categories", categories);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("listCategory.jsp");
        dispatcher.forward(request, response);
    }
}
