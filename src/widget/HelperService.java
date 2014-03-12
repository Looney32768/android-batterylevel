package widget;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;

public class HelperService extends Service {
	public BatteryLevelBroadcastReceiver br = new BatteryLevelBroadcastReceiver();
	
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.d("onStartCommand", "");		
		registerReceiver(br, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
		return 0;
	}
		
	@Override
	public IBinder onBind(Intent arg0) {
		Log.d("onBind", "");
		return null;
	}
}
