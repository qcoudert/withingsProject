package com.qcoudert.withingsproject.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.qcoudert.withingsproject.R
import com.qcoudert.withingsproject.databinding.MainFragmentBinding
import com.qcoudert.withingsproject.pixabay.PixabayApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var binding: MainFragmentBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.launchQueryButton.setOnClickListener {
            lifecycleScope.launch(Dispatchers.Main) {
                try {
                    val hits =
                        PixabayApiClient.pixabayApiService.getHits(viewModel.queryText.value ?: "")
                    if (hits.isSuccessful) {
                        binding.firstURLTextView.text =
                            hits.body()?.hits?.get(0)?.largeImageURL ?: "Error"
                        hits.body()?.hits?.let { pixabayHits ->
                            Log.d("MainFragment", "Obtained ${pixabayHits.size} hits !")
                            (activity as? MainActivity)?.switchToDisplayResult(
                                pixabayHits
                            )
                        }
                    }
                } catch (e: Exception) {
                    binding.firstURLTextView.text = e.message
                }
            }
        }

        binding.queryEditText.doOnTextChanged { text, _, _, _ ->
            viewModel.queryText.postValue(
                text.toString()
            )
        }
        return binding.root
    }

}