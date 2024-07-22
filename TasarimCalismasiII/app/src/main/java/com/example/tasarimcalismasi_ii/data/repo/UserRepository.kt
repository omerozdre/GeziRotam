package com.example.tasarimcalismasi_ii.data.repo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tasarimcalismasi_ii.data.entity.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class UserRepository(private val auth: FirebaseAuth,
                     private val firestore: FirebaseFirestore) {

/*    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage
*/

    suspend fun kayit(user: User): Boolean {
        return try {
            val result = auth.createUserWithEmailAndPassword(user.kullanici_email.toString(),
                user.kullanici_sifre.toString()).await()
            val userId = result.user?.uid ?: return false
            firestore.collection("users").document(userId).set(user).await()
            true
        } catch (e: Exception) {
            false
        }
    }

    suspend fun giris(kullanici_email: String, kullanici_sifre: String): Boolean {
        return try {
            auth.signInWithEmailAndPassword(kullanici_email , kullanici_sifre).await()
            true
        } catch (e: Exception) {
            false
        }
    }
/*
    fun logout() {
        auth.signOut()
    }

    suspend fun getCurrentUser(): User? {
        val userId = auth.currentUser?.uid ?: return null
        val snapshot = firestore.collection("users").document(userId).get().await()
        return snapshot.toObject(User::class.java)
    }
*/
}