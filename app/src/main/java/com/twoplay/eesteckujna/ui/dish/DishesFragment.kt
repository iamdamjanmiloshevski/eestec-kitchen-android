/*
 * This is free and unencumbered software released into the public domain.
 *
 * Anyone is free to copy, modify, publish, use, compile, sell, or
 * distribute this software, either in source code form or as a compiled
 * binary, for any purpose, commercial or non-commercial, and by any
 * means.
 *
 * In jurisdictions that recognize copyright laws, the author or authors
 * of this software dedicate any and all copyright interest in the
 * software to the public domain. We make this dedication for the benefit
 * of the public at large and to the detriment of our heirs and
 * successors. We intend this dedication to be an overt act of
 * relinquishment in perpetuity of all present and future rights to this
 * software under copyright law.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS BE LIABLE FOR ANY CLAIM, DAMAGES OR
 * OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 *
 * For more information, please refer to <https://unlicense.org>
 */

package com.twoplay.eesteckujna.ui.dish

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.twoplay.eesteckujna.R

import com.twoplay.eesteckujna.models.Dish
import com.twoplay.eesteckujna.ui.adapters.DishRecyclerViewAdapter
import com.twoplay.eesteckujna.ui.OnDishItemClickedListener
import com.twoplay.eesteckujna.databinding.FragmentDishesBinding
import com.twoplay.eesteckujna.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DishesFragment : BaseFragment(), OnDishItemClickedListener {
    private lateinit var mBinding: FragmentDishesBinding
    private val dishesAdapter = DishRecyclerViewAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        initBinding(inflater, container)
        initUI()
        requestData()
        return mBinding.root
    }

    override fun onDishClicked(dish: Dish) {
        val navController = findNavController()
        val args = bundleOf(DishFragment.DISH_ID_KEY to dish.id)
        navController.navigate(
            R.id.action_DishesFragment_to_DishFragment,
            args
        )
    }

    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?) {
        mBinding = FragmentDishesBinding.inflate(inflater, container, false)
    }

    override fun observeData() {
        dishViewModel.observeDishes().observe(viewLifecycleOwner, { result ->
            val dishes = result.data as List<Dish>
            dishesAdapter.setDishes(dishes)
        })
    }

    override fun requestData() {
        dishViewModel.getDishes()
    }

    override fun initUI() {
        dishesAdapter.setOnDishClickListener(this)
        dishesAdapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
            override fun onChanged() {
                mBinding.tvNoData.visibility =
                    if (dishesAdapter.itemCount == 0) View.VISIBLE else View.GONE
            }
        })
        mBinding.rvDishes.apply {
            adapter = dishesAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
    }
}