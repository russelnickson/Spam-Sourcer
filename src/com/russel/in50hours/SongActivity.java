package com.russel.in50hours;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.SimpleCursorAdapter;

public class SongActivity extends ListActivity {
	
	
	public String rep;
    private int mNoteNumber = 1;
    private NotesDbAdapter mDbHelper;
	 public static final int INSERT_ID = Menu.FIRST;
	
	String[] blk_num = new String[10];
	String line;
	int i=0;
	
    /** Called when the activity is first created. */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.song);
        
        
		mDbHelper = new NotesDbAdapter(this);
        mDbHelper.open();
        //createNote();
		//fillData();
		setListAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, this.fetchTwitterPublicTimeline()));        
    }
 
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
    private void createNote() {
    	i = 0;
    	while(i<blk_num.length){
    		rep = blk_num[i];
    		mDbHelper.createNote(rep);
    		i++;
    	}
        
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
  
	
}