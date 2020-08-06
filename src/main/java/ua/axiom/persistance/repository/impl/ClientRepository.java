package ua.axiom.persistance.repository.impl;

import ua.axiom.core.Context;
import ua.axiom.model.actors.Client;
import ua.axiom.model.actors.factories.ClientFactory;
import ua.axiom.persistance.database.SimpleDBConnectionProvider;
import ua.axiom.persistance.query.*;
import ua.axiom.persistance.repository.AbstractRepository;

import java.util.AbstractMap;
import java.util.Collections;

public class ClientRepository extends AbstractRepository<Long, Client> {

    public ClientRepository() {
        super(
            new FindAllQuery<>(new ClientFactory(), "clients", Context.get(SimpleDBConnectionProvider.class)),
            new FindOneQuery<>(new ClientFactory(), "clients", "id", Context.get(SimpleDBConnectionProvider.class)),
                new InQuery<>(null, null),
                new UpdateQuery<>("clients", "id", Client.class, Context.get(SimpleDBConnectionProvider.class)),
                new AbstractMap.SimpleEntry<>(
                        Collections.singletonList("username"),
                        new FindByKeys<>(
                                new ClientFactory(),
                                "clients",
                                Collections.singletonList("username"),
                                Context.get(SimpleDBConnectionProvider.class)))
        );
    }

    @Override
    public Class<Client> getPersistedClass() {
        return Client.class;
    }
}
