package edu.cinec.healthwellness

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import edu.cinec.healthwellness.databinding.ItemHealthTipBinding

class TipsAdapter(
    private var tips: List<HealthTip>,
    private val onTipClick: ((HealthTip) -> Unit)? = null
) : RecyclerView.Adapter<TipsAdapter.TipViewHolder>() {

    fun updateTips(newTips: List<HealthTip>) {
        tips = newTips
        notifyDataSetChanged()
    }

    class TipViewHolder(val binding: ItemHealthTipBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TipViewHolder {
        val binding = ItemHealthTipBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TipViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TipViewHolder, position: Int) {
        val tip = tips[position]
        holder.binding.apply {
            tipTitle.text = tip.title
            tipDesc.text = tip.description
            tipCategory.text = tip.category
            tipIcon.setImageResource(tip.iconRes)
            
            // Set colors
            tipIcon.setColorFilter(ContextCompat.getColor(root.context, tip.colorRes))
            tipIcon.backgroundTintList = ContextCompat.getColorStateList(root.context, tip.bgColorRes)
            tipCategory.setTextColor(ContextCompat.getColor(root.context, tip.colorRes))

            root.setOnClickListener {
                onTipClick?.invoke(tip)
            }
        }
    }

    override fun getItemCount() = tips.size
}