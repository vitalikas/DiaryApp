package lt.vitalijus.diaryapp.domain

sealed class Response<out T> {

    object Pending : Response<Nothing>()

    object Loading : Response<Nothing>()

    data class Success<out T>(
        val data: T? = null
    ) : Response<T>()

    data class Failure(
        val e: Exception
    ) : Response<Nothing>()
}
