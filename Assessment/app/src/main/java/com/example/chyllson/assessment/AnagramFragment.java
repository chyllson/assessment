package com.example.chyllson.assessment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.chyllson.assessment.R.string.error_message_anagram;


public class AnagramFragment extends Fragment {

    public View onCreateView(LayoutInflater inflater,ViewGroup container,
                             Bundle savedInstanceState) {

        View anagramInflater = inflater.inflate(R.layout.fragment_anagram, container,false);

        final EditText firstText = (EditText) anagramInflater.findViewById(R.id.editText_firstText);
        final EditText secondText = (EditText) anagramInflater.findViewById(R.id.editText_secondText);
        Button submit = (Button) anagramInflater.findViewById(R.id.button_submitAnagram);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isAnagram = isAnagram(firstText.getText().toString(),
                        secondText.getText().toString());

                if(isAnagram) {
                    Intent intent = new Intent(getActivity().getApplicationContext(),SuccessfulActivity.class);
                    startActivity(intent);
                    getActivity().finish();

                } else {
                    AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                            .create();
                    alertDialog.setCancelable(true);
                    alertDialog.setMessage("The word is not a anagram. Please try again.");
                    alertDialog.show();
                }
            }
        });

        return anagramInflater;

    }

    /**
     * Checks if the word is an anagram
     * @param firstText
     * @param secondText
     * @return boolean if the word is anagram or not
     */
    private boolean isAnagram(String firstText, String secondText) {
        if(firstText.length() != secondText.length()) {
            return false;
        } else  {
            try {
                char[] firstTextArray = firstText.toLowerCase().toCharArray();
                StringBuilder secondTextArray = new StringBuilder(secondText.toLowerCase());
                for(Character character : firstTextArray) {
                    int idx = secondTextArray.indexOf(String.valueOf(character));
                    if (idx == -1) {
                        return false;
                    }
                    secondTextArray.delete(idx, idx + 1);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return true;
        }
    }
}
