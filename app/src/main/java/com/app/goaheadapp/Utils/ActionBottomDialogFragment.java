package com.app.goaheadapp.Utils;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.app.goaheadapp.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class ActionBottomDialogFragment extends BottomSheetDialogFragment
    implements View.OnClickListener {

  public static final String TAG = "ActionBottomDialog";

  private ItemClickListener mListener;

  public static ActionBottomDialogFragment newInstance() {
    return new ActionBottomDialogFragment();
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                           @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.dialog_message, container, false);
  }

  @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    view.findViewById(R.id.ok).setOnClickListener(this);
    view.findViewById(R.id.cancel).setOnClickListener(this);
  }

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    if (context instanceof ItemClickListener) {
      mListener = (ItemClickListener) context;
    } else {
      throw new RuntimeException(context.toString()
          + " must implement ItemClickListener");
    }
  }

  @Override
  public void onDetach() {
    super.onDetach();
    mListener = null;
  }

  @Override public void onClick(View view) {
    String tvSelected ;
    switch (view.getId()){
      case R.id.ok:
        tvSelected = "ok";
        break;
      case R.id.cancel:
        tvSelected = "cancel";
        break;
      default:
        throw new IllegalStateException("Unexpected value: " + view.getId());
    }
    mListener.onItemClick(tvSelected);
    dismiss();
  }

  public interface ItemClickListener {
    void onItemClick(String item);
  }
}
