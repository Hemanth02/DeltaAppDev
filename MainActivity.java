package com.example.android.myapplication;
import java.util.ArrayList;
import java.util.Random;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Vibrator;

import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.os.CountDownTimer;
import android.view.View;
import android.os.Bundle;
import android.widget.EditText;

import android.widget.TextView;
import android.widget.Toast;

import static java.lang.Integer.parseInt;
import static java.lang.Math.min;

public class MainActivity extends AppCompatActivity {
    int num=0,ans,correct,score=0,i=0;
    boolean pressed=false,clock=false;
    public static final String SHARED_PREFS="sharedprefs";
    public static final String POINTS = "points";
    public static final String NUMBERS = "numbers";
    public static final String OPTION_1 = "option1";
    public static final String OPTION_2 = "option2";
    public static final String OPTION_3 = "option3";
    public static final String HIGH_SCORE ="highscore";
    public static final String COLOR="color";
    public static final String TIMER = "timer";
    public static final String ANS = "ans";
    public static final String CORRECT= "correct";
    public static final String SUBMIT = "submit";
    public static final String CLOCK = "clock";


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button option1 = (Button) findViewById(R.id.option_1);
        final Button option2 = (Button) findViewById(R.id.option_2);
        final Button option3 = (Button) findViewById(R.id.option_3);
        final Button submit1 = (Button) findViewById(R.id.submit);
        TextView timer = (TextView) findViewById(R.id.timer);
        i++;
        loadData();
        updateView();

        final View root = getWindow().getDecorView().getRootView();

        final CountDownTimer watch = new CountDownTimer(Integer.parseInt(timer.getText().toString()) * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                TextView timer = (TextView) findViewById(R.id.timer);
                timer.setText("" + millisUntilFinished / 1000);
                saveData();
            }

            @Override
            public void onFinish() {
                if (clock == true) {
                    EditText number = (EditText) findViewById(R.id.number);
                    TextView timer = (TextView) findViewById(R.id.timer);
                    Button option1 = (Button) findViewById(R.id.option_1);
                    Button option2 = (Button) findViewById(R.id.option_2);
                    Button option3 = (Button) findViewById(R.id.option_3);
                    number.setText("" + 0);
                    option1.setText("" + 0);
                    option2.setText("" + 0);
                    option3.setText("" + 0);
                    timer.setText("" + 10);
                    TextView higher = (TextView) findViewById(R.id.high_score);
                    int highscore = Integer.parseInt(higher.getText().toString());
                    if (score > highscore)
                        higher.setText("" + score);
                    score = 0;
                    TextView points = (TextView) findViewById(R.id.score);
                    points.setText("Score : " + score);

                    root.setBackgroundColor(Color.RED);
                    pressed = false;
                    Toast.makeText(getApplicationContext(), "Wrong Answer\nCorrect Answer is " + correct , Toast.LENGTH_SHORT).show();
                    //Toast.makeText(getApplicationContext(),"Game Over",Toast.LENGTH_SHORT).show();
                    saveData();
                    Vibrator vibe = (Vibrator) getSystemService(VIBRATOR_SERVICE);
                    vibe.vibrate(200);
                    clock = false;
                }

            }
        };


        if (pressed == true) {
            watch.start();

        }

        submit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (pressed == false) {

                    pressed = true;
                    EditText number = (EditText) findViewById(R.id.number);
                    if(number.getText().toString().length()==0)
                    {

                        Toast.makeText(getApplicationContext(),"Enter a number",Toast.LENGTH_SHORT);
                        pressed = false;
                        return;
                    }
                    num = Integer.parseInt(number.getText().toString());
                    if (num <= 0 || num > 32000) {
                        num = 0;
                        Toast.makeText(getApplicationContext(), "Number must be within the range 1 to 32000(inclusive)", Toast.LENGTH_SHORT).show();
                        pressed = false;
                        saveData();
                        return;
                    }
                    Random r = new Random();
                    ans = r.nextInt(3) + 1;
                    ArrayList<Integer> factors = new ArrayList<Integer>();


                    for (int i = 1; i <= num / 2; i++) {
                        if (num % i == 0) {
                            factors.add(i);
                        }
                    }

                    factors.add(num);


                    if (ans == 1) {
                        Button option1 = (Button) findViewById(R.id.option_1);
                        Button option2 = (Button) findViewById(R.id.option_2);
                        Button option3 = (Button) findViewById(R.id.option_3);
                        int a, b;
                        correct = factors.get(r.nextInt(factors.size()));
                        option1.setText("" + correct);
                        if (num > 4)
                            a = r.nextInt(num) + 1;
                        else
                            a = r.nextInt(5) + 1;
                        while (factors.contains(a)) {
                            if (num > 4)
                                a = r.nextInt(num) + 1;
                            else
                                a = r.nextInt(5) + 1;
                        }
                        option2.setText("" + a);
                        if (num > 4)
                            b = r.nextInt(num) + 1;
                        else
                            b = r.nextInt(5) + 1;
                        while (factors.contains(b) || b == a) {
                            if (num > 4)
                                b = r.nextInt(num) + 1;
                            else
                                b = r.nextInt(5) + 1;
                        }

                        option3.setText("" + b);

                        saveData();
                    } else if (ans == 2) {
                        Button option1 = (Button) findViewById(R.id.option_1);
                        Button option2 = (Button) findViewById(R.id.option_2);
                        Button option3 = (Button) findViewById(R.id.option_3);
                        int a, b;
                        correct = factors.get(r.nextInt(factors.size()));
                        option2.setText("" + correct);
                        if (num > 4)
                            a = r.nextInt(num) + 1;
                        else
                            a = r.nextInt(5) + 1;
                        while (factors.contains(a)) {
                            if (num > 4)
                                a = r.nextInt(num) + 1;
                            else
                                a = r.nextInt(5) + 1;
                        }
                        option1.setText("" + a);
                        if (num > 4)
                            b = r.nextInt(num) + 1;
                        else
                            b = r.nextInt(5) + 1;
                        while (factors.contains(b) || b == a) {
                            if (num > 4)
                                b = r.nextInt(num) + 1;
                            else
                                b = r.nextInt(5) + 1;
                        }
                        option3.setText("" + b);
                        saveData();
                    } else {
                        Button option1 = (Button) findViewById(R.id.option_1);
                        Button option2 = (Button) findViewById(R.id.option_2);
                        Button option3 = (Button) findViewById(R.id.option_3);
                        int a, b;
                        correct = factors.get(r.nextInt(factors.size()));
                        option3.setText("" + correct);
                        if (num > 4)
                            a = r.nextInt(num) + 1;
                        else
                            a = r.nextInt(5) + 1;
                        while (factors.contains(a)) {
                            if (num > 4)
                                a = r.nextInt(num) + 1;
                            else
                                a = r.nextInt(5) + 1;
                        }
                        option1.setText("" + a);
                        if (num > 4)
                            b = r.nextInt(num) + 1;
                        else
                            b = r.nextInt(5) + 1;
                        while (factors.contains(b) || b == a) {
                            if (num > 4)
                                b = r.nextInt(num) + 1;
                            else
                                b = r.nextInt(5) + 1;
                        }
                        option2.setText("" + b);
                        saveData();
                    }
                    clock = true;
                    watch.start();
                    number.onEditorAction(EditorInfo.IME_ACTION_DONE);
                }


            }

        });
        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pressed == true) {
                    pressed = false;
                    int chosen = Integer.parseInt(option1.getText().toString());
                    if (chosen == correct) {
                        EditText number = (EditText) findViewById(R.id.number);
                        TextView timer = (TextView) findViewById(R.id.timer);
                        score++;
                        TextView points = (TextView) findViewById(R.id.score);
                        points.setText("Score : " + score);
                        clock = false;
                        watch.cancel();
                        number.setText("");
                        option1.setText("" + 0);
                        option2.setText("" + 0);
                        option3.setText("" + 0);
                        timer.setText("" + 10);
                        root.setBackgroundColor(Color.GREEN);
                        saveData();
                        Toast.makeText(getApplicationContext(), "Correct Answer ", Toast.LENGTH_SHORT).show();
                    } else {
                        EditText number = (EditText) findViewById(R.id.number);
                        TextView timer = (TextView) findViewById(R.id.timer);
                        clock = false;
                        watch.cancel();
                        number.setText("");
                        option1.setText("" + 0);
                        option2.setText("" + 0);
                        option3.setText("" + 0);
                        timer.setText("" + 10);
                        TextView higher = (TextView) findViewById(R.id.high_score);
                        int highscore = Integer.parseInt(higher.getText().toString());
                        if (score > highscore)
                            higher.setText("" + score);
                        score = 0;
                        TextView points = (TextView) findViewById(R.id.score);
                        points.setText("Score : " + score);
                        root.setBackgroundColor(Color.RED);
                        saveData();
                        Toast.makeText(getApplicationContext(), "Wrong Answer\nCorrect Answer is " + correct, Toast.LENGTH_SHORT).show();
                        //Toast.makeText(getApplicationContext(), "Game Over", Toast.LENGTH_SHORT).show();
                        Vibrator vibe = (Vibrator) getSystemService(VIBRATOR_SERVICE);
                        vibe.vibrate(200);
                    }
                }
            }
        });

        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pressed == true) {
                    pressed = false;
                    int chosen = parseInt(option2.getText().toString());
                    if (chosen == correct) {
                        EditText number = (EditText) findViewById(R.id.number);
                        TextView timer = (TextView) findViewById(R.id.timer);
                        score++;
                        TextView points = (TextView) findViewById(R.id.score);
                        points.setText("Score : " + score);
                        clock = false;
                        watch.cancel();
                        number.setText("");
                        option1.setText("" + 0);
                        option2.setText("" + 0);
                        option3.setText("" + 0);
                        timer.setText("" + 10);
                        root.setBackgroundColor(Color.GREEN);
                        saveData();
                        Toast.makeText(getApplicationContext(), "Correct Answer ", Toast.LENGTH_SHORT).show();


                    } else {
                        EditText number = (EditText) findViewById(R.id.number);
                        TextView timer = (TextView) findViewById(R.id.timer);
                        clock = false;
                        watch.cancel();
                        number.setText("");
                        option1.setText("" + 0);
                        option2.setText("" + 0);
                        option3.setText("" + 0);
                        timer.setText("" + 10);
                        TextView higher = (TextView) findViewById(R.id.high_score);
                        int highscore = Integer.parseInt(higher.getText().toString());
                        if (score > highscore)
                            higher.setText("" + score);
                        score = 0;
                        TextView points = (TextView) findViewById(R.id.score);
                        points.setText("Score : " + score);
                        root.setBackgroundColor(Color.RED);
                        saveData();
                        Toast.makeText(getApplicationContext(), "Wrong Answer\nCorrect Answer is " + correct, Toast.LENGTH_SHORT).show();
                        //Toast.makeText(getApplicationContext(), "Game Over", Toast.LENGTH_SHORT).show();
                        Vibrator vibe = (Vibrator) getSystemService(VIBRATOR_SERVICE);
                        vibe.vibrate(200);

                    }
                }

            }
        });
        option3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (pressed == true) {
                    pressed = false;
                    int chosen = Integer.parseInt(option3.getText().toString());
                    if (chosen == correct) {
                        EditText number = (EditText) findViewById(R.id.number);
                        TextView timer = (TextView) findViewById(R.id.timer);
                        score++;
                        TextView points = (TextView) findViewById(R.id.score);
                        points.setText("Score : " + score);
                        clock = false;
                        watch.cancel();
                        number.setText("");
                        option1.setText("" + 0);
                        option2.setText("" + 0);
                        option3.setText("" + 0);
                        timer.setText("" + 10);
                        root.setBackgroundColor(Color.GREEN);
                        saveData();
                        Toast.makeText(getApplicationContext(), "Correct Answer ", Toast.LENGTH_SHORT).show();
                    } else {
                        EditText number = (EditText) findViewById(R.id.number);
                        TextView timer = (TextView) findViewById(R.id.timer);
                        clock = false;
                        watch.cancel();
                        number.setText("");
                        option1.setText("" + 0);
                        option2.setText("" + 0);
                        option3.setText("" + 0);
                        timer.setText("" + 10);
                        TextView higher = (TextView) findViewById(R.id.high_score);
                        int highscore = Integer.parseInt(higher.getText().toString());
                        if (score > highscore)
                            higher.setText("" + score);
                        score = 0;
                        TextView points = (TextView) findViewById(R.id.score);
                        points.setText("Score : " + score);
                        root.setBackgroundColor(Color.RED);
                        saveData();
                        Toast.makeText(getApplicationContext(), "Wrong Answer\nCorrect Answer is " + correct, Toast.LENGTH_SHORT).show();
                        //Toast.makeText(getApplicationContext(), "Game Over", Toast.LENGTH_SHORT).show();
                        Vibrator vibe = (Vibrator) getSystemService(VIBRATOR_SERVICE);
                        vibe.vibrate(200);
                    }
                }
            }
        });
        loadData();
        updateView();
        onStop();
    }

    public void saveData()
    {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.putInt(POINTS,score);
        TextView higher = (TextView) findViewById(R.id.high_score);
        editor.putInt(HIGH_SCORE,Integer.parseInt(higher.getText().toString()));
        TextView timer = (TextView) findViewById(R.id.timer);
        editor.putInt(TIMER,Integer.parseInt(timer.getText().toString()));
        View root = getWindow().getDecorView().getRootView();
        ColorDrawable backgroundColor =(ColorDrawable) root.getBackground();
        int colorId = backgroundColor.getColor();
        editor.putInt(COLOR,colorId);
        editor.putInt(NUMBERS,num);
        Button option1=(Button) findViewById(R.id.option_1);
        Button option2=(Button) findViewById(R.id.option_2);
        Button option3=(Button) findViewById(R.id.option_3);
        editor.putInt(OPTION_1,Integer.parseInt(option1.getText().toString()));
        editor.putInt(OPTION_2,Integer.parseInt(option2.getText().toString()));
        editor.putInt(OPTION_3,Integer.parseInt(option3.getText().toString()));
        editor.putInt(CORRECT,correct);
        editor.putInt(ANS,ans);
        editor.putBoolean(SUBMIT,pressed);
        editor.putBoolean(CLOCK,clock);
        editor.apply();


    }
    public void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        score= sharedPreferences.getInt(POINTS,0);
        num = sharedPreferences.getInt(NUMBERS,0);
        correct = sharedPreferences.getInt(CORRECT,0);
        ans = sharedPreferences.getInt(ANS,0);
        pressed= sharedPreferences.getBoolean(SUBMIT,false);
        clock = sharedPreferences.getBoolean(CLOCK,false);

    }
    public void updateView()
    {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        TextView timer = (TextView) findViewById(R.id.timer);
        timer.setText(""+sharedPreferences.getInt(TIMER,10));
        TextView points = (TextView) findViewById(R.id.score);
        points.setText("Score : "+score);
        TextView higher = (TextView) findViewById(R.id.high_score);
        higher.setText(""+sharedPreferences.getInt(HIGH_SCORE,0));
        EditText number = (EditText) findViewById(R.id.number);
        if(num!=0)
        number.setText(""+num);
        else
            number.setText("");
        Button option1=(Button) findViewById(R.id.option_1);
        Button option2=(Button) findViewById(R.id.option_2);
        Button option3=(Button) findViewById(R.id.option_3);
        option1.setText(""+sharedPreferences.getInt(OPTION_1,0));
        option2.setText(""+sharedPreferences.getInt(OPTION_2,0));
        option3.setText(""+sharedPreferences.getInt(OPTION_3,0));
        View root = getWindow().getDecorView().getRootView();
        root.setBackgroundColor(sharedPreferences.getInt(COLOR,Color.GRAY));


    }

    @Override
    protected void onStop() {
        super.onStop();
        clock=false;
        saveData();
    }
}
