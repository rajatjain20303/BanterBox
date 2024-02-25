package com.example.banterbox.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import com.example.banterbox.data.Result
class UserRepository(private val auth:FirebaseAuth,
      private val firestore:FirebaseFirestore) {
      suspend fun signUp(email: String, password: String, firstName: String, lastName: String): Result<Boolean> =
            try {
                  auth.createUserWithEmailAndPassword(email, password).await()
                  val user = User(firstName, lastName, email)
                  saveUserToFirestore(user)
                  Result.Success(true)
            } catch (e: Exception) {
                  Result.Error(e)
            }
      suspend fun login(email:String, password:String):Result<Boolean> =
      try{
            auth.signInWithEmailAndPassword(email,password).await()
            Result.Success(true)
      } catch(e:Exception){
            Result.Error(e)
      }

      private suspend fun saveUserToFirestore(user: User) {
            firestore.collection("users").document(user.email).set(user).await()
      }
}