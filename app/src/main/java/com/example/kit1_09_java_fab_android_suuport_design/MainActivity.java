package com.example.kit1_09_java_fab_android_suuport_design;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


public class MainActivity extends AppCompatActivity {

    private Snackbar mSnackbar;  // ССылка для создания Snackbar.make
    private View.OnClickListener snackbarOnClickListener;
    //private FloatingActionButton fab; // Ссылка на кнопку для слушателя
    private View fabView;             // Ссылка на ту же кнопку для привязки Snackbar
    FloatingActionButton dismissButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Устанавливаем разметку и меню из шаблона
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // стандартный текст шаблона с верхним меню
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fabView = findViewById(R.id.fab);
        //fab = findViewById(R.id.fab);
        dismissButton = (FloatingActionButton) findViewById(R.id.buttonDismiss);

        // Объявляем слушателя для кнопки ДА: нужен ниже для setActoin
        snackbarOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Молодец!", Toast.LENGTH_SHORT).show();
            }
        };
               // createSnack
            createSnack();
            // Сооружаем нижний Snackbar без вызова: вызов будет по нажатию на кнопку ниже: show



        // Ставим слушателя на snackbar просто так для примера:
        mSnackbar.addCallback(new Snackbar.Callback() {
            @Override
            public void onDismissed(Snackbar snackbar, int event) {
                if (event == Snackbar.Callback.DISMISS_EVENT_TIMEOUT)  Log.i("SnackBar BANDIT", "Закрыт по истечении таймаута DO");
                if(event == Snackbar.Callback.DISMISS_EVENT_SWIPE) Log.i("SnackBar BANDIT", "Swipe DO");
                // При смахивании больше не вызывается - - ОШИБКА МОЯ!!!
            }
            @Override
            public void onShown(Snackbar snackbar) { Log.i("SnackBar BADIT", "onShown DO"); }
        });

         // Объявляем слушателя для кнопки с лапой который показывает Snack
        fabView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Snackbar.make(view, "Тяпнул лапой БАНДИТ", Snackbar.LENGTH_INDEFINITE)
                        // .setAction("Action", null) Климов - Устарело
                        .show();  */
                if (mSnackbar == null) createSnack();

                mSnackbar.show();
                          }
        });

          // dismissButton = (FloatingActionButton) findViewById(R.id.buttonDismiss);
        dismissButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if (mSnackbar != null) {
                   mSnackbar.dismiss();
                   Log.i("SnackBar BANDIT", "Dimiss DO");
               }
            }
        });


    }    // Конец OnCreate


        public void createSnack() {
        mSnackbar = Snackbar.make(fabView, "Тяпнул лапой БАНДИТ? ", BaseTransientBottomBar.LENGTH_LONG)
                .setAction("Да", snackbarOnClickListener)
                .setActionTextColor(Color.MAGENTA); // цвет текста у кнопки действия
        // Меняем ему цвет и цвет буковок
        View snackbarView = mSnackbar.getView();
        snackbarView.setBackgroundColor(Color.BLUE);
        TextView snackTextView = (TextView) snackbarView.findViewById(R.id.snackbar_text);
        snackTextView.setTextColor(Color.RED);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
