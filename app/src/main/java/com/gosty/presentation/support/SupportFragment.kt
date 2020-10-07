@file:Suppress("UNCHECKED_CAST")

package com.gosty.presentation.support

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.gosty.R
import com.gosty.di.ServiceLocator
import com.gosty.extensions.createFactory
import com.gosty.extensions.inflate
import com.gosty.presentation.support.question.QuestionDetailsDialog
import com.gosty.presentation.support.question.QuestionDetailsDialogArgs
import com.gosty.presentation.support.question.QuestionDetailsDialogParams
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.support_fragment.*
import kotlinx.android.synthetic.main.support_header_layout.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class SupportFragment : Fragment(R.layout.support_fragment) {

    private val viewModel by viewModels<SupportViewModel> {
        createFactory { ServiceLocator.supportViewModel }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val questionsAdapter = QuestionsAdapter {
            findNavController().navigate(
                R.id.questionDetailsDialog,
                QuestionDetailsDialogArgs(
                    QuestionDetailsDialogParams(it, R.drawable.asset_ip3_open_door_1)
                ).toBundle()
            )
        }

        supportRecycler.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = ConcatAdapter(TopAdapter(), questionsAdapter)
        }

        viewModel.state.onEach {
            questionsAdapter.submitList(it.questions)
            it.throwable?.let { throwable ->
                Snackbar.make(
                    requireView(),
                    throwable.localizedMessage ?: throwable.toString(), //fixme add error handler
                    Snackbar.LENGTH_LONG
                ).show()
                viewModel.consumeError()
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private inner class TopAdapter : RecyclerView.Adapter<TopAdapter.TopHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopHolder {
            return TopHolder(parent.inflate(R.layout.support_header_layout))
        }

        override fun onBindViewHolder(holder: TopHolder, position: Int) {
            holder.supportEmailImage.setOnClickListener { viewModel.onEmailClicked() }
            holder.supportEmailTitle.setOnClickListener { viewModel.onEmailClicked() }
            holder.supportSocialWhatsUp.setOnClickListener { viewModel.onWhatsUpClicked() }
            holder.supportSocialTelegram.setOnClickListener { viewModel.onTelegramClicked() }
            holder.supportSocialViber.setOnClickListener { viewModel.onViberClicked() }
        }

        override fun getItemCount(): Int = 1

        inner class TopHolder(override val containerView: View) :
            RecyclerView.ViewHolder(containerView), LayoutContainer
    }
}