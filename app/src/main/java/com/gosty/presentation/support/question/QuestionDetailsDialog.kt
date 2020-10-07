package com.gosty.presentation.support.question

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.gosty.R
import com.gosty.extensions.inflate
import kotlinx.android.synthetic.main.question_details_dialog.*

class QuestionDetailsDialog : BottomSheetDialogFragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.question_details_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val params = QuestionDetailsDialogArgs.fromBundle(requireArguments()).params
        questionDetailsTitle.text = params.question.title
        questionDetailsDescription.text = params.question.description
        questionDetailsDescriptionImage.setImageResource(params.descriptionImage)
    }


}