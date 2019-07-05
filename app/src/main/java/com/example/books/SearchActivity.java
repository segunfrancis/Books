package com.example.books;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.net.URL;

public class SearchActivity extends AppCompatActivity {

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
                Intent intent = new Intent(getApplicationContext(), BookListActivity.class);
                intent.putExtra("Query", queryURL);
                startActivity(intent);
            }
        });
    }
}
