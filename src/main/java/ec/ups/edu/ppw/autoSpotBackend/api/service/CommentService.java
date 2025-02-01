package ec.ups.edu.ppw.autoSpotBackend.api.service;

import ec.ups.edu.ppw.autoSpotBackend.api.management.CommentManagement;
import ec.ups.edu.ppw.autoSpotBackend.model.Comment;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/comments")
@Produces("application/json")
@Consumes(MediaType.APPLICATION_JSON)
public class CommentService {

    @Inject
    private CommentManagement  commentManagement;

    @POST
    @Path("/create")
    public Response create(Comment comment) throws Exception {
        try {
            commentManagement.addComment(comment);
            return Response.ok("COMENTARIO PUBLICADO CORRECTAMENTE").build();
        }catch(Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }

    }

    @GET
    @Path("/getAll")
    public Response getAll(){
        try {
            List<Comment> comments = commentManagement.getAllComments();
            return Response.ok(comments).build();
        }catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

}


