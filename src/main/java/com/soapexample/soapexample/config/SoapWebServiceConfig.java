package com.soapexample.soapexample.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;
@EnableWs
@Configuration
public class SoapWebServiceConfig  {
    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext context) {
        MessageDispatcherServlet messageDispatcherServlet = new MessageDispatcherServlet();
        messageDispatcherServlet.setApplicationContext(context);
        messageDispatcherServlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean<MessageDispatcherServlet>(messageDispatcherServlet, "/soapWS/*");
    }
    // /ws/anselme/students.wsdl
    // course-details.xsd
    @Bean(name = "users")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema coursesSchema) {
        DefaultWsdl11Definition definition = new DefaultWsdl11Definition();
        definition.setPortTypeName("UserServicePort");
        definition.setTargetNamespace("http://techprimers.com/spring-boot-soap-example");
        definition.setLocationUri("/soapWS");
        definition.setSchema(coursesSchema);
        return definition;
    }
    @Bean
    public XsdSchema usersSchema() {
        return new SimpleXsdSchema(new ClassPathResource("users.xsd"));
    }
}
