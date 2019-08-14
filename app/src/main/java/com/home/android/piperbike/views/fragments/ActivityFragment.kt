package com.home.android.piperbike.views.fragments

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.home.android.piperbike.R
import com.home.android.piperbike.databinding.FragmentActivityListBinding
import com.home.android.piperbike.ui.activity.ActivityListViewModel

class ActivityFragment : Fragment() {

    private lateinit var viewModel: ActivityListViewModel
    private lateinit var binding: FragmentActivityListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_activity_list, container, false)

        viewModel = ViewModelProviders.of(this).get(ActivityListViewModel::class.java)
        binding.activityListViewModel = viewModel

        return binding.root
    }
}
