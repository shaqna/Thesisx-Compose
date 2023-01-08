package com.ngedev.thesisx.utils

import com.ngedev.thesisx.domain.model.CupBoardModel
import com.ngedev.thesisx.domain.model.LoanModel
import com.ngedev.thesisx.domain.model.Thesis
import com.ngedev.thesisx.domain.model.User

object DataDummy {
    fun generateDummyLoginResponse(): Unit? = null

    fun generateDummyRegisterResponse(): Unit? = null

    fun generateDummyResetPasswordResponse(): Unit? = null

    fun generateDummyCupBoardResponse(): CupBoardModel =
        CupBoardModel(key = "1234")

    fun generateDummyThesisResponse(): Thesis =
        Thesis(
            uid = "1",
            title = "title",
            author = "author",
            year = 2022,
            category = "category",
            thesisAbstract = "thesis abstract",
            borrowed = false,
            searchKeyword = "search me"
        )

    fun generateDummyListThesisResponse(): List<Thesis> {
        val listThesis = mutableListOf<Thesis>()

        for (i in 0..10) {
            val thesis = generateDummyThesisResponse()
            listThesis.add(thesis)
        }

        return listThesis
    }

    fun generateDummyFormResponse():Unit? = null

    fun generateDummyLoanListResponse(): List<LoanModel> {
        val listLoanModel = mutableListOf<LoanModel>()

        for (i in 0..10) {
            val loan = LoanModel(
                uid = "uid",
                name = "shaq-name",
                npm = "1900333444",
                phone = "098444",
                date = 0,
                note = "note-note",
                photoIdentity = "photo-identity",
                book_title = "book-title",
                author = "author",
                year = 0,
                category = "category",
                userId = "user-id",
                thesisId = "thesis-id"
            )
            listLoanModel.add(loan)
        }

        return listLoanModel
    }

    fun generateDummyUser() =
        User(
            uid = "uid",
            username = "username",
            email = "email",
            favorite = listOf("fav1","fav2"),
            borrowing = listOf("bor1", "bor2")
        )
}