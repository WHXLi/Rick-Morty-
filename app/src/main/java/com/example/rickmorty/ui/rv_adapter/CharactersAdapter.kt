package com.example.rickmorty.ui.rv_adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rickmorty.databinding.ItemCharactersBinding
import com.example.rickmorty.framework.entity.Character
import com.example.rickmorty.mvp.presenter.FragmentCharactersPresenter.CharactersPresenter
import com.example.rickmorty.mvp.view.rv.ICharactersView

class CharactersAdapter(private val presenter: CharactersPresenter) :
    RecyclerView.Adapter<CharactersAdapter.ViewHolder>() {
    inner class ViewHolder(private val binding: ItemCharactersBinding) :
        RecyclerView.ViewHolder(binding.root), ICharactersView {
        @SuppressLint("SetTextI18n")
        override fun setCharacter(character: Character) {
            Glide.with(itemView).load(character.image).optionalFitCenter().into(binding.itemCharactersImage)
            binding.itemCharactersTitle.text = character.name
            binding.itemCharactersStatus.text = "${character.status} - ${character.species}"
            binding.itemCharactersBodyLocation.text = character.location.name
            binding.itemCharactersBodyOrigin.text = character.origin.name
        }


        override var pos = -1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            ItemCharactersBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        ).apply {
            itemView.setOnClickListener { presenter.itemClickListener?.invoke(this) }
        }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        presenter.bindView(holder.apply { pos = position })
    }

    override fun getItemCount() = presenter.getCount()
}