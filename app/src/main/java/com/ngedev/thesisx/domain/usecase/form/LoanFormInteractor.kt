package com.ngedev.thesisx.domain.usecase.form

import android.net.Uri
import com.ngedev.thesisx.domain.Resource
import com.ngedev.thesisx.domain.model.LoanModel
import com.ngedev.thesisx.domain.model.User
import com.ngedev.thesisx.domain.repository.ILoanRepository
import kotlinx.coroutines.flow.Flow

class LoanFormInteractor(
    private val formRepository: ILoanRepository,
) : LoanFormUseCase {
    override fun uploadForm(form: LoanModel, imageUri: Uri): Flow<Resource<Unit>> {
        return formRepository.uploadForm(form, imageUri)
    }

    override fun getCurrentUser(): Flow<Resource<User>> {
        return formRepository.getCurrentUser()
    }


    override fun updateThesisAvailability(state: Boolean, thesisId: String): Flow<Unit> {
        return formRepository.updateThesisAvailability(state, thesisId)
    }


}