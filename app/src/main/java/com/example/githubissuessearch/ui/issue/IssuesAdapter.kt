package com.example.githubissuessearch.ui.issue

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.githubissuessearch.R
import com.example.githubissuessearch.databinding.ItemIssuesBinding
import com.example.githubissuessearch.model.Issue
import com.example.githubissuessearch.utils.AdapterItemsContract

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

    fun updatePostList(postList:List<Issue>){
        this.issues = postList
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemIssuesBinding):RecyclerView.ViewHolder(binding.root){

        private val viewModel = IssueViewModel()
        fun bind(issue:Issue){
            viewModel.bind(issue)
            binding.viewModel = viewModel
        }
    }
}