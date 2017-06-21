package com.hackathonproject.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hackathonproject.R;
import com.hackathonproject.models.User;

public class UserProfile extends AppCompatActivity {


    private static final int READ_CONTACTS_REQUEST_CODE = 789;
    private static final String TAG = UserProfile.class.getSimpleName();
    private ImageView mUserImage;
    private EditText mUserName, mUserPhone, mUserEmail, mUserTag;
    private Button mSubmit;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        mUserImage = (ImageView) findViewById(R.id.iv_user_image);
        mSubmit = (Button) findViewById(R.id.submit);

        mUserName = (EditText) findViewById(R.id.et_user_name);
        mUserPhone = (EditText) findViewById(R.id.et_user_phone);
        mUserEmail = (EditText) findViewById(R.id.et_user_email);
        mUserTag = (EditText) findViewById(R.id.et_tag);

        mDatabase =
                FirebaseDatabase.getInstance().getReference();

        setListeners();
    }

    private void setListeners() {
        mUserImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                askPermission();
            }
        });

        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User(mUserName.getText().toString().trim(),
                        mUserEmail.getText().toString().trim(),
                        mUserPhone.getText().toString().trim(),
                        "http://www.platform505.com/wp-content/uploads/Sample-stamp-by-Stuart-Miles_edited-1.jpg",
                        mUserTag.getText().toString().trim(),
                        28.5930586,
                        77.2002744);

                mDatabase.child("User").child(mUserEmail.getText().toString().trim().replace(".", "@")).setValue(user);
            }
        });


        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                User user = dataSnapshot.getValue(User.class);
                Toast.makeText(UserProfile.this, "Added user", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(UserProfile.this, MainActivity.class);
                startActivity(i);
                // ...
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                Toast.makeText(UserProfile.this, "Some problem", Toast.LENGTH_SHORT).show();
                // ...
            }
        };

        mDatabase.addValueEventListener(postListener);
    }

    private void askPermission() {
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            // perform your work
        } else {
            // request for permission
            boolean isDeniedPreviously = ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_CONTACTS);
            if (isDeniedPreviously) {
                // show an explanation as to why you need this permission and again request for permission
                // if don't ask again box is checked, and we have again asked for permission it will directly call
                // onRequestPermissionResult with Permission_DENIED result
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, READ_CONTACTS_REQUEST_CODE); // shows the dialog
            } else {
                // request for permission
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, READ_CONTACTS_REQUEST_CODE); // shows the dialog
            }
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == READ_CONTACTS_REQUEST_CODE) {
            // if request is cancelled grantResults array is empty
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // perform your work as permission is granted

            } else {
                // permission is denied so disable any functionality the app will use because of this permission
                //your app might show a dialog or snackbar explaining why it could not perform the user's requested action that needs that permission.
            }
        }
    }
}
