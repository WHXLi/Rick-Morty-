package com.example.rickmorty.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.rickmorty.App
import com.example.rickmorty.ui.BackBtnClickListener
import com.example.rickmorty.R
import com.example.rickmorty.databinding.FragmentCharacterBinding
import com.example.rickmorty.mvp.model.Character
import com.example.rickmorty.network.LocationResponse
import com.example.rickmorty.mvp.presenter.FragmentCharacterPresenter
import com.example.rickmorty.mvp.view.FragmentCharacterView
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class FragmentCharacter : MvpAppCompatFragment(R.layout.fragment_character), FragmentCharacterView,
    BackBtnClickListener {
    companion object {
        private const val TAG = "character"
        fun instance(character: Character) = FragmentCharacter().apply {
            arguments = Bundle().apply {
                putParcelable(TAG, character)
            }
        }
    }

    private val binding: FragmentCharacterBinding by viewBinding()
    private var dialog: AlertDialog? = null
    private val presenter: FragmentCharacterPresenter by moxyPresenter {
        val character = arguments?.getParcelable<Character>(TAG) as Character
        FragmentCharacterPresenter(character).apply {
            App.instance.appComponent.inject(this)
        }
    }

    override fun loadData() {
        presenter.loadLocation()
    }

    @SuppressLint("SetTextI18n")
    override fun displayInfo(character: Character, location: LocationResponse) {
        Glide.with(this).load(character.image).fitCenter().into(binding.fragmentCharacterImage)
        binding.fragmentCharacterName.text = character.name
        setStatusColor(character)
        binding.fragmentCharacterStatus.text = "${character.status} - ${character.species}"
        binding.fragmentCharacterGenderBody.text = character.gender
        binding.fragmentCharacterOriginBody.text = character.origin.name
        binding.fragmentCharacterLocationBody.text = location.name
        binding.fragmentCharacterTypeBody.text = location.type
        binding.fragmentCharacterDimensionBody.text = location.dimension
        binding.fragmentCharacterEpisodesBody.text = character.episode[0].substringAfterLast("/")
    }

    override fun setBackBtnClickListener() {
        binding.fragmentCharacterBackBtn.setOnClickListener{
            presenter.backBtnClick()
        }
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

    private fun setStatusColor(character: Character) {
        if (character.status == "Alive") Glide.with(this).load(R.drawable.alive_icon)
            .into(binding.fragmentCharacterStatusCircle)
        else Glide.with(this).load(R.drawable.dead_icon)
            .into(binding.fragmentCharacterStatusCircle)
    }
}