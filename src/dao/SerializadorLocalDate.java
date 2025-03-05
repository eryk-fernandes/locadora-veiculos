package dao;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class SerializadorLocalDate implements JsonSerializer<LocalDate>, JsonDeserializer<LocalDate> {

	@Override
	public JsonElement serialize(LocalDate data, Type tipo, JsonSerializationContext serializador) {
		return new JsonPrimitive(data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
	}
	
	@Override
	public LocalDate deserialize(JsonElement json, Type tipo, JsonDeserializationContext deserializador) throws JsonParseException {
		return LocalDate.parse(json.getAsString(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}

}
