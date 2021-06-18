package com.app.goaheadapp.fragment.notes;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.app.goaheadapp.models.SubCatResponse;

public class NoteViewModel extends ViewModel {
    MutableLiveData<SubCatResponse> postsMutableLiveData = new MutableLiveData<>();
}
