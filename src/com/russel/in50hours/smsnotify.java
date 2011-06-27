/**
 * 
 */
package com.russel.in50hours;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * @author nickson
 *
 */
public class smsnotify extends Activity {

	
	
	
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.main);

	
	
	String ns = Context.NOTIFICATION_SERVICE;
	NotificationManager mNotificationManager = (NotificationManager) getSystemService(ns);
	int icon = R.drawable.icon;
	CharSequence tickerText = "Hello";
	long when = System.currentTimeMillis();

	Notification notification = new Notification(icon, tickerText, when);
	
	Context context = this.getApplicationContext();
	CharSequence contentTitle = "Blocked SMS";
	CharSequence contentText = "Blocked SMS!";
	Intent notificationIntent = new Intent(this, App.class);
	PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);

	notification.setLatestEventInfo(context, contentTitle, contentText, contentIntent);
	
	}	
}
