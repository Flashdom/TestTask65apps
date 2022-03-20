package com.test.testtask65apps.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.test.testtask65apps.R
import com.test.testtask65apps.databinding.ItemWorkerBinding
import com.test.testtask65apps.models.Worker

class WorkerAdapter(private val onWorkerClick: (worker: Worker) -> Unit) :
    RecyclerView.Adapter<WorkerAdapter.WorkerViewHolder>() {

    private val differ = AsyncListDiffer(this, WorkerItemCallback())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkerViewHolder {
        return WorkerViewHolder(ItemWorkerBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: WorkerViewHolder, position: Int) {
        return holder.bind(differ.currentList[position], onWorkerClick)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun updateList(newList: List<Worker>) {
        differ.submitList(newList)
    }

    class WorkerViewHolder(private val binding: ItemWorkerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Worker, onWorkerClick: (worker: Worker) -> Unit) {
            binding.tvLastName.text = item.lastName
            binding.tvFirstName.text = item.firstName
            binding.tvAge.text = item.age.ifBlank { itemView.context.getString(R.string.no_birthdate) }
            binding.root.setOnClickListener {
                onWorkerClick(item)
            }
        }
    }

    class WorkerItemCallback : DiffUtil.ItemCallback<Worker>() {
        override fun areItemsTheSame(oldItem: Worker, newItem: Worker): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Worker, newItem: Worker): Boolean {
            return oldItem == newItem
        }
    }
}