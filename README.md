# resteasy

to start server part just
> mvn clean install wildfly-swarm:run

then you can check e.g. with Postman everything works as expected POST/GET/PUT/DELETE at "http://localhost:8080/concreteEntity"

entity is very simple:
{
        "id": 1002,
        "concreteEntityProperty": "BlahBlahBlahBlah"
}

to start client (while server still running) which creates an entity but fails to read (deserialize json) it from server then
in client module run
> mvn clean install -DmainClass=my.pkg.Client  exec:java