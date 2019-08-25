package com.home.android.piperbike.di.module

import androidx.lifecycle.ViewModelProviders
import com.home.android.piperbike.data.activities.ActivityRepository
import com.home.android.piperbike.ui.activity.ActivityListViewModel
import com.home.android.piperbike.ui.activity.ActivityListViewModelFactory
import com.home.android.piperbike.views.fragments.ActivityFragment
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val fragment: ActivityFragment) {
    @Provides
    fun provideActivityListViewModel(
        factory: ActivityListViewModelFactory
    ): ActivityListViewModel {
        return ViewModelProviders.of(fragment, factory).get(ActivityListViewModel::class.java)
    }

    @Provides
    fun provideActivityListViewModelFactory(activityRepository: ActivityRepository): ActivityListViewModelFactory {
        return ActivityListViewModelFactory(activityRepository)
    }
}