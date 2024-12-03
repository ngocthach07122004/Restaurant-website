//package restaurant.example.restaurant.configuration;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.converter.HttpMessageConverter;
//import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//import java.util.List;
//
//@Configuration
//public class WebConfig extends WebMvcConfigurationSupport {
//
//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        MappingJackson2HttpMessageConverter jacksonConverter = new MappingJackson2HttpMessageConverter();
//        jacksonConverter.setObjectMapper(new ObjectMapper());
//        converters.add(jacksonConverter);
//    }
//}