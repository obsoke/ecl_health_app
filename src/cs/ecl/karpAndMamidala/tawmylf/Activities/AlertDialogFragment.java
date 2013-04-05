package cs.ecl.karpAndMamidala.tawmylf.Activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import cs.ecl.karpAndMamidala.tawmylf.R;

public class AlertDialogFragment extends DialogFragment {

    /* The activity that creates an instance of this dialog fragment must
     * implement this interface in order to receive event callbacks.
     * Each method passes the DialogFragment in case the host needs to query it. */
    public interface NoticeDialogListener {
        public void onDialogPositiveClick(DialogFragment dialog);
        public void onDialogNegativeClick(DialogFragment dialog);
    }

    // Use this instance of the interface to deliver action events
    NoticeDialogListener mListener;

    private int dialogId;
    private int yesId;
    private int noId;

    public AlertDialogFragment(int dia, int yes, int no) {
        super();
        this.dialogId = dia;
        this.yesId = yes;
        this.noId = no;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(this.dialogId)
                .setPositiveButton(this.yesId, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // handled by interface
                        mListener.onDialogPositiveClick(AlertDialogFragment.this);
                    }
                })
                .setNegativeButton(this.noId, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // handled by interface
                        mListener.onDialogNegativeClick(AlertDialogFragment.this);
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }

    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (NoticeDialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement NoticeDialogListener");
        }
    }
}