package com.app.goaheadapp.fragment.notelist;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.goaheadapp.R;
import com.app.goaheadapp.adapters.ChatListAdapter;
import com.app.goaheadapp.databinding.FragmentNoteBinding;
import com.app.goaheadapp.models.NoteListResponse;
import com.app.goaheadapp.models.MessageList;

import java.util.ArrayList;
import java.util.List;

public class NoteFragment extends Fragment {


    private NoteListViewModel viewModel;
    FragmentNoteBinding binding;
    ChatListAdapter adapter;
    List<MessageList> MessageLists;

    public NoteFragment() {
        // Required empty public constructor
    }

//    // TODO: Rename and change types and number of parameters
//    public static NoteFragment newInstance(String param1, String param2) {
//        NoteFragment fragment = new NoteFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MessageLists = new ArrayList<>();
        viewModel = new ViewModelProvider(this).get(NoteListViewModel.class);
        adapter = new ChatListAdapter(getContext(), MessageLists);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_note, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        binding.setMymodel(viewModel);
        initView(getView());

        viewModel.getNoteList(getContext());

        viewModel.postsMutableLiveData.removeObservers(getViewLifecycleOwner());
        viewModel.postsMutableLiveData.observe(getViewLifecycleOwner(), new Observer<NoteListResponse>() {
            @Override
            public void onChanged(NoteListResponse noteListResponse) {
                if (noteListResponse.isStatus()){
                    adapter.addAll(noteListResponse.getItems());
                }
            }
        });
    }

    private void initView(View view) {
        binding.resc.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        binding.resc.setAdapter(adapter);
    }
}