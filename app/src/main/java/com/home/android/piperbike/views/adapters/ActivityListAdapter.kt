package com.home.android.piperbike.views.adapters

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.home.android.piperbike.R
import com.home.android.piperbike.data.model.DtoGetActivityResponse
import com.home.android.piperbike.databinding.ActivityItemCellBinding
import com.home.android.piperbike.ui.activity.ActivityViewModel

class ActivityListAdapter : RecyclerView.Adapter<ActivityListAdapter.ActivityViewHolder>() {

    private lateinit var activities: List<DtoGetActivityResponse>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivityViewHolder {
        val binding : ActivityItemCellBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.activity_item_cell, parent, false)
        return ActivityViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return if (::activities.isInitialized) activities.size else 0
    }

    override fun onBindViewHolder(holder: ActivityViewHolder, position: Int) = holder.bind(activities[position])

    fun updateActivityList(activities: List<DtoGetActivityResponse>) {
        this.activities = activities
        notifyDataSetChanged()
    }

    class ActivityViewHolder(private var binding: ActivityItemCellBinding): RecyclerView.ViewHolder(binding.root) {
        private var viewModel = ActivityViewModel()

        fun bind(activity: DtoGetActivityResponse) {
            viewModel.bind(activity)
            binding.activityViewModel = viewModel
        }
    }
}