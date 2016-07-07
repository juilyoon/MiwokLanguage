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
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorsFragment extends Fragment {

    MediaPlayer mediaPlayer;
    MediaPlayer.OnCompletionListener completionListener = new MediaPlayer.OnCompletionListener() {
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
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.word_list, null);

        // Generate translations of number words
        // From dictionary at https://docs.google.com/document/d/16sQ-0r5zMrdRXUwOaYbTLNXADasuSYFpy7K0Bn8oVfg/pub?embedded=true
        final ArrayList<Word> colourList= new ArrayList<Word>();
        colourList.add(new Word("weṭeṭṭi", "red", R.raw.color_red, R.drawable.color_red));
        colourList.add(new Word("chokokki", "green", R.raw.color_green, R.drawable.color_green));
        colourList.add(new Word("ṭakaakki", "brown", R.raw.color_brown, R.drawable.color_brown));
        colourList.add(new Word("ṭopoppi", "gray", R.raw.color_gray, R.drawable.color_gray));
        colourList.add(new Word("kululli", "black", R.raw.color_black, R.drawable.color_black));
        colourList.add(new Word("kelelli", "white", R.raw.color_white, R.drawable.color_white));
        colourList.add(new Word("ṭopiisә", "dusty yellow", R.raw.color_dusty_yellow, R.drawable.color_dusty_yellow));
        colourList.add(new Word("chiwiiṭә", "mustard yellow", R.raw.color_mustard_yellow, R.drawable.color_mustard_yellow));

        WordAdapter itemsAdapter = new WordAdapter(getActivity(), colourList);
        ListView list = (ListView) view.findViewById(R.id.list_view);
        list.setBackgroundColor(getResources().getColor(R.color.category_colors));
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
