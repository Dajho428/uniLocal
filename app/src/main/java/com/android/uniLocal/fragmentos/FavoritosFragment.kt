package com.android.uniLocal.fragmentos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.uniLocal.R
import com.android.uniLocal.databinding.FragmentFavoritosBinding


class FavoritosFragment : Fragment() {
    lateinit var binding: FragmentFavoritosBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoritosBinding.inflate(inflater, container, false)

        return binding.root
    }
}