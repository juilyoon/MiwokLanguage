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

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class ColorsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        // Generate translations of number words
        // From dictionary at https://docs.google.com/document/d/16sQ-0r5zMrdRXUwOaYbTLNXADasuSYFpy7K0Bn8oVfg/pub?embedded=true
        ArrayList<Word> colourList= new ArrayList<Word>();
        colourList.add(new Word("weṭeṭṭi", "red", R.drawable.color_red));
        colourList.add(new Word("chokokki", "green", R.drawable.color_green));
        colourList.add(new Word("ṭakaakki", "brown", R.drawable.color_brown));
        colourList.add(new Word("ṭopoppi", "gray", R.drawable.color_gray));
        colourList.add(new Word("kululli", "black", R.drawable.color_black));
        colourList.add(new Word("kelelli", "white", R.drawable.color_white));
        colourList.add(new Word("ṭopiisә", "dusty yellow", R.drawable.color_dusty_yellow));
        colourList.add(new Word("chiwiiṭә", "mustard yellow", R.drawable.color_mustard_yellow));

        WordAdapter itemsAdapter = new WordAdapter(this, colourList);
        ListView list = (ListView) findViewById(R.id.list_view);
        list.setBackgroundColor(getResources().getColor(R.color.category_colors));
        list.setAdapter(itemsAdapter);
    }
}
