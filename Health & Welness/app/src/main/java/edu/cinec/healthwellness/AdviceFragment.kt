package edu.cinec.healthwellness

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import edu.cinec.healthwellness.databinding.FragmentAdviceBinding
import java.util.*

class AdviceFragment : Fragment() {

    private var _binding: FragmentAdviceBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAdviceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.profileContainer.setOnClickListener {
            showInfoDialog(
                "👤 Lifestyle & Health Profile",
                "Sleep Target: 8.0 hrs\nStress Level: Low / Balanced\nMorning Routine: 10 mins Active"
            )
        }

        binding.notificationButton.setOnClickListener {
            showInfoDialog(
                "🔔 Health & Lifestyle Alerts",
                "• Sleep Reminder: 10:30 PM\n• Mindful Breathing Break: 3:00 PM\n• Evening Stretch: 8:00 PM"
            )
        }

        // Expand/Collapse Sleep Hygiene Card
        binding.sleepCard.setOnClickListener {
            if (binding.sleepDetails.visibility == View.VISIBLE) {
                binding.sleepDetails.visibility = View.GONE
            } else {
                binding.sleepDetails.visibility = View.VISIBLE
            }
        }

        binding.btnSleepReminder.setOnClickListener {
            com.google.android.material.snackbar.Snackbar.make(
                binding.root,
                "⏰ Sleep Reminder successfully set for 10:30 PM!",
                com.google.android.material.snackbar.Snackbar.LENGTH_LONG
            ).show()
        }

        // Stress Relief Card
        binding.stressCard.setOnClickListener {
            showInfoDialog(
                "🧘 6 Stress Relief Techniques",
                "1. 4-7-8 Deep Breathing (Inhale 4s, Hold 7s, Exhale 8s)\n2. Progressive Muscle Relaxation\n3. 10-Minute Mindful Walk in Nature\n4. Digital Detox (1 hour phone-free)\n5. Warm Herbal Chamomile Tea\n6. Journaling Thoughts before bed"
            )
        }

        // Daily Habits Card
        binding.habitsCard.setOnClickListener {
            showInfoDialog(
                "✨ 8 Healthy Habits to Build",
                "1. Drink 500ml water right after waking up\n2. Get 15 mins morning sunlight\n3. Walk 8,000 to 10,000 steps daily\n4. Eat whole, unprocessed foods\n5. Stretch for 10 mins daily\n6. Limit screen time before bed\n7. Maintain consistent sleep schedule\n8. Practice daily gratitude"
            )
        }

        // Focus Card & Start Routine Button
        val startRoutineAction = View.OnClickListener {
            com.google.android.material.dialog.MaterialAlertDialogBuilder(requireContext())
                .setTitle("🌅 Morning Wellness Routine Started!")
                .setMessage("Step 1: Sit comfortably & close your eyes.\nStep 2: Take 5 deep breaths.\nStep 3: Gentle neck and shoulder stretches for 3 minutes.\nStep 4: Drink a glass of fresh water.\n\nGreat job starting your morning right!")
                .setPositiveButton("Complete Routine 🎯") { _, _ ->
                    com.google.android.material.snackbar.Snackbar.make(
                        binding.root,
                        "🎉 Morning Routine Completed! +50 Health Points",
                        com.google.android.material.snackbar.Snackbar.LENGTH_LONG
                    ).show()
                }
                .setNegativeButton("Cancel", null)
                .show()
        }

        binding.focusCard.setOnClickListener(startRoutineAction)
        binding.btnStart.setOnClickListener(startRoutineAction)
    }

    private fun showInfoDialog(title: String, message: String) {
        com.google.android.material.dialog.MaterialAlertDialogBuilder(requireContext())
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("OK", null)
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}