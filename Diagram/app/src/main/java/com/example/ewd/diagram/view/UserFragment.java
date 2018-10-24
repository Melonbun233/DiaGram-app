package com.example.ewd.diagram.view;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ewd.diagram.R;
import com.example.ewd.diagram.model.local.User;

/**
 * A simple {@link Fragment} subclass.

 * Use the {@link UserFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserFragment extends Fragment {

    // The fragment initialization parameters
    private static final String USER_ID = "USER_ID";
    private static final String SESSION_KEY = "SESSION_KEY";

    private String userId;
    private String sessionKey;

    public UserFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param userId Parameter 1.
     * @param sessionKey Parameter 2.
     * @return A new instance of fragment UserFragment.
     */
    public static UserFragment newInstance(String userId, String sessionKey) {
        UserFragment fragment = new UserFragment();
        Bundle args = new Bundle();
        args.putString(USER_ID, userId);
        args.putString(SESSION_KEY, sessionKey);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            userId = getArguments().getString(USER_ID);
            sessionKey = getArguments().getString(SESSION_KEY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user, container, false);
    }

}
