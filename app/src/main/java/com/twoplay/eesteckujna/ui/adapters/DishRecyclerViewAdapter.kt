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

package com.twoplay.eesteckujna.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.twoplay.eesteckujna.databinding.ItemDishBinding
import com.twoplay.eesteckujna.models.Dish
import com.twoplay.eesteckujna.ui.OnDishItemClickedListener
import com.twoplay.eesteckujna.ui.viewholders.DishViewHolder

/*
    Author: Damjan Miloshevski 
    Created on 2/20/21 12:39 PM
    Project: EESTECKujna
    © 2Play Tech  2021. All rights reserved
*/
class DishRecyclerViewAdapter : RecyclerView.Adapter<DishViewHolder>() {
    private val dishes = mutableListOf<Dish>()
    private var mDishListener: OnDishItemClickedListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemDishBinding = ItemDishBinding.inflate(layoutInflater, parent, false)
        return DishViewHolder(itemDishBinding)
    }

    override fun onBindViewHolder(holder: DishViewHolder, position: Int) {
        val dish = dishes[position]
        holder.binding.dish = dish
        holder.bind(dish,mDishListener)
    }

    override fun getItemCount(): Int {
        return dishes.size
    }

    fun setDishes(dishes: List<Dish>) {
        val setOfDishes = mutableSetOf<Dish>()
        setOfDishes.addAll(dishes)
        this.dishes.clear()
        this.dishes.addAll(setOfDishes.toList())
        notifyDataSetChanged()
    }
    fun setOnDishClickListener(listener: OnDishItemClickedListener){
        this.mDishListener = listener
    }
}