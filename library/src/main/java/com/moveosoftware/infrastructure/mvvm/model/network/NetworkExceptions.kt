package com.moveosoftware.infrastructure.mvvm.model.network

import android.content.Context
import com.moveosoftware.infrastructure.R

/**
 * Created by eliran on 26/02/2018.
 */

class NetworkExceptions{
    companion object {
        fun getNoServerConnectivityError(context: Context) :Exception{
            return Exception(context.resources.getString(R.string.no_server_connectivity_error))
        }
        fun getNoNetworkConnectivityError(context: Context) :Exception{
            return Exception(context.resources.getString(R.string.no_network_connectivity_error))
        }
        fun getUnexpectedError(context: Context) :Exception{
            return Exception(context.resources.getString(R.string.un_expected_error))
        }


    }
}