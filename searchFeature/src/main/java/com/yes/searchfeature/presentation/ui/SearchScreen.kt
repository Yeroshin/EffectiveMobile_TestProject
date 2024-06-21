package com.yes.searchfeature.presentation.ui

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.yes.searchfeature.R
import com.yes.searchfeature.databinding.PopularItemBinding
import com.yes.searchfeature.databinding.SearchBinding
import com.yes.searchfeature.presentation.model.IUiPopular
import com.yes.searchfeature.presentation.model.UiPopular
import androidx.navigation.fragment.findNavController
import com.yes.sharedmodule.NavCommand

class SearchScreen(

):BottomSheetDialogFragment(

) {
     lateinit var binding: SearchBinding


  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)
        binding.departure.text=Editable.Factory.getInstance().newEditable(
            arguments?.getString("departure")
        )

      binding.bottomSheet.layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT

      val behavior = BottomSheetBehavior.from(binding.bottomSheet)
      behavior.apply {
          peekHeight = resources.displayMetrics.heightPixels
          state = BottomSheetBehavior.STATE_EXPANDED
          skipCollapsed = true

          addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
              override fun onStateChanged(bottomSheet: View, newState: Int) {
              }

              override fun onSlide(bottomSheet: View, slideOffset: Float) {}
          })
      }
  }
    override fun getTheme() = com.yes.sharedmodule.R.style.NoBackgroundDialogTheme
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = SearchBinding.inflate(inflater, container, false)
        binding.root.setBackgroundResource(R.drawable.rounded_shape)
        setUpView()
        return binding.root
    }

    private fun setUpView(){
        val items = listOf(
            UiPopular(
                R.drawable.stambul,
                "Стамбул"
            ),
            UiPopular(
                R.drawable.sochi,
                "Сочи"
            ),
            UiPopular(
                R.drawable.phucket,
                "Пхукет"
            ),
        )
        binding.searchPopular.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.searchPopular.adapter = adapter
        adapter.items=items
///////////////////////
        binding.difficult.setOnClickListener{
            (activity as NavCommand).navigate(
                "android-app://StubScreen"
            )

        }
        binding.world.setOnClickListener{
            setArrival(
                binding.worldDestination.text.toString()
            )
        }
        binding.calendar.setOnClickListener{
            (activity as NavCommand).navigate(
                "android-app://StubScreen"
            )

        }
        binding.fire.setOnClickListener{
            (activity as NavCommand).navigate(
                "android-app://StubScreen"
            )

        }
        binding .arrival.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                val inputText = binding.arrival.text.toString()
                val regex = Regex("[А-Яа-яЁё -]+")
                return@setOnEditorActionListener if (inputText.isNotBlank() && inputText.matches(regex)) {
                    binding.arrival.error =null
                    val arrival = binding.arrival.text
                    val departure = binding.departure.text
                    (activity as NavCommand).navigate(
                        "android-app://searchSelectedScreen/$departure/$arrival"
                    )

                    false
                } else {
                    binding.arrival.error = "Введите только русские буквы"
                    true
                }

            } else {
                true
            }
        }


    }
    private val adapter by lazy {
        ListDelegationAdapter(
            popularAdapterDelegate { item->
                setArrival(item) },

            )
    }
    private fun setArrival(item:String){
        binding.arrival.text= Editable.Factory.getInstance().newEditable(
            item
        )
    }
    private fun popularAdapterDelegate(itemClickedListener : (String) -> Unit) =
        adapterDelegateViewBinding<UiPopular, IUiPopular, PopularItemBinding>(
            { layoutInflater, root -> PopularItemBinding.inflate(layoutInflater, root, false) }
        ) {
            binding.popularItem.setOnClickListener {
                itemClickedListener(item.country)
            }
            bind {
                Glide
                    .with(context)
                    .load(item.image)
                    .fitCenter()
                    .into(binding.image)
                binding.popularTown.text = item.country
            }
        }
}