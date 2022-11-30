package com.example.freedictionary.util

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.example.freedictionary.R
import com.example.freedictionary.databinding.LayoutBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.gson.Gson
import retrofit2.HttpException

fun Fragment.initToolbar(
    toolbar: Toolbar,
    homeAsUpEnabled: Boolean = true,
    light: Boolean = false
) {
    val iconBack = if (light) R.drawable.ic_back_white else R.drawable.ic_back_blue

    (activity as AppCompatActivity).setSupportActionBar(toolbar)
    (activity as AppCompatActivity).title = ""
    (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(homeAsUpEnabled)
    (activity as AppCompatActivity).supportActionBar?.setHomeAsUpIndicator(iconBack)
    toolbar.setNavigationOnClickListener { activity?.onBackPressedDispatcher?.onBackPressed() }
}

inline fun <reified T> HttpException.getErrorResponse(): T? {
    return try {
        Gson().fromJson(response()?.errorBody()?.string(), T::class.java)
    } catch (e: Exception) {
        null
    }
}

fun Fragment.showBottomSheet(
    titleDialog: CharSequence? = null,
    titleButton: CharSequence? = null,
    message: CharSequence? = null,
    onClick: () -> Unit = {}
) {
    val bottomSheetDialog = BottomSheetDialog(requireContext(), R.style.BottomSheetDialog)
    bottomSheetDialog.setCancelable(false)
    val bottomSheetBinding: LayoutBottomSheetBinding =
        LayoutBottomSheetBinding.inflate(layoutInflater, null, false)

    bottomSheetBinding.textTitle.text = titleDialog ?: getString(R.string.text_title_bottom_sheet)
    bottomSheetBinding.textMessage.text = message ?: getString(R.string.error_generic)
    bottomSheetBinding.btnOk.text = titleButton ?: getString(R.string.text_button_bottom_sheet)

    bottomSheetBinding.btnOk.setOnClickListener {
        bottomSheetDialog.dismiss()
        onClick()
    }

    bottomSheetDialog.setContentView(bottomSheetBinding.root)
    bottomSheetDialog.show()
}