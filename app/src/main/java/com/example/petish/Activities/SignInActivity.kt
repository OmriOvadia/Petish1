package com.example.petish.Activities

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.petish.R
import com.example.petish.ViewModel.SignInViewModel
import com.jakewharton.rxbinding2.widget.RxCompoundButton
import com.jakewharton.rxbinding2.widget.RxTextView
import com.moveosoftware.infrastructure.mvvm.view.BaseActivity
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_sign_in.*

class SignInActivity : BaseActivity<SignInViewModel>() {
    override fun getViewModelClass(): Class<SignInViewModel> = SignInViewModel::class.java


    override fun getLayout(): Int {
      return R.layout.activity_sign_in
    }



  //  private lateinit var mySignInVIewModel: SignInViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      //  setContentView(R.layout.activity_sign_in)

     //   mySignInVIewModel = ViewModelProvider(this).get(SignInViewModel::class.java);
      //  initView()
        initObservables()
        initLiveDataObservable()

    }

    private fun initObservables() {
        mViewModel.setNameObservalbe(RxTextView.textChangeEvents(et_name_signUpActivity))
        mViewModel.setPasswordObservalbe(RxTextView.textChangeEvents(et_password_signUpActivity))
        mViewModel.setUEmailObservalbe(RxTextView.textChangeEvents(et_email_signUpActivity))
        mViewModel.setCheckboxObservalbe(RxCompoundButton.checkedChanges(checkbox_privacyPolicy_signUpActivity))


    }
    private fun initLiveDataObservable(){
        mViewModel.getLiveData().observe(this,  Observer<SignInViewModel.TextInputValid>{
            when (it){
                is SignInViewModel.TextInputValid.NameError->{
                    et_name_signUpActivity.error = "Name is empty"
                }
                is SignInViewModel.TextInputValid.PasswordError -> {
                    et_password_signUpActivity.error = "Password is empty"
                }
                is SignInViewModel.TextInputValid.EmailError -> {
                    et_email_signUpActivity.error = "Email is incorrect"
                }
                is SignInViewModel.TextInputValid.IsValid -> {
                    btn_sign_in_SignInActivity.isEnabled = it.isValid
                }
            }
        }
        )
    }
    override fun initView() {
        btn_sign_in_SignInActivity.setOnClickListener {
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }


    }


}
