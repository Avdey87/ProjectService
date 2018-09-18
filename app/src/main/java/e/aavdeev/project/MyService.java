package e.aavdeev.project;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MyService extends Service {

    public static final String TAG = "TAG";
    private ScheduledExecutorService scheduledExecutorService;
    @Override
    public IBinder onBind(Intent intent) {
                throw new UnsupportedOperationException("Not yet implemented");
    }


    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate");
        scheduledExecutorService = Executors.newScheduledThreadPool(1);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "run" + System.currentTimeMillis());
            }
        }, 1000, 1000, TimeUnit.MICROSECONDS);


        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "STOP");
        scheduledExecutorService.shutdownNow();
    }
}
