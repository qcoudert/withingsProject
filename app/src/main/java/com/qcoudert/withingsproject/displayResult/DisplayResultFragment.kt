package com.qcoudert.withingsproject.displayResult

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.qcoudert.withingsproject.R
import com.qcoudert.withingsproject.databinding.DisplayResultFragmentBinding
import com.qcoudert.withingsproject.pixabay.PixabayHit

class DisplayResultFragment(private val pixabayHits: List<PixabayHit>) : Fragment() {

    companion object {
        fun newInstance(pixabayHits: List<PixabayHit>): DisplayResultFragment = DisplayResultFragment(pixabayHits)
    }

    private lateinit var viewModel: DisplayResultViewModel
    private lateinit var binding: DisplayResultFragmentBinding
    private lateinit var layoutManager: GridLayoutManager
    private lateinit var adapter: PixabayHitsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.display_result_fragment, container, false)
        viewModel = ViewModelProvider(this).get(DisplayResultViewModel::class.java)
        return binding.also {
            it.viewModel = viewModel
            it.lifecycleOwner = viewLifecycleOwner
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("DisplayResult", "Displaying ${pixabayHits.size} hits !")
        layoutManager = GridLayoutManager(context, 2)
        adapter = PixabayHitsAdapter(pixabayHits)
        adapter.isItemSelected.observe(viewLifecycleOwner) {
            if (it) {
                binding.acceptSelectionButton.visibility = View.VISIBLE
            } else {
                binding.acceptSelectionButton.visibility = View.GONE
            }
        }
        binding.resultRecyclerView.layoutManager = layoutManager
        binding.resultRecyclerView.adapter = adapter

        binding.acceptSelectionButton.setOnClickListener {

        }
    }
}