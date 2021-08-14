package com.qcoudert.withingsproject.displayResult

import androidx.fragment.app.Fragment
import com.qcoudert.withingsproject.databinding.DisplayResultFragmentBinding

class DisplayResultFragment: Fragment() {

    companion object {
        fun newInstance(): DisplayResultFragment = DisplayResultFragment()
    }

    private lateinit var viewModel: DisplayResultViewModel
    private lateinit var binding:DisplayResultFragmentBinding

}