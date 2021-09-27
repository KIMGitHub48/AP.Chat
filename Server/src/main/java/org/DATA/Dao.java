package org.DATA;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.reflections.Reflections;

import javax.persistence.Entity;



//Класс работы с базой
public class Dao {

    private SessionFactory sessionFactory;

    private Dao() {
        String url = String.format("jdbc:sqlserver://%s;database=%s", "ADMIN-PC", "chat");
        try {
            Configuration configuration = new Configuration();
            new Reflections("chat.model").getTypesAnnotatedWith(Entity.class).forEach(configuration::addAnnotatedClass);
            final StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties())
                    .applySetting("hibernate.connection.driver_class", "com.microsoft.sqlserver.jdbc.SQLServerDriver")
                    .applySetting("hibernate.dialect", "org.hibernate.dialect.SQLServer2012Dialect")
                    .applySetting("hibernate.connection.url", url)//"jdbc:sqlserver://ADMIN-PC;database=chat"
                    .applySetting("hibernate.connection.username", "sa")    //sa
                    .applySetting("hibernate.connection.password", "t11ck3dej")    //t11ck3dej
                    .applySetting("hibernate.show_sql", false)
                    .applySetting("hibernate.format_sql", true)
                    .applySetting("hibernate.current_session_context_class", "thread")
                    .applySetting("hibernate.c3p0.min_size", 1)
                    .applySetting("hibernate.c3p0.max_size", 10)
                    .applySetting("hibernate.c3p0.timeout", 1000)
                    .applySetting("hibernate.c3p0.max_statements", 50)
                    .applySetting("hibernate.c3p0.idle_test_period", 3600)
                    .applySetting("hibernate.c3p0.validate", true)
                    .applySetting("hibernate.connection.provider_class", "org.hibernate.c3p0.internal.C3P0ConnectionProvider");
            sessionFactory = configuration.buildSessionFactory(builder.build());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

     /**
     * Получить открытую сессию для работы с БД.
     */

/*    public Session getSession() {
        final Session currentSession = sessionFactory.getCurrentSession();
        if (currentSession != null && currentSession.isOpen()) {
            return currentSession;
        } else {
            return sessionFactory.openSession();
        }

    }

    private Session openSession() {
        return sessionFactory.openSession();
    }

 */
}

