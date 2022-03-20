package com.test.testtask65apps.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.testtask65apps.MainActivity
import com.test.testtask65apps.adapters.WorkerAdapter
import com.test.testtask65apps.databinding.FragmentWorkerListBinding
import com.test.testtask65apps.viewmodels.WorkersViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WorkerListFragment :
    BaseFragment<FragmentWorkerListBinding>(FragmentWorkerListBinding::inflate) {

    private val viewModel: WorkersViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.listenWorkersFromDb(requireArguments().getInt(ARG_JOB_ID))
        viewModel.workers.observe(viewLifecycleOwner) { workers ->
            (binding.rvWorkers.adapter as WorkerAdapter).updateList(workers)
        }
        binding.rvWorkers.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = WorkerAdapter { worker ->
                (activity as MainActivity).switchFragment(
                    WorkerDetailedFragment.newInstance(
                        workerId = worker.id
                    )
                )
            }
        }
    }

    companion object {
        private const val ARG_JOB_ID = "arg_job_id"
        fun newInstance(jobId: Int): WorkerListFragment {
            return WorkerListFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_JOB_ID, jobId)
                }
            }
        }
    }


}