package controller;

import model.Category;
import model.Post;
import model.Tag;
import service.CategoryService;
import service.PostService;
import service.Validator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "PostServlet", urlPatterns = "/Admin-post")
public class PostServlet extends HttpServlet {

    PostService postService = new PostService();
    CategoryService categoryService = new CategoryService();
    Validator validator = new Validator();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }

        switch (action) {
            case "insertPost":
                insertPost(request, response);
                break;
            case "updatePost":
                updatePost(request,response);
                break;
            default:
                listPost(request, response);
        }

    }

    private void updatePost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        String shortContent = request.getParameter("shortContent");
        String fullContent = request.getParameter("fullContent");
        String image = request.getParameter("image");
        int idCategory = Integer.parseInt(request.getParameter("category"));
        String tags = request.getParameter("tags");
        List<Tag> arrTags = new ArrayList<Tag>();
        if(tags != null){
            tags =tags.trim();
            String[] sTags = tags.split("[,]");
            for(String sTag : sTags){
                arrTags.add(new Tag(sTag));
            }
        }

        Category category = new Category(idCategory);
        Post post = new Post(id,title,shortContent,fullContent,image,category,arrTags);

        try {
            postService.update(post);
            List<Category> categories = categoryService.selectAll();
            request.setAttribute("post",post);
            request.setAttribute("categories",categories);
            request.setAttribute("mess","update success !");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("updatePost.jsp");
        dispatcher.forward(request,response);
    }

    private void insertPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String shortContent = request.getParameter("shortContent");
        String fullContent = request.getParameter("fullContent");
        String image = request.getParameter("image");
        String tags = request.getParameter("tags");
        int id = 0;
        List<Tag> arrTags = new ArrayList<Tag>();
        try {
            id = Integer.parseInt(request.getParameter("category"));
            if(tags != null){
                tags =tags.trim();
                String[] sTags = tags.split("[,]");
                for(String sTag : sTags){
                    arrTags.add(new Tag(sTag));
                }
            }
        }catch (NumberFormatException e){
            response.sendRedirect("/error?code=01");
            return;
        }
        Category category = new Category(id);
        Post post = new Post(title, shortContent, fullContent, image, category, arrTags);
        try {
            if (validator.ValidatePost(post)) {
                postService.insert(post);
                request.setAttribute("mess", "success !");
                List<Category> categories = categoryService.selectAll();
                request.setAttribute("categories", categories);
            } else {
                request.setAttribute("message","input error !");
                List<Category> categories = categoryService.selectAll();
                request.setAttribute("categories", categories);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("insertPost.jsp");
        dispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "insertPost":
                insertPostForm(request, response);
                break;
            case"deletePost":
                deletePost(request,response);
                break;
            case "updatePost":
                updateForm(request,response);
                break;
            default:
                listPost(request, response);
        }
    }

    private void updateForm(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int id = 0;
        try{
            id = Integer.parseInt(request.getParameter("id"));
            Post post = postService.findById(id);
            if(post==null){
                response.sendRedirect("/error?code=02");
                return;
            }else {
                List<Category> categories = categoryService.selectAll();
                String tagsString = "";
                int index = 0;
                for(Tag tag : post.getTags()){
                    index++;
                    if(index != 1){
                        tagsString += ",";
                    }
                    tagsString += tag.getTagName();
                }
                request.setAttribute("post", post);
                request.setAttribute("categories", categories);
                request.setAttribute("tagsString", tagsString);
            }
        }catch (Exception e){
            response.sendRedirect("/error?code=01");
            return;
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("updatePost.jsp");
        dispatcher.forward(request,response);
    }

    private void deletePost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try{
            int id = Integer.parseInt(request.getParameter("id"));
            postService.delete(id);
            response.sendRedirect("/Admin-post");
        }catch (SQLException e){
            response.sendRedirect("/error?code=01");
        }
    }

    private void insertPostForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Category> categories = categoryService.selectAll();
            request.setAttribute("categories", categories);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("insertPost.jsp");
        dispatcher.forward(request, response);
    }

    private void listPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Post> postList = postService.selectAll();
            request.setAttribute("list", postList);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("listPost.jsp");
        dispatcher.forward(request, response);
    }
}
