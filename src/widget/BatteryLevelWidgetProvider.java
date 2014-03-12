package widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class BatteryLevelWidgetProvider extends AppWidgetProvider {

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.d("BatteryLevelWidgetProvider::onReceive", intent.getAction());
		super.onReceive(context, intent);
	}

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		Log.d("BatteryLevelWidgetProvider::onUpdate", "");
		super.onUpdate(context, appWidgetManager, appWidgetIds);
		
		
		context.startService(new Intent(context, HelperService.class));
	}
}
