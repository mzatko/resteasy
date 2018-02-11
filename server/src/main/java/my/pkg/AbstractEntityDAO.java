package my.pkg;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Stateless
public abstract class AbstractEntityDAO<ENTITY extends AbstractEntity> implements Serializable {
    @PersistenceContext(unitName = "mainPU")
    private EntityManager em;

    private Class<ENTITY> entityClass;

    public AbstractEntityDAO(Class<ENTITY> entityClass){
        this.entityClass = entityClass;
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public ENTITY create(ENTITY entity)
    {
        em.persist(entity);
        return entity;
    }

    public ENTITY read(Long id)
    {
        return em.find(entityClass, id);
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public ENTITY update(Long id, ENTITY entity)
    {
        entity.setId(id);
        return em.merge(entity);
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public ENTITY delete(Long id)
    {
        ENTITY entity = read(id);
        em.remove(entity);
        return entity;
    }

    public List<ENTITY> search()
    {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ENTITY> cq = cb.createQuery(entityClass);
        Root<ENTITY> rootEntry = cq.from(entityClass);
        CriteriaQuery<ENTITY> all = cq.select(rootEntry);
        TypedQuery<ENTITY> allQuery = em.createQuery(all);
        return allQuery.getResultList();
    }
}
