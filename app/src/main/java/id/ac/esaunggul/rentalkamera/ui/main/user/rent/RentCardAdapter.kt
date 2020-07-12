package id.ac.esaunggul.rentalkamera.ui.main.user.rent

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import id.ac.esaunggul.rentalkamera.data.model.CameraModel
import id.ac.esaunggul.rentalkamera.databinding.FragmentRentCardBinding
import id.ac.esaunggul.rentalkamera.util.listener.ClickListener

class RentCardAdapter(
    private val clickListener: ClickListener
) : ListAdapter<CameraModel, RentCardAdapter.RentCardViewHolder>(RentCardDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RentCardViewHolder {
        return RentCardViewHolder.from(this, parent)
    }

    override fun onBindViewHolder(holder: RentCardViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class RentCardViewHolder
    private constructor(
        private val binding: FragmentRentCardBinding,
        private val clickListener: ClickListener
    ) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        init {
            binding.rentCardView.setOnClickListener(this)
        }

        fun bind(cameraModel: CameraModel) {
            binding.cameraModel = cameraModel
            binding.executePendingBindings()
        }

        override fun onClick(v: View?) {
            adapterPosition
        }

        companion object {
            fun from(
                rentCardAdapter: RentCardAdapter,
                parent: ViewGroup
            ): RentCardViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = FragmentRentCardBinding.inflate(inflater, parent, false)
                return RentCardViewHolder(binding, rentCardAdapter.clickListener)
            }
        }
    }

    class RentCardDiffCallback : DiffUtil.ItemCallback<CameraModel>() {

        override fun areItemsTheSame(oldItem: CameraModel, newItem: CameraModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CameraModel, newItem: CameraModel): Boolean {
            return oldItem == newItem
        }
    }
}