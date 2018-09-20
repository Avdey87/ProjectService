package e.aavdeev.project;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.lang.ref.WeakReference;

public class MyBroadcastReceiver extends BroadcastReceiver {
    public static final String CHANGE = "e.aavdeev.project";

    private WeakReference<ProgressBar> progressBarWeakReference;

    public MyBroadcastReceiver(ProgressBar progressBar) {
        progressBarWeakReference = new WeakReference<>(progressBar);
    }


    @Override
    public void onReceive(Context context, Intent intent) {
        ProgressBar progressBar = progressBarWeakReference.get();
        int progress = ((MainActivity) context).boundService.getProgress();

        progressBar.setProgress(progress);
        if (progress == 100) {
            Toast.makeText(context, "Donwload finished", Toast.LENGTH_SHORT).show();
        }
    }
}
