package com.evgeniysharafan.changelanguage.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.evgeniysharafan.changelanguage.R;
import com.evgeniysharafan.changelanguage.model.mockdata.MockData;
import com.evgeniysharafan.changelanguage.ui.activity.SettingsActivity;
import com.evgeniysharafan.changelanguage.ui.adapter.ChatsAdapter;
import com.evgeniysharafan.changelanguage.ui.adapter.ChatsAdapter.OnChatClickListener;
import com.evgeniysharafan.utils.Toasts;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ChatsFragment extends Fragment implements OnChatClickListener {

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    public static ChatsFragment newInstance() {
        return new ChatsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chats, container, false);
        ButterKnife.bind(this, view);

        prepareActionBar();
        initUI();

        return view;
    }

    private void initUI() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new ChatsAdapter(MockData.getChats(100, 5, 20), this));
    }

    private void prepareActionBar() {
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(R.string.app_name);
            actionBar.setDisplayHomeAsUpEnabled(false);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_chats, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            SettingsActivity.launch(getActivity());
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onChatClick(int position) {
        Toasts.showShort("onChatClick");
    }

    @Override
    public void onChatIconClick(int position) {
        Toasts.showShort("onChatIconClick");
    }

}
