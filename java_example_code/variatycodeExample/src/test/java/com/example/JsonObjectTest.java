package com.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class JsonObjectTest {

  public enum SessionStatus {
    LOADING,
  }

  @Test
  public void enumTest() {
    System.out.println(Enum.valueOf((Class < Enum >), SessionStatus.LOADING));
  }
  @Test
  public void jsonTest() throws JsonProcessingException {
    String value = String.format(
        "{\"userId\":\"%d\",\"userName\":\"%s\",\"sessionStatus\":\"%s\"}",
        123,
        "victor",
        SessionStatus.LOADING
    );
    String redisValue = generateRedisKeyValuePair("userId",123124, "userName",null,"sessionStatus", SessionStatus.LOADING);
    Map<String, String> keyValueMap = new HashMap<>();
    String[] keyValues = redisValue.split(":");
    for (int i = 0; i < keyValues.length - 1; i += 2) {
      String key = keyValues[i];
      String newValue = keyValues[i + 1];
      keyValueMap.put(key, newValue);
    }
    ObjectMapper objectMapper = new ObjectMapper();
    String json = objectMapper.writeValueAsString(keyValueMap);
    System.out.println("check value => " + value);
    System.out.println("check redisValue => " + redisValue);
    System.out.println("check json => " + json);
  }

  public static String generateRedisKeyValuePair(Object... args) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < args.length; i += 2) {
      Object key = args[i];
      Object value = args[i + 1];

      if (key == null || "".equals(key.toString()) || value == null || "".equals(value.toString())) {
        continue;
      }
      if (sb.length() > 0) {
        sb.append(":");
      }

      sb.append(key).append(":").append(value);
    }
    return sb.toString();
  }
//  @DisplayName("ObjectMapper 테스트")
//  @Test
//  public void objectMapperTest() {
//
//  }
//
//  @DisplayName("JsonParser 테스트")
//  @Test
//  public void jsonParserTest() {
//
//  }
//
//  @DisplayName("jsonObject 테스트")
//  @Test
//  public void jsonObjectTest() {
//    String value = "{}"
//    JsonObject originalObject = JsonParser.parseString(value).getAsJsonObject();
//    JsonObject newObject = new JsonObject();
//  }

//  @DisplayName("JsonElement 테스트")
//  @Test
//  public void jsonElementTest() {
//
//  }

}
