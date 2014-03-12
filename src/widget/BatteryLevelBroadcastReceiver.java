package widget;

import org.looney.batterylevelinfo.R;

import android.appwidget.AppWidgetManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.BatteryManager;
import android.util.Log;
import android.widget.RemoteViews;

public class BatteryLevelBroadcastReceiver extends BroadcastReceiver {
	int charge;

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.d("onReceive", intent.getAction());

		RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.main);

		if (intent.getAction().equals(Intent.ACTION_BATTERY_CHANGED)) {
			float level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
			float scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, 1);
			int powerConnected = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, 0);
			if (powerConnected > 0)
				indicatePowerConnected(views);
			else indicatePowerDisconnected(views);
			
			charge = Math.round(level / scale * 100);
			System.out.println(charge);

			views.setTextViewText(R.id.batteryLevelText, String.valueOf(charge) + "%");
		}
		else if (intent.getAction().equals(Intent.ACTION_POWER_CONNECTED)) {
			indicatePowerConnected(views);
		}
		else if (intent.getAction().equals(Intent.ACTION_POWER_DISCONNECTED)) {
			indicatePowerDisconnected(views);
		}

		//update widget views
		AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context.getApplicationContext());
		int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(context.getApplicationContext(), BatteryLevelWidgetProvider.class));//intent.getIntArrayExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS);     
		for(int widgetId : appWidgetIds) {
			appWidgetManager.updateAppWidget(widgetId, views);
		}
	}
	
	private void indicatePowerConnected(RemoteViews views) {
		views.setTextColor(R.id.batteryLevelText, Color.MAGENTA);
	}
	
	private void indicatePowerDisconnected(RemoteViews views) {
		views.setTextColor(R.id.batteryLevelText, Color.GREEN);
	}
}
