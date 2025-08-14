package com.join.tab.controller.main;

import org.springframework.context.annotation.PropertySource;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.join.tab.config.ApplicationGlobalContainer;
import com.join.tab.config.secutiry.SecurityConfig;

import jakarta.servlet.FilterRegistration;
import jakarta.servlet.MultipartConfigElement;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRegistration;

@PropertySource("classpath:application.properties")
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    
    // private static final Logger log = LoggerFactory.getLogger(WebAppInitializer.class);

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{ ApplicationGlobalContainer.class, SecurityConfig.class };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return null;
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{ "/" };
    }

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        String location = System.getProperty("multipart.location", "/tmp");
        long maxFileSize = Long.parseLong(System.getProperty("multipart.maxFileSize", "5242880"));
        long maxRequestSize = Long.parseLong(System.getProperty("multipart.maxRequestSize", "20971520"));
        int fileSizeThreshold = Integer.parseInt(System.getProperty("multipart.fileSizeThreshold", "0"));

        MultipartConfigElement multipartConfig = new MultipartConfigElement(
            location, maxFileSize, maxRequestSize, fileSizeThreshold);
        registration.setMultipartConfig(multipartConfig);
    }

     @Override
        public void onStartup(ServletContext servletContext) throws ServletException {
            super.onStartup(servletContext);
            FilterRegistration.Dynamic securityFilter = servletContext.addFilter("springSecurityFilterChain", new DelegatingFilterProxy("springSecurityFilterChain"));
            securityFilter.addMappingForUrlPatterns(null, false, "/*");
        }


}

        // log.info("--------------------------------location: {}", location);
        // log.info("--------------------------------maxFileSize: {}", maxFileSize);
        // log.info("--------------------------------maxRequestSize: {}", maxRequestSize);
        // log.info("(\"--------------------------------fileSizeThreshold: {}", fileSizeThreshold);

        // System.out.println("/tmp");
        // System.out.println(5242880);
        // System.out.println(20971520);
        // System.out.println(0);
