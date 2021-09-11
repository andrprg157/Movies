package com.shiv.movies.data.repository

import com.shiv.movies.data.local.MoviesDao
import com.shiv.movies.data.remote.MovieInterface
import com.shiv.movies.utils.BaseDataSource
import com.shiv.movies.utils.Constants
import com.shiv.movies.utils.performGetOperation

class MovieDetailsRepository(
    private val movieInterface: MovieInterface,
    private val movieDao: MoviesDao
) :
    BaseDataSource() {


    fun getMovieDetails(id: String) = performGetOperation(
        databaseQuery = { movieDao.getSingleMovie(id) },
        networkCall = { getResult { movieInterface.getMovieDetails(id, Constants.API_KEY) } },
        saveCallResult = { movieDao.insertMovieSingle(it) }
    )

}