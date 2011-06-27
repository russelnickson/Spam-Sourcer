/*
 * Copyright (C) 2008 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.russel.in50hours;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class Notepadv1 extends ListActivity {
	public String[] columns;
	public int[] names,temp;
	Cursor c;
	private ListAdapter adapter;
	private static final Uri SMS_INBOX = Uri.parse("content://sms/inbox");

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        c = getContentResolver().query(SMS_INBOX, null, null, null, null);
        startManagingCursor(c);
        columns = new String[] { "address" };
        names = new int[] { R.id.row };
        adapter = new SimpleCursorAdapter(this, R.layout.notepad_list, c, columns, names);
        setListAdapter(adapter);

	}
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
    	super.onListItemClick(l, v, position, id);
    	Intent myIntent = new Intent(Notepadv1.this, report.class);
		Bundle b = new Bundle();
    	c.moveToPosition(position);
		b.putString("report_num", c.getString(2));
		myIntent.putExtras(b);
	    Notepadv1.this.startActivity(myIntent);
    	   	
	}
    public void textToast(String textToDisplay) {
    	Context context = getApplicationContext();
    	CharSequence text = textToDisplay;
    	int duration = Toast.LENGTH_SHORT;

    	Toast toast = Toast.makeText(context, text, duration);
    	toast.setGravity(Gravity.TOP|Gravity.LEFT, 50, 50);
    	toast.show();
    	}

}






































/*public static final int INSERT_ID = Menu.FIRST;
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
    private void createNote() {
        String noteName = "Note " + mNoteNumber++;
        mDbHelper.createNote(noteName);
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
    
}*/
