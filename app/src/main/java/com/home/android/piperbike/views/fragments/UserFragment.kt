package com.home.android.piperbike.views.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.home.android.piperbike.R
import com.home.android.piperbike.data.activities.model.DtoGetWeeklyGoalResponse
import kotlinx.android.synthetic.main.fragment_user.textRemainingDays
import kotlinx.android.synthetic.main.fragment_user.textWeeklyGoal
import kotlinx.android.synthetic.main.fragment_user.viewWeeklyGoalProgress

class UserFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val goal = getWeeklyGoal()
        textWeeklyGoal.text = goal.title
        viewWeeklyGoalProgress.calculateProgress(goal.estimated, goal.completed)
        textRemainingDays.text = "Remaining days: ${goal.remainingDays}"
    }

    private fun getWeeklyGoal(): DtoGetWeeklyGoalResponse =
        DtoGetWeeklyGoalResponse(
            title = "Run 40km in a week",
            estimated = 40,
            completed = 35f,
            remainingDays = 2
        )
}
