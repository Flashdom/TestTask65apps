package com.test.testtask65apps.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.testtask65apps.MainActivity
import com.test.testtask65apps.adapters.JobAdapter
import com.test.testtask65apps.databinding.FragmentJobsListBinding
import com.test.testtask65apps.viewmodels.JobsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class JobListFragment : BaseFragment<FragmentJobsListBinding>(FragmentJobsListBinding::inflate) {

    private val viewModel: JobsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvJobs.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = JobAdapter { job ->
                (activity as MainActivity).switchFragment(WorkerListFragment.newInstance(job.id))
            }
            setHasFixedSize(true)
        }
        viewModel.jobs.observe(viewLifecycleOwner) { jobs ->
            (binding.rvJobs.adapter as JobAdapter).updateList(jobs)
        }
    }
}