package com.example.foodie;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity{







public void logout(View view){
	FirebaseAuth.getInstance().signOut();
	startActivity(new Intent(getApplicationContext(), Login.class));
	finish;
}

}