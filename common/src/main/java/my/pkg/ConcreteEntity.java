package my.pkg;

import javax.persistence.Entity;

@Entity
public class ConcreteEntity extends AbstractEntity {
    private String concreteEntityProperty;

    public String getConcreteEntityProperty() {
        return concreteEntityProperty;
    }

    public void setConcreteEntityProperty(String concreteEntityProperty) {
        this.concreteEntityProperty = concreteEntityProperty;
    }
}
