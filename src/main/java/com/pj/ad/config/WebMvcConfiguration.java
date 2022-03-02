package com.pj.ad.config;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebMvcConfiguration implements WebMvcConfigurer {

	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		for (HttpMessageConverter<?> converter : converters) {
			if (converter instanceof MappingJackson2HttpMessageConverter) {
				MappingJackson2HttpMessageConverter c = (MappingJackson2HttpMessageConverter) converter;
				c.getObjectMapper().registerModule(new ObjectMapperCustomModule());
			}
		}
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedMethods("*").allowedHeaders("*").allowCredentials(true).allowedOrigins(
				"https://localhost:4200"
				, "http://localhost:4200"
				, "https://admission.softsquare.ga"
				, "http://admission.softsquare.ga"
				, "https://adm.slcm.dpu.ac.th"
				, "http://adm.slcm.dpu.ac.th"
				);
	}
}
