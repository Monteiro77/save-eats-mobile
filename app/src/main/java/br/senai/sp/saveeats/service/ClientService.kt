package br.senai.sp.saveeats.service

import br.senai.sp.saveeats.BaseResponse
import br.senai.sp.saveeats.BaseResponseStatus
import br.senai.sp.saveeats.model.ClientAddress
import br.senai.sp.saveeats.model.ClientAddressList
import br.senai.sp.saveeats.model.ClientEmail
import br.senai.sp.saveeats.model.ClientEmailList
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ClientService {
    @GET("/v1/saveeats/endereco/cliente/id/{id}")
    fun getAddressClient(@Path("id") id: Int):Call<ClientAddressList>

    @GET("kalos/aluno/email/{email}")
    suspend fun getClienteByEmail(@Path("email") email: String): Response<BaseResponseStatus<ClientEmailList>>


}