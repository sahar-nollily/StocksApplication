package com.saharnollily.stocksapplication.utils

import android.app.AlertDialog
import android.content.Context
import com.saharnollily.stocksapplication.R

object CreateConfirmationDialog {

    fun showAlert(context: Context, isConfirmed : (Boolean) -> Unit ){
        val alertDialog = AlertDialog.Builder(context)
        alertDialog.setTitle(context.getString(R.string.confirm_message))

        alertDialog.setPositiveButton(context.getString(R.string.common_yes)) { dialog, which ->
            isConfirmed(true)
        }

        alertDialog.setNegativeButton(context.getString(R.string.common_no)) { dialog, which ->
            isConfirmed(false)
            dialog?.dismiss() }

        alertDialog.show()
    }
}