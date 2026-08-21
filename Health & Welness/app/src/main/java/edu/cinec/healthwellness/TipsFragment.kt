package edu.cinec.healthwellness

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import edu.cinec.healthwellness.databinding.FragmentTipsBinding

class TipsFragment : Fragment() {

    private var _binding: FragmentTipsBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: TipsAdapter
    private var allTips: List<HealthTip> = emptyList()
    private var currentCategory: String = "All"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTipsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupData()
        setupRecyclerView()
        setupListeners()
    }

    private fun setupData() {
        allTips = listOf(
            HealthTip(
                getString(R.string.tip_diet_title),
                getString(R.string.tip_diet_desc),
                getString(R.string.nutrition),
                android.R.drawable.ic_menu_gallery,
                R.color.primary_green,
                R.color.soft_green_bg
            ),
            HealthTip(
                getString(R.string.tip_run_title),
                getString(R.string.tip_run_desc),
                getString(R.string.exercise),
                android.R.drawable.ic_menu_directions,
                R.color.primary_green,
                R.color.soft_green_bg
            ),
            HealthTip(
                getString(R.string.tip_sleep_title),
                getString(R.string.tip_sleep_desc),
                getString(R.string.sleep),
                android.R.drawable.ic_menu_recent_history,
                R.color.primary_green,
                R.color.soft_green_bg
            ),
            HealthTip(
                getString(R.string.tip_water_title),
                getString(R.string.tip_water_desc),
                getString(R.string.hydration),
                android.R.drawable.ic_menu_send,
                R.color.primary_green,
                R.color.soft_green_bg
            ),
            HealthTip(
                getString(R.string.tip_breathing_title),
                getString(R.string.tip_breathing_desc),
                getString(R.string.mental_health),
                android.R.drawable.ic_menu_view,
                R.color.primary_green,
                R.color.soft_green_bg
            )
        )
    }

    private fun setupRecyclerView() {
        adapter = TipsAdapter(allTips) { tip ->
            com.google.android.material.dialog.MaterialAlertDialogBuilder(requireContext())
                .setTitle("${tip.category} • ${tip.title}")
                .setMessage("${tip.description}\n\nKey Takeaway:\nConsistency is essential for seeing long-term health improvements. Integrate this tip into your daily routine for optimal wellness.")
                .setPositiveButton("Got It!", null)
                .show()
        }
        binding.rvTips.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@TipsFragment.adapter
        }
    }

    private fun setupListeners() {
        binding.profileImage.setOnClickListener {
            com.google.android.material.dialog.MaterialAlertDialogBuilder(requireContext())
                .setTitle("👤 User Profile")
                .setMessage("Name: Cinec Health User\nSaved Tips: 12\nCompleted Habits: 5")
                .setPositiveButton("OK", null)
                .show()
        }

        binding.btnFilter.setOnClickListener {
            com.google.android.material.dialog.MaterialAlertDialogBuilder(requireContext())
                .setTitle("⚙️ Filter & Sort Tips")
                .setItems(arrayOf("Show All", "Sort A-Z", "Nutrition Only", "Exercise Only", "Sleep Only")) { _, which ->
                    when (which) {
                        0 -> binding.chipAll.isChecked = true
                        1 -> adapter.updateTips(allTips.sortedBy { it.title })
                        2 -> binding.chipNutrition.isChecked = true
                        3 -> binding.chipExercise.isChecked = true
                        4 -> binding.chipSleep.isChecked = true
                    }
                }
                .setNegativeButton("Cancel", null)
                .show()
        }

        binding.chipGroup.setOnCheckedStateChangeListener { _, checkedIds ->
            if (checkedIds.isNotEmpty()) {
                val checkedId = checkedIds.first()
                currentCategory = when (checkedId) {
                    R.id.chip_nutrition -> getString(R.string.nutrition)
                    R.id.chip_exercise -> getString(R.string.exercise)
                    R.id.chip_sleep -> getString(R.string.sleep)
                    R.id.chip_hydration -> getString(R.string.hydration)
                    R.id.chip_mental_health -> getString(R.string.mental_health)
                    else -> "All"
                }
                filterTips()
            }
        }

        binding.searchEditText.doOnTextChanged { _, _, _, _ ->
            filterTips()
        }
    }

    private fun filterTips() {
        val query = binding.searchEditText.text?.toString()?.trim().orEmpty()
        val filteredList = allTips.filter { tip ->
            val matchesCategory = (currentCategory == "All" || tip.category.equals(currentCategory, ignoreCase = true))
            val matchesQuery = query.isEmpty() ||
                    tip.title.contains(query, ignoreCase = true) ||
                    tip.description.contains(query, ignoreCase = true) ||
                    tip.category.contains(query, ignoreCase = true)
            matchesCategory && matchesQuery
        }
        adapter.updateTips(filteredList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}