package e.aavdeev.project;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

  /*  Создать проект++

    При запуске приложения создать Bound Service (информация о Bound Service в дополнительных материалах), в потоке которого постепенно будет меняться значение прогресса и, соответственно, обновляться ProgressBar. Если брать максимум ProgressBar - 100%, то значение прогресса должно меняться на 5% каждые 200 миллисекунд.

    По достижению 100% ProgressBar перестает заполняться. В любой момент по нажатию на кнопку шкала уменьшается на 50%, но не меньше 0%. (75% -> 25%; 35% -> 0%)
    Дополнительно: Каждый раз по достижении 100% появляется тост о завершении загрузки.*/

    MyBoundService boundService;
    boolean isBoundServiceActive = false;

    private MyBroadcastReceiver broadcastReceiver;
    private IntentFilter intentFilter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ProgressBar progressBar = findViewById(R.id.progressbar);
        Button returnButton = findViewById(R.id.return_button);

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBoundServiceActive) {
                    boundService.regrss();
                }
            }
        });

        broadcastReceiver = new MyBroadcastReceiver(progressBar);
        intentFilter = new IntentFilter(MyBroadcastReceiver.CHANGE);
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder service) {
            MyBoundService.ProgrressBinder binder = (MyBoundService.ProgrressBinder) service;
            boundService = binder.getBoundService();
            isBoundServiceActive = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            isBoundServiceActive = false;
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, MyBoundService.class);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(broadcastReceiver, intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (isBoundServiceActive) {
            unbindService(serviceConnection);
            isBoundServiceActive = false;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadcastReceiver);
    }
}
