package com.example.rickmorty.ui.fragment

import android.annotation.SuppressLint
import android.app.Dialog
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rickmorty.App
import com.example.rickmorty.R
import com.example.rickmorty.databinding.FragmentCharactersBinding
import com.example.rickmorty.mvp.presenter.FragmentCharactersPresenter
import com.example.rickmorty.mvp.view.FragmentCharactersView
import com.example.rickmorty.ui.rv_adapter.CharactersAdapter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class FragmentCharacters : MvpAppCompatFragment(R.layout.fragment_characters),
    FragmentCharactersView {
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
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun updateRv() {
        adapter?.notifyDataSetChanged()
    }

    override fun showLoadingProgress() {
        requireContext().let {
            dialog = AlertDialog.Builder(it)
                .setMessage("Загрузка...")
                .create()
            dialog?.show()
        }
    }

    override fun hideLoadingProgress() {
        dialog?.hide()
    }


}