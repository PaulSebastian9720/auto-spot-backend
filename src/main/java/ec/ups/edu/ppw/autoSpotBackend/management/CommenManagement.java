package ec.ups.edu.ppw.autoSpotBackend.management;

import ec.ups.edu.ppw.autoSpotBackend.dao.CommentDAO;
import ec.ups.edu.ppw.autoSpotBackend.model.Comment;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class CommenManagement {
    @Inject
    private CommentDAO commentDAO;

    public void addComment(Comment comment) throws Exception {
        if(comment == null) throw  new Exception("Datos de el comentario no validos");
        commentDAO.insertComment(comment);
    }

    public List<Comment> getAllComments(){
        return commentDAO.getComments();
    }


}
