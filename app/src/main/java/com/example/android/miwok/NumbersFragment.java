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

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v4.app.Fragment;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class NumbersFragment extends Fragment {

    private MediaPlayer mediaPlayer;
    private MediaPlayer.OnCompletionListener completionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };
    AudioManager audioManager;
    AudioManager.OnAudioFocusChangeListener afChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                public void onAudioFocusChange(int focusChange) {
                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                            focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                        mediaPlayer.pause();
                        mediaPlayer.seekTo(0);
                    }
                    else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                        mediaPlayer.stop();
                        releaseMediaPlayer();
                    }
                    else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                        mediaPlayer.start();
                    }
                }
            };

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.word_list, container, false);

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

        WordAdapter itemsAdapter = new WordAdapter(getActivity(), numberList);
        ListView list = (ListView) view.findViewById(R.id.list_view);
        list.setBackgroundColor(getResources().getColor(R.color.category_numbers));
        list.setAdapter(itemsAdapter);
        // Play audio file on click
        audioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word = (Word) parent.getItemAtPosition(position);
                int result = audioManager.requestAudioFocus(afChangeListener,
                        AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    releaseMediaPlayer();
                    mediaPlayer = MediaPlayer.create(getActivity(), word.getSoundResourceId());
                    mediaPlayer.start();
                    mediaPlayer.setOnCompletionListener(completionListener);
                }
            }
        });

        return view;
    }

    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mediaPlayer = null;

            // Abandon audio focus
            audioManager.abandonAudioFocus(afChangeListener);
        }

    }

    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }
}
