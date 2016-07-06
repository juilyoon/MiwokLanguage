package com.example.android.miwok;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by juil on 16-07-04.
 */
public class WordAdapter extends ArrayAdapter<Word> {
    public WordAdapter(Activity context, ArrayList<Word> words) {
        super(context, 0, words);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item_2text_image, parent, false);
        }

        // Get the {@link Word} object located at this position in the list
        Word currentWord = getItem(position);

        // Find the ImageView in the list_item_2text_image.xml layout with the ID item_imageview
        ImageView imageView = (ImageView) listItemView.findViewById(R.id.item_imageview);
        imageView.setImageResource(currentWord.getImageResourceId());

        // Find the TextView in the list_item_2text.xml layout with the ID miwok_textview
        TextView miwokTextView = (TextView) listItemView.findViewById(R.id.miwok_textview);
        // Get the Miwok translation and set this to the text view
        miwokTextView.setText(currentWord.getMiwokTranslation());

        // find the TextView in the list_item_2text.xml layout with the ID default_textview
        TextView defaultTextView = (TextView) listItemView.findViewById(R.id.default_textview);
        // Get the default translation and set this to the text view
        defaultTextView.setText(currentWord.getDefaultTranslation());

        // Return the whole list item layout (containing 2 TextViews) to be shown in the ListView
        return listItemView;
    }
}
