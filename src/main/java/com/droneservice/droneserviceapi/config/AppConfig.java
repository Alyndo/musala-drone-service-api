package com.droneservice.droneserviceapi.config;

import com.droneservice.droneserviceapi.utils.message.MessageService;
import com.droneservice.droneserviceapi.utils.message.MessageServiceImpl;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
public class AppConfig {

    @Bean(name = "customMessageSource")
    public MessageSource customMessageSource(){
        final ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:message/messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;

    }

    @Bean
    public MessageService messageService(){
        return new MessageServiceImpl(customMessageSource());
    }
}
