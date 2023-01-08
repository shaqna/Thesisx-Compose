package com.ngedev.thesisx.data.mapper

import android.util.Log
import com.ngedev.thesisx.data.source.local.entity.LoanEntity
import com.ngedev.thesisx.data.source.remote.response.LoanResponse
import com.ngedev.thesisx.domain.model.LoanModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

fun LoanModel.toEntity(): LoanEntity =
    LoanEntity(
        this.uid,
        this.name,
        this.npm,
        this.phone,
        this.date,
        this.note,
        this.photoIdentity,
        this.book_title,
        this.author,
        this.year,
        this.category,
        this.userId,
        this.thesisId
    )

fun LoanResponse.toEntity(): LoanEntity =
    LoanEntity(
        this.uid,
        this.name,
        this.npm,
        this.phone,
        this.date,
        this.note,
        this.photoIdentity,
        this.book_title,
        this.author,
        this.year,
        this.category,
        this.userId,
        this.thesisId
    )

fun LoanEntity.toModel(): LoanModel {
    Log.d("MyModel", this.toString())
    return LoanModel(
        this.uid,
        this.name,
        this.npm,
        this.phone,
        this.date,
        this.note,
        this.photoIdentity,
        this.book_title,
        this.author,
        this.year,
        this.category,
        this.userId,
        this.thesisId
    )
}


fun Flow<LoanEntity>.toFlowModel(): Flow<LoanModel> {
    Log.d("MyMy", this.toString())
    return this.map {
        it.toModel()
    }
}


fun List<LoanEntity>.toListModel(): List<LoanModel> =
    this.map {
        it.toModel()
    }

fun List<LoanResponse>.toListEntity(): List<LoanEntity> =
    this.map {
        it.toEntity()
    }

fun Flow<List<LoanEntity>>.toListFlowModel() =
    this.map {
        it.toListModel()
    }