package com.example.petish.ViewModel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.petish.Utils.isEmail
import com.jakewharton.rxbinding2.widget.TextViewTextChangeEvent
import com.moveosoftware.infrastructure.mvvm.viewmodel.BaseViewModel
import io.reactivex.*
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Function
import io.reactivex.functions.Function3

class SignInViewModel : BaseViewModel() {

    private var setErrors: MutableLiveData<TextInputValid> = MutableLiveData();
    //var isInputValid: MutableLiveData<Boolean> = MutableLiveData(false)
    private var isNameValid = false
    private var isEmailValid = false
    private var isPasswordValid = false
    private var isCheckboxSelected = false

    fun getLiveData():MutableLiveData<TextInputValid>{
        return setErrors
    }


    @SuppressLint("CheckResult")
    fun setNameObservalbe(observable: Observable<TextViewTextChangeEvent>) {
        observable
            .subscribe {
                isNameValid = it.text().toString().isNotEmpty()
                if(!isNameValid) setErrors.postValue(TextInputValid.NameError)
                allIsValid()
            }
    }


    fun setPasswordObservalbe(observable: Observable<TextViewTextChangeEvent>) {

        observable.subscribe(object : Observer<TextViewTextChangeEvent>{
            override fun onComplete() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onSubscribe(d: Disposable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onNext(t: TextViewTextChangeEvent) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onError(e: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })
        observable
            .subscribe {
                isPasswordValid = it.text().toString().isNotEmpty()
                if (!isPasswordValid) setErrors.postValue(TextInputValid.PasswordError)
                allIsValid()
            }

    }


    @SuppressLint("CheckResult")
    fun setUEmailObservalbe(observable: Observable<TextViewTextChangeEvent>) {
        observable
            .subscribe {
                isEmailValid = it.text().toString().isEmail()
                if(!isEmailValid) setErrors.postValue(TextInputValid.EmailError)
                allIsValid()
            }
    }

    private fun allIsValid() {
        setErrors.postValue(TextInputValid.IsValid(isEmailValid and isCheckboxSelected and isPasswordValid and isNameValid))
    }

    @SuppressLint("CheckResult")
    fun setCheckboxObservalbe(observable: Observable<Boolean>) {
        observable
            .subscribe {
                isCheckboxSelected = it
                allIsValid()
            }
    }

    sealed class TextInputValid{
        object NameError : TextInputValid()
        object EmailError: TextInputValid()
        object PasswordError: TextInputValid()
        data class IsValid(var isValid: Boolean) : TextInputValid()
    }
}