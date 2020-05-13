package com.may.force.domain.usecases


import com.may.force.domain.entities.PeopleInfoDetailsEntity
import com.may.force.domain.qualifiers.Background
import com.may.force.domain.qualifiers.Foreground
import com.may.force.domain.repository.ChallengeRepository
import com.may.force.domain.usecases.base.ObservableUseCase
import io.reactivex.Observable
import io.reactivex.Scheduler
import javax.inject.Inject

class GetPeopleInfoDetailsTask @Inject constructor(
    private val challengeRepository: ChallengeRepository,
    @Background backgroundScheduler: Scheduler,
    @Foreground foregroundScheduler: Scheduler
) : ObservableUseCase<PeopleInfoDetailsEntity, GetPeopleInfoDetailsTask.Params>(
    backgroundScheduler,
    foregroundScheduler
) {
    override fun generateObservable(input: Params?): Observable<PeopleInfoDetailsEntity> {
        if (input == null) {
            throw IllegalArgumentException("GetPeopleInfoDetailsTask parameter can't be null")
        }
        return challengeRepository.getPeopleInfoDetails(input.page)
    }

    data class Params(val page: Int)
}