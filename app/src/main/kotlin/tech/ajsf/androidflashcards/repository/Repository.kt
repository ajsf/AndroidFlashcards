package tech.ajsf.androidflashcards.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import tech.ajsf.androidflashcards.db.FlashcardDao
import tech.ajsf.androidflashcards.db.FlashcardEntity
import tech.ajsf.androidflashcards.model.Flashcard
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.concurrent.thread

data class FirebaseFlashcard(
    var question: String = "",
    var answer: String = "",
    var category: String = ""
)

interface Repository<T> {
    fun loadData(): LiveData<List<T>>
    fun updateItem(item: T)
}

@Singleton
class FlashcardRepository @Inject constructor(private val flashcardDao: FlashcardDao) :
    Repository<Flashcard> {

    private val liveData = MediatorLiveData<List<Flashcard>>()

    override fun loadData(): LiveData<List<Flashcard>> {
        liveData.addSource(flashcardDao.getFlashcards()) { flashcards ->
            if (flashcards.isNullOrEmpty()) {
                FirebaseDatabase.getInstance().reference
                    .addListenerForSingleValueEvent(object : ValueEventListener {
                        override fun onDataChange(snapshot: DataSnapshot) {
                            val fbFlashcards = snapshot.children
                                .mapNotNull { it.getValue(FirebaseFlashcard::class.java) }

                            thread {
                                flashcardDao.insertAll(fbFlashcards.map {
                                    FlashcardEntity(
                                        it.question,
                                        it.answer,
                                        it.category
                                    )
                                })
                            }
                        }

                        override fun onCancelled(error: DatabaseError) {
                            error.toException().printStackTrace()
                        }
                    })
            } else {
                liveData.postValue(flashcards.map {
                    Flashcard(
                        it.question,
                        it.answer,
                        it.category
                    )
                })
            }
        }
        return liveData
    }

    override fun updateItem(item: Flashcard) {
        flashcardDao.update(FlashcardEntity(item.question, item.answer, item.category))
    }
}