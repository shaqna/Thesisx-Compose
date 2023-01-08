package com.ngedev.thesisx.ui.cupboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.ngedev.thesisx.domain.Resource
import com.ngedev.thesisx.domain.model.CupBoardModel
import com.ngedev.thesisx.domain.usecase.cupboard.CupBoardUseCase

class CupBoardViewModel(private val useCase: CupBoardUseCase): ViewModel() {
    fun getKey(): LiveData<Resource<CupBoardModel>> =
        useCase.getNewLockerKey().asLiveData()
}