package com.example.freedictionary.data.repository.firebase

import com.example.freedictionary.domain.model.WordRefFirebase
import com.example.freedictionary.domain.repository.firebase.FirebaseRepository
import com.example.freedictionary.util.FirebaseHelper
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject
import kotlin.coroutines.suspendCoroutine

class FirebaseRepositoryImpl @Inject constructor(
    database: FirebaseDatabase
) : FirebaseRepository {

    private val favoriteRef = database.reference
        .child(FAVORITE_REFERENCE)
        .child(FirebaseHelper.getUserId())

    private val historicRef = database.reference
        .child(HISTORIC_REFERENCE)
        .child(FirebaseHelper.getUserId())

    companion object {
        const val FAVORITE_REFERENCE = "favorites"
        const val HISTORIC_REFERENCE = "historic"
    }

    override suspend fun saveFavorite(wordRefFirebase: WordRefFirebase) {
        return suspendCoroutine { continuation ->
            wordRefFirebase.id = favoriteRef.push().key ?: ""
            favoriteRef
                .child(wordRefFirebase.id)
                .setValue(wordRefFirebase).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        continuation.resumeWith(Result.success(Unit))
                    } else {
                        task.exception?.let {
                            continuation.resumeWith(Result.failure(it))
                        }
                    }
                }
        }
    }

    override suspend fun removeFavorite(wordId: String) {
        return suspendCoroutine { continuation ->
            favoriteRef
                .child(wordId)
                .removeValue().addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        continuation.resumeWith(Result.success(Unit))
                    } else {
                        task.exception?.let {
                            continuation.resumeWith(Result.failure(it))
                        }
                    }
                }
        }
    }

    override fun getFavorites(): Flow<Result<List<WordRefFirebase>>> = callbackFlow {
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val favorites = dataSnapshot.children.map { ds ->
                    ds.getValue(WordRefFirebase::class.java)
                }
                this@callbackFlow.trySendBlocking(Result.success(favorites.filterNotNull()))
            }

            override fun onCancelled(error: DatabaseError) {
                this@callbackFlow.trySendBlocking(Result.failure(error.toException()))
            }
        }

        favoriteRef.addValueEventListener(postListener)

        awaitClose {
            favoriteRef.removeEventListener(postListener)
        }
    }

    override suspend fun saveWordHistoric(wordRefFirebase: WordRefFirebase) {
        return suspendCoroutine { continuation ->
            wordRefFirebase.id = ""
            historicRef
                .child(wordRefFirebase.idLocal.toString())
                .setValue(wordRefFirebase).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        continuation.resumeWith(Result.success(Unit))
                    } else {
                        task.exception?.let {
                            continuation.resumeWith(Result.failure(it))
                        }
                    }
                }
        }
    }

    override fun getHistorics(): Flow<Result<List<WordRefFirebase>>> = callbackFlow {
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val wordHistoric = dataSnapshot.children.map { ds ->
                    ds.getValue(WordRefFirebase::class.java)
                }
                this@callbackFlow.trySendBlocking(Result.success(wordHistoric.filterNotNull()))
            }

            override fun onCancelled(error: DatabaseError) {
                this@callbackFlow.trySendBlocking(Result.failure(error.toException()))
            }
        }

        historicRef.addValueEventListener(postListener)

        awaitClose {
            historicRef.removeEventListener(postListener)
        }
    }

}