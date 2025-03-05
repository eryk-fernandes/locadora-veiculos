package dao;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import model.Veiculo;

public class SerializadorVeiculo implements JsonSerializer<Veiculo>, JsonDeserializer<Veiculo> {
	
	@Override
	public JsonElement serialize(Veiculo veiculo, Type tipo, JsonSerializationContext serializador) {
		
		JsonObject objeto = new JsonObject();
		
		objeto.add("tipo", new JsonPrimitive(veiculo.getClass().getSimpleName()));
		objeto.add("propriedades", serializador.serialize(veiculo, veiculo.getClass()));
		
		return objeto;
	}
	
	@Override
	public Veiculo deserialize(JsonElement json, Type tipo, JsonDeserializationContext deserializador) throws JsonParseException {
		
		JsonObject objeto = json.getAsJsonObject();
		
		String tipoVeiculo = objeto.get("tipo").getAsString();
		
		JsonElement elemento = objeto.get("propriedades");
		
		try {
			return deserializador.deserialize(elemento, Class.forName("model." + tipoVeiculo));
		}
		catch (Exception e) {
			throw new JsonParseException(e.getMessage());
		}
	}

}
