package com.example.quizapp_codsoft;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.animation.Animator;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizapp_codsoft.Module.Question_modal;

import java.util.ArrayList;

public class QuestionActivity extends AppCompatActivity {

    ConstraintLayout option_container;

    TextView timer_text,total_question,question;
    Button option1,option2,option3,option4,next_btn;

    ArrayList<Question_modal> list = new ArrayList<>();

    private int count = 0;
    private int position = 0;
    private int score = 0;

    CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        timer_text = (TextView) findViewById(R.id.timer_text);
        total_question = (TextView) findViewById(R.id.total_question);
        question = (TextView) findViewById(R.id.question);


        option1 = (Button) findViewById(R.id.option1);
        option2 = (Button) findViewById(R.id.option2);
        option3 = (Button) findViewById(R.id.option3);
        option4 = (Button) findViewById(R.id.option4);
        next_btn = (Button) findViewById(R.id.next_btn);

        option_container = (ConstraintLayout) findViewById(R.id.option_container);

        resetTimer();
        timer.start();

        String setName = getIntent().getStringExtra("set");
        if(setName.equals("Set-1")){
            setOne();
        } else if (setName.equals("Set-2")) {
            setTwo();
        }

        for(int i = 0; i < 4; i++){
            option_container.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    checkAnswer((Button) v);
                }
            });
        }

        playAnimation(question,0,list.get(position).getQuestion());


        next_btn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                if(timer != null){
                    timer.cancel();
                }
                timer.start();

                next_btn.setEnabled(false);
                next_btn.setAlpha((float)0.3);

                enableOption(true);
                position++;

                if(position == list.size()){
                    Intent intent = new Intent(QuestionActivity.this, ScoreActivity.class);
                    intent.putExtra("score",score);
                    intent.putExtra("total",list.size());
                    startActivity(intent);
                    finish();
                   return;
                }

                count = 0;
                playAnimation(question,0,list.get(position).getQuestion());
            }
        });

    }

    private void resetTimer() {
        timer = new CountDownTimer(30000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                timer_text.setText(String.valueOf(millisUntilFinished/1000));

            }

            @Override
            public void onFinish() {

                Dialog dialog = new Dialog(QuestionActivity.this);
                dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.timeout_dialog);
                dialog.findViewById(R.id.try_again_btn).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(QuestionActivity.this, SetsActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
                dialog.show();


            }
        };
    }

    private void playAnimation(View view, int value, String data ) {

        view.animate().alpha(value).scaleX(value).scaleY(value).setDuration(500).setStartDelay(100).setInterpolator(new DecelerateInterpolator()).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(@NonNull Animator animation) {

                if(value == 0 && count < 4){
                    String option = "";
                    if(count == 0){
                        option = list.get(position).getOption1();
                    } else if (count == 1) {
                        option = list.get(position).getOption2();
                    }
                    else if (count == 2) {
                        option = list.get(position).getOption3();
                    }
                    else if (count == 3) {
                        option = list.get(position).getOption4();
                    }

                    playAnimation(option_container.getChildAt(count),0,option);
                    count++;

                }
            }

            @Override
            public void onAnimationEnd(@NonNull Animator animation) {

                if(value == 0){
                  try{
                      ((TextView) view).setText(data);
                      total_question.setText(position+1+"/"+ list.size());
                  } catch (Exception e){
                      ((Button) view).setText(data);
                  }

                  view.setTag(data);
                  playAnimation(view,1,data);
                }
            }

            @Override
            public void onAnimationCancel(@NonNull Animator animation) {

            }

            @Override
            public void onAnimationRepeat(@NonNull Animator animation) {

            }
        });
    }

    private void enableOption(boolean enable) {

        for(int i = 0; i < 4; i++){
            option_container.getChildAt(i).setEnabled(enable);

            if(enable){

                option_container.getChildAt(i).setBackgroundResource(R.drawable.btn_option);
            }
        }


    }

    private void checkAnswer(Button selected_option) {

        if(timer != null){
            timer.cancel();
        }
        next_btn.setEnabled(true);
        next_btn.setAlpha(1);

        if(selected_option.getText().toString().equals(list.get(position).getCorrectAnswer())){
            score++;
            selected_option.setBackgroundResource(R.drawable.right_answer);
        }
        else{
            selected_option.setBackgroundResource(R.drawable.wrong_answer);
            Button correctAnswer = (Button) option_container.findViewWithTag(list.get(position).getCorrectAnswer());
            correctAnswer.setBackgroundResource(R.drawable.right_answer);
        }
    }

    private void setTwo() {

        list.add(new Question_modal("(1) From the options given below, the pair having different expressive power is","(A) Deterministic Push Down Automata (DPDA) and Non-deterministic Push Down Automata (NPDA)","(B) Deterministic Finite Automata (DFA) and Non-deterministic Finite Automata(NFA)","(C) Single tape turning machine and multi tape turning machine.","(D) Deterministic single tape turning machine and Non-Deterministic single tape turning machine","(A) Deterministic Push Down Automata (DPDA) and Non-deterministic Push Down Automata (NPDA)"));

        list.add(new Question_modal("(2) The problem that is undecidable -","(A) Finiteness problem for FSA’s","(B) Membership problem for CFG’s","(C) Equivalence problem for FSA’s","(D) Ambiguity problem for CFG’s","(D) Ambiguity problem for CFG’s"));

        list.add(new Question_modal("(3) The language which is generated by the grammar S-> aSa I bSb I a I b over the alphabet {a, b} is the set of","(A) Strings that begin and end with the same symbol","(B) All odd and even length palindromes","(C) All odd length palindromes","(D) All even length palindromes","(C) All odd length palindromes"));
        list.add(new Question_modal("(4) Two persons X and Y have been asked to show that a certain problem p is NP-complete. X shows a polynomial time reduction from the 3-SAT problem to p and Y shows a polynomial time reduction from p to 3-SAT. From these reduction it can be inferred that","(A) π is NP-complete","(B) π is NP-hard but not NP-complete","(C) π is in NP but not NP-complete","(D) π is neither NP-hard nor in NP","(A) π is NP-complete"));

        list.add(new Question_modal("(5) 3-SAT and 2-SAT problems are","(A) NP-complete and in P respectively","(B) Undecidable and NP-complete","(C) Both NP-complete","(D) Both in P","(A) NP-complete and in P respectively"));


    }

    private void setOne() {

        list.add(new Question_modal("(1) Out of the three problems S, Q and R, S is an NP-complete problem and Q and R are the two other problems not known to be in NP. Which one of the following statements is true if Q is polynomial time reducible to S and S is the polynomial time reducible to R?","(A) Q is NP-complete","(B) R is NP-complete","(C) Q is NP-hard","(D) R is NP-hard","(B) R is NP-complete"));
        list.add(new Question_modal("(2) From the options given below the statement, which is not necessarily true if X1 is the recursive language and X2 and X3 are the languages that is recursively enumerable but not recursive is","(A) X2 ∩ X1 is recursively enumerable","(B) X2 ∪ X1 is recursively enumerable","(C) X2 – X1 is recursively enumerable","(D) X1 – X3 is recursively enumerable","(C) X2 – X1 is recursively enumerable"));
        list.add(new Question_modal("(3) For the language {ap I P is a prime}, the statement which hold true is","(A) It is not regular but context free","(B) It is regular but not context free","(C) It is neither regular nor context free, but accepted by a turing machine","(D) It is not accepted by turing machine","(C) It is neither regular nor context free, but accepted by a turing machine"));
        list.add(new Question_modal("(4) The statement that holds true is","(A) Infinite union of finite sets is regular","(B) The union of two non-regular set is not regular","(C) Every finite subset of a non-regular set is regular","(D) Every subset of a regular set is regular","(C) Every finite subset of a non-regular set is regular"));
        list.add(new Question_modal("(5) The language described by the regular expression (0+1)*0(0+1)*0(0+1)* over the alphabet {0 1} is the set of","(A) All strings containing at least two 1’s","(B) All strings containing at least two 0’s","(C) All strings that begin and end with either 0’s or 1’s","(D) All strings containing the substring 00","(B) All strings containing at least two 0’s"));


    }

}