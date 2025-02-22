package com.project.start.api.commons.configs;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
public class MessageSourceConfig {

	@Bean
	ResourceBundleMessageSource messageSource() {
		var source = new ResourceBundleMessageSource();
		source.setBasenames("messages/messages");
		source.setUseCodeAsDefaultMessage(true);
		source.setDefaultLocale(Locale.forLanguageTag("pt-BR"));

		return source;
	}

}
