package my.pkg;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface EntityEndpoint<ENTITY extends AbstractEntity> {
    @POST
    void create(ENTITY entity);
    @GET
    @Path("{id}")
    ENTITY read(@PathParam("id") Long id);
    @PUT
    @Path("{id}")
    void update(@PathParam("id") Long id, ENTITY entity);
    @DELETE
    @Path("{id}")
    void delete(@PathParam("id") Long id);
    @GET
    List<ENTITY> search();
}
