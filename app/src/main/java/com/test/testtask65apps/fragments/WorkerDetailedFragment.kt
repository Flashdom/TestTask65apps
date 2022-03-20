package com.test.testtask65apps.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.test.testtask65apps.R
import com.test.testtask65apps.databinding.FragmentWorkerDetailedBinding
import com.test.testtask65apps.viewmodels.WorkersWithJobsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WorkerDetailedFragment :
    BaseFragment<FragmentWorkerDetailedBinding>(FragmentWorkerDetailedBinding::inflate) {

    private val viewModel: WorkersWithJobsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.worker.observe(viewLifecycleOwner) { workerDetailed ->
            binding.tvFirstName.text =
                workerDetailed.worker.firstName
            binding.tvLastName.text = workerDetailed.worker.lastName
            binding.tvBirthDate.text = if (workerDetailed.worker.birthDay.isNotBlank())
                requireContext().getString(
                    R.string.birthdate,
                    workerDetailed.worker.birthDay
                ) else getString(R.string.no_birthdate)
            binding.tvAge.text =
                workerDetailed.worker.age.ifBlank { getString(R.string.no_birthdate) }
            binding.tvJobName.text =
                workerDetailed.job.joinToString { jobName -> jobName.name + " " }
        }
        viewModel.listenWorkerWithJobs(requireArguments().getInt(ARG_WORKER_ID))
    }

    companion object {
        private const val ARG_WORKER_ID = "arg_worker_id"
        fun newInstance(workerId: Int): WorkerDetailedFragment {
            return WorkerDetailedFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_WORKER_ID, workerId)
                }
            }
        }
    }
}