package ua.axiom.persistance.repository;

import ua.axiom.model.actors.Client;
import ua.axiom.model.actors.factories.ClientFactory;
import ua.axiom.persistance.database.SimpleDBConnectionProvider;
import ua.axiom.persistance.query.FindAllQuery;
import ua.axiom.persistance.query.FindOneQuery;

public class ClientRepository extends AbstractRepository<Long, Client> {

    public ClientRepository() {
        super(
            new FindAllQuery<>(new ClientFactory(), "clients", new SimpleDBConnectionProvider()),
            new FindOneQuery<>(new ClientFactory(), "clients", "id", new SimpleDBConnectionProvider()),
            new FindOneQuery<>(new ClientFactory(), "clients", "username", new SimpleDBConnectionProvider())
        );
    }

    @Override
    public Class<Client> getPersistedClass() {
        return Client.class;
    }
}
