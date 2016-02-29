package com.evgeniysharafan.changelanguage.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.evgeniysharafan.changelanguage.R;
import com.evgeniysharafan.changelanguage.ui.fragment.ChatsFragment;
import com.evgeniysharafan.utils.Fragments;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ChatsActivity extends LanguageActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_fragment);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        if (savedInstanceState == null) {
            Fragments.replace(getSupportFragmentManager(), R.id.content, ChatsFragment.newInstance(), null);
        }
    }

}
