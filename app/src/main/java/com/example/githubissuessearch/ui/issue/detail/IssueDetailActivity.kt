package com.example.githubissuessearch.ui.issue.detail

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.githubissuessearch.R
import com.example.githubissuessearch.databinding.ActivityIssueDetailBinding
import com.example.githubissuessearch.model.Issue
import com.example.githubissuessearch.ui.issue.ISSUE_EXTRA

class IssueDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityIssueDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_issue_detail)

        if(intent.hasExtra(ISSUE_EXTRA)){
            val viewModel: IssueDetailViewModel = ViewModelProviders.of(this).get(IssueDetailViewModel::class.java)
            val issue = (intent.getSerializableExtra(ISSUE_EXTRA) as? Issue)!!
            viewModel.bind(issue, binding.ivAvatar)
            binding.viewModel = viewModel
        }
    }
}
