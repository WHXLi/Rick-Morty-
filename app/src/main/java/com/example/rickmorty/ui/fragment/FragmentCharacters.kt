package com.example.rickmorty.ui.fragment

import android.app.Dialog
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rickmorty.App
import com.example.rickmorty.ui.BackBtnClickListener
import com.example.rickmorty.R
import com.example.rickmorty.databinding.FragmentCharactersBinding
import com.example.rickmorty.mvp.presenter.FragmentCharactersPresenter
import com.example.rickmorty.mvp.view.FragmentCharactersView
import com.example.rickmorty.mvp.adapter.CharactersAdapter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class FragmentCharacters : MvpAppCompatFragment(R.layout.fragment_characters),
    FragmentCharactersView, BackBtnClickListener {
    private val binding: FragmentCharactersBinding by viewBinding()
    private lateinit var adapter: CharactersAdapter
    private var layoutManager: LinearLayoutManager? = null
    private lateinit var dialog: Dialog
    private val presenter by moxyPresenter {
        FragmentCharactersPresenter().apply {
            App.instance.appComponent.inject(this)
        }
    }

    companion object {
        fun instance() = FragmentCharacters()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (layoutManager != null) presenter.charactersPresenter.currentPosition =
            layoutManager!!.findFirstVisibleItemPosition()
    }

    override fun loadCharacters() {
        presenter.charactersPresenter.loadData()
    }

    override fun initRv() {
        adapter = CharactersAdapter(presenter.charactersPresenter)
        layoutManager = LinearLayoutManager(context).apply {
            scrollToPosition(presenter.charactersPresenter.currentPosition)
        }
        binding.charactersRv.adapter = adapter
        binding.charactersRv.layoutManager = layoutManager
    }


    override fun updateRv() {
        adapter.notifyItemRangeChanged(
            presenter.charactersPresenter.startPosition,
            presenter.charactersPresenter.lastPosition
        )
    }

    override fun setItemClickListener() {
        presenter.characterClick()
    }

    override fun showLoadingProgress() {
        requireContext().let {
            dialog = AlertDialog.Builder(it, R.style.transparent_dialog)
                .setCancelable(false)
                .setView(R.layout.dialog_fragment_loading)
                .create()
            dialog.show()
        }
    }

    override fun hideLoadingProgress() {
        dialog.dismiss()
    }

    override fun backPressed(): Boolean = presenter.backClick()

}