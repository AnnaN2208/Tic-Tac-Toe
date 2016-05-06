package com.example.tictactoe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

public class FinalResults extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_final_results);
		
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		
		int width = dm.widthPixels;
		int height = dm.heightPixels;
		
		getWindow().setLayout((int)(width*.8), (int)(height*.6));
		
		Intent intent = getIntent();
        
        String finalScore = intent.getStringExtra(Game.EXTRA_FINAL_SCORE);        
        TextView textView = (TextView) findViewById(R.id.winnerTextView);
        if(finalScore.equals("0"))
        	textView.setText("The winner is Player O");	
        else if(finalScore.equals("1"))
        	textView.setText("The winner is Player X");	
        else if(finalScore.equals("2"))
        	textView.setText("DRAW");	
        
        
        ImageButton replayButton = (ImageButton) findViewById(R.id.newGameButton);
        ImageButton quitButton = (ImageButton) findViewById(R.id.quitButton);
        
        replayButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(FinalResults.this, Game.class);
				startActivity(intent);
			}
		});
        
        quitButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Intent.ACTION_MAIN);
				intent.addCategory(Intent.CATEGORY_HOME);
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(intent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.final_results, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
