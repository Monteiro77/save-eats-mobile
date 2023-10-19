package br.senai.sp.saveeats.components

import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import br.senai.sp.saveeats.R
import br.senai.sp.saveeats.model.RetrofitFactory
import br.senai.sp.saveeats.service.ClientService
import br.senai.sp.saveeats.viewmodel.LoginScreenViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.launch


private val auth: FirebaseAuth = Firebase.auth
private val loading = MutableLiveData(false)

@Composable
fun LoginGoogle(
    navController: NavController,
    viewModel: LoginScreenViewModel,
    localStorage: br.senai.sp.saveeats.Storage,
    lifecycleCoroutineScope: LifecycleCoroutineScope
) {


    val auth = FirebaseAuth.getInstance()
    val currentUser = auth.currentUser

    var statusFirebase = remember{ mutableStateOf(false) }
    val token = "792868771874-5kpa8c7ecrdmtdtl7fr7uscs5nhgf8f8.apps.googleusercontent.com"
    val context =  LocalContext.current
    val user = Firebase.auth.currentUser
    val userEmail = user?.email

    //Login via email
    val emailUserFirebase = localStorage.saveDataString(context, userEmail!!, "userEmail")

    val clienteService : ClientService = RetrofitFactory.getInstance().create(ClientService::class.java)

    val launcher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.StartActivityForResult(),
            ){
            val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
            try {
                val accocunt = task.getResult(ApiException::class.java)
                val credential = GoogleAuthProvider.getCredential(accocunt.idToken, null)
                viewModel.signInWithGoogleCredential(credential) {
                    lifecycleCoroutineScope.launch {

                        var response = clienteService.getClienteByEmail(userEmail.toString())

                        Log.e("TESTE1",  "${response.body()?.data?.clientes?.email}")
                        Log.e("TESTE2",  "${userEmail}")

//                        var idClient = result.body()?.clientes?.id
//
//                        if(userEmail == emailUserFirebase) {
//
//                            if (result.isSuccessful) {
//                                localStorage.saveDataString(context, "${userEmail}", "email")
//                                localStorage.saveDataString(context, "${idClient}", "idCliente")
//
//                                navController.navigate("home_screen")
//                            } else {
//                                navController.navigate("first_signup_screen")
//                            }
//                        }else{
//                            navController.navigate("home_screen")
//                        }


                    }
                }
            }catch (ex : Exception){
                Log.d("Falha no login", "Login Falhou ")
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 50.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            IconButton(
                onClick = {
                    statusFirebase.value = true

                    val opcoes = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestIdToken(token).requestEmail().build()

                    localStorage.saveDataString(context, user.toString(), "userFirebase")
                    localStorage.saveDataString(context, userEmail.toString(), "userEmailFirebase")
                    localStorage.saveDataString(context, statusFirebase.toString(), "statusFirebase")

                    val googleSingInCliente = GoogleSignIn.getClient(context, opcoes)
                    launcher.launch(googleSingInCliente.signInIntent)
                }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.google),
                    contentDescription = "Imagem do Google",
                    modifier = Modifier.size(60.dp)
                )
        }
    }

}
