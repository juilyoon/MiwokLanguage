/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NumbersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        // Generate translations of number words
        // From dictionary at https://docs.google.com/document/d/16sQ-0r5zMrdRXUwOaYbTLNXADasuSYFpy7K0Bn8oVfg/pub?embedded=true
        final ArrayList<Word> numberList= new ArrayList<Word>();
        numberList.add(new Word("lutti", "one", R.raw.number_one, R.drawable.number_one));
        numberList.add(new Word("otiiko", "two", R.raw.number_two, R.drawable.number_two));
        numberList.add(new Word("tolookosu", "three", R.raw.number_three, R.drawable.number_three));
        numberList.add(new Word("oyyisa", "four", R.raw.number_four, R.drawable.number_four));
        numberList.add(new Word("massokka", "five", R.raw.number_five, R.drawable.number_five));
        numberList.add(new Word("temmokka", "six", R.raw.number_six, R.drawable.number_six));
        numberList.add(new Word("kenekaku", "seven", R.raw.number_seven, R.drawable.number_seven));
        numberList.add(new Word("kawinta", "eight", R.raw.number_eight, R.drawable.number_eight));
        numberList.add(new Word("wo'e", "nine", R.raw.number_nine, R.drawable.number_nine));
        numberList.add(new Word("na'aacha", "ten", R.raw.number_ten, R.drawable.number_ten));

        WordAdapter itemsAdapter = new WordAdapter(this, numberList);
        ListView list = (ListView) findViewById(R.id.list_view);
        list.setBackgroundColor(getResources().getColor(R.color.category_numbers));
        list.setAdapter(itemsAdapter);
        // Play audio file on click
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word = (Word) parent.getItemAtPosition(position);
                MediaPlayer.create(NumbersActivity.this, word.getSoundResourceId()).start();
            }
        });
    }
}
