package id.firodev.firebasedatabase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    ImageButton imageButton_minecraft;
    ImageButton imageButton_dota;
    ImageButton imageButton_android;
    TextView    textView_hobi;

    DatabaseReference hobiku;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageButton_minecraft = (ImageButton)findViewById(R.id.buttonMinecraft);
        imageButton_dota = (ImageButton)findViewById(R.id.buttonDota);
        imageButton_android = (ImageButton)findViewById(R.id.buttonAndroid);
        textView_hobi  = (TextView)findViewById(R.id.textView_hobiku);

        hobiku = FirebaseDatabase.getInstance().getReference().child("hobiku");

    }

    @Override
    protected void onStart() {
        super.onStart();

        hobiku.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String hobi_saya = dataSnapshot.getValue(String.class);
                textView_hobi.setText(hobi_saya);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        imageButton_minecraft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hobiku.setValue("Mainan Minecraft");
            }
        });

        imageButton_dota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hobiku.setValue("Mainan Dota");
            }
        });

        imageButton_android.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hobiku.setValue("Ngoding Android");
            }
        });
    }
}
