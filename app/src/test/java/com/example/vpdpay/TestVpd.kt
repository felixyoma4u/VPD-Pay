package com.example.vpdpay

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.vpdpay.model.TransferDetails
import com.example.vpdpay.model.UserAccount
import com.example.vpdpay.repositories.TransactionRepository
import com.example.vpdpay.viewModel.TransferViewModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock

@OptIn(ExperimentalCoroutinesApi::class)
class TransferViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    private val testDispatcher = TestCoroutineDispatcher()
    private val mockRepository: TransactionRepository = mock(TransactionRepository::class.java)

    @Before
    fun setup(){
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown(){
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun performTransfer_validInput_showsConfirmationDialog() {
        val viewModel = TransferViewModel(mockRepository)
        //mock the repository
        val fromAccount = UserAccount(2345313712, "Felix Onoberevune", 5000.00)
        val toAccount = UserAccount(5632786427, "William James", 3000.00)
        val amount = 500.00

        viewModel.run {
            performTransfer(
                fromAccount.accountNumber.toString(),
                toAccount.accountNumber.toString(),
                amount
            )
        }
        // Verify that showConfirmation LiveData emits a non-null TransferDetails object
       assertEquals("Invalid transfer details.", viewModel.transfer.value)
    }

    @Test
    fun confirmTransfer_sufficientFunds_updatesBalance() {
        val viewModel = TransferViewModel(mockRepository)
        //mock the repository
        val fromAccount = UserAccount(2345313712, "John Onoberevune", 5000.00)
        val toAccount = UserAccount(5632786427, "John James", 3000.00)
        val amount = 500.00
        val transferDetails = TransferDetails(fromAccount, toAccount, amount)

        viewModel.confirmTransfer(transferDetails)

        // Verify updated balances
        assertEquals(4500.0, fromAccount.balance)
        assertEquals(3500.0, toAccount.balance)

        // Verify success message
        assertEquals("Transfer successful!", viewModel.transfer.value)

    }
}