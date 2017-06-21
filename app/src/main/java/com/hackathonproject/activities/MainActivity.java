package com.hackathonproject.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hackathonproject.R;
import com.hackathonproject.models.User;
import com.hackathonproject.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get a reference to our posts
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();
//        DatabaseReference ref = database.getReferenceFromUrl("https://hackathon-cb7bb.firebaseio.com/");

//        DatabaseReference ref = ref1.child("User").getRoot();

        // Attach a listener to read the data at our posts reference
        ref.child("Event1").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Toast.makeText(MainActivity.this, "Exists", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "No data", Toast.LENGTH_SHORT).show();
                    return;
                }


                List notes = new ArrayList<>();
                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                    User note = noteDataSnapshot.getValue(User.class);
                    notes.add(note);
                    Utils.showLog("MainActivity", note.getName());
                }

                Toast.makeText(MainActivity.this, "" + notes
                        .size(), Toast.LENGTH_SHORT).show();

                User post = dataSnapshot.getValue(User.class);
                Utils.showLog("MainActivity", post.getName());
                Toast.makeText(MainActivity.this, "" + post.getName(), Toast.LENGTH_SHORT).show();
//                ArrayList<Events> eventsArrayList =
//                System.out.println(post);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

    }
}
