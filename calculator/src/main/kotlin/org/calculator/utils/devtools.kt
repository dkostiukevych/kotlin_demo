package org.calculator.utils

/**
 * Created by sepi on 26.08.17.
 */
fun openDevTools() {
    val resp = khttp.get("http://localhost:9222/json/version").jsonObject["WebKit-Version"]
    val hash = resp.toString().substringAfter("(").substringBefore(")")//Regex("\\\\((.*?)\\\\)").find(resp.toString())?.value
    val tabs = khttp.get("http://localhost:9222/json").jsonArray.getJSONObject(0)["devtoolsFrontendUrl"]
    val id = tabs.toString().removePrefix("/devtools/")//Regex("[^/]+\$").find(tabs.toString())?.value
    val url_template = "https://chrome-devtools-frontend.appspot.com/serve_file/$hash/$id&remoteFrontend=true"
    println(url_template)
}