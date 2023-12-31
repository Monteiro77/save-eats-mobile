package br.senai.sp.saveeats.model

import br.senai.sp.saveeats.service.CategoryRestaurantService
import br.senai.sp.saveeats.service.CategoryService
import br.senai.sp.saveeats.service.ClientService
import br.senai.sp.saveeats.service.LoginService
import br.senai.sp.saveeats.service.ProductsRestaurantService
import br.senai.sp.saveeats.service.RecipesService
import br.senai.sp.saveeats.service.RestaurantService
import br.senai.sp.saveeats.service.SignupService
import br.senai.sp.saveeats.service.TipsService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitFactory {

    private const val baseURL = "http://192.168.100.164:8080/"

    private const val baseURL2 = "http://10.107.144.8:8080/"

    private const val baseURL3 = "https://save-eats.cyclic.cloud/"



    private var retrofitFactory = Retrofit
        .Builder()
        .baseUrl(baseURL2)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getSignup(): SignupService {
        return retrofitFactory.create(SignupService::class.java)
    }
    fun getLogin(): LoginService {
        return retrofitFactory.create(LoginService::class.java)
    }
    fun getCategory(): CategoryService {
        return retrofitFactory.create(CategoryService::class.java)
    }
    fun getRestaurant(): RestaurantService {
        return retrofitFactory.create(RestaurantService::class.java)
    }
    fun getProductsRestaurant(): ProductsRestaurantService {
        return retrofitFactory.create(ProductsRestaurantService::class.java)
    }
    fun getCategoryRestaurant(): CategoryRestaurantService {
        return retrofitFactory.create(CategoryRestaurantService::class.java)
    }

    fun getAddressClient(): ClientService {
        return retrofitFactory.create(ClientService::class.java)
    }

    fun getRecipes(): RecipesService {
        return retrofitFactory.create(RecipesService::class.java)
    }

    fun getCategoryRecipes(): RecipesService {
        return retrofitFactory.create(RecipesService::class.java)
    }

    fun getRecipeDetails(): RecipesService {
        return retrofitFactory.create(RecipesService::class.java)
    }

    fun getTips(): TipsService {
        return retrofitFactory.create(TipsService::class.java)
    }

    fun getCategoryTips(): TipsService {
        return retrofitFactory.create(TipsService::class.java)
    }

    fun getClientByEmail(): ClientService {
        return retrofitFactory.create(ClientService::class.java)
    }

    fun getInstance(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(baseURL2)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

}