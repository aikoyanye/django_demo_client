package com.example.aikoyanye.django_client_demo

import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
/**
 * Created by KirisameMarisaNo扫把哈哈哈 on 18-5-9.
 */

// kotlin特色，花式构造函数
class MyViewPageAdapter(var view_list: ArrayList<View>, var title_list: ArrayList<String>): PagerAdapter() {

    override fun isViewFromObject(view: View?, `object`: Any?): Boolean {
        // 我也不知道是啥
        return view==`object`
    }

    override fun getCount(): Int {
        // 返回总数
        return view_list.size
    }

    override fun destroyItem(container: ViewGroup?, position: Int, `object`: Any?) {
        container!!.removeView(view_list[position])
    }

    override fun instantiateItem(container: ViewGroup?, position: Int): Any {
        container!!.addView(view_list[position], 0)
        return view_list[position]
    }

    override fun getItemPosition(`object`: Any?): Int {
        return super.getItemPosition(`object`)
    }

    override fun getPageTitle(position: Int): CharSequence {
        return title_list[position]
    }
}