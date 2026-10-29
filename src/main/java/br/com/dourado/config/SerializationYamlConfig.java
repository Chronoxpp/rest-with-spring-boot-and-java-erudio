package br.com.dourado.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.yaml.JacksonYamlHttpMessageConverter;
import tools.jackson.dataformat.yaml.YAMLMapper;

@Configuration
public class SerializationYamlConfig
{
  @Bean
  public JacksonYamlHttpMessageConverter yamlConverter()
  {
    return new JacksonYamlHttpMessageConverter(
      YAMLMapper.builder()
        .changeDefaultPropertyInclusion(
          inclusion -> inclusion.withValueInclusion(JsonInclude.Include.NON_NULL)
        )
    );
  }
}
