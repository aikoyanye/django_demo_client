package com.example.aikoyanye.django_client_demo

import org.json.JSONArray
import org.json.JSONObject

object JSONTOOL {

    fun analyze_some_json(data: String): List<Map<String, String>>{
        val list = mutableListOf<Map<String, String>>()
        val jsons = JSONArray(data)
        var i = 0
        while(i < jsons.length()){
            list.add(analyze_once_json(jsons.get(i).toString()))
            i++
        }
        return list
    }

    fun analyze_once_json(data: String): Map<String, String>{
        val map = mutableMapOf<String, String>()
        val obj = JSONObject(data)
        val it = obj.keys()
        while(it.hasNext()){
            val key = it.next()
            map.put(key, obj.getString(key))
        }
        return map
    }
}