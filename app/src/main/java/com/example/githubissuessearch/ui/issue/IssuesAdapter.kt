package com.example.githubissuessearch.ui.issue

import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.githubissuessearch.R
import com.example.githubissuessearch.databinding.ItemIssuesBinding
import com.example.githubissuessearch.model.Issue
import com.example.githubissuessearch.ui.issue.detail.IssueDetailActivity

class IssuesAdapter: RecyclerView.Adapter<IssuesAdapter.ViewHolder>() {

    private lateinit var issues:List<Issue>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IssuesAdapter.ViewHolder {
        val binding: ItemIssuesBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_issues, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: IssuesAdapter.ViewHolder, position: Int) {
        holder.bind(issues[position])
    }

    override fun getItemCount(): Int {
        return if(::issues.isInitialized) issues.size else 0
    }

    fun updatePostList(issues:List<Issue>){
        this.issues = issues
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemIssuesBinding):RecyclerView.ViewHolder(binding.root){

        private val viewModel = IssueViewModel()
        var issue: Issue? = null

        init {
            binding.root.setOnClickListener {
                val intent = Intent(binding.root.context, IssueDetailActivity::class.java)
                intent.putExtra(ISSUE_EXTRA, issue)
                binding.root.context.startActivity(intent)
            }
        }

        fun bind(issue:Issue){
            this.issue = issue
            viewModel.bind(issue, binding.ivAvatar)
            binding.viewModel = viewModel
        }
    }
}