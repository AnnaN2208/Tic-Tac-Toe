package com.example.tictactoe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.Toast;

public class Game extends Activity implements OnClickListener{
	
	ImageButton b11, b12, b13, b21, b22, b23, b31, b32, b33;
	ImageButton[] buttonArray;
	public final static String EXTRA_FINAL_SCORE = "com.example.shapeorcolor.FINAL_SCORE";
	
	boolean turnX = true;
	int turnCount = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		
		b11 = (ImageButton) findViewById(R.id.b11);
		b12 = (ImageButton) findViewById(R.id.b12);
		b13 = (ImageButton) findViewById(R.id.b13);
		b21 = (ImageButton) findViewById(R.id.b21);
		b22 = (ImageButton) findViewById(R.id.b22);
		b23 = (ImageButton) findViewById(R.id.b23);
		b31 = (ImageButton) findViewById(R.id.b31);
		b32 = (ImageButton) findViewById(R.id.b32);
		b33 = (ImageButton) findViewById(R.id.b33);
		
		buttonArray= new ImageButton[]{b11, b12, b13, b21, b22, b23, b31, b32, b33};
		
		for(ImageButton b : buttonArray)
		{
			b.setOnClickListener(this);
			b.setTag(2);  
			
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(turnX)
			toast("Player O");
		else 
			toast("Player X");
		
		ImageButton b = (ImageButton) v;
		buttonClicked(b);
	}
	
	public void buttonClicked(ImageButton b) {
		
		if(turnX)
		{
			b.setImageResource(R.drawable.ic_x);
			b.setTag(1);  
		}
		else
		{
			b.setImageResource(R.drawable.ic_o);
			b.setTag(0);  
		}
		b.setClickable(false);
		turnX = !turnX;
		turnCount ++;
		
		checkWinner();
	}
	
	private void checkWinner() {
		// TODO Auto-generated method stub
		
		boolean winner = false;
		
		int src11 = (Integer)b11.getTag();
		int src12 = (Integer)b12.getTag();
		int src13 = (Integer)b13.getTag();
		int src21 = (Integer)b21.getTag();
		int src22 = (Integer)b22.getTag();
		int src23 = (Integer)b23.getTag();
		int src31 = (Integer)b31.getTag();
		int src32 = (Integer)b32.getTag();
		int src33 = (Integer)b33.getTag();
		
		if(src11 == src12 && src12 == src13 && (src11==1 || src11==0))
			winner = true;
		else if(src21 == src22 && src22 == src23 && (src22==1 || src22==0))
			winner = true;
		else if(src31 == src32 && src32 == src33 && (src33==1 || src33==0))
			winner = true;
		else if(src11 == src21 && src21 == src31 && (src11==1 || src11==0))
			winner = true;
		else if(src12 == src22 && src22 == src32 && (src22==1 || src22==0))
			winner = true;
		else if(src13 == src23 && src23 == src33 && (src33==1 || src33==0))
			winner = true;
		else if(src11 == src22 && src22 == src33 && (src11==1 || src11==0))
			winner = true;
		else if(src13 == src22 && src22 == src31 && (src22==1 || src22==0))
			winner = true;
		
		
		
		
		if(winner)
		{
			Intent intent =  new Intent(Game.this, FinalResults.class);
			if(!turnX)
			{
				toast("Player X wins");
				intent.putExtra(EXTRA_FINAL_SCORE, "1" );
			}
			else
			{
				toast("Player O wins");
				intent.putExtra(EXTRA_FINAL_SCORE, "0" );
			}
			enableDisableButtons(false);
			startActivity(intent);
		}
		else if (turnCount == 9) 
		{
			Intent intent =  new Intent(Game.this, FinalResults.class);
			intent.putExtra(EXTRA_FINAL_SCORE, "2" );
			toast("There are no more moves!");
			startActivity(intent);
		}
		
		
	}
	
	private void enableDisableButtons(boolean enable) {
		// TODO Auto-generated method stub
		for(ImageButton b : buttonArray)
		{
			b.setClickable(enable);
		}
		
	}

	private void toast(String message) {
		Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game, menu);
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
