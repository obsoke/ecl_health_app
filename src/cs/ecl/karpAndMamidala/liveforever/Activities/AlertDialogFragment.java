package cs.ecl.karpAndMamidala.liveforever.Activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

public class AlertDialogFragment extends DialogFragment {

    public interface AlertDialogNotifier {
        public void onDialogPositiveClick(DialogFragment dialog);
        public void onDialogNegativeClick(DialogFragment dialog);
    }

    AlertDialogNotifier mListener;

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
        return builder.create();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (AlertDialogNotifier) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement AlertDialogNotifier");
        }
    }
}