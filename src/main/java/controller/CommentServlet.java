package controller;

import model.Comment;
import model.Post;
import model.User;
import service.CommentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "CommentServlet", urlPatterns = "/comment")
public class CommentServlet extends HttpServlet {

    CommentService commentService = new CommentService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "insertComment":
                insertComment(request, response);
                break;
        }
    }

    private void insertComment(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int idPost = Integer.parseInt(request.getParameter("postId"));
        String message = request.getParameter("message");
        String name = request.getParameter("username");
        String email = request.getParameter("email");

        Post post = new Post(idPost);

        Comment comment = new Comment(message, name, email, post);

        try {
            commentService.insert(comment);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        response.sendRedirect("/home?action=blogSingle&id=" + idPost);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
