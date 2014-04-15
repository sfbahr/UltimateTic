package cs2114.ultimatetic;

import android.app.Activity;
import android.content.DialogInterface;
import android.app.AlertDialog;
import android.os.Bundle;
import android.app.Dialog;
import android.app.DialogFragment;

// -------------------------------------------------------------------------
/**
 * Write a one-sentence summary of your class here. Follow it with additional
 * details about its purpose, what abstraction it represents, and how to use it.
 *
 * @author Me
 * @version Apr 14, 2014
 */

public class RestartGameDialogFragment
    extends DialogFragment
{

    /*
     * The activity that creates an instance of this dialog fragment must  *
     * implement this interface in order to receive event callbacks.    * Each
     * method passes the DialogFragment in case the host needs to query it.
     */
    public interface RestartGameDialogListener
    {
        public void onDialogPositiveClick(DialogFragment dialog);


        public void onDialogNegativeClick(DialogFragment dialog);
    }

    // Use this instance of the interface to deliver action events
    RestartGameDialogListener mListener;


    // Override the Fragment.onAttach() method to instantiate the
// RestartGameDialogListener
    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try
        {
            // Instantiate the RestartGameDialogListener so we can send events
// to the host
            mListener = (RestartGameDialogListener)activity;
        }
        catch (ClassCastException e)
        {
            throw new ClassCastException(activity.toString()
                + " must implement RestartGameDialogListener");
        }
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder
            .setMessage(R.string.dialog_restart_game)
            .setPositiveButton(
                R.string.restart,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id)
                    {
                        mListener
                            .onDialogPositiveClick(
                                RestartGameDialogFragment.this);
                    }
                })
            .setNegativeButton(
                R.string.cancel,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id)
                    {
                        mListener
                            .onDialogNegativeClick(
                                RestartGameDialogFragment.this);
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
