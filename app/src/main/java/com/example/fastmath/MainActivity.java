package com.example.fastmath;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Random;


public class MainActivity extends AppCompatActivity {
    final int stack_size=10;

    int digit;
    int counter=1;
    int sum=0;
    int flag=0,flag_opp=0;
    TextView n;
    Button answer,next;
    LinearLayout add,add_sub,div,multi,speed_spiner,repeat_spiner;
    Random rn ;
    Spinner speed,repeat,digits;
    int spd =1000;
    int rep =10;
    int dig=1;
    int firstInt,secondInt;

    String []speed_v={"500","1000","1500","2000","2500","3000"};
    String []repeat_v={"5","10","15","20","25","30"};
    String []digits_v={"1","2","3"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       intiateData();
       add.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               flag_opp=0;
               div.setBackgroundResource(R.drawable.title);
               add_sub.setBackgroundResource(R.drawable.title);
               add.setBackgroundResource(R.drawable.selected_title);
               multi.setBackgroundResource(R.drawable.title);
               speed_spiner.setVisibility(View.VISIBLE);
               repeat_spiner.setVisibility(View.VISIBLE);
           }
       });
       add_sub.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               flag_opp=1;
               div.setBackgroundResource(R.drawable.title);
               add_sub.setBackgroundResource(R.drawable.selected_title);
               add.setBackgroundResource(R.drawable.title);
               multi.setBackgroundResource(R.drawable.title);
               speed_spiner.setVisibility(View.VISIBLE);
               repeat_spiner.setVisibility(View.VISIBLE);
           }
       });
        div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag_opp=3;
                div.setBackgroundResource(R.drawable.selected_title);
                add_sub.setBackgroundResource(R.drawable.title);
                add.setBackgroundResource(R.drawable.title);
                multi.setBackgroundResource(R.drawable.title);
                speed_spiner.setVisibility(View.GONE);
                repeat_spiner.setVisibility(View.GONE);

            }
        });
        multi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag_opp=2;
                multi.setBackgroundResource(R.drawable.selected_title);
                add_sub.setBackgroundResource(R.drawable.title);
                add.setBackgroundResource(R.drawable.title);
                div.setBackgroundResource(R.drawable.title);
                speed_spiner.setVisibility(View.GONE);
                repeat_spiner.setVisibility(View.GONE);
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spd=Integer.parseInt(speed.getSelectedItem().toString());
                rep=Integer.parseInt(repeat.getSelectedItem().toString());
                dig=Integer.parseInt(digits.getSelectedItem().toString());
                operation(rep,spd,dig,flag_opp);

            }
        });

            answer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    n.setTextSize(150);
                    n.setText(String.valueOf(sum));

                }
            });







    }
  

    void operation(int repeat, int speed,int d,int f) {
      sum=0;
      counter=1;

      if(f==0) {
          addation(repeat,speed,d);
         /* CountDownTimer timer = new CountDownTimer(repeat * speed, speed) {

              public void onTick(long millisUntilFinished) {

                 // digit = rn.nextInt((int) Math.pow(10, d)) + 1;
                  digit=createDigits(d);
                  n.setTextSize(150);
                  n.setText(String.valueOf(digit));
                  sum = sum + digit;
                  counter++;
              }

              public void onFinish() {
                  n.setText("?");
                  answer.setOnClickListener(new View.OnClickListener() {
                      @Override
                      public void onClick(View v) {
                          n.setTextSize(150);
                          n.setText(String.valueOf(sum));

                      }
                  });


              }
          };

          timer.start();

          */
      }


         if(f==1){
             subtract(repeat,speed,d);
            /*  CountDownTimer time = new CountDownTimer(repeat * speed, speed) {

                  public void onTick(long millisUntilFinished) {
                      do {
                          digit=createDigits(d);
                         // digit = rn.nextInt((int) Math.pow(10, d)) + 1;
                          if (counter % 2 == 0) {
                              digit = createDigits(d) * -1;
                          }
                      } while ((sum + digit) <= 0);
                      n.setTextSize(150);
                      n.setText(String.valueOf(digit));
                      sum = sum + digit;
                      counter++;

                  }

                  public void onFinish() {
                      n.setText("?");
                      answer.setOnClickListener(new View.OnClickListener() {
                          @Override
                          public void onClick(View v) {
                              n.setTextSize(150);
                              n.setText(String.valueOf(sum));
                              sum = 0;
                          }
                      });


                  }
              };

              time.start();

             */

      }
        if(f==3) {
            //division
            do{
             firstInt=createDigits(1);
             secondInt=createDigits(d);
            sum=secondInt/firstInt;
            }while(secondInt % firstInt!=0||firstInt==1||firstInt==secondInt);
            String formula=String.valueOf(secondInt)+"/"+String.valueOf(firstInt);
            n.setTextSize(80);
            n.setText(formula);

        }
        if(f==2) {
            //multiply
             firstInt=createDigits(1);
            secondInt=createDigits(d);
            String formula=String.valueOf(firstInt)+"X"+String.valueOf(secondInt);
            n.setTextSize(80);
            n.setText(formula);
            sum=firstInt*secondInt;
            answer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    n.setText(String.valueOf(sum));
                    sum = 0;
                }
            });

        }

    }
    void intiateSpiner(Spinner spinner, String[] arr){

        ArrayAdapter dayAdapter=new ArrayAdapter(this,R.layout.spinnew_items,arr);
        dayAdapter.setDropDownViewResource(R.layout.spinnew_items);

        spinner.setAdapter(dayAdapter);
       // spinner.setSelection(1);
    }
int createDigits(int f){
    int x=rn.nextInt(9)+1;
    int y=rn.nextInt(9)+1;
    int z=rn.nextInt(9)+1;
    int m=0;
    if(f==1){
        m=x;
    }
    else if (f==2){
        m=(y*10)+x;
    }
    else{
        m=(z*100)+(y*10)+x;
    }
    return m;
}
void addation(int r1 , int s1,int d1 ){
        counter=1;
    final Handler handler1 = new Handler();


    final Runnable runnable1 = new Runnable() {
        public void run() {
            // need to do tasks on the UI thread
            digit=createDigits(d1);
            n.setTextSize(150);
            n.setText(String.valueOf(digit));
            sum = sum + digit;
            if (counter++ < r1) {
                handler1.postDelayed(this, s1);
            }
        }
    };

// trigger first time
    handler1.post(runnable1);

    n.setText("?");


}
    void subtract(int r2 , int s2,int d2 ) {
        counter=1;
        final Handler handler2 = new Handler();


        final Runnable runnable2 = new Runnable() {
            public void run() {
                // need to do tasks on the UI thread

                    do {
                        digit = createDigits(d2);
                        if(counter==1&&digit==1){
                            digit=8;
                        }

                        // digit = rn.nextInt((int) Math.pow(10, d)) + 1;
                        if (counter % 2 == 0) {
                            digit = digit * -1;
                        }
                    } while ((sum + digit) <= 0);

                    n.setTextSize(150);
                    n.setText(String.valueOf(digit));
                    sum = sum + digit;

                    if (counter++ < r2) {
                        handler2.postDelayed(this, s2);
                    }
            }
        };

// trigger first time
        handler2.post(runnable2);
        n.setText("?");



    }
void intiateData(){

    n=findViewById(R.id.n);
    answer=findViewById(R.id.answer);
    next=findViewById(R.id.next);
    speed=findViewById(R.id.speed);
    repeat=findViewById(R.id.repeat);
    digits=findViewById(R.id.digits);
    add=findViewById(R.id.add);
    add_sub=findViewById(R.id.add_sub);
    div=findViewById(R.id.div);
    multi=findViewById(R.id.multi);
    speed_spiner=findViewById(R.id.apeed_spiner);
    repeat_spiner=findViewById(R.id.repeat_spiner);
    intiateSpiner(speed,speed_v);
    intiateSpiner(repeat,repeat_v);
    intiateSpiner(digits,digits_v);
    spd=Integer.parseInt(speed.getSelectedItem().toString());
    rep=Integer.parseInt(repeat.getSelectedItem().toString());
    dig=Integer.parseInt(digits.getSelectedItem().toString());
    rn = new Random();

}
}
