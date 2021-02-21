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

package com.twoplay.eesteckujna.repository

import android.util.Log
import com.twoplay.eesteckujna.models.Dish
import kotlinx.coroutines.*
import retrofit2.Retrofit
import timber.log.Timber
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

/*
    Author: Damjan Miloshevski 
    Created on 2/20/21 10:58 AM
    Project: EESTECKujna
    © 2Play Tech  2021. All rights reserved
*/
class NetworkRepository @Inject constructor(private val api: BabaMirkaApi) : CoroutineScope,
    IRepository {
    override fun getAllDishes(successCallback: (List<Dish>) -> Unit) {
        launch {
            val dishes = api.getAllDishes()
            withContext(Dispatchers.Main) {
                successCallback.invoke(dishes)
            }
        }
    }

    override fun getDishById(dishId: String, successCallback: (Dish) -> Unit) {
        launch {
            val dish = api.getDishById(dishId)
            withContext(Dispatchers.Main) {
                successCallback.invoke(dish)
            }
        }
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO
}
