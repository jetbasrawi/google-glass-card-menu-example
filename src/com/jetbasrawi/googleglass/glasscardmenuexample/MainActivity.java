package com.jetbasrawi.googleglass.glasscardmenuexample;

import com.google.android.glass.app.Card;
import com.google.android.glass.media.Sounds;
import com.google.android.glass.touchpad.Gesture;
import com.google.android.glass.touchpad.GestureDetector;
import com.jetbasrawi.googleglass.glasscardmenuexample.MainActivity;
//import com.jetbasrawi.googleglass.glasscardmenuexample.R;





import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;


public class MainActivity extends Activity {

	private GestureDetector mGestureDetector;
    private AudioManager mAudioManager;
	
	/**Create a gesture detector to detect the tap which will launch the menu.*/
	private final GestureDetector.BaseListener mBaseListener = new GestureDetector.BaseListener() {
        @Override
        public boolean onGesture(Gesture gesture) {
            if (gesture == Gesture.TAP) {
                mAudioManager.playSoundEffect(Sounds.TAP);
                openOptionsMenu();
                return true;
            } else {
                return false;
            }
        }
    };
	
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		Card card = new Card(this);
		card.setText("Tap for menu");
		setContentView(card.getView());
		
		mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
		mGestureDetector = new GestureDetector(this).setBaseListener(mBaseListener);
		
	}
	
	
	/** Intercept touch pad events and pass them to the gesture detector. */
	@Override
    public boolean onGenericMotionEvent(MotionEvent event) {
        return mGestureDetector.onMotionEvent(event);
    }
	
	/** Initialise the contents of the menu */
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

	
    /** This method is called each time the menu is displayed.
     * if you want to customise the menu according to some contextual criteria
     * you can do this here.
     */
    @Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		
    	MenuItem item = menu.getItem(2);
    	item.setEnabled(false);
    	return super.onPrepareOptionsMenu(menu);
	  
	}

    
    /** Handle the selected menu item */
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        
        switch (item.getItemId()) {
            case R.id.menuitem1:
            	updateDisplay(R.string.option1);
                return true;

            case R.id.menuitem2:
            	updateDisplay(R.string.option2);
                return true;
            
            case R.id.menuitem3:
            	updateDisplay(R.string.option3);
                return true;

            default:
                return false;
        }
    	
    }

	private void updateDisplay(int msg) {
		
		final Card card = new Card(this);
		card.setText("Tap for menu");
		card.setFootnote(msg);
		setContentView(card.getView());
        
	}

}
