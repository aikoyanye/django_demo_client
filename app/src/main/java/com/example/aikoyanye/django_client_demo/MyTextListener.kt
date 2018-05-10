package com.example.aikoyanye.django_client_demo

import android.os.Handler
import android.os.Message
import com.loopj.android.http.TextHttpResponseHandler

// 对用activity中的handler，通过msg把数据传回activity，s_state是成功访问的状态码，f_state是访问失败的状态码
// 这个类可以自己定义，不一定按照我这种写法
// 花式构造函数
class MyTextListener(var handler: Handler, var s_state: Int, var f_state: Int): TextHttpResponseHandler() {

    val msg = Message.obtain()

    override fun onSuccess(p0: Int, p1: Array<out cz.msebera.android.httpclient.Header>?, p2: String?) {
        // 成功访问
        msg.what = s_state
        msg.obj = p2
        handler.sendMessage(msg)
    }

    override fun onFailure(p0: Int, p1: Array<out cz.msebera.android.httpclient.Header>?, p2: String?, p3: Throwable?) {
        // 失败访问
        msg.what = f_state
        handler.sendMessage(msg)
    }
}