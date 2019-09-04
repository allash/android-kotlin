package com.home.android.piperbike.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.home.android.piperbike.R
import com.home.android.piperbike.databinding.FragmentActivityListBinding
import com.home.android.piperbike.di.inject
import com.home.android.piperbike.ui.activity.ActivityListViewModel
import javax.inject.Inject

class ActivityFragment : Fragment() {

    @Inject
    lateinit var viewModel: ActivityListViewModel

    lateinit var binding: FragmentActivityListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_activity_list, container, false)

        binding.activityListViewModel = viewModel

        return binding.root
    }
}
