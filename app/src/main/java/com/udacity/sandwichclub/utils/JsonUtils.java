package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        Sandwich sandwich = new Sandwich();
        try {
            JSONObject jsonObject = new JSONObject(json);

            JSONObject mainNameJson = jsonObject.getJSONObject("name");
            JSONArray alsoKnownAsJson = mainNameJson.getJSONArray("alsoKnownAs");
            JSONArray ingredientsJson = jsonObject.getJSONArray("ingredients");

            String image = jsonObject.getString("image");
            String mainName = mainNameJson.getString("mainName");
            List<String> alsoKnownAs = ConvertJsonArrayToList(alsoKnownAsJson);
            String placeOfOrigin = jsonObject.getString("placeOfOrigin");
            String description = jsonObject.getString("description");
            List<String> ingredients = ConvertJsonArrayToList(ingredientsJson);


            sandwich.setImage(image);
            sandwich.setMainName(mainName);
            sandwich.setAlsoKnownAs(alsoKnownAs);
            sandwich.setPlaceOfOrigin(placeOfOrigin);
            sandwich.setDescription(description);
            sandwich.setIngredients(ingredients);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sandwich;
    }

    public static List<String> ConvertJsonArrayToList(JSONArray jsonArray) {
        List<String> results = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                results.add(jsonArray.getString(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return results;
    }
}
