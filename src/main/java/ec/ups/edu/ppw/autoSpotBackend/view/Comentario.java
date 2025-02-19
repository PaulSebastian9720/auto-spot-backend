package ec.ups.edu.ppw.autoSpotBackend.view;

import ec.ups.edu.ppw.autoSpotBackend.dao.CommentDAO;
import ec.ups.edu.ppw.autoSpotBackend.model.Comment;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.List;

@Named("comentario")
@RequestScoped
public class Comentario {
    @Inject
    private CommentDAO commentDAO;

    private Comment comment = new Comment();
    private List<Comment> comentarios;

    @PostConstruct
    public void init() {
        comentarios = commentDAO.getComments();
    }

    public List<Comment> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comment> comentarios) {
        this.comentarios = comentarios;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public String guardarComentario() {
        try {
            commentDAO.insertComment(comment);
            comment = new Comment();
            comentarios = commentDAO.getComments(); // Actualizar la lista
            return "Comentario guardado correctamente";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String eliminarComentario(int id) {
        try {
            commentDAO.deleteComment(id);
            comentarios = commentDAO.getComments();
            return "Comentario eliminado correctamente";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
