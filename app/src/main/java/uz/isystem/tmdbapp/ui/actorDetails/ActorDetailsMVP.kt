package com.example.movieapp.ui.main.movieDetails

import uz.isystem.tmdbapp.core.models.response.main.home.castSimilar.CastSimilarResponse
import uz.isystem.tmdbapp.core.models.response.main.home.detailActor.ActorDetailResponse

interface ActorDetailsMVP {

    interface View {

        fun setActorDetails(actorDetailsResponse: ActorDetailResponse)

        fun setSimilarMovies(castSimilarResponse: CastSimilarResponse)

        fun onError(message: String)

    }

    interface Presenter {

        fun cancelRequest()

        fun loadActorDetails(movieId: Int)

        fun loadSimilarMovies(movieId: Int)


    }

}