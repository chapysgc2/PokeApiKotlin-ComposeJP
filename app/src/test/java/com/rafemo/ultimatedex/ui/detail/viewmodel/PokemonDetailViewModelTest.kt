package com.rafemo.ultimatedex.ui.detail.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.rafemo.ultimatedex.data.remote.response.Pokemon
import com.rafemo.ultimatedex.data.remote.response.Sprites
import com.rafemo.ultimatedex.repository.PokemonRepositoryImpl
import com.rafemo.ultimatedex.util.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.*
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner


@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class PokemonDetailViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var repository: PokemonRepositoryImpl
    private lateinit var viewModel: PokemonDetailViewModel

    @Before
    fun setUp() {
        repository = Mockito.mock(PokemonRepositoryImpl::class.java)
        viewModel = PokemonDetailViewModel(repository)
    }

    @Test
    fun getPokemonDetails() = runTest {
        val name = "Bulbasaur"

        Mockito.`when`(viewModel.getPokemonDetail(name)).thenReturn(getMockResponse())
        val result = viewModel.getPokemonDetail(name)

        Assert.assertEquals(getMockResponse().data, result.data)
    }

    private fun getMockResponse(): Resource<Pokemon> {
        return Resource.Success(getBulbasaur())
    }

    private fun getBulbasaur(): Pokemon {
        return Pokemon(7, 1, "Bulbasaur", Sprites(""), emptyList(), emptyList(), 69)
    }

}