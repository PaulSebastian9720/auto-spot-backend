package ec.ups.edu.ppw.autoSpotBackend.dao;

import ec.ups.edu.ppw.autoSpotBackend.model.Comment;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import java.util.List;

@Stateless
public class CommentDAO {

    @PersistenceContext(unitName = "autoSpotBackendPersistenceUnit")
    private EntityManager em;

    public void insertComment(Comment comment) {
        em.persist(comment);
    }

    public Comment updateComment(Comment comment) {
        return em.merge(comment);
    }

    public void deleteComment(int id) {
        Comment comment = em.find(Comment.class, id);
        if (comment != null) {
            em.remove(comment);
        }
    }

    public Comment readComment(int id) {
        return em.find(Comment.class, id);
    }


    public List<Comment> getComments() {
        String jpql = "SELECT c FROM Comment c";
        Query q = em.createQuery(jpql, Comment.class);
        List<Comment> list = q.getResultList();
        return list;
    }

}
