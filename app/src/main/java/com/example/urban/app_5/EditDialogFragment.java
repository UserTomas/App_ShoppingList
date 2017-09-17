package com.example.urban.app_5;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by urban on 17. 9. 2017.
 */

public class EditDialogFragment extends DialogFragment{

    public String itm;
    public interface EditDialogListener{
        public void onDialogPositiveClick(DialogFragment dialog, String teamName);
        public void onDialogNegativeClick(DialogFragment dialog);
    }

    // Use this instance of the interface to deliver action events
    EditDialogListener mListener;

    // Override the Fragment.onAttach() method to instantiate the EditDialogListener
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the EditDialogListener so we can send events to the host
            mListener = (EditDialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString() + " must implement EditDialogListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Bundle extras = getArguments();

        if(extras != null){
            itm = extras.getString("itemName");
        }
        // create a new AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        final View dialogView = inflater.inflate(R.layout.edit_item_dialog, null);
        TextView txtView = (TextView) dialogView.findViewById(R.id.editText);
        txtView.setText(itm);
        builder.setView(dialogView)

                // Set title
                .setTitle("Edit item")
                // Add action buttons
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // find a team name
                        EditText editText = (EditText) dialogView.findViewById(R.id.editText);
                        String itemName = editText.getText().toString();
                        // Send the positive button event back to the host activity
                        mListener.onDialogPositiveClick(EditDialogFragment.this,itemName);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Send the negative button event back to the host activity
                        mListener.onDialogNegativeClick(EditDialogFragment.this);
                    }
                });
        return builder.create();
    }


}
