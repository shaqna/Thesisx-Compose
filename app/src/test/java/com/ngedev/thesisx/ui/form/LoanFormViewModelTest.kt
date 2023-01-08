package com.ngedev.thesisx.ui.form

import android.net.Uri
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ngedev.thesisx.domain.Resource
import com.ngedev.thesisx.domain.model.LoanModel
import com.ngedev.thesisx.domain.usecase.form.LoanFormUseCase
import com.ngedev.thesisx.utils.CoroutinesTestRule
import com.ngedev.thesisx.utils.DataDummy
import com.ngedev.thesisx.utils.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import org.junit.Assert.*

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class LoanFormViewModelTest {

    private lateinit var viewModel: LoanFormViewModel

    @get:Rule
    val instantExecutor = InstantTaskExecutorRule()

    @get:Rule
    val coroutineTestRule = CoroutinesTestRule()

    @Mock
    private lateinit var useCase: LoanFormUseCase

    @Mock
    private lateinit var imageMock: Uri

    @Mock
    private lateinit var formMock: LoanModel

    @Before
    fun setupViewModel() {
        viewModel = LoanFormViewModel(useCase)
    }

    @Test
    fun `verify successful loan`() {

        val flowData = flowOf(Resource.Success(null))
        val expectedResponse = DataDummy.generateDummyFormResponse()

        Mockito.`when`(useCase.uploadForm(formMock, imageMock)).thenReturn(flowData)
        val actualResponse = viewModel.uploadForm(formMock, imageMock).getOrAwaitValue().data

        assertEquals(expectedResponse, actualResponse)

        Mockito.verify(useCase).uploadForm(formMock, imageMock)

    }

}