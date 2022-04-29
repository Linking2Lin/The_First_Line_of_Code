package com.lins.sunnyweatherdemo.ui.place.caiyun

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.lins.sunnyweatherdemo.databinding.FragmentPlaceBinding

class PlaceCaiYunFragment : Fragment() {

    // by关键字将左边委托给了右边去实现，当调用左边的属性时，会自动调用右边对象的对应方法，
    // lazy是一个高阶函数，在该函数中会创建并返回一个Delegate对象，
    val viewModel by lazy { ViewModelProvider(this).get(PlaceCaiYunViewModel::class.java) }

    private lateinit var adapter:PlaceAdapterCaiYun

    private var _binding:FragmentPlaceBinding? = null
    private val binding get() = _binding!!

    //为碎片加载布局
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlaceBinding.inflate(inflater,container,false)
        val view = binding.root
        //return inflater.inflate(R.layout.fragment_place,container,false)
        return view
    }


    //初始化代码在此处完成
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    //View相关代码放在此处
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    //当碎片与活动建立关联时调用
    override fun onAttach(context: Context) {
        super.onAttach(context)
        requireActivity().lifecycle.addObserver(object : DefaultLifecycleObserver{
            @SuppressLint("NotifyDataSetChanged")
            override fun onCreate(owner: LifecycleOwner) {

                val layoutManager = LinearLayoutManager(activity)
                binding.recyclerView.layoutManager = layoutManager
                adapter = PlaceAdapterCaiYun(this@PlaceCaiYunFragment,viewModel.placeList)
                binding.recyclerView.adapter = adapter

                binding.searchPlaceEdit.addTextChangedListener { text: Editable? ->
                    val content = text.toString()
                    if (content.isNotEmpty()){
                        viewModel.searchPlaces(content)//搜索输入的数据
                    }else{
                        binding.recyclerView.visibility = View.GONE
                        binding.bgImageView.visibility = View.VISIBLE
                        viewModel.placeList.clear()
                        adapter.notifyDataSetChanged()
                    }
                }
                viewModel.placeLiveData.observe(this@PlaceCaiYunFragment){ result ->
                    val places = result.getOrNull()
                    if (places != null){
                        binding.recyclerView.visibility = View.VISIBLE
                        binding.bgImageView.visibility = View.GONE
                        viewModel.placeList.clear()
                        viewModel.placeList.addAll(places)
                        adapter.notifyDataSetChanged()
                    }else{
                        Toast.makeText(activity, "未知地点", Toast.LENGTH_SHORT).show()
                        result.exceptionOrNull()?.printStackTrace()
                    }
                }

                owner.lifecycle.removeObserver(this)
            }

        })
    }

    //确保与碎片关联的活动创建完毕时调用，已废弃
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}