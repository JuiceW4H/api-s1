package com.api.test;

import java.util.*;
import ch.qos.logback.core.encoder.JsonEscapeUtil;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class TestApplication {

	public void FifthJoke(){
		List<String> jokes = new ArrayList<>();

		int index = 0;

		while(index < 10) {
			String json = new RestTemplate().getForObject(
					"https://official-joke-api.appspot.com/jokes/random", String.class);
			JsonObject response = JsonParser.parseString(json).getAsJsonObject();
			String a = response.get("setup").getAsString();
			String b = response.get("punchline").getAsString();
			jokes.add(a + " " + b + "\n");
			index++;
		}

		System.out.println("5th Jokes: " + jokes.get(4));
	}

	public static void main(String[] args) {

		String API_Key = "a9b5424a6627ec29ce82029c87f19fb6";
		Integer characterID1 = (int)(Math.random() * 733);
		Integer characterID2 = (int)(Math.random() * 733);
		String initialURL = "https://superheroapi.com/api/%s/";
		String urlWithAPI = String.format(initialURL,API_Key);

		String jsonCall1 = new RestTemplate().getForObject(
				urlWithAPI + characterID1 , String.class);

		String jsonCall2 = new RestTemplate().getForObject(
				urlWithAPI + characterID2 , String.class);

		JsonObject response1 = JsonParser.parseString(jsonCall1).getAsJsonObject();
		JsonObject response2 = JsonParser.parseString(jsonCall2).getAsJsonObject();

		Integer heroStrength1 = response1.get("powerstats").getAsJsonObject().get("strength").getAsInt();
		Integer heroStrength2 = response2.get("powerstats").getAsJsonObject().get("strength").getAsInt();

		String heroName1 = response1.get("name").getAsString();
		String heroName2 = response2.get("name").getAsString();

		if(heroStrength1 != null){
			if(heroStrength2 != null){
				if (heroStrength1 > heroStrength2){
					System.out.println(heroName1);
				}else{
					System.out.println(heroName2);
				}
			}
		}
	}
}
