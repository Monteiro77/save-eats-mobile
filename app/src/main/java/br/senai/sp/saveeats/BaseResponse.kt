package br.senai.sp.saveeats

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
    @SerializedName("clientes")
    var data: T? = null
)