package e.aavdeev.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

  /*  Создать проект++

    При запуске приложения создать Bound Service (информация о Bound Service в дополнительных материалах), в потоке которого постепенно будет меняться значение прогресса и, соответственно, обновляться ProgressBar. Если брать максимум ProgressBar - 100%, то значение прогресса должно меняться на 5% каждые 200 миллисекунд.

    По достижению 100% ProgressBar перестает заполняться. В любой момент по нажатию на кнопку шкала уменьшается на 50%, но не меньше 0%. (75% -> 25%; 35% -> 0%)
    Дополнительно: Каждый раз по достижении 100% появляется тост о завершении загрузки.*/


    private ProgressBar progressBar;
    private Button startButton;
    private Button returnButton;
    private Button stopButton;
    private int status = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressbar);
        startButton = findViewById(R.id.start_button);
        returnButton = findViewById(R.id.return_button);
        stopButton = findViewById(R.id.stop_button);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MyService.class);
                startService(intent);
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MyService.class);
                stopService(intent);
            }
        });

    }




}
