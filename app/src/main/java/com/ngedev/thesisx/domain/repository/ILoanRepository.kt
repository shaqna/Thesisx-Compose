package com.ngedev.thesisx.domain.repository

import android.net.Uri
import com.ngedev.thesisx.domain.Resource
import com.ngedev.thesisx.domain.model.LoanModel
import com.ngedev.thesisx.domain.model.User
import kotlinx.coroutines.flow.Flow

interface ILoanRepository {
    fun uploadForm(form: LoanModel, imageUri: Uri): Flow<Resource<Unit>>
    fun deleteLoan(id: String): Flow<Resource<Unit>>
    fun getLoanById(id: String): Flow<Resource<LoanModel>>
    fun getMyLoan(ids: List<String>): Flow<Resource<List<LoanModel>>>
    fun getCurrentUser(): Flow<Resource<User>>
    fun updateThesisAvailability(state: Boolean, thesisId: String): Flow<Unit>
    fun getCurrentUserId(): String
    fun getUser(id: String): Flow<Resource<User>>
    fun refreshUser(): Flow<Resource<Unit>>
//    suspend fun clearLoanWhereIsDeleted()
}