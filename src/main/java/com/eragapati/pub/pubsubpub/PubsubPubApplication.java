package com.eragapati.pub.pubsubpub;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.api.gax.core.CredentialsProvider;
import com.google.cloud.NoCredentials;
import com.google.cloud.spring.core.CredentialsSupplier;
import com.google.cloud.spring.core.DefaultCredentialsProvider;
import com.google.cloud.spring.pubsub.support.converter.JacksonPubSubMessageConverter;
import com.google.cloud.spring.pubsub.support.converter.PubSubMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import java.io.IOException;

@SpringBootApplication
public class PubsubPubApplication {

	public static void main(String[] args) {
		SpringApplication.run(PubsubPubApplication.class, args);
	}

	@Bean
	@Primary
	public CredentialsProvider googleCredentials() throws IOException {
		return NoCredentials::getInstance;
	}

	@Bean
	@Primary
	public PubSubMessageConverter pubSubMessageConverter(ObjectMapper objectMapper) {
		return new JacksonPubSubMessageConverter(objectMapper);
	}


}
