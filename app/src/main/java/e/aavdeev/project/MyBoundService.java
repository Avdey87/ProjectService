package e.aavdeev.project;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MyBoundService extends Service {

    private IBinder progressBinder = new ProgrressBinder();
    private ScheduledExecutorService scheduledExecutorService;
    private int progress = 0;

    @Override
    public void onCreate() {
        super.onCreate();
        scheduledExecutorService = Executors.newScheduledThreadPool(1);
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                if (progressBinder.isBinderAlive() && progress != 100) {
                    updateProgress();
                }
            }
        }, 0, 200, TimeUnit.MILLISECONDS);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return progressBinder;
    }


    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    public class ProgrressBinder extends Binder {
        MyBoundService getBoundService() {
            return MyBoundService.this;
        }
    }

    public void updateProgress() {
        progress += 5;
        sendBroadcast(new Intent(MyBroadcastReceiver.CHANGE));
    }

    public void regrss() {
        if (progress <= 50) {
            progress = 0;
        }
        if (progress > 50) {
            progress = progress - 50;
        }
        sendBroadcast(new Intent(MyBroadcastReceiver.CHANGE));
    }

    public int getProgress() {
        return progress;
    }
}
