package br.senai.sp.saveeats.service

import br.senai.sp.saveeats.model.ClientAddress
import br.senai.sp.saveeats.model.ClientAddressList
import br.senai.sp.saveeats.model.ClientEmailList
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ClientService {
    @GET("/v1/saveeats/endereco/cliente/id/{id}")
    fun getAddressClient(@Path("id") id: Int):Call<ClientAddressList>

    @GET("/v1/saveeats/cliente/email/{email}")
    fun getClientByEmail(@Path("email") email: String):Response<ClientEmailList>


}