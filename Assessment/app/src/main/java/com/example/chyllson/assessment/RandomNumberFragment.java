package com.example.chyllson.assessment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class RandomNumberFragment extends Fragment {
    public View onCreateView(LayoutInflater inflater,ViewGroup container,
                             Bundle savedInstanceState) {

        View randomNumberInflater = inflater.inflate(R.layout.fragment_random_number, container,false);

        final TextView firstRandomNumber = (TextView) randomNumberInflater.findViewById(R.id.textView_firstRandomNumber);
        final TextView secondRandomNumber = (TextView) randomNumberInflater.findViewById(R.id.textView_secondRandomNumber);
        final EditText answerNumber = (EditText) randomNumberInflater.findViewById(R.id.editText_answer);
        Button submit = (Button) randomNumberInflater.findViewById(R.id.button_submit);
        final TextView errorMessage = (TextView) randomNumberInflater.findViewById(R.id.textView_errorMessage);

        firstRandomNumber.setText(getRandomNumber());
        secondRandomNumber.setText(getRandomNumber());

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isAnswerValidated = validateAnswer(firstRandomNumber.getText().toString(),
                        secondRandomNumber.getText().toString(), answerNumber.getText().toString());

                if(isAnswerValidated) {
                    errorMessage.setVisibility(View.GONE);
                    Fragment palendromeFragment;
                    palendromeFragment = new PalendromeFragment();

                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_switch, palendromeFragment);
                    fragmentTransaction.commit();
                } else {
                    errorMessage.setVisibility(View.VISIBLE);
                }
            }
        });

        return randomNumberInflater;


    }

    /**
     * Generates random number from 1 - 100.
     * @return String of the random number generated
     */
    private String getRandomNumber() {
        Random random = new Random();
        int randomNumber = random.nextInt(100) + 1;
        return String.valueOf(randomNumber);
    }

    /**
     * Validates answer.
     * @param firstRandomNumber
     * @param secondRandomNumber
     * @param answer
     * @return answer valid is true or not
     */
    private boolean validateAnswer(String firstRandomNumber, String secondRandomNumber, String answer) {
        int firstNum = Integer.valueOf(firstRandomNumber);
        int secondNum = Integer.valueOf(secondRandomNumber);
        int answerNum = Integer.valueOf(answer);
        int sum = firstNum + secondNum;

        if (sum == answerNum) {
            return true;
        } else {
            return false;
        }
    }
}
