package com.test.testtask65apps.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.test.testtask65apps.databinding.ItemJobBinding
import com.test.testtask65apps.models.Job

class JobAdapter(private val onJobClick: (job: Job) -> Unit) :
    RecyclerView.Adapter<JobAdapter.JobViewHolder>() {

    private val differ = AsyncListDiffer(this, JobItemCallback())


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobViewHolder {
        return JobViewHolder(
            ItemJobBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: JobViewHolder, position: Int) {
        holder.bind(differ.currentList[position], onJobClick)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun updateList(newList: List<Job>) {
        differ.submitList(newList)
    }

    class JobViewHolder(private val binding: ItemJobBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Job, onJobClick: (job: Job) -> Unit) {
            binding.tvJobName.text = item.name
            binding.root.setOnClickListener {
                onJobClick(item)
            }
        }
    }

    class JobItemCallback : DiffUtil.ItemCallback<Job>() {
        override fun areItemsTheSame(oldItem: Job, newItem: Job): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Job, newItem: Job): Boolean {
            return oldItem == newItem
        }
    }


}