package com.zcf.testapp.fragment3

import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager.VERTICAL
import com.zcf.testapp.R
import com.zcf.testapp.databinding.FragmentBottomNav3Binding
import com.zcf.testapp.databinding.ViewRecyclerItemBinding
import com.zcf.testapp.shared.SharedViewModel

class BottomNav3Fragment: Fragment(R.layout.fragment_bottom_nav_3) {

    private lateinit var binding: FragmentBottomNav3Binding

    private val sharedViewModel by activityViewModels<SharedViewModel>()

    private val viewModel by viewModels<BottomNav3ViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBottomNav3Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = MyRecyclerViewAdapter()
        binding.recyclerView.layoutManager = StaggeredGridLayoutManager(2, VERTICAL)
    }
}

internal class MyRecyclerViewHolder(val itemBinding: ViewRecyclerItemBinding) :
    RecyclerView.ViewHolder(itemBinding.root)

internal class MyRecyclerViewAdapter: RecyclerView.Adapter<MyRecyclerViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyRecyclerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ViewRecyclerItemBinding.inflate(inflater, parent, false)
        return MyRecyclerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyRecyclerViewHolder, position: Int) {
        holder.itemBinding.textView.text = "Item $position"

        val height = 50 + 30 * (position % 2)
        val resources = holder.itemBinding.root.resources
        holder.itemBinding.textView.height =
            TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, height.toFloat(), resources.displayMetrics)
                .toInt()
    }

    override fun getItemCount(): Int {
        return 100
    }

}