package my.pkg;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.jboss.resteasy.plugins.providers.RegisterBuiltin;
import org.jboss.resteasy.spi.ResteasyProviderFactory;

import java.time.LocalDateTime;

public class Client {
    public static void main(String[] args){
        RegisterBuiltin.register(ResteasyProviderFactory.getInstance());
        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget webTarget  = client.target("http://localhost:8080");
        ConcreteEntityEndpoint concreteEntityEndpoint = webTarget.proxy(ConcreteEntityEndpoint.class);


        ConcreteEntity toCreate = new ConcreteEntity();
        toCreate.setConcreteEntityProperty("created from Client at " + LocalDateTime.now().toString());
        concreteEntityEndpoint.create(toCreate);
    }
}
