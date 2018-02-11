package my.pkg;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import java.util.List;

@Stateless
public abstract class EntityEndpointImpl<ENTITY extends AbstractEntity> implements EntityEndpoint<ENTITY> {
    @Context
    private HttpServletRequest servletRequestContext;

    @Context
    private HttpServletResponse servletResponseContext;

    @Inject
    private AbstractEntityDAO<ENTITY> entityDAO;

    @Override
    public void create(ENTITY entity) {
        ENTITY created = entityDAO.create(entity);
        servletResponseContext.setHeader(HttpHeaders.LOCATION, servletRequestContext.getRequestURL().append('/').append(created.getId()).toString());
    }

    @Override
    public ENTITY read(Long id) {
        return entityDAO.read(id);
    }

    @Override
    public void update(Long id, ENTITY entity) {
        entityDAO.update(id, entity);
    }

    @Override
    public void delete(Long id) {
        entityDAO.delete(id);
    }

    @Override
    public List<ENTITY> search()
    {
        return entityDAO.search();
    }
}
