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
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        // Generate translations of number words
        // From dictionary https://docs.google.com/document/d/16sQ-0r5zMrdRXUwOaYbTLNXADasuSYFpy7K0Bn8oVfg/pub?embedded=true
        ArrayList<Word> familyList= new ArrayList<Word>();
        familyList.add(new Word("әpә", "father", R.drawable.family_father));
        familyList.add(new Word("әṭa", "mother", R.drawable.family_mother));
        familyList.add(new Word("angsi", "son", R.drawable.family_son));
        familyList.add(new Word("tune", "daughter", R.drawable.family_daughter));
        familyList.add(new Word("taachi", "older brother", R.drawable.family_older_brother));
        familyList.add(new Word("chalitti", "younger brother", R.drawable.family_younger_brother));
        familyList.add(new Word("teṭe", "older sister", R.drawable.family_older_sister));
        familyList.add(new Word("kolliti", "younger sister", R.drawable.family_younger_sister));
        familyList.add(new Word("ama", "grandmother", R.drawable.family_grandmother));
        familyList.add(new Word("paapa", "grandfather", R.drawable.family_grandfather));

        WordAdapter itemsAdapter = new WordAdapter(this, familyList);
        ListView list = (ListView) findViewById(R.id.list_view);
        list.setAdapter(itemsAdapter);
    }
}
