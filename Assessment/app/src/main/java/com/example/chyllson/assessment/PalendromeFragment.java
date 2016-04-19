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
import android.widget.Toast;


public class PalendromeFragment extends Fragment {

    public View onCreateView(LayoutInflater inflater,ViewGroup container,
                             Bundle savedInstanceState) {

        View palendromeInflater = inflater.inflate(R.layout.fragment_palendrome, container,false);

        final EditText answerText = (EditText) palendromeInflater.findViewById(R.id.editText_textPalendrome);
        Button submit = (Button) palendromeInflater.findViewById(R.id.button_submitPalendrome);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isPalendrome = isPalendrome(answerText.getText().toString());

                if(isPalendrome) {
                    Fragment anagramFragment;
                    anagramFragment = new AnagramFragment();

                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_switch, anagramFragment);
                    fragmentTransaction.commit();
                } else {
                    Toast.makeText(getActivity(), R.string.error_message_palendrome,
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        return palendromeInflater;


    }

    /**
     * Checks if the word is a palendrome
     * @param text
     * @return boolean if the word is palendrome or not
     */
    private boolean isPalendrome(String text) {
        if(text.length() == 1) {
            return true;
        } else if (text.length() == 0) {
            return false;
        } else if(text.charAt(0) == (text.charAt(text.length()-1))) {
            if(text.length() > 2) {
                return isPalendrome(text.substring(1,text.length()-1));
            } else {
                return isPalendrome(text.substring(1));
            }
        } else {
            return false;
        }
    }

}
