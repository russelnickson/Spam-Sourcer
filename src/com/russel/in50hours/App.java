package com.russel.in50hours;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;

public class App extends TabActivity {
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.main);

	    Resources res = getResources(); // Resource object to get Drawables
	    TabHost tabHost = getTabHost();  // The activity TabHost
	    TabHost.TabSpec spec;  // Resusable TabSpec for each tab
	    Intent intent;  // Reusable Intent for each tab

	    // Create an Intent to launch an Activity for the tab (to be reused)
	    intent = new Intent(this, Notepadv1.class);

	    // Initialize a TabSpec for each tab and add it to the TabHost
	    spec = tabHost.newTabSpec("report_spam").setIndicator("Report Spam",
	                      res.getDrawable(R.drawable.ic_tab_artists)).setContent(intent);
	    tabHost.addTab(spec);



	    intent = new Intent(this, block_list.class);
	    spec = tabHost.newTabSpec("block_list").setIndicator("Block List",
	                      res.getDrawable(R.drawable.ic_tab_songs)).setContent(intent);
	    tabHost.addTab(spec);

	    tabHost.setCurrentTab(2);
	}
}