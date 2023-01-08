package com.ngedev.thesisx.data.mapper

import com.ngedev.thesisx.data.source.remote.response.CupBoardResponse
import com.ngedev.thesisx.domain.model.CupBoardModel


fun CupBoardResponse.toModel(): CupBoardModel {
    return CupBoardModel(
        this.key
    )
}