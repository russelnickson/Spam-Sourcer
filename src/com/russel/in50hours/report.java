package com.russel.in50hours;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;



public class report extends ListActivity {
	public String rep;
    private int mNoteNumber = 1;
    private NotesDbAdapter mDbHelper;
	 public static final int INSERT_ID = Menu.FIRST;
	

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.report);
        Bundle b = getIntent().getExtras(); 
		rep = b.getString("report_num");
		
		mDbHelper = new NotesDbAdapter(this);
        mDbHelper.open();
		final Button btn = (Button) this.findViewById(R.id.btn);
	    btn.setOnClickListener(new OnClickListener() {
	    	@Override
	    	public void onClick(View v) {
	    	if(v==btn) {
	    			createNote();
	    			fillData();
	    			try {
	    				URL twitter = new URL(
	    						"http://ec2-204-236-212-104.compute-1.amazonaws.com/add.php?num="+rep);
	    				URLConnection tc = twitter.openConnection();
	    				BufferedReader in = new BufferedReader(new InputStreamReader(
	    						tc.getInputStream()));
	    	 
	    				
	    				}
	    		 catch (MalformedURLException e) {
	    				// TODO Auto-generated catch bloc
	    				e.printStackTrace();
	    			} catch (IOException e1) {
	    				// TODO Auto-generated catch block
	    				e1.printStackTrace();
	    			}
	        		textToast(rep+" is marked as Spam!");
	        		
	    	}
	    }
	    });
	}

    private void createNote() {
        mDbHelper.createNote(rep);
        //fillData();
    }
    private void fillData() {
        // Get all of the notes from the database and create the item list
        Cursor c1 = mDbHelper.fetchAllNotes();
        startManagingCursor(c1);

        String[] from = new String[] { NotesDbAdapter.KEY_TITLE };
        int[] to = new int[] { R.id.text1 };
        
        // Now create an array adapter and set it to display using our row
        SimpleCursorAdapter notes =
            new SimpleCursorAdapter(this, R.layout.notes_row, c1, from, to);
        setListAdapter(notes);
    }
  

    public void textToast(String textToDisplay) {
    	Context context = getApplicationContext();
    	CharSequence text = textToDisplay;
    	int duration = Toast.LENGTH_SHORT;

    	Toast toast = Toast.makeText(context, text, duration);
    	toast.setGravity(Gravity.TOP|Gravity.LEFT, 50, 50);
    	toast.show();
    	
    	Intent myIntent = new Intent(report.this, App.class);
		Bundle b = new Bundle();
		
		
		myIntent.putExtras(b);
	    report.this.startActivity(myIntent);
    	
    	}
}
