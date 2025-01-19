package ec.ups.edu.ppw.autoSpotBackend.management;

import ec.ups.edu.ppw.autoSpotBackend.dao.CommentDAO;
import ec.ups.edu.ppw.autoSpotBackend.model.Comment;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class CommentManagement {
    @Inject
    private CommentDAO commentDAO;

    public void addComment(Comment comment) throws Exception {
        if(comment == null) throw  new Exception("DATOS DE EL COMENTARIOS NO VALIDOS");
        commentDAO.insertComment(comment);
    }

    public List<Comment> getAllComments(){
        return commentDAO.getComments();
    }


}
