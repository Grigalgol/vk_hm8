package core;

import api.HelpRest;
import api.JacksonMessageBodyHandler;
import api.ObjectMapperProvider;
import api.ProductRest;
import com.google.inject.AbstractModule;
import com.google.inject.Module;
import dao.CompanyDao;
import dao.DataManager;
import dao.ProductDao;
import org.jboss.resteasy.plugins.guice.GuiceResteasyBootstrapServletContextListener;

import javax.servlet.ServletContext;
import java.util.Collections;
import java.util.List;

public final class GuiceListener extends GuiceResteasyBootstrapServletContextListener {
    @Override
    protected List<? extends Module> getModules(ServletContext context) {
        return Collections.singletonList(new GuiceModule());
    }

    private static final class GuiceModule extends AbstractModule {
        @Override
        protected void configure() {
            bind(ProductDao.class);
            bind(CompanyDao.class);
            bind(DataManager.class);
            bind(ProductRest.class);
            bind(HelpRest.class);
            bind(ObjectMapperProvider.class).toInstance(new ObjectMapperProvider());
            bind(JacksonMessageBodyHandler.class).toInstance(new JacksonMessageBodyHandler());
        }
    }
}
