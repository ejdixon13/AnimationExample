package com.ericStuff.animationexample;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class AnimationExampleActivity extends Activity {
	private ObjectAnimator animation3;
	private ObjectAnimator animation3_1;
	private CheckBox button;
/** Called when the activity is first created. */

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

  }

  public void startAnimation(View view) {
    float dest = 0;
    ImageView aniView = (ImageView) findViewById(R.id.imageView1);
    int id = view.getId();
	if (id == R.id.Button01) {
		dest = 360;
		if (aniView.getRotation() == 360) {
		    System.out.println(aniView.getAlpha());
		    dest = 0;
		  }
		ObjectAnimator animation1 = ObjectAnimator.ofFloat(aniView,
		      "rotation", dest);
		animation1.setDuration(2000);
		animation1.start();
	} else if (id == R.id.Button02) {
		// shows how to define a animation via code
		  // also use an Interpolator (BounceInterpolator)
		  Paint paint = new Paint();
		TextView aniTextView = (TextView) findViewById(R.id.textView1);
		float measureTextCenter = paint.measureText(aniTextView.getText()
		      .toString());
		dest = 0 - measureTextCenter;
		if (aniTextView.getX() < 0) {
		    dest = 0;
		  }
		ObjectAnimator animation2 = ObjectAnimator.ofFloat(aniTextView,
		      "x", dest);
		animation2.setDuration(2000);
		animation2.start();
	} else if (id == R.id.Button03) {
			button = (CheckBox) findViewById(R.id.Button03);
			if (button.isChecked()){
				button.setChecked(false);
			}
			else {
				button.setChecked(true);
			}
			animation3 = ObjectAnimator.ofFloat(button, "alpha",
				0);
			animation3.setDuration(300);
			animation3.start();
		    animation3.addListener(new AnimatorListenerAdapter() {

			      @Override
			      public void onAnimationEnd(Animator animation) {
			    	
			    	button = (CheckBox) findViewById(R.id.Button03);
			    	  
					animation3_1 = ObjectAnimator.ofFloat(button, "alpha",
							1);
					animation3_1.setDuration(300);
					animation3_1.start();
					 if (!button.isChecked()) {
						  button.setChecked(true);
					  }
					 else {
						 button.setChecked(false);
					 }
						
			      }
			    });
		
		
	} else if (id == R.id.Button04) {
		ObjectAnimator fadeOut = ObjectAnimator.ofFloat(aniView, "alpha",
		      0f);
		fadeOut.setDuration(2000);
		ObjectAnimator mover = ObjectAnimator.ofFloat(aniView,
		      "translationX", -500f, 0f);
		mover.setDuration(2000);
		ObjectAnimator fadeIn = ObjectAnimator.ofFloat(aniView, "alpha",
		      0f, 1f);
		fadeIn.setDuration(2000);
		AnimatorSet animatorSet = new AnimatorSet();
		animatorSet.play(mover).with(fadeIn).after(fadeOut);
		animatorSet.start();
	} else {
	}

  }
  
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.main, menu);
    return super.onCreateOptionsMenu(menu);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    Intent intent = new Intent(this, HitActivity.class);
    startActivity(intent);
    return true;
  }
} 