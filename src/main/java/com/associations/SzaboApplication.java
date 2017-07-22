package com.associations;

import com.associations.security.CORSFilter;
import com.associations.security.JwtFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication
@EnableAutoConfiguration
@PropertySources({
		@PropertySource("classpath:application.properties"),
		@PropertySource("classpath:auth0.properties")
})
@Configuration
public class SzaboApplication {

	@Value(value = "${userBucketPath}")
	private String bucket;

	@Bean
	public FilterRegistrationBean CORSFilter() {
		final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(new CORSFilter());
		registrationBean.addUrlPatterns("/api/*");

		return registrationBean;
	}

	@Bean
	public FilterRegistrationBean jwtFilter() {
		final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(new JwtFilter(bucket));
		registrationBean.addUrlPatterns("/api/*");

		return registrationBean;
	}

	public static void main(String[] args) {
		SpringApplication.run(SzaboApplication.class, args);
	}
}
