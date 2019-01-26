package com.example.githubissuessearch


import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.githubissuessearch.databinding.FragmentIssuesBinding
import com.example.githubissuessearch.ui.issue.IssueListViewModel

class IssuesFragment : Fragment() {

    private lateinit var binding: FragmentIssuesBinding
    private lateinit var viewModel: IssueListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentIssuesBinding.inflate(inflater, container, false)
        binding.rvIssues.layoutManager = LinearLayoutManager(activity)
        viewModel = ViewModelProviders.of(this).get(IssueListViewModel::class.java)
        binding.viewModel = viewModel

        return binding.root
    }
}
