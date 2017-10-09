package com.findme.utils;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.findme.exception.BadRequestException;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Component
public class MessageUtility {

	private final JsonParser parser = new JsonParser();
	private final Gson gson = new Gson();

	public JsonObject parseMessage(String json) {
		return parser.parse(json).getAsJsonObject();
	}

	public JsonObject mapToJsonObject(Map<String, Object> messages) {
		return parser.parse(gson.toJson(messages)).getAsJsonObject();
	}

	public String createResponseMessage(String key, String message) {
		JsonObject response = new JsonObject();
		response.addProperty(key, message);
		return response.toString();
	}

	public String createResponseMessage(Map<String, Object> messages) {
		return gson.toJson(messages);

	}

	public JsonObject validateJsonObject(JsonObject jsonObject, String... keys) throws BadRequestException {
		for (String key : keys) {
			if (jsonObject.get(key) == null) {
				throw new BadRequestException(
						"missing or unexpected value found when validating json: " + jsonObject.toString());
			}
		}
		return null;
	}
}
