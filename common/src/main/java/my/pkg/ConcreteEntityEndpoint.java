package my.pkg;

import javax.ws.rs.Path;

@Path("concreteEntity")
public interface ConcreteEntityEndpoint extends EntityEndpoint<ConcreteEntity> {
    // nothing needed here
}
