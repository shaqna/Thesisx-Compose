package com.ngedev.thesisx.domain.usecase.cupboard


import com.ngedev.thesisx.domain.Resource
import com.ngedev.thesisx.domain.model.CupBoardModel
import com.ngedev.thesisx.domain.repository.ICupBoardRepository
import kotlinx.coroutines.flow.Flow

class CupBoardInteractor(private val repository: ICupBoardRepository): CupBoardUseCase {
    override fun getNewLockerKey(): Flow<Resource<CupBoardModel>> {
        return repository.getLockerKey()
    }

}