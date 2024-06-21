package com.yes.searchfeature.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.yes.searchfeature.R
import com.yes.searchfeature.databinding.StubsBinding

class StubScreen : Fragment() {
    private lateinit var binding: StubsBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = StubsBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.searchToolbar.inflateMenu(R.menu.back_menu)
        binding.searchToolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_back -> {
                    findNavController().navigateUp()
                    true
                }
                else -> false
            }
        }
    }
}