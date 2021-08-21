package com.apek.javat365.activities.auth

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import com.apek.javat365.R
import com.apek.javat365.activities.BaseActivity
import com.apek.javat365.api.ApiClient
import com.apek.javat365.api.ApiService
import com.apek.javat365.models.LoginRequest
import com.apek.javat365.storage.UserSharedPrefManager
import com.apek.javat365.utils.Constants
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import kotlinx.android.synthetic.main.activity_signin.*
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception


class SigninActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

        // calling the action bar
        setupActionBar()

        btn_continue.setOnClickListener {
            loginUser()
        }
    }

    private fun setupActionBar() {

//        setSupportActionBar(toolbar_sign_in_activity)

        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_ios_new_24)
        }

//        toolbar_sign_in_activity.setNavigationOnClickListener { onBackPressed() }
    }

    private fun loginUser() {

        if (Constants.isNetworkAvailable(this@SigninActivity)) {
            val email: String = et_email.text.toString().trim { it <= ' ' }



            if (validateForm(email)) {

                val auth = LoginRequest(email, null)

//                val retrofit = Retrofit.Builder()
//                    .baseUrl("https://api.demo.javat365.com/")
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build()
//
//                val service: ApiService = retrofit.create<ApiService>(ApiService::class.java)
//                val loginCall: Call<LoginRequest> = service.userLogin(auth)

                showProgressDialog(resources.getString(R.string.please_wait))

//                loginCall.enqueue(object : Callback<LoginResponse> {
                ApiClient.instance.userLogin(auth).enqueue(object: Callback<Void> {

                    override fun onResponse(
                        call: Call<Void>,
                        response: Response<Void>?
                    ) {

                        if (response != null) {
                            Log.i("Res", response.isSuccessful.toString())
                            if (response.isSuccessful) {
                                hideProgressDialog()
                                UserSharedPrefManager.getInstance(this@SigninActivity)
                                    .saveEmail(email)
                                navigateToActivity(
                                    this@SigninActivity,
                                    SignInConfirmationActivity::class.java
                                )

                            } else {
                                hideProgressDialog()
                                try {
                                    val jObjError =
                                        JSONObject(response.errorBody()!!.string())
                                    Log.i("errorrrr type", jObjError.getString("name"))
                                    Toast.makeText(
                                        this@SigninActivity,
                                        jObjError.getString("message"),
                                        Toast.LENGTH_LONG
                                    ).show()
                                } catch (e: Exception) {
//                                    Toast.makeText(
//                                        this@SigninActivity,
//                                        e.message,
//                                        Toast.LENGTH_LONG
//                                    ).show()
                                    e.message?.let { Log.e("errorrr", it) }
                                }
                            }
                        }
                    }

                    override fun onFailure(call: Call<Void>, t: Throwable) {
                        hideProgressDialog() // Hides the progress dialog
                        if (t != null) {

                            Toast.makeText(
                                this@SigninActivity,
                                t.message.toString(),
                                Toast.LENGTH_LONG
                            ).show()

                            Log.e("Errorrrrr", t.message.toString())
                        }
                    }

                })

            }
        }
    }

    private fun validateForm(email: String): Boolean {
        return when {
            TextUtils.isEmpty(email) -> {
                showErrorSnackBar("Please enter your email address")
                false
            }
            else -> {
                true
            }
        }
    }

    private fun ApiJsonMap(email:String): JsonObject {
        var gsonObject = JsonObject()
        try {
            val jsonObj_ = JSONObject()
            jsonObj_.put("email", email)
            val jsonParser = JsonParser()
            gsonObject = jsonParser.parse(jsonObj_.toString()) as JsonObject

            //print parameter
            Log.e("MY gson.JSON:  ", "AS PARAMETER  $gsonObject")
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return gsonObject
    }
}