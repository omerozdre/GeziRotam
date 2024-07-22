package com.example.tasarimcalismasi_ii.di

import com.example.tasarimcalismasi_ii.data.datasource.GezilerDataSource
import com.example.tasarimcalismasi_ii.data.repo.GezilerRepository
import com.example.tasarimcalismasi_ii.data.repo.UserRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideGezilerDataSource(collectionGeziler: CollectionReference) : GezilerDataSource{
        return GezilerDataSource(collectionGeziler)
    }

    @Provides
    @Singleton
    fun provideGezilerRepository(gds:GezilerDataSource) : GezilerRepository{
        return GezilerRepository(gds)
    }

    @Provides
    @Singleton
    fun provideCollectionReferencee() : CollectionReference{
        return Firebase.firestore.collection("Geziler")
    }

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideFirestore(): FirebaseFirestore = FirebaseFirestore.getInstance()

    @Provides
    @Singleton
    fun provideUserRepository(
        auth: FirebaseAuth,
        firestore: FirebaseFirestore
    ): UserRepository = UserRepository(auth, firestore)


}