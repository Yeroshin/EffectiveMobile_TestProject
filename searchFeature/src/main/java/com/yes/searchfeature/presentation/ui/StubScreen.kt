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
import com.yes.searchfeature.databinding.StubBinding

class StubScreen : Fragment() {

    private lateinit var binding: StubBinding



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = StubBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.searchToolbar.inflateMenu(R.menu.back_menu)
        binding.searchToolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_back -> {
                    findNavController().popBackStack()
                    true
                }
                else -> false
            }
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_back -> {
                findNavController().popBackStack()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }


}