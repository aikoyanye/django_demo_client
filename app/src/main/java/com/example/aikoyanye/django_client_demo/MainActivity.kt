package com.example.aikoyanye.django_client_demo

import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    // 分页的view
    lateinit var v1: View
    lateinit var v2: View
    lateinit var v3: View
    lateinit var v4: View

    // 无奈这样写
    lateinit var btn_delete: Button
    lateinit var btn_add_reset: Button
    lateinit var btn_get: Button
    lateinit var btn_put: Button
    lateinit var btn_add: Button

    companion object {
        lateinit var add_yep_data: TextView
        lateinit var get_nop_data: TextView
        lateinit var get_yep_data: TextView
        lateinit var put_yep_data: TextView

        lateinit var put_name: EditText
        lateinit var put_age: EditText
        lateinit var put_pwd: EditText
        lateinit var get_name: EditText
        lateinit var get_pwd: EditText
        lateinit var delete_name: EditText
        lateinit var add_name: EditText
        lateinit var add_age: EditText
        lateinit var add_pwd: EditText
    }

    // 用来存储分页显示的view
    val view_list = ArrayList<View>()
    // 每页title的数据
    val title_list = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init_view_page()
    }

    fun init_btn_tv(){
        // 如果可以直接用id调用的同学，真的请告诉我
        btn_add = v1.findViewById(R.id.btn_add)
        btn_add_reset = v1.findViewById(R.id.btn_add_reset)
        btn_get = v2.findViewById(R.id.btn_get)
        btn_put = v3.findViewById(R.id.btn_put)
        btn_delete = v4.findViewById(R.id.btn_delete)
        btn_add.setOnClickListener(MyOnClickListener())
        btn_add_reset.setOnClickListener(MyOnClickListener())
        btn_get.setOnClickListener(MyOnClickListener())
        btn_put.setOnClickListener(MyOnClickListener())
        btn_delete.setOnClickListener(MyOnClickListener())

        add_yep_data = v1.findViewById(R.id.add_yep_data)
        get_nop_data = v2.findViewById(R.id.get_nop_data)
        get_yep_data = v2.findViewById(R.id.get_yep_data)
        put_yep_data = v3.findViewById(R.id.put_yep_data)

        add_pwd = v1.findViewById(R.id.add_pwd)
        add_age = v1.findViewById(R.id.add_age)
        add_name = v1.findViewById(R.id.add_name)
        get_pwd = v2.findViewById(R.id.get_pwd)
        get_name = v2.findViewById(R.id.get_name)
        put_name = v3.findViewById(R.id.put_name)
        put_pwd = v3.findViewById(R.id.put_pwd)
        put_age = v3.findViewById(R.id.put_age)
        delete_name = v4.findViewById(R.id.delete_name)
    }

    fun init_view_page(){
        // 加载要显示页卡
        v1 = LayoutInflater.from(this).inflate(R.layout.add_layout, null)
        v2 = LayoutInflater.from(this).inflate(R.layout.get_layout, null)
        v3 = LayoutInflater.from(this).inflate(R.layout.put_layout, null)
        v4 = LayoutInflater.from(this).inflate(R.layout.delete_layout, null)

        view_list.add(v1)
        view_list.add(v2)
        view_list.add(v3)
        view_list.add(v4)

        title_list.add("增")
        title_list.add("查")
        title_list.add("改")
        title_list.add("删")

        // 相当于
        // ViewPager view_pager = findVIewById(R.id.view_pager);
        // view_pager.setAdapter(new MyViewPageAdapter(view_list));
        view_pager.adapter = MyViewPageAdapter(view_list, title_list)
        // 下划线颜色以及背景颜色，兼容性问题
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            pager_tab.setBackgroundColor(this.getColor(R.color.colorWhite))
            pager_tab.setTabIndicatorColor(this.getColor(R.color.colorPrimary))
        }else{
//            pager_tab.setBackgroundColor(resources.getColor(R.color.colorWhite))
            pager_tab.setTabIndicatorColor(resources.getColor(R.color.colorPrimary))
        }
        init_btn_tv()
    }
}
