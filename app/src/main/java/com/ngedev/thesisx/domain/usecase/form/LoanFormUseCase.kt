package com.ngedev.thesisx.domain.usecase.form

import android.net.Uri
import com.ngedev.thesisx.domain.Resource
import com.ngedev.thesisx.domain.model.LoanModel
import com.ngedev.thesisx.domain.model.User
import kotlinx.coroutines.flow.Flow

interface LoanFormUseCase {
    fun uploadForm(form: LoanModel, imageUri: Uri): Flow<Resource<Unit>>
    fun getCurrentUser(): Flow<Resource<User>>
    fun updateThesisAvailability(state: Boolean, thesisId: String): Flow<Unit>

}