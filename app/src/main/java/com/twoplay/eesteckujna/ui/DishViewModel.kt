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

package com.twoplay.eesteckujna.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.twoplay.eesteckujna.models.Dish
import com.twoplay.eesteckujna.repository.NetworkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/*
    Author: Damjan Miloshevski 
    Created on 2/20/21 11:08 AM
    Project: EESTECKujna
    © 2Play Tech  2021. All rights reserved
*/
@HiltViewModel
class DishViewModel @Inject constructor(private val repository: NetworkRepository) : ViewModel() {
    private val dishesObserver = MutableLiveData<List<Dish>>()
    private val dishObserver = MutableLiveData<Dish>()

    fun getDishes() {
        repository.getAllDishes(successCallback = { dishes ->
            dishesObserver.value = dishes
        })
    }
    fun getDishById(dishId:String){
           repository.getDishById(dishId,successCallback = {dish->
               dishObserver.value = dish
       })
    }
    fun observeDishes() = dishesObserver
    fun observeDish() = dishObserver
}