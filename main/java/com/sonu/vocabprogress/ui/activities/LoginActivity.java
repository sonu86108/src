package com.sonu.vocabprogress.ui.activities;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.sonu.vocabprogress.R;
import android.view.View;
import android.widget.ProgressBar;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import android.content.*;
import com.google.firebase.auth.*;
import com.google.android.gms.tasks.*;
import com.google.android.gms.auth.api.signin.*;
import android.widget.*;
import com.google.android.gms.common.api.*;

public class LoginActivity extends AppCompatActivity
implements View.OnClickListener{
    private final int RC_SIGN_IN=123;
		private FirebaseAuth mAuth;
		private FirebaseAuth.AuthStateListener mAuthListener;
		ProgressBar pbLogin;
		SignInButton btnSignIn;
		GoogleSignInClient mGoogleSignInClient;
		@Override
		protected void onCreate(Bundle savedInstanceState){
				super.onCreate(savedInstanceState);
				setContentView(R.layout.activity_login);

				mAuth=FirebaseAuth.getInstance();
				
				pbLogin=findViewById(R.id.id_pb_login);
				btnSignIn=findViewById(R.id.id_btn_signIn);
				
				// Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
						.requestIdToken(getString(R.string.default_web_client_id))
						.requestEmail()
						.build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        //setting listners
			btnSignIn.setOnClickListener(this);
			  
		}

		@Override
		public void onClick(View p1)
		{
				switch(p1.getId()){
						case R.id.id_btn_signIn:
								 signIn();
								break;
				}
		}
		private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
		private void signOut() {
				pbLogin.setVisibility(View.VISIBLE);
        // Firebase sign out
        mAuth.signOut();

        // Google sign out
        mGoogleSignInClient.signOut().addOnCompleteListener(this,
						new OnCompleteListener<Void>() {
								@Override
								public void onComplete( Task<Void> task) {
										pbLogin.setVisibility(View.GONE);
								}
						});
    }
		
		@Override
		protected void onActivityResult(int requestCode, int resultCode, Intent data){
				super.onActivityResult(requestCode, resultCode, data);
				// Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {

            }
        }
		}
		
		private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
				pbLogin.setVisibility(View.VISIBLE);
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
						.addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
								@Override
								public void onComplete(Task<AuthResult> task) {
										if (task.isSuccessful()) {
												pbLogin.setVisibility(View.GONE);
												startHomeActivity();
										} else {
												showInToast(task.getException().getMessage());
												pbLogin.setVisibility(View.GONE);
										}


								}
						});
    }
		

		private void showInToast(String msg){
				Toast.makeText(LoginActivity.this,msg,Toast.LENGTH_LONG).show();
		}
		
		
		private void startHomeActivity(){
				startActivity(new Intent(LoginActivity.this,MainActivity.class));
		}
		
		@Override
		protected void onStart(){
				super.onStart();
				FirebaseUser user;
				if((user=mAuth.getCurrentUser()) !=null){
             startHomeActivity();
						}


		}
		
		

}
