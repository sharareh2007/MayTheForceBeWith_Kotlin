package com.may.force.domain.usecases

import com.may.force.domain.entities.PeopleInfoEntity
import com.may.force.domain.qualifiers.Background
import com.may.force.domain.qualifiers.Foreground
import com.may.force.domain.repository.ChallengeRepository
import com.may.force.domain.usecases.base.CompletableUseCase
import io.reactivex.Completable
import io.reactivex.Scheduler
import javax.inject.Inject

class saveFavoriteTask @Inject constructor(
    private val challengeRepository: ChallengeRepository,
    @Background backgroundScheduler: Scheduler,
    @Foreground foregroundScheduler: Scheduler
) : CompletableUseCase<PeopleInfoEntity>(
    backgroundScheduler,
    foregroundScheduler
) {

    override fun generateCompletable(input: PeopleInfoEntity?): Completable {
        if (input == null) {
            throw IllegalArgumentException("Save Favorite parameter can't be null")
        }
        return challengeRepository.saveFavorite(input)
    }
}