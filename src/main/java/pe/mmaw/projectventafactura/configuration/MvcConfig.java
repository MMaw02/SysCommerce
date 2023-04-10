package pe.mmaw.projectventafactura.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        CONFIGURACIÓN PARA MOSTRAR RECURSOS AL NAVEGADOR O A LA VISTA
        registry.addResourceHandler("/images/**").addResourceLocations("file:images/");
    }
}
