package com.example.books;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.net.URL;

public class SearchActivity extends AppCompatActivity {

    public static String QUERY = "Query";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        EditText etTitle = findViewById(R.id.etTitle);
        EditText etAuthor = findViewById(R.id.etAuthor);
        EditText etPublisher = findViewById(R.id.etPublisher);
        EditText etIsbn = findViewById(R.id.etIsbn);
        Button button = findViewById(R.id.btnSearch);

        button.setOnClickListener(view -> {
            String title = etTitle.getText().toString().trim();
            String author = etAuthor.getText().toString().trim();
            String publisher = etPublisher.getText().toString().trim();
            String isbn = etIsbn.getText().toString().trim();

            if (title.isEmpty() && author.isEmpty() && publisher.isEmpty() && isbn.isEmpty()) {
                String message = getString(R.string.no_search_data);
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
            } else {
                URL queryURL = ApiUtil.buildUrl(title, author, publisher, isbn);
                // SharedPreferences
                Context context = getApplicationContext();
                int position = SpUtil.getPreferenceInt(context, SpUtil.POSITION);
                if (position == 0 || position == 5) {
                    position = 1;
                } else {
                    position++;
                }
                String key = SpUtil.QUERY + String.valueOf(position);
                String value = title + "," + author + "," + publisher + "," + isbn;
                SpUtil.setPreferenceString(context, key, value);
                SpUtil.setPreferenceInt(context, SpUtil.POSITION, position);

                Intent intent = new Intent(getApplicationContext(), BookListActivity.class);
                intent.putExtra(QUERY, queryURL.toString());
                startActivity(intent);
            }
        });
    }
}
