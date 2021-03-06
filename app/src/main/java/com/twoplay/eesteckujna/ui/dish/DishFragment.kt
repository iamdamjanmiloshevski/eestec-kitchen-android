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
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.twoplay.eesteckujna.R
import com.twoplay.eesteckujna.databinding.FragmentDishBinding
import com.twoplay.eesteckujna.models.Dish
import com.twoplay.eesteckujna.ui.BaseFragment
import com.twoplay.eesteckujna.util.loadImage
import com.twoplay.eesteckujna.util.toEuroPrice
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DishFragment : BaseFragment() {
    private lateinit var mBinding: FragmentDishBinding
    private var dishId: String? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        initBinding(inflater, container)
        initUI()
        requestData()
        return mBinding.root
    }

    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?) {
        mBinding = FragmentDishBinding.inflate(inflater, container, false)
    }

    override fun observeData() {
        dishViewModel.observeDish().observe(viewLifecycleOwner, { result ->
            val dish = result.data
            dish?.let {
                with(it){
                    mBinding.dish = this
                    mBinding.ivDishImage.loadImage(this.image)
                    mBinding.tvDishPrice.text = this.price.toEuroPrice()
                }
            }
        })
    }

    override fun requestData() {
        dishId?.let { id ->
            dishViewModel.getDishById(id)
        } ?: throw NullPointerException("dishId must not be null!!")
    }

    override fun initUI() {
        val args = arguments
        args?.let { bundleArgs ->
            dishId = bundleArgs.getString(DISH_ID_KEY)
        }
    }

    companion object {
        const val DISH_ID_KEY = "dishId"
    }
}