package my.pkg;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.jboss.resteasy.plugins.providers.RegisterBuiltin;
import org.jboss.resteasy.spi.ResteasyProviderFactory;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.ClientResponseFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedMap;
import java.io.IOException;
import java.time.LocalDateTime;

public class Client {

    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(Client.class);
    private static final ThreadLocal<MultivaluedMap<String, String>> responseHeadersStore = new ThreadLocal<>();

    public static void main(String[] args) {
        RegisterBuiltin.register(ResteasyProviderFactory.getInstance());
        ResteasyClient client = new ResteasyClientBuilder().build().register(new ClientResponseFilter() {
            @Override
            public void filter(ClientRequestContext requestContext, ClientResponseContext responseContext) throws IOException {
                responseHeadersStore.set(responseContext.getHeaders());
            }
        });
        ResteasyWebTarget webTarget = client.target("http://localhost:8080");
        ConcreteEntityEndpoint concreteEntityEndpoint = webTarget.proxy(ConcreteEntityEndpoint.class);

        // create ConcreteEntity
        ConcreteEntity toCreate = new ConcreteEntity();
        toCreate.setConcreteEntityProperty("created from Client at " + LocalDateTime.now().toString());
        concreteEntityEndpoint.create(toCreate);

        // get id of created entity from response header
        String contentIDHeader = responseHeadersStore.get().getFirst(HttpHeaders.CONTENT_ID);
        Long id = Long.valueOf(contentIDHeader);

        logger.info("id: '{}'", id);
        ConcreteEntity resolved = concreteEntityEndpoint.read(id);
        //and here it crashes
    }
}
