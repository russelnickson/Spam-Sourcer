package com.russel.in50hours;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SimpleCursorAdapter;

public class block_list extends ListActivity {
	String[] blk_num = new String[10];
	String line;
	int i=0;
	
	public ArrayList<String> fetchTwitterPublicTimeline()
    {
    	ArrayList<String> listItems = new ArrayList<String>();
 
    	try {
			URL twitter = new URL(
					"http://ec2-204-236-212-104.compute-1.amazonaws.com/download.php");
			URLConnection tc = twitter.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					tc.getInputStream()));
 
			line = in.readLine();
			blk_num = line.split(",");
			while(i<blk_num.length){
			
			listItems.add(blk_num[i]);
			i++;
			}
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch bloc
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listItems;
    }
	
	 private int mNoteNumber = 1;
	    private NotesDbAdapter mDbHelper;
		public static final int INSERT_ID = Menu.FIRST;

	    /** Called when the activity is first created. */
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.block_list);
	        final Button btn2 = (Button) this.findViewById(R.id.button1);
		    btn2.setOnClickListener(new OnClickListener() {
		    	@Override
		    	public void onClick(View v) {
		    	if(v==btn2) {
		    		Intent myIntent = new Intent(block_list.this, SongActivity.class);
	        		block_list.this.startActivity(myIntent);
		    	}
		    }
		    });

	        mDbHelper = new NotesDbAdapter(this);
	          mDbHelper.open();
	          fillData();
	      }

	      @Override
	      public boolean onCreateOptionsMenu(Menu menu) {
	          // TODO Auto-generated method stub
	      	boolean result = super.onCreateOptionsMenu(menu);
	      	menu.add(0, INSERT_ID, 0, R.string.menu_insert);
	          return result;
	      }

	      @Override
	      public boolean onOptionsItemSelected(MenuItem item) {
	          switch (item.getItemId()) {
	          case INSERT_ID:
	              createNote();
	              return true;
	          }
	         
	          return super.onOptionsItemSelected(item);
	      }

	      private void createNote() {i=0;
			while(i<blk_num.length){
				
				mDbHelper.createNote(blk_num[i]);
				i++;
				}
	          fillData();
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

	      
}

