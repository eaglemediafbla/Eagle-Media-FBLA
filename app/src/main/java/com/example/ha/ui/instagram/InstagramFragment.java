package com.example.ha.ui.instagram;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ha.R;
import com.example.ha.databinding.FragmentInstagramBinding;


public class InstagramFragment extends Fragment {
    private FragmentInstagramBinding binding;
    ListView post;
    int[] posts = {R.drawable.eagle_post_3, R.drawable.insta_post_4, R.drawable.eagle_post_2, R.drawable.eagle_post_1, R.drawable.insta_post_5};
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        InstagramViewModel instagramViewModel =
                new ViewModelProvider(this).get(InstagramViewModel.class);
        binding = FragmentInstagramBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        //Item on the layout
        post = root.findViewById(R.id.postsView);
        //Other class object
        CustomAdapter customAdapter = new CustomAdapter();
        post.setAdapter(customAdapter);
        final TextView textView = binding.textInstagram;
        instagramViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    //New class to store images in an adapter
    class CustomAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return posts.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = getLayoutInflater().inflate(R.layout.instgrampostsview, null);
            ImageView images = (ImageView) view.findViewById(R.id.postI);

            images.setImageResource(posts[position]);
            return view;
        }
    }
}