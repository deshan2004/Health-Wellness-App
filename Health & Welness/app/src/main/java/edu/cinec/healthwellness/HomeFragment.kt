package edu.cinec.healthwellness

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import edu.cinec.healthwellness.databinding.FragmentHomeBinding
import java.text.SimpleDateFormat
import java.util.*

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateDateTime()

        val bottomNav = requireActivity().findViewById<BottomNavigationView>(R.id.bottom_nav)

        // Profile & Header Clicks
        binding.profileImage.setOnClickListener {
            showInfoDialog(
                "👤 User Profile",
                "Name: Cinec Health User\nGoal: 10,000 steps / day\nActive Streak: 7 Days 🔥\nStatus: Premium Member"
            )
        }

        binding.notificationButton.setOnClickListener {
            showInfoDialog(
                "🔔 Active Reminders",
                "1. Water Reminder (Every 2 Hours) - ACTIVE\n2. Evening Walk Reminder (6:00 PM) - ACTIVE\n3. Sleep Reminder (10:30 PM) - ACTIVE"
            )
        }

        binding.overviewCard.setOnClickListener {
            showInfoDialog(
                "📊 Today's Wellness Summary",
                "• Steps: 8,240 / 10,000 steps (82% complete)\n• Water: 6 / 8 cups\n• Sleep: 7.5 hrs (Good quality)\n• Active Calories: 420 kcal"
            )
        }

        // Overview Cards navigation
        binding.stepsCard.setOnClickListener {
            showInfoDialog(
                "👟 Step Tracking Details",
                "Today: 8,240 steps\nDistance: 6.2 km\nCalories Burned: 340 kcal\nGoal: 10,000 steps"
            )
            bottomNav.selectedItemId = R.id.nav_tips // Steps -> Exercise/Tips
        }

        binding.waterCard.setOnClickListener {
            showInfoDialog(
                "💧 Hydration Tracker",
                "Consumed: 6 / 8 cups (1.5 L)\nRemaining: 2 cups (500 ml)\nNext reminder in 45 minutes!"
            )
            bottomNav.selectedItemId = R.id.nav_nutrition // Water -> Nutrition
        }

        binding.sleepCard.setOnClickListener {
            showInfoDialog(
                "🌙 Sleep Tracking Summary",
                "Duration: 7.5 hours\nDeep Sleep: 2h 15m\nREM Sleep: 1h 45m\nQuality Score: 88% (Great)"
            )
            bottomNav.selectedItemId = R.id.nav_advice // Sleep -> Advice
        }

        // Recent Activity Chips
        binding.chipMorningRun.setOnClickListener {
            showInfoDialog(
                "🏃 Morning Run Details",
                "Distance: 5.2 km\nTime: 28 min 14 sec\nPace: 5'25\" /km\nCalories: 310 kcal"
            )
        }

        binding.chipYogaSession.setOnClickListener {
            showInfoDialog(
                "🧘 Yoga Session Details",
                "Duration: 20 minutes\nType: Vinyasa Flow\nFocus: Flexibility & Breathing\nCalories: 95 kcal"
            )
        }

        // Quick Access Cards navigation
        binding.bmiCard.setOnClickListener {
            bottomNav.selectedItemId = R.id.nav_bmi
        }

        binding.tipsCard.setOnClickListener {
            bottomNav.selectedItemId = R.id.nav_tips
        }

        binding.nutritionCard.setOnClickListener {
            bottomNav.selectedItemId = R.id.nav_nutrition
        }

        binding.adviceCard.setOnClickListener {
            bottomNav.selectedItemId = R.id.nav_advice
        }
    }

    private fun showInfoDialog(title: String, message: String) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("OK", null)
            .show()
    }

    private fun updateDateTime() {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val greeting = when (hour) {
            in 0..11 -> "Good Morning"
            in 12..16 -> "Good Afternoon"
            else -> "Good Evening"
        }
        binding.greetingText.text = greeting

        val dateFormat = SimpleDateFormat("EEEE, d MMMM yyyy", Locale.getDefault())
        binding.dateText.text = dateFormat.format(calendar.time)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}