package com.may.force.domain.usecases
import com.may.force.domain.entities.PeopleInfoEntity
import com.may.force.domain.qualifiers.Background
import com.may.force.domain.qualifiers.Foreground
import com.may.force.domain.repository.ChallengeRepository
import com.may.force.domain.usecases.base.ObservableUseCase
import io.reactivex.Observable
import io.reactivex.Scheduler

import javax.inject.Inject

class GetPeopleInfoTask @Inject constructor(
    private val challengeRepository: ChallengeRepository,
    @Background backgroundScheduler: Scheduler,
    @Foreground foregroundScheduler: Scheduler
) : ObservableUseCase<List<PeopleInfoEntity>, String>(backgroundScheduler, foregroundScheduler) {

    override fun generateObservable(input: String?): Observable<List<PeopleInfoEntity>> {
        if (input == null) {
            throw IllegalArgumentException("People ID can't be null")
        }
        return challengeRepository.getPeopleInfo()
    }

}