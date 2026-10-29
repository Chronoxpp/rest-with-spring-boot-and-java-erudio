package br.com.dourado.serializer;

import tools.jackson.core.JsonGenerator;
import tools.jackson.databind.SerializationContext;
import tools.jackson.databind.ser.std.StdSerializer;

public class GenderSerializer extends StdSerializer<String>
{
  public GenderSerializer()
  {
    super(String.class);
  }

  @Override
  public void serialize(
    String gender,
    JsonGenerator gen,
    SerializationContext ctxt
  )
  {
    gen.writeString(gender.equals("Male") ? "M" : "F");
  }
}
