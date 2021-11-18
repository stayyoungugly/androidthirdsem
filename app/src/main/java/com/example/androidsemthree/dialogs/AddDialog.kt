package com.example.androidsemthree.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.example.androidsemthree.R
import com.example.androidsemthree.databinding.DialogAddBinding
import com.google.android.material.snackbar.Snackbar

class AddDialog : DialogFragment() {
    private lateinit var binding: DialogAddBinding

    var positiveCallBack: ((Array<String>) -> Unit)? = null
    var positiveNotFullCallBack: ((Boolean) -> Unit)? = null

    override fun onCreateDialog(
        savedInstanceState: Bundle?
    ): Dialog = AlertDialog.Builder(requireContext())
        .setView(DialogAddBinding.inflate(layoutInflater).let {
            binding = it
            it.root
        })
        .setTitle("Add Album")
        .setMessage("Please, add all your data")
        .setPositiveButton("OK") { _, _ ->
            var flag = false;
            val title = binding.etTitle.text?.toString() ?: ""
            val desc = binding.etDescription.text?.toString() ?: ""
            val pos = binding.etPosition.text?.toString() ?: ""
            if (title.isNotEmpty()) {
                if (desc.isNotEmpty()) {
                    flag = true
                    val array = arrayOf(title, desc, pos)
                    positiveCallBack?.invoke(array)
                }
            }
            if (!flag) {
                positiveNotFullCallBack?.invoke(flag)
            }
        }
        .setNegativeButton("Cancel") { dialog, _ ->
            dialog.dismiss()
        }.create()

    companion object {
        fun show(
            fragmentManager: FragmentManager,
            positive: (Array<String>) -> Unit,
            notFull: (Boolean) -> Unit
        ) {
            AddDialog().apply {
                positiveNotFullCallBack = notFull
                positiveCallBack = positive
                show(fragmentManager, AddDialog::class.java.name)
            }
        }
    }
}

