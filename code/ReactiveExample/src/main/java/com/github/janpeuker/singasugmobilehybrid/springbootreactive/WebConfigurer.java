package com.github.janpeuker.singasugmobilehybrid.springbootreactive;


import com.github.janpeuker.singasugmobilehybrid.springbootreactive.resources.PriceUpdatesController;
import org.atmosphere.cache.UUIDBroadcasterCache;
import org.atmosphere.cpr.ApplicationConfig;
import org.atmosphere.cpr.AtmosphereFramework;
import org.atmosphere.cpr.AtmosphereServlet;
import org.atmosphere.cpr.MetaBroadcaster;
import org.springframework.boot.context.embedded.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * Created by janpeuker on 3/11/14.
 *
 * Copied from https://github.com/AndreasKl/springboot-angular-atmosphere-quickstart/blob/master/src/main/java/net/andreaskluth/toastonatmosphere/configuration/WebConfigurer.java
 *
 */
@Configuration
public class WebConfigurer implements ServletContextInitializer {

    @Bean
    public AtmosphereServlet atmosphereServlet(){
        return new AtmosphereServlet();
    }

    @Bean
    public AtmosphereFramework atmosphereFramework() {
        return atmosphereServlet().framework();
    }

    @Bean
    public MetaBroadcaster metaBroadcaster() {
        AtmosphereFramework framework = atmosphereFramework();
        return framework.metaBroadcaster();
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        configureAthmosphere(atmosphereServlet(), servletContext);
    }

    private void configureAthmosphere(AtmosphereServlet servlet, ServletContext servletContext) {
        ServletRegistration.Dynamic atmosphereServlet = servletContext.addServlet("atmosphereServlet", servlet);
        atmosphereServlet.setInitParameter(ApplicationConfig.ANNOTATION_PACKAGE, PriceUpdatesController.class.getPackage().getName());
        atmosphereServlet.setInitParameter(ApplicationConfig.BROADCASTER_CACHE, UUIDBroadcasterCache.class.getName());
        atmosphereServlet.setInitParameter(ApplicationConfig.BROADCASTER_SHARABLE_THREAD_POOLS, "true");
        atmosphereServlet.setInitParameter(ApplicationConfig.BROADCASTER_MESSAGE_PROCESSING_THREADPOOL_MAXSIZE, "10");
        atmosphereServlet.setInitParameter(ApplicationConfig.BROADCASTER_ASYNC_WRITE_THREADPOOL_MAXSIZE, "10");
        servletContext.addListener(new org.atmosphere.cpr.SessionSupport());
        atmosphereServlet.addMapping("/priceupdates/*");
        atmosphereServlet.setLoadOnStartup(0);
        atmosphereServlet.setAsyncSupported(true);
    }

}