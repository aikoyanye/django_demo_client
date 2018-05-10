package com.example.aikoyanye.django_client_demo

import android.os.Handler
import android.view.View
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.RequestParams

class MyOnClickListener: View.OnClickListener {

    companion object {
        val handler = Handler(){
            when(it.what){
                1 ->{
                    MainActivity.delete_name.setText("删除成功")
                } 0x1 ->{
                    MainActivity.delete_name.setText("删除失败")
                } 2 -> {
                    MainActivity.add_yep_data.setText(it.obj.toString())
                } 0x2 ->{
                    MainActivity.add_yep_data.setText("添加失败")
                } 3 -> {
                    MainActivity.get_nop_data.setText(it.obj.toString())
                    val map = JSONTOOL.analyze_once_json(it.obj.toString())
                    val sb = StringBuffer()
                    sb.append("id:" + map["id"])
                    sb.append("\nname:" + map["name"])
                    sb.append("\npwd:" + map["pwd"])
                    sb.append("\nage:" + map["age"])
                    MainActivity.get_yep_data.setText(sb.toString())
                } 0x3 -> {
                    MainActivity.get_yep_data.setText("获取失败")
                } 4 -> {
                    MainActivity.put_yep_data.setText("修改成功\n" + it.obj.toString())
                } 0x4 -> {
                    MainActivity.put_yep_data.setText("修改失败")
                }
            }
            return@Handler true
        }
    }

    override fun onClick(p0: View?) {
        // 用来访问url
        val client = AsyncHttpClient()
        // 用来存放表单数据
        val params = RequestParams()
        when(p0!!.id){
            R.id.btn_delete ->{
                params.put("method", "_DELETE")
                params.put("name", MainActivity.delete_name.text.toString())
                // 虚拟机访问本机地址，如果是用真机的话，那就只能部署在公网上然后访问
                client.post("http://10.0.2.2:8000/android_user/", params, MyTextListener(MyOnClickListener.handler, 1, 0x1))
            }R.id.btn_add ->{
                params.put("method", "_POST")
                params.put("name", MainActivity.add_name.text.toString())
                params.put("age", MainActivity.add_age.text.toString())
                params.put("pwd", MainActivity.add_pwd.text.toString())
                client.post("http://10.0.2.2:8000/android_user/", params, MyTextListener(MyOnClickListener.handler, 2, 0x2))
            }R.id.btn_add_reset ->{
                MainActivity.add_age.setText("")
                MainActivity.add_name.setText("")
                MainActivity.add_pwd.setText("")
            }R.id.btn_get ->{
                params.put("method", "_GET")
                params.put("name", MainActivity.get_name.text.toString())
                params.put("pwd", MainActivity.get_pwd.text.toString())
                client.post("http://10.0.2.2:8000/android_user/", params, MyTextListener(MyOnClickListener.handler, 3, 0x3))
            }R.id.btn_put ->{
                params.put("method", "_PUT")
                params.put("name", MainActivity.put_name.text.toString())
                params.put("age", MainActivity.put_age.text.toString())
                params.put("pwd", MainActivity.put_pwd.text.toString())
                client.post("http://10.0.2.2:8000/android_user/", params, MyTextListener(MyOnClickListener.handler, 4, 0x4))
            }
        }
    }
}