package service;

import model.Category;
import model.Post;

public class Validator {

    CategoryService categoryService = new CategoryService();

    public boolean ValidatePost(Post post){
        if (post.getTitle() == null || post.getTitle().equals("")){
            return false;
        }
        if(post.getShortContent() == null || post.getShortContent().equals("")){
            return false;
        }
        if(post.getFullContent() == null || post.getFullContent().equals("")){
            return false;
        }
        if(post.getImage() == null || post.getImage().equals("")){
            return false;
        }
        if(post.getCategory().getId() <= 0 ){
            return false;
        }
        if(!categoryService.checkEx(post.getCategory().getId())){
            return false;
        }
        return true;
    }

}
