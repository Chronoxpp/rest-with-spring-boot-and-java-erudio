package br.com.dourado.serialization.converter;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractJacksonHttpMessageConverter;
import tools.jackson.dataformat.yaml.YAMLMapper;

public final class YamlJacksonHttpMessageConverter extends AbstractJacksonHttpMessageConverter<YAMLMapper>
{
  public YamlJacksonHttpMessageConverter()
  {
    super(
      YAMLMapper.builder()
        .changeDefaultPropertyInclusion(
          inclusion -> inclusion.withValueInclusion(
            JsonInclude.Include.NON_NULL
          )
        ),
      MediaType.APPLICATION_YAML,
      MediaType.parseMediaType("application/yaml")
    );
  }
}
