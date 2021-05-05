package controller;

import model.Category;
import model.Comment;
import model.Post;
import model.Tag;
import service.CategoryService;
import service.CommentService;
import service.PostService;
import service.TagService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "HomeServlet", urlPatterns = "/home")
public class HomeServlet extends HttpServlet {

    PostService postService = new PostService();
    CategoryService categoryService = new CategoryService();
    CommentService commentService = new CommentService();
    TagService tagService = new TagService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        String action = request.getParameter("action");
        if(action == null){
            action = "";
        }

        switch (action){
            case "listPostInCategory":
                postInCategory(request,response);
                break;
            case "blogSingle":
                blogSingle(request,response);
                break;
            default:
                home(request,response);
        }


    }

    private void blogSingle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Post post = null;
        List<Category> categories = null;
        List<Post> top3Post = null;
        List<Post> top3PostInViews = null;
        List<Comment> commentList = null;
        List<Tag> listTagByPost = null;

        try {
            post = postService.findById(id);
            categories = categoryService.selectAll();
            top3Post = postService.top3PostNew();
            top3PostInViews = postService.top3PostInViews();
            commentList = commentService.selectCommentsInPost(id);
            listTagByPost = tagService.getAllTagsByPost(id);
            postService.updateViews(id);
            request.setAttribute("post",post);
            request.setAttribute("categories",categories);
            request.setAttribute("top3Post", top3Post);
            request.setAttribute("top3PostInView",top3PostInViews);
            request.setAttribute("commentList",commentList);
            request.setAttribute("tagList",listTagByPost);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("balita/blogSingle.jsp");
        dispatcher.forward(request,response);
    }

    private void postInCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String categoryName = request.getParameter("categoryName");

        List<Post> postList = null;
        List<Category> categories = null;
        List<Post> top3Post = null;
        List<Post> top3PostInViews = null;
        try {
            postList = postService.listPostInCategory(id);
            categories = categoryService.selectAll();
            top3Post = postService.top3PostNew();
            top3PostInViews = postService.top3PostInViews();
            request.setAttribute("postList",postList);
            request.setAttribute("name",categoryName);
            request.setAttribute("categories",categories);
            request.setAttribute("top3Post", top3Post);
            request.setAttribute("top3PostInView",top3PostInViews);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("balita/listPostInCategory.jsp");
        dispatcher.forward(request,response);
    }

    private void home(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Post> top3Post = null;
        List<Category> categoryList = null;
        List<Post> randomList = null;
        List<Post> top3PostInViews = null;
        try {
            top3Post = postService.top3PostNew();
            categoryList = categoryService.selectAll();
            top3PostInViews = postService.top3PostInViews();
            randomList = postService.randomList();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        String page = request.getParameter("page");
        int pageStart = 1;
        List<Post> postListPage = null;

        if(page == null){
            int back = 1;
            int next = 1;
            request.setAttribute("back",back);
            request.setAttribute("next",next);
        }
        // nut Prev
        if (page != null) {
            int back = Integer.parseInt(page);
            if (back == 0 | back == 1) {
                back = 1;
            } else {
                back = Integer.parseInt(page) - 1;
            }
            request.setAttribute("back", back);
        }
        if (page != null) {
            try {
                pageStart = Integer.parseInt(page);
                postListPage = postService.sizePage(Integer.parseInt(page), 10);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } else {
            try {
                postListPage = postService.sizePage(1, 10);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        request.setAttribute("postListPage", postListPage);
        try {
            int count = postService.count();
            int pages = 10;
            int end = count / pages;
            if (count % pages != 0) {
                end++;
            }
            boolean isDotDot = false;
            if (end - pageStart > 3) {
                end = pageStart + 3;
                isDotDot = true;
            }
            if (page != null) {
                int next = 1;
                if (next == end ) {
                    next = end;
                }else if(next < end) {
                    next = Integer.parseInt(page) + 1;
                }
                request.setAttribute("next", next);
            }
            request.setAttribute("start", pageStart);
            request.setAttribute("end", end);
            request.setAttribute("isDotDot", isDotDot);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        request.setAttribute("top3Post", top3Post);
        request.setAttribute("categories", categoryList);
        request.setAttribute("randomPost",randomList);
        request.setAttribute("top3PostInView",top3PostInViews);

        RequestDispatcher dispatcher = request.getRequestDispatcher("balita/home.jsp");
        dispatcher.forward(request, response);
    }
}
