package edu.cinec.healthwellness

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import edu.cinec.healthwellness.databinding.FragmentBmiBinding
import java.util.*

class BmiFragment : Fragment() {

    private var _binding: FragmentBmiBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBmiBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateGreeting()

        binding.profileImage.setOnClickListener {
            showInfoDialog(
                "👤 User Profile",
                "Name: Cinec Health User\nHeight: 175 cm\nWeight: 68.5 kg\nTarget BMI: 22.0 (Healthy Normal)"
            )
        }

        binding.notificationButton.setOnClickListener {
            showInfoDialog(
                "🔔 BMI & Health Reminders",
                "• Monthly Weight Log: Scheduled for 1st of every month\n• Weekly Fitness Goal Check: Active"
            )
        }

        binding.resultCard.setOnClickListener {
            showInfoDialog(
                "📊 BMI Reference Ranges",
                "• Underweight: < 18.5\n• Normal: 18.5 – 24.9\n• Overweight: 25.0 – 29.9\n• Obese: ≥ 30.0"
            )
        }

        binding.btnCalculate.setOnClickListener {
            calculateBmi()
        }

        // Setup toggles
        binding.heightToggle.addOnButtonCheckedListener { _, checkedId, isChecked ->
            if (isChecked) {
                when (checkedId) {
                    R.id.btn_cm -> binding.heightInputLayout.suffixText = getString(R.string.cm)
                    R.id.btn_ft -> binding.heightInputLayout.suffixText = getString(R.string.ft)
                }
            }
        }

        binding.weightToggle.addOnButtonCheckedListener { _, checkedId, isChecked ->
            if (isChecked) {
                when (checkedId) {
                    R.id.btn_kg -> binding.weightInputLayout.suffixText = getString(R.string.kg)
                    R.id.btn_lbs -> binding.weightInputLayout.suffixText = getString(R.string.lbs)
                }
            }
        }
    }

    private fun showInfoDialog(title: String, message: String) {
        com.google.android.material.dialog.MaterialAlertDialogBuilder(requireContext())
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("OK", null)
            .show()
    }

    private fun calculateBmi() {
        val heightStr = binding.etHeight.text.toString()
        val weightStr = binding.etWeight.text.toString()

        if (heightStr.isNotEmpty() && weightStr.isNotEmpty()) {
            var height = heightStr.toFloat()
            var weight = weightStr.toFloat()

            // Convert to metric if needed
            if (binding.heightToggle.checkedButtonId == R.id.btn_ft) {
                height *= 30.48f // ft to cm
            }
            if (binding.weightToggle.checkedButtonId == R.id.btn_lbs) {
                weight *= 0.453592f // lbs to kg
            }

            height /= 100 // cm to meters
            val bmi = weight / (height * height)
            displayResult(bmi)
        } else {
            Toast.makeText(context, "Please enter both height and weight", Toast.LENGTH_SHORT).show()
        }
    }

    private fun displayResult(bmi: Float) {
        binding.tvBmiValue.text = String.format(Locale.getDefault(), "%.1f", bmi)
        
        val category: String
        val color: Int
        val advice: String
        
        when {
            bmi < 18.5 -> {
                category = "● Underweight"
                color = android.graphics.Color.BLUE
                advice = "You are in the underweight range. Consider consulting with a nutritionist."
            }
            bmi < 25 -> {
                category = "● Normal"
                color = resources.getColor(R.color.primary_green, null)
                advice = getString(R.string.bmi_advice_normal)
            }
            bmi < 30 -> {
                category = "● Overweight"
                color = android.graphics.Color.parseColor("#FDE047") // Yellow
                advice = "You are in the overweight range. Regular exercise and a balanced diet can help."
            }
            else -> {
                category = "● Obese"
                color = android.graphics.Color.RED
                advice = "You are in the obese range. It's recommended to consult with a healthcare provider."
            }
        }
        
        binding.tvBmiCategory.text = category
        binding.tvBmiCategory.setTextColor(color)
        binding.tvBmiAdvice.text = advice
        binding.bmiGauge.setBmi(bmi)
        
        // Show result card
        binding.resultCard.visibility = View.VISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun updateGreeting() {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val greeting = when (hour) {
            in 0..11 -> "Good Morning,"
            in 12..16 -> "Good Afternoon,"
            else -> "Good Evening,"
        }
        binding.greetingText.text = greeting
    }
}