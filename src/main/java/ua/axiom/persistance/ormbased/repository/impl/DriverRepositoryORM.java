package ua.axiom.persistance.ormbased.repository.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ua.axiom.core.annotations.Autowired;
import ua.axiom.core.annotations.Component;
import ua.axiom.core.annotations.InitMethod;
import ua.axiom.model.actors.Driver;
import ua.axiom.persistance.dao.DriverDao;
import ua.axiom.persistance.ormbased.SessionFactoryProvider;
import ua.axiom.persistance.ormbased.repository.AbstractORMRepository;

@Component
    public class DriverRepositoryORM extends AbstractORMRepository<Long, Driver> implements DriverDao {

    @Autowired
    private SessionFactoryProvider sessionFactoryProvider;

    private SessionFactory sessionFactory;

    @InitMethod
    private void init() {
        sessionFactory = sessionFactoryProvider.getSessionFactory();
    }

    @Override
    protected Session getSession() {
        return sessionFactory.openSession();
    }

    @Override
    protected Class<Driver> getEntryType() {
        return Driver.class;
    }

}
