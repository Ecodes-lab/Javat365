package com.apek.javat365.activities.auth

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import com.apek.javat365.R
import com.apek.javat365.activities.BaseActivity
import com.apek.javat365.activities.MainActivity
import com.apek.javat365.api.ApiClient
import com.apek.javat365.models.LoginRequest
import com.apek.javat365.models.User
import com.apek.javat365.storage.UserSharedPrefManager
import com.apek.javat365.utils.Constants
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import kotlinx.android.synthetic.main.activity_sign_in_confirmation.*
import org.json.JSONException
import org.json.JSONObject
import retrofit2.*
import java.lang.Exception


class SignInConfirmationActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in_confirmation)

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
        if (Constants.isNetworkAvailable(this@SignInConfirmationActivity)) {
            val codeOne = et_code_one.text.toString().trim { it <= ' ' }
            val codeTwo = et_code_two.text.toString().trim { it <= ' ' }
            val codeThree = et_code_three.text.toString().trim { it <= ' ' }
            val codeFour = et_code_four.text.toString().trim { it <= ' ' }


            if (validateForm(codeOne, codeTwo, codeThree, codeFour)) {


                val email =
                    UserSharedPrefManager.getInstance(this@SignInConfirmationActivity).user.user.email.toString()
                val code = codeOne + codeTwo + codeThree + codeFour

                val auth = LoginRequest(email, code)

                showProgressDialog(resources.getString(R.string.please_wait))

                ApiClient.instance.userConfirmLogin(auth)
                    .enqueue(object : Callback<User> {

                        override fun onResponse(
                            call: Call<User>,
                            response: Response<User>?
                        ) {

                            if (response != null) {
                                if (response.isSuccessful) {
                                    hideProgressDialog()
                                    navigateToActivity(
                                        this@SignInConfirmationActivity,
                                        MainActivity::class.java
                                    )
                                    response.body()?.let {
                                        UserSharedPrefManager.getInstance(this@SignInConfirmationActivity)
                                            .saveUser(it)
                                    }
                                } else {
                                    hideProgressDialog()
                                    try {
                                        val jObjError =
                                            JSONObject(response.errorBody()!!.string())
                                        if(jObjError.getString("name") != "RestException") {
                                            Toast.makeText(
                                                this@SignInConfirmationActivity,
                                                jObjError.getString("message"),
                                                Toast.LENGTH_LONG
                                            ).show()
                                        }
                                    } catch (e: Exception) {
//                                    Toast.makeText(
//                                        this@SignInConfirmationActivity,
//                                        e.message,
//                                        Toast.LENGTH_LONG
//                                    ).show()
                                        e.message?.let { Log.e("errorrr", it) }
                                    }
                                }
                            }
                        }

                        override fun onFailure(call: Call<User>, t: Throwable) {
                            hideProgressDialog() // Hides the progress dialog
                            if (t != null) {

                                Toast.makeText(
                                    this@SignInConfirmationActivity,
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

    private fun ApiJsonMap(email:String, code:String): JsonObject {
        var gsonObject = JsonObject()
        try {
            val jsonObj_ = JSONObject()
            jsonObj_.put("email", email)
            jsonObj_.put("code", code)
            val jsonParser = JsonParser()
            gsonObject = jsonParser.parse(jsonObj_.toString()) as JsonObject

            //print parameter
            Log.e("MY gson.JSON:  ", "AS PARAMETER  $gsonObject")
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return gsonObject
    }

    private fun validateForm(codeOne: String, codeTwo: String, codeThree: String, codeFour: String): Boolean {
        return when {
            TextUtils.isEmpty(codeOne) -> {
                showErrorSnackBar("Fields can not be empty")
                false
            }
            TextUtils.isEmpty(codeTwo) -> {
                showErrorSnackBar("Fields can not be empty")
                false
            }
            TextUtils.isEmpty(codeThree) -> {
                showErrorSnackBar("Fields can not be empty")
                false
            }
            TextUtils.isEmpty(codeFour) -> {
                showErrorSnackBar("Fields can not be empty")
                false
            }
            else -> {
                true
            }
        }
    }

    private fun moveToNext() {
        val codeOne = et_code_one.text.toString().trim { it <= ' ' }
        val codeTwo = et_code_two.text.toString().trim { it <= ' ' }
        val codeThree = et_code_three.text.toString().trim { it <= ' ' }
        val codeFour = et_code_four.text.toString().trim { it <= ' ' }

        if(codeOne.isNotEmpty()){

        }
    }
}