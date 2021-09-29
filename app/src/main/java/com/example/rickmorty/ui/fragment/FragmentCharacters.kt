package com.example.rickmorty.ui.fragment

import android.annotation.SuppressLint
import android.app.Dialog
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rickmorty.App
import com.example.rickmorty.ui.BackBtnClickListener
import com.example.rickmorty.R
import com.example.rickmorty.databinding.FragmentCharactersBinding
import com.example.rickmorty.mvp.presenter.FragmentCharactersPresenter
import com.example.rickmorty.mvp.view.FragmentCharactersView
import com.example.rickmorty.ui.rv_adapter.CharactersAdapter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class FragmentCharacters : MvpAppCompatFragment(R.layout.fragment_characters),
    FragmentCharactersView, BackBtnClickListener {
    private val binding: FragmentCharactersBinding by viewBinding()
    private var adapter: CharactersAdapter? = null
    private var dialog: Dialog? = null
    private val presenter by moxyPresenter {
        FragmentCharactersPresenter().apply {
            App.instance.appComponent.inject(this)
        }
    }

    companion object {
        fun instance() = FragmentCharacters()
    }

    override fun loadCharacters() {
        presenter.loadData()
    }

    override fun initRv() {
        binding.charactersRv.layoutManager = LinearLayoutManager(context)
        adapter = CharactersAdapter(presenter.charactersPresenter)
        binding.charactersRv.adapter = adapter
        (binding.charactersRv.layoutManager as LinearLayoutManager).scrollToPosition(presenter.charactersPresenter.currentPosition)
        binding.charactersRv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) {
                    val nextPage = presenter.charactersPresenter.info?.next?.substringAfterLast("=")
                    presenter.loadData(nextPage)
                }
            }
        })
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun updateRv() {
        adapter?.notifyDataSetChanged()
    }

    override fun showLoadingProgress() {
        requireContext().let {
            dialog = AlertDialog.Builder(it, R.style.transparent_dialog)
                .setCancelable(false)
                .setView(R.layout.dialog_fragment_loading)
                .create()
            dialog?.show()
        }
    }

    override fun hideLoadingProgress() {
        dialog?.dismiss()
    }

    override fun backPressed(): Boolean = presenter.backClick()

}