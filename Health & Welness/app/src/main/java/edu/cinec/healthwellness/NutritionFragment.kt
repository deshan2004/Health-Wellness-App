package edu.cinec.healthwellness

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import edu.cinec.healthwellness.databinding.FragmentNutritionBinding
import java.util.*

class NutritionFragment : Fragment() {

    private var _binding: FragmentNutritionBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNutritionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.profileImage.setOnClickListener {
            showInfoDialog(
                "👤 Nutrition Profile",
                "Daily Calorie Target: 2,200 kcal\nCurrent Consumed: 1,840 kcal\nDiet Preference: Balanced Whole Foods"
            )
        }

        binding.notificationButton.setOnClickListener {
            showInfoDialog(
                "🔔 Nutrition Reminders",
                "• Breakfast Log: 8:00 AM\n• Lunch Log: 1:00 PM\n• Dinner Log: 7:30 PM\n• Daily Hydration Check: ACTIVE"
            )
        }

        binding.tipCard.setOnClickListener {
            showInfoDialog(
                "🌈 Eat the Rainbow 🌈",
                "Consuming a variety of colorful fruits and vegetables ensures a wide range of essential vitamins, minerals, and antioxidants.\n\n• Red/Purple: Heart & Brain Health\n• Green: Detox & Immunity\n• Yellow/Orange: Eye & Skin Health"
            )
        }

        // Macros Clicks
        binding.cardProtein.setOnClickListener {
            showInfoDialog(
                "🥩 Protein Progress",
                "Progress: 128g / 180g (71% of daily goal)\n\nRecommended Sources:\n• Chicken Breast (31g per 100g)\n• Salmon (20g per 100g)\n• Eggs (13g per 2 eggs)\n• Greek Yogurt & Tofu"
            )
        }

        binding.cardCarbs.setOnClickListener {
            showInfoDialog(
                "🌾 Carbohydrate Progress",
                "Progress: 240g / 300g (80% of daily goal)\n\nRecommended Sources:\n• Oats & Whole Grains\n• Sweet Potatoes & Brown Rice\n• Fruits & Berries"
            )
        }

        binding.cardFats.setOnClickListener {
            showInfoDialog(
                "🥑 Healthy Fats Progress",
                "Progress: 62g / 70g (88% of daily goal)\n\nRecommended Sources:\n• Avocados (15g fat)\n• Extra Virgin Olive Oil\n• Almonds & Walnuts\n• Seeds & Fatty Fish"
            )
        }

        binding.btnSeeAll.setOnClickListener {
            showInfoDialog(
                "🥗 All Healthy Food Picks",
                "1. Avocado - Healthy Fats (160 kcal)\n2. Salmon - Omega-3 Rich (208 kcal)\n3. Broccoli - Fiber Rich (55 kcal)\n4. Blueberries - Antioxidants (84 kcal)\n5. Eggs - High Protein (155 kcal)\n6. Chicken - Lean Protein (165 kcal)"
            )
        }

        // Food Picks Card Clicks
        binding.cardAvocado.setOnClickListener {
            showFoodDetail(
                "🥑 Avocado",
                "Healthy Fats & Fiber",
                "160 kcal",
                "15g",
                "2g",
                "Rich in monounsaturated oleic acid, heart-healthy fats, and potassium. Great for brain function and lowering LDL cholesterol."
            )
        }

        binding.cardSalmon.setOnClickListener {
            showFoodDetail(
                "🐟 Salmon",
                "Omega-3 Rich & High Protein",
                "208 kcal",
                "13g",
                "20g",
                "Packed with high-quality protein and Omega-3 fatty acids EPA & DHA. Supports heart, brain, and joint health."
            )
        }

        binding.cardBroccoli.setOnClickListener {
            showFoodDetail(
                "🥦 Broccoli",
                "Fiber Rich & Vitamin C",
                "55 kcal",
                "0.6g",
                "3.7g",
                "High in glucosinolates, Vitamin C, Vitamin K, and dietary fiber. Boosts immune function and aids digestion."
            )
        }

        binding.cardBlueberries.setOnClickListener {
            showFoodDetail(
                "🫐 Blueberries",
                "Superfood & Antioxidants",
                "84 kcal",
                "0.5g",
                "1.1g",
                "Rich in anthocyanin antioxidants. Improves memory, brain function, and combats oxidative stress."
            )
        }

        binding.cardEggs.setOnClickListener {
            showFoodDetail(
                "🥚 Eggs",
                "High Protein & Choline",
                "155 kcal",
                "11g",
                "13g",
                "Complete protein source containing all 9 essential amino acids and essential choline for cell membrane health."
            )
        }

        binding.cardChicken.setOnClickListener {
            showFoodDetail(
                "🍗 Chicken Breast",
                "Lean Protein Source",
                "165 kcal",
                "3.6g",
                "31g",
                "Very low in fat and packed with lean protein. Essential for muscle growth, repair, and metabolism."
            )
        }
    }

    private fun showInfoDialog(title: String, message: String) {
        com.google.android.material.dialog.MaterialAlertDialogBuilder(requireContext())
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("OK", null)
            .show()
    }

    private fun showFoodDetail(name: String, category: String, calories: String, fat: String, protein: String, benefits: String) {
        com.google.android.material.dialog.MaterialAlertDialogBuilder(requireContext())
            .setTitle("$name ($category)")
            .setMessage("• Calories: $calories\n• Fat: $fat\n• Protein: $protein\n\nNutritional Benefits:\n$benefits")
            .setPositiveButton("Add to Meal Log") { _, _ ->
                com.google.android.material.snackbar.Snackbar.make(
                    binding.root,
                    "Added $name to your daily meal log! 🍽️",
                    com.google.android.material.snackbar.Snackbar.LENGTH_SHORT
                ).show()
            }
            .setNegativeButton("Close", null)
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}