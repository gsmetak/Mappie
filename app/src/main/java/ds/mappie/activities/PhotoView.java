package ds.mappie.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import ds.mappie.R;
import ds.mappie.tasks.GetPhoto;

public class PhotoView extends AppCompatActivity {
    public int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_view);
        Intent i = getIntent();

        ((TextView)findViewById(R.id.textView)).setText(i.getStringExtra("name"));
        ((TextView)findViewById(R.id.textView10)).setText(i.getStringExtra("check"));

        final ArrayList<String> photos = i.getExtras().getStringArrayList("photos");
        try {
            new GetPhoto((ImageView) findViewById(R.id.imageView)).execute(photos.get(0));
        } catch (NullPointerException e) {
            Intent n = new Intent(getApplicationContext(), PhotoView.class);
            startActivity(n);
        }

        Button next = ((Button) findViewById(R.id.button7));
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (counter == (photos.size()-1))
                    counter = 0;
                else
                    counter++;

                try {
                    new GetPhoto((ImageView) findViewById(R.id.imageView)).execute(photos.get(counter));
                } catch (NullPointerException e) {
                    Intent n = new Intent(getApplicationContext(), PhotoView.class);
                    startActivity(n);
                }
            }
        });

        Button previous = ((Button) findViewById(R.id.button9));
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (counter == 0)
                    counter = photos.size() - 1;
                else
                    counter--;

                try {
                    new GetPhoto((ImageView) findViewById(R.id.imageView)).execute(photos.get(counter));
                } catch (NullPointerException e) {
                    Intent n = new Intent(getApplicationContext(), PhotoView.class);
                    startActivity(n);
                }
            }
        });
    }
}
