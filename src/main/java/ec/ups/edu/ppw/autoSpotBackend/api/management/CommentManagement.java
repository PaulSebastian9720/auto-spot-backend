package ec.ups.edu.ppw.autoSpotBackend.api.management;

import ec.ups.edu.ppw.autoSpotBackend.api.exception.CustomException;
import ec.ups.edu.ppw.autoSpotBackend.dao.CommentDAO;
import ec.ups.edu.ppw.autoSpotBackend.model.Comment;
import ec.ups.edu.ppw.autoSpotBackend.util.consts.Errors;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class CommentManagement {
    @Inject
    private CommentDAO commentDAO;

    public void addComment(Comment comment) throws CustomException {
        this.validator(comment);
        commentDAO.insertComment(comment);
    }

    public List<Comment> getAllComments(){
        return commentDAO.getComments();
    }

    private void validator(Comment comment) throws CustomException {
        if(comment == null) throw new CustomException(Errors.BAD_REQUEST, "Comment cannot be null");
        if (comment.getName() == null || comment.getName().isBlank()) throw new CustomException(Errors.BAD_REQUEST, "Comment name cannot be null");
        if (comment.getMail() == null || comment.getMail().isBlank() ) throw new CustomException(Errors.BAD_REQUEST, "Comment mail cannot be null");
        if (comment.getComment() == null || comment.getComment().isBlank()) throw new CustomException(Errors.BAD_REQUEST, "Comment comment cannot be null");
    }

}
