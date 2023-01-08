package com.ngedev.thesisx.data.repository


import android.util.Log
import com.ngedev.thesisx.data.cache.NetworkBoundRequest
import com.ngedev.thesisx.data.cache.NetworkBoundResource
import com.ngedev.thesisx.data.cache.NetworkRequest
import com.ngedev.thesisx.data.mapper.*
import com.ngedev.thesisx.data.source.local.LocalDataSource
import com.ngedev.thesisx.domain.model.Thesis
import com.ngedev.thesisx.domain.model.User
import com.ngedev.thesisx.data.source.remote.RemoteDataSource
import com.ngedev.thesisx.data.source.remote.network.FirebaseResponse
import com.ngedev.thesisx.data.source.remote.response.ThesisResponse
import com.ngedev.thesisx.data.source.remote.response.UserResponse
import com.ngedev.thesisx.domain.Resource
import com.ngedev.thesisx.domain.repository.IThesisRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow

class ThesisRepositoryImpl(
    private val remote: RemoteDataSource,
    private val local: LocalDataSource
) : IThesisRepository {
    override fun getAllThesis(): Flow<Resource<List<Thesis>>> =
        object : NetworkBoundResource<List<Thesis>, List<ThesisResponse>>() {
            override fun loadFromDB(): Flow<List<Thesis>?> {
                return local.selectAllThesis().toListFlowModel()
            }

            override fun shouldFetch(data: List<Thesis>?): Boolean =
                //data == null || data.isEmpty()
                true

            override suspend fun createCall(): Flow<FirebaseResponse<List<ThesisResponse>>> =
                remote.getAllThesis()

            override suspend fun saveCallResult(data: List<ThesisResponse>) {
                //local.clearThesis()
                local.insertTheses(data.toListEntity())
            }

        }.asFlow()

    override fun getAllThesisByCategory(category: String): Flow<Resource<List<Thesis>>> =
        object : NetworkBoundResource<List<Thesis>, List<ThesisResponse>>() {
            override fun loadFromDB(): Flow<List<Thesis>?> {
                return local.selectAllThesisByCategory(category).toListFlowModel()
            }

            override fun shouldFetch(data: List<Thesis>?): Boolean {
                return data == null || data.isEmpty()
            }

            override suspend fun createCall(): Flow<FirebaseResponse<List<ThesisResponse>>> {
                return remote.getAllThesis()
            }

            override suspend fun saveCallResult(data: List<ThesisResponse>) {
                local.insertTheses(data.toListEntity())
            }

        }.asFlow()

    override fun getAllFavorites(ids: List<String>): Flow<Resource<List<Thesis>>> =
        object : NetworkBoundResource<List<Thesis>, List<ThesisResponse>>() {
            override fun loadFromDB(): Flow<List<Thesis>?> {
                return local.selectAllFavorites(ids).toListFlowModel()
            }

            override fun shouldFetch(data: List<Thesis>?): Boolean {
                return true
            }

            override suspend fun createCall(): Flow<FirebaseResponse<List<ThesisResponse>>> {
                return remote.getMyFavorite(ids)
            }

            override suspend fun saveCallResult(data: List<ThesisResponse>) {
                local.insertTheses(data.toListEntity())
            }

        }.asFlow()


    override fun getThesisById(id: String): Flow<Resource<Thesis>> =
        object : NetworkBoundResource<Thesis, ThesisResponse>() {
            override fun loadFromDB(): Flow<Thesis?> {
                return local.selectThesisById(id).toFlowModel()
            }

            override fun shouldFetch(data: Thesis?): Boolean {
                return data == null
            }

            override suspend fun createCall(): Flow<FirebaseResponse<ThesisResponse>> {
                return remote.getThesisById(id)
            }

            override suspend fun saveCallResult(data: ThesisResponse) {
                Log.d("Dataku", data.thesisAbstract)
                local.insertThesis(data.toEntity())
            }

        }.asFlow()

    override fun addFavoriteThesis(id: String): Flow<Resource<Unit>> =
        object : NetworkBoundRequest<UserResponse>() {
            override suspend fun createCall(): Flow<FirebaseResponse<UserResponse>> {
                return remote.addFavoriteThesis(id)
            }

            override suspend fun saveCallResult(data: UserResponse) {
                local.insertUser(data.toEntity())
            }

        }.asFlow()

    override fun deleteFavoriteThesis(id: String): Flow<Resource<Unit>> =
        object : NetworkBoundRequest<UserResponse>() {
            override suspend fun createCall(): Flow<FirebaseResponse<UserResponse>> {
                return remote.deleteFavoriteThesis(id)
            }

            override suspend fun saveCallResult(data: UserResponse) {
                local.insertUser(data.toEntity())
            }

        }.asFlow()



    override fun getCurrentUser(): Flow<Resource<User>> =
        flow {
            val userId = getCurrentUserId()
            if (userId.isNotEmpty()) {
                emitAll(getUser(userId))
            }
        }

    override fun getCurrentUserId(): String =
        remote.getCurrentUserId()


    override fun getUser(id: String): Flow<Resource<User>> =
        object : NetworkBoundResource<User, UserResponse>() {
            override fun loadFromDB(): Flow<User?> {
                return local.selectUser().toFlowModel()
            }

            override fun shouldFetch(data: User?): Boolean {
                return data == null
            }

            override suspend fun createCall(): Flow<FirebaseResponse<UserResponse>> {
                return remote.getCurrentUser(id)
            }

            override suspend fun saveCallResult(data: UserResponse) {
                local.insertUser(data.toEntity())
            }

        }.asFlow()

    override fun searchThesis(title: String): Flow<Resource<List<Thesis>>> =
        object : NetworkBoundResource<List<Thesis>, List<ThesisResponse>>() {
            override fun loadFromDB(): Flow<List<Thesis>?> {
                return local.searchThesis(title).toListFlowModel()
            }

            override fun shouldFetch(data: List<Thesis>?): Boolean {
                return true
            }

            override suspend fun createCall(): Flow<FirebaseResponse<List<ThesisResponse>>> {
                return remote.searchThesisByTitle(title)
            }

            override suspend fun saveCallResult(data: List<ThesisResponse>) {
                local.insertTheses(data.toListEntity())
            }

        }.asFlow()

//    override fun searchThesis(title: String): Flow<Resource<List<Thesis>>> =
//        object : NetworkRequest<List<Thesis>, List<ThesisResponse>>() {
//            override suspend fun createCall(): Flow<FirebaseResponse<List<ThesisResponse>>> {
//                return remote.searchThesisByTitle(title)
//            }
//
//            override fun fetchResult(result: List<ThesisResponse>): List<Thesis> {
//                return result.toModels()
//            }
//
//        }.asFlow()


    override fun updateThesisAvailability(state: Boolean, thesisId: String): Flow<Unit> =
        remote.updateThesisAvailability(state, thesisId)


}