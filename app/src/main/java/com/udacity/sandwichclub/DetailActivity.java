package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import org.w3c.dom.Text;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;
    TextView name_tv;
    TextView origin_tv;
    TextView description_tv;
    TextView ingredients_tv;
    TextView also_known_tv;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);
        name_tv = findViewById(R.id.name_tv);
        origin_tv = findViewById(R.id.origin_tv);
        description_tv = findViewById(R.id.description_tv);
        ingredients_tv = findViewById(R.id.ingredients_tv);
        also_known_tv = findViewById(R.id.also_known_tv);


        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

         position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {
        name_tv.setText(sandwich.getMainName());
        origin_tv.setText(sandwich.getPlaceOfOrigin());
        description_tv.setText(sandwich.getDescription());
        also_known_tv.setText(convertListToString(sandwich.getAlsoKnownAs()));
        ingredients_tv.setText(convertListToString(sandwich.getIngredients()));
    }

    private String convertListToString(List<String> list){
        String string = "";
        for (int i=0 ; i<list.size();i++){
            if (string.equals("")){
                string = list.get(i);
            }else{
                string = string + "\n" + list.get(i);
            }

        }
        return string;
    }
}
