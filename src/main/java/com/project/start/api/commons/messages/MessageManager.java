package com.project.start.api.commons.messages;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MessageManager {
	
    private final MessageSource messageSource;
    private static final Locale DEFAULT_LOCALE = Locale.forLanguageTag("pt-BR");
    
    public String get(String key, Object ... arguments) {
    	return messageSource.getMessage(key, arguments, DEFAULT_LOCALE);
    }
    
    public String get(String key) {
    	return messageSource.getMessage(key, null, DEFAULT_LOCALE);
    }

}
